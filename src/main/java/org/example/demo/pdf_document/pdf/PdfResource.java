package org.example.demo.pdf_document.pdf;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.demo.pdf_document.pdf.model.request.Request;
import org.example.demo.pdf_document.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class PdfResource {
    @Autowired
    private PdfService pdfService;

    @GetMapping(value = "/fill-pdf")
    public String processNafathNotification(
            @RequestBody Request request,
            @RequestParam String pdfPath,
            @RequestParam String outputPath
    ) throws IOException {

        pdfService.fillPDFForm(pdfPath, outputPath, request);

        return String.format("Pdf is ready to view and saved to %s", outputPath);
    }
}

