package org.openmrs.module.xlsximport.api.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openmrs.module.xlsximport.api.XlsxImportService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class XlsxImportServiceImpl implements XlsxImportService {

    @Override
    public void importXlsx(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                System.out.println("Cell value: " + cell.getStringCellValue());
                // TODO: Import logic (e.g., create patients)
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to import XLSX", e);
        }
    }
}
