package com.example.template.service;

import java.io.ByteArrayInputStream;

public interface ReportingService {
    ByteArrayInputStream generateExcelReport();
    ByteArrayInputStream generatePdfReport();
}
