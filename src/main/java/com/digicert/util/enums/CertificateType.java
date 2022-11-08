package com.digicert.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertificateType {

    PKCS12(".p12");

    final String type;

}
