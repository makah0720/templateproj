package com.example.template.service;

import com.example.template.domain.Transaction;
import com.example.template.repository.TransactionRepository;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final TransactionRepository transactionRepository;

    public ReportingServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ByteArrayInputStream generateExcelReport() {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Transactions");

            String[] headers = {"ID", "Description", "Amount", "Date", "Type", "Category", "Supplier"};
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            List<Transaction> transactions = transactionRepository.findAll();
            int rowIdx = 1;
            for (Transaction transaction : transactions) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(transaction.getId());
                row.createCell(1).setCellValue(transaction.getDescription());
                row.createCell(2).setCellValue(transaction.getAmount().doubleValue());
                row.createCell(3).setCellValue(transaction.getDate().toString());
                row.createCell(4).setCellValue(transaction.getType().toString());
                row.createCell(5).setCellValue(transaction.getCategory().getName());
                row.createCell(6).setCellValue(transaction.getSupplier().getName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate Excel report: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream generatePdfReport() {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Spend Report"));

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Description");
            table.addCell("Amount");
            table.addCell("Date");
            table.addCell("Type");
            table.addCell("Category");
            table.addCell("Supplier");

            List<Transaction> transactions = transactionRepository.findAll();
            for (Transaction transaction : transactions) {
                table.addCell(String.valueOf(transaction.getId()));
                table.addCell(transaction.getDescription());
                table.addCell(transaction.getAmount().toString());
                table.addCell(transaction.getDate().toString());
                table.addCell(transaction.getType().toString());
                table.addCell(transaction.getCategory().getName());
                table.addCell(transaction.getSupplier().getName());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
