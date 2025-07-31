package org.openmrs.module.xlsximport.api;

import org.springframework.web.multipart.MultipartFile;

public interface XlsxImportService {
    void importXlsx(MultipartFile file);
}
