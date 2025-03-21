package com.excel.reader.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Auto-incremented company ID

    @Column(nullable = false, unique = true)
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
}
