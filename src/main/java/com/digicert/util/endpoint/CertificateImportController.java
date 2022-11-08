package com.digicert.util.endpoint;

import com.digicert.util.dto.CertificateRequest;
import com.digicert.util.exception.CertificateImportException;
import com.digicert.util.service.CertificateImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping(value = "v1")
public class CertificateImportController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CertificateImportService certificateImportService;

    public CertificateImportController(CertificateImportService certificateImportService) {
        this.certificateImportService = certificateImportService;
    }

    @PostMapping(value = "/file/encode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> read(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "suffix") String suffix) throws IOException {
        logger.debug("fileName: " + file.getName());
        if(file.isEmpty()){
            throw new CertificateImportException("File is null");
        }else {
            String certificateResponse = certificateImportService.encodeToBase64(file, suffix);
            return ResponseEntity.ok().body(Collections.singletonMap("certificate", certificateResponse));
        }
    }

    @PostMapping(value = "/file/decode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> write(@RequestBody CertificateRequest certificateRequest) throws IOException {
        certificateImportService.decodeToFile(certificateRequest);
        return ResponseEntity.ok().build();
    }

}
