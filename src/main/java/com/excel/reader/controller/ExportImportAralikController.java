package com.excel.reader.controller;

import com.excel.reader.exception.Status;
import com.excel.reader.model.BackEndResponse;
import com.excel.reader.model.ExportImportAralikSearchParams;
import com.excel.reader.model.ServiceStatus;
import com.excel.reader.service.ExportImportAralikService;
import com.excel.reader.util.DateUtil;
import com.excel.reader.util.LogUtil;
import com.excel.reader.util.TimeMetrics;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.excel.reader.util.ExcelConstants.*;

@RestController
@RequestMapping("/ExportImportAralik")
@Slf4j
public class ExportImportAralikController extends BaseController {

    @Autowired
    private ExportImportAralikService exportImportAralikService;

    @PostMapping(value = "/exportExportImportAralik", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Export ExportImportAralik data as Excel",
            description = "Export filtered, sorted, and paginated ExportImportAralik data as Excel file")
    public @ResponseBody ResponseEntity<Resource> exportExportImportAralikBySearchParams(
            @RequestBody ExportImportAralikSearchParams exportImportAralikSearchParams, @RequestHeader HttpHeaders headers) {

        String action = "ExportImportAralikExportXls";
        String user = LogUtil.getUser(headers);
        TimeMetrics tm = new TimeMetrics();
        tm.start(action, user, null);
        log.info(tm.getMetricsString(action, "start"));

        try {
            // Generate the report
            var inputStreamResource = exportImportAralikService.exportExportImportAralikBySearchParams(exportImportAralikSearchParams);

            if (inputStreamResource == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                String fileName = "ExportImportAralik_" + DateUtil.getFormattedCurrentDate() + ".xlsx";
                HttpHeaders responseHttpHeaders = new HttpHeaders();
                responseHttpHeaders.add(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + fileName);
                responseHttpHeaders.add(CONTENT_TYPE,
                        APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_SPREADSHEETML_SHEET);

                tm.stop(action);
                log.info(tm.getMetricsString(action));

                return ResponseEntity.ok().headers(responseHttpHeaders).body(inputStreamResource);
            }

        } catch (Exception e) {
            log.error(tm.getErrorString(action), e);
            String errMsg = "An error occurred while exporting ExportImportAralik data to Excel";
            log.error(errMsg);

            tm.stop(action);
            log.info(tm.getMetricsString(action));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value = "/getExportImportAralik", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get ExportImportAralik data with filtering and pagination",
            description = "Retrieve ExportImportAralik records based on search parameters with filtering and pagination support")
    public @ResponseBody BackEndResponse getExportImportAralikBySearchParams(
            @RequestBody ExportImportAralikSearchParams exportImportAralikSearchParams,
            @RequestHeader HttpHeaders headers) {

        BackEndResponse ber = new BackEndResponse();
        var serviceStatus = new ServiceStatus();
        Status responseStatus = new Status();
        responseStatus.setErrorCode(NO_ERR);

        String action = "getExportImportAralik";
        String user = LogUtil.getUser(headers);
        TimeMetrics tm = new TimeMetrics();
        tm.start(action, user, null);
        log.info(tm.getMetricsString(action, "start"));

        try {
            // Call the service to get ExportImportAralik data
            var exportImportAralikData = exportImportAralikService.getExportImportAralikBySearchParams(exportImportAralikSearchParams);

            // Wrap the response in ResponseEntity
            HttpHeaders responseHeaders = new HttpHeaders();
            var resp = new ResponseEntity<>(exportImportAralikData, responseHeaders, HttpStatus.OK);

            // Set the response in BackEndResponse object
            ber.setResponse(resp);
            serviceStatus.setHttpStatus(HttpStatus.OK);
            responseStatus.setMessage("ExportImportAralik data retrieved successfully");
            serviceStatus.setStatus(responseStatus);
            ber.setServiceStatus(serviceStatus);

            tm.stop(action);
            log.info(tm.getMetricsString(action));

        } catch (Exception e) {
            log.error("An error occurred while retrieving ExportImportAralik data", e);

            // Handle error and build response
            ber = buildFatalResponse(ber, serviceStatus, responseStatus, action, "3399");

            tm.stop(action);
            log.info(tm.getMetricsString(action));
        }

        return ber;
    }
}