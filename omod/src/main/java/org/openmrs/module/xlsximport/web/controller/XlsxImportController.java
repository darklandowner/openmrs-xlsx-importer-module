package org.openmrs.module.xlsximport.web.controller;

import org.openmrs.module.xlsximport.api.XlsxImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/module/xlsximport/upload")
public class XlsxImportController {

    @Autowired
    private XlsxImportService xlsxImportService;

    @GetMapping
    public String showUploadForm() {
        return "/module/xlsximport/upload";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            xlsxImportService.importXlsx(file);
            model.addAttribute("message", "File imported successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Import failed: " + e.getMessage());
        }
        return "/module/xlsximport/upload";
    }
}
