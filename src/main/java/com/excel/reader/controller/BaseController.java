package com.excel.reader.controller;

import com.excel.reader.exception.Status;
import com.excel.reader.model.BackEndResponse;
import com.excel.reader.model.ServiceStatus;
import org.springframework.http.HttpStatus;

public abstract class BaseController {

    protected BackEndResponse buildExcelFailure(BackEndResponse ber, ServiceStatus serviceStatus, Status status,
                                                String action, String code) {
        serviceStatus.setHttpStatus(HttpStatus.BAD_REQUEST);
        status.setMessage("A serious error occurred in " + action);
        status.setErrorCode(code);
        status.addError("Request failed");
        serviceStatus.setStatus(status);
        ber.setServiceStatus(serviceStatus);
        return ber;
    }

    protected BackEndResponse buildFatalResponse(BackEndResponse ber, ServiceStatus serviceStatus, Status status,
                                                 String action, String code) {
        serviceStatus.setHttpStatus(HttpStatus.BAD_REQUEST);
        status.setMessage("A serious error occurred in " + action);
        status.setErrorCode(code);
        status.addError("Request failed");
        serviceStatus.setStatus(status);
        ber.setServiceStatus(serviceStatus);
        return ber;
    }
}
