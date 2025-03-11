package com.excel.reader.model;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ImportOtherSearchParams {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private ReaderPagination<?> page;
    private List<SearchCriteria> criteriaList;


    public boolean hasClause() {
        return CollectionUtils.isNotEmpty(criteriaList) && criteriaList.stream().anyMatch(r -> r.hasClause());
    }
}
