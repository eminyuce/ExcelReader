package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BackEndResponse implements Serializable {

    @JsonProperty("response")
    private ResponseEntity<?> response;
    @JsonProperty("serviceStatus")
    private ServiceStatus serviceStatus;

}
