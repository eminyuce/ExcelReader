package com.excel.reader.service;

import com.excel.reader.entities.Company;
import com.excel.reader.entities.dto.CompanyDTO;
import com.excel.reader.model.CompanyLookup;
import com.excel.reader.repo.CompanyRepository;
import com.excel.reader.repo.ExportImportAralikRepository;
import com.excel.reader.repo.ExportImportOtherRespository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CompanyService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${GOOGLE_API_KEY}")
    private String API_KEY;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ExportImportOtherRespository exportImportOtherRepository;

    @Autowired
    private ExportImportAralikRepository exportImportAralikRepository;

    @Autowired
    private CompanyLookupService companyLookupService;
    private List<Company> companiesBatch = new ArrayList<>();
    private int BATCH_COMPANY_SIZE = 5;

    public void generateCompanyPlaceDataFromGooglePlaceAPI(int size, int batchSize) {
        int page = 0;
        boolean hasMoreData = true;
        BATCH_COMPANY_SIZE=batchSize;

        while (hasMoreData) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Tuple> companyPage = exportImportAralikRepository.findMinIdAndGroupedCompanies(pageable);
            printCompanyInfo(companyPage,"aralik");

            // Check if there are more pages
            hasMoreData = companyPage.hasNext();
            page++;
        }

        page = 0;
        hasMoreData = true;


        while (hasMoreData) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Tuple> companyPage = exportImportOtherRepository.findMinIdAndGroupedCompanies(pageable);
            printCompanyInfo(companyPage,"others");

            // Check if there are more pages
            hasMoreData = companyPage.hasNext();
            page++;
        }

    }

    private void printCompanyInfo(Page<Tuple> companyPage, String companyType) {
        companyPage.forEach(tuple -> {

            Integer id = tuple.get("id", Integer.class);
            String gonderiSirket = tuple.get("gonderiSirket", String.class);
            String aliciSirket = tuple.get("aliciSirket", String.class);

            // You can now use these values more effectively!
            System.out.println("Company ID: " + id);
            System.out.println("Sender: " + gonderiSirket);
            System.out.println("Receiver: " + aliciSirket);

            // Process sender and receiver companies
            try {
                processCompany(id, gonderiSirket,companyType);
            } catch (Exception e) {
                log.error("processCompany Exception", e);
            }
            try {
                processCompany(id, aliciSirket,companyType);
            } catch (Exception e) {
                log.error("processCompany Exception", e);
            }

        });
    }

    /**
     * Process a company by checking the cache first, then fetching details if needed
     */
    private void processCompany(Integer recordId, String companyName, String companyType) {
        if (companyName == null || companyName.trim().isEmpty()) {
            log.warn("Skipping empty company name for record ID: {}", recordId);
            return;
        }

        // Check if we've already processed this company name
        Optional<CompanyLookup> lookupEntry = companyLookupService.findByCompanyName(companyName.trim());

        if (lookupEntry.isPresent()) {
            // We've seen this company before
            CompanyLookup lookup = lookupEntry.get();
            log.info("Company '{}' was already processed. Lookup entry ID: {}", companyName, lookup.getId());

            // If the lookup entry has a company ID, we've successfully processed it before
            if (lookup.getCompanyId() != null) {
                log.info("Company details already exist with ID: {}", lookup.getCompanyId());
            } else {
                // If the lookup entry exists but has no company ID, it means we failed to process it before
                log.info("Company was previously attempted but failed to process");
            }
        } else {
            // First time seeing this company - try to fetch and save it
            try {
                Company company = fetchAndSaveCompany(recordId, companyName,companyType);

                // Create a new lookup entry with successful processing
                CompanyLookup newLookup = new CompanyLookup();
                newLookup.setCompanyName(companyName.trim());
                newLookup.setCompanyId(company.getCompanyId());
                companyLookupService.save(newLookup);

                log.info("Successfully processed new company: {}", companyName);
            } catch (Exception e) {
                // Create a lookup entry even for failed attempts to avoid retrying
                CompanyLookup failedLookup = new CompanyLookup();
                failedLookup.setCompanyName(companyName.trim());
                failedLookup.setErrorMessage(e.getMessage());
                companyLookupService.save(failedLookup);

                log.error("Failed to process company '{}': {}", companyName, e.getMessage());
            }
        }
    }

    /**
     * Fetches company details from Google Maps API and saves to database
     */
    private Company fetchAndSaveCompany(Integer recordId, String companyName, String companyType) {
        try {
            // Step 1: First search for the place by name to get a place_id
            String placeId = findPlaceIdByName(companyName);
            if (placeId == null) {
                throw new RuntimeException("Could not find a place ID for company: " + companyName);
            }

            // Step 2: Now get the place details using the place_id
            String detailsUrlString = "https://maps.googleapis.com/maps/api/place/details/json"
                    + "?place_id=" + placeId
                    + "&fields=name,formatted_address,formatted_phone_number,international_phone_number,"
                    + "website,url,opening_hours,photos,rating,types,user_ratings_total,geometry,plus_code"
                    + "&key=" + API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(new URI(detailsUrlString), String.class);
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode result = root.path("result");

            if (result.isMissingNode()) {
                throw new RuntimeException("No results found for the given Place ID: " + placeId);
            }

            // Create Company entity
            Company company = new Company();
            // Use a combination of record ID and place ID as the company ID
            company.setCompanyId(recordId);
            company.setCompanyType(companyType);
            company.setName(companyName); // Store the original company name
            company.setPlaceId(placeId); // Store the Google place ID
            company.setAddress(result.path("formatted_address").asText("N/A"));
            company.setPhoneNumber(result.path("formatted_phone_number").asText("N/A"));
            company.setInternationalPhone(result.path("international_phone_number").asText("N/A"));
            company.setWebsite(result.path("website").asText("N/A"));
            company.setGoogleMapsUrl(result.path("url").asText("N/A"));

            JsonNode location = result.path("geometry").path("location");
            company.setLatitude(location.path("lat").asDouble(0.0));
            company.setLongitude(location.path("lng").asDouble(0.0));

            company.setRating(result.path("rating").asDouble(0.0));
            company.setTotalRatings(result.path("user_ratings_total").asInt(0));

            JsonNode typesNode = result.path("types");
            company.setPlaceType(typesNode.isArray() ? String.join(", ", objectMapper.convertValue(typesNode, String[].class)) : "N/A");

            // Save to database
            return saveBatch(company);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching company details: " + e.getMessage(), e);
        }
    }

    private Company saveBatch(Company company) {
        companiesBatch.add(company);
        if (companiesBatch.size() >= BATCH_COMPANY_SIZE) {
            batchInsertCompanies(companiesBatch);
            log.info("Saved batch of {} companiesBatch records", companiesBatch.size());
            companiesBatch.clear();
        }
        return company;
    }

    /**
     * Search for a place ID by company name using the Places API Text Search
     */
    private String findPlaceIdByName(String companyName) throws Exception {
        try {
            // URL encode the company name
            String encodedName = URLEncoder.encode(companyName, StandardCharsets.UTF_8.toString());

            // Build the Text Search API URL
            String searchUrlString = "https://maps.googleapis.com/maps/api/place/textsearch/json"
                    + "?query=" + encodedName
                    + "&key=" + API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(new URI(searchUrlString), String.class);

            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode status = root.path("status");

            if ("OK".equals(status.asText())) {
                JsonNode results = root.path("results");
                if (results.isArray() && results.size() > 0) {
                    // Get the place_id from the first result
                    return results.get(0).path("place_id").asText();
                }
            } else {
                log.error("NOT OK RESPONSE: {}", jsonResponse);
            }

            log.warn("No results found for company name: {} (Status: {})", companyName, status.asText());
            return null;

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding company name: " + e.getMessage(), e);
        }
    }

    @Transactional
    public int batchInsertCompanies(List<Company> companies) {
        // Convert list of entities to DTOs if needed
        List<CompanyDTO> dtos = companies.stream()
                .map(r -> CompanyDTO.convertToDTO(r))
                .collect(Collectors.toList());

        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            // Unwrap the HikariCP connection to get the native SQL Server connection
            var sqlServerConnection =
                    connection.unwrap(com.microsoft.sqlserver.jdbc.SQLServerConnection.class);

            // Create SQLServerDataTable matching the table type for the companies table
            SQLServerDataTable dataTable = new SQLServerDataTable();
            dataTable.addColumnMetadata("address", Types.NVARCHAR);
            dataTable.addColumnMetadata("company_id", Types.INTEGER);
            dataTable.addColumnMetadata("google_maps_url", Types.NVARCHAR);
            dataTable.addColumnMetadata("international_phone", Types.NVARCHAR);
            dataTable.addColumnMetadata("latitude", Types.FLOAT);
            dataTable.addColumnMetadata("longitude", Types.FLOAT);
            dataTable.addColumnMetadata("name", Types.NVARCHAR);
            dataTable.addColumnMetadata("phone_number", Types.NVARCHAR);
            dataTable.addColumnMetadata("place_id", Types.NVARCHAR);
            dataTable.addColumnMetadata("place_type", Types.NVARCHAR);
            dataTable.addColumnMetadata("rating", Types.FLOAT);
            dataTable.addColumnMetadata("total_ratings", Types.INTEGER);
            dataTable.addColumnMetadata("website", Types.NVARCHAR);
            dataTable.addColumnMetadata("company_type", Types.NVARCHAR);

            // Populate the data table with DTO data
            for (CompanyDTO dto : dtos) {
                dataTable.addRow(
                        dto.getAddress(),              // String (NVARCHAR)
                        dto.getCompanyId(),            // Integer (INTEGER)
                        dto.getGoogleMapsUrl(),        // String (NVARCHAR)
                        dto.getInternationalPhone(),   // String (NVARCHAR)
                        dto.getLatitude(),             // Float (FLOAT)
                        dto.getLongitude(),            // Float (FLOAT)
                        dto.getName(),                 // String (NVARCHAR)
                        dto.getPhoneNumber(),          // String (NVARCHAR)
                        dto.getPlaceId(),              // String (NVARCHAR)
                        dto.getPlaceType(),            // String (NVARCHAR)
                        dto.getRating(),               // Float (FLOAT)
                        dto.getTotalRatings(),         // Integer (INTEGER)
                        dto.getWebsite()   ,            // String (NVARCHAR)
                        dto.getCompanyType()
                );
            }

            // Execute the stored procedure using the unwrapped connection
            String sql = "{call InsertCompaniesBatch(?)}";
            try (SQLServerPreparedStatement stmt = (SQLServerPreparedStatement) sqlServerConnection.prepareStatement(sql)) {
                stmt.setStructured(1, "dbo.CompaniesType", dataTable);
                stmt.execute();

                // Get the affected rows from the result set (if any)
                if (stmt.getMoreResults()) {
                    try (var rs = stmt.getResultSet()) {
                        if (rs.next()) {
                            int affectedRows = rs.getInt(1);
                            System.out.println("Affected rows: " + affectedRows);
                            return affectedRows;
                        }
                    }
                }
                return 0; // Default return if no result set
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute batch insert", e);
        }
    }

}