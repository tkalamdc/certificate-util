package com.digicert.util.exception;

public class CertificateImportException extends RuntimeException {

    public CertificateImportException(String message) {
        super(message);
    }

    public CertificateImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
