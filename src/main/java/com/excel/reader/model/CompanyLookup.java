package com.excel.reader.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CompanyLookup {

    private Long id;

    private String companyName;

    private Integer companyId;

    private String errorMessage;

    private LocalDateTime createdAt = LocalDateTime.now();

}