package com.excel.reader.exception;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Status {

    private String message;
    private List<String> errors;
    private String errorCode;

    public void addError(String err) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(err);
    }

    public void addAllErrors(List<String> errs) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.addAll(errs);
    }


    /*
     * Convenience method that works if error list is null or if error list is empty
     * returns true if this status has errors
     */
    public boolean hasErrors() {
        boolean errorsExist = (this.errors != null) && (!this.errors.isEmpty());
        return errorsExist;
    }

}
