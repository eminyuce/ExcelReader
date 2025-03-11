package com.excel.reader.model;


import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class SearchCriteria {
    private String field;
    private String operator;
    private String value;

    public boolean hasClause() {
        return StringUtils.isNotEmpty(field) && StringUtils.isNotEmpty(operator) && StringUtils.isNotEmpty(value);
    }

}