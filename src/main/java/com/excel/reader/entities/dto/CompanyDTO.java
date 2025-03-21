package com.excel.reader.entities.dto;

import com.excel.reader.entities.Company;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CompanyDTO {
    private Integer companyId; // Google Place ID
    private String name;
    private String placeId;
    private String address;
    private String phoneNumber;
    private String internationalPhone;
    private String website;
    private String googleMapsUrl;
    private Double latitude;
    private Double longitude;
    private Double rating;
    private Integer totalRatings;
    private String placeType;
    private String companyType;

    public static CompanyDTO convertToDTO(Company company) {
        if (company == null) {
            return null;
        }

        return CompanyDTO.builder()
                .companyId(company.getCompanyId())
                .name(company.getName())
                .placeId(company.getPlaceId())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .internationalPhone(company.getInternationalPhone())
                .website(company.getWebsite())
                .googleMapsUrl(company.getGoogleMapsUrl())
                .latitude(company.getLatitude())
                .longitude(company.getLongitude())
                .rating(company.getRating())
                .totalRatings(company.getTotalRatings())
                .placeType(company.getPlaceType())
                .companyType(company.getCompanyType())
                .build();
    }
}