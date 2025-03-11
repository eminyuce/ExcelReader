package com.excel.reader.model;

import com.excel.reader.exception.Status;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ServiceStatus {
    private HttpStatus httpStatus;
    private Status status;
}

