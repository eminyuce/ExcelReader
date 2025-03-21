package com.excel.reader.service;

import com.excel.reader.model.CompanyLookup;
import com.excel.reader.repo.CompanyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableCaching
public class CompanyLookupService {

    // In-memory cache using ConcurrentHashMap
    private final Map<String, CompanyLookup> companyCache = new ConcurrentHashMap<>();

    @Autowired
    private CompanyRepository companyRepository;

    @PostConstruct
    private void init() {
        var p = companyRepository.findAllBy();
        long id = 1;
        for (var c : p) {
            CompanyLookup built = CompanyLookup.builder().
                    companyId(c.getCompanyId())
                    .id(id++)
                    .companyName(c.getName()).
                    build();
            companyCache.put(c.getName().trim().toLowerCase(), built);
        }
    }

    /**
     * Find a company lookup entry by company name
     */
    public Optional<CompanyLookup> findByCompanyName(String companyName) {
        if (companyName == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(companyCache.get(companyName.trim().toLowerCase()));
    }

    /**
     * Check if a company lookup entry exists for a company name
     */
    public boolean existsByCompanyName(String companyName) {
        if (companyName == null) {
            return false;
        }
        return companyCache.containsKey(companyName.trim().toLowerCase());
    }

    /**
     * Save a company lookup entry
     */
    public CompanyLookup save(CompanyLookup lookup) {
        if (lookup == null || lookup.getCompanyName() == null) {
            throw new IllegalArgumentException("Company lookup or company name cannot be null");
        }

        // Set creation time if not already set
        if (lookup.getCreatedAt() == null) {
            lookup.setCreatedAt(LocalDateTime.now());
        }

        // Set an ID if not present (simulate auto-increment)
        if (lookup.getId() == null) {
            lookup.setId((long) (companyCache.size() + 1));
        }

        // Store in cache using lowercase company name as key
        companyCache.put(lookup.getCompanyName().trim().toLowerCase(), lookup);
        return lookup;
    }

    /**
     * Get all entries in the cache (for debugging)
     */
    public Map<String, CompanyLookup> getAllEntries() {
        return Map.copyOf(companyCache);
    }

    /**
     * Clear the cache
     */
    public void clearCache() {
        companyCache.clear();
    }
}