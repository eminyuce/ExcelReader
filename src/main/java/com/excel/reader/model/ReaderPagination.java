package com.excel.reader.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ReaderPagination<S> {
    private Integer pageNum;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;
}