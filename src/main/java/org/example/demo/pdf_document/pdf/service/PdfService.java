package org.example.demo.pdf_document.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.example.demo.pdf_document.pdf.model.request.Request;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    public void fillPDFForm(String pdfPath, String outputPath, Request request) throws IOException {

        File file = new File(pdfPath);

        try (PDDocument pdfDocument = PDDocument.load(file)) {

            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {

                PDField field = acroForm.getField("last_name");
                if (field != null) {
                    field.setValue(request.lastName);
                }

                PDField orderAmount = acroForm.getField("order_amount");
                if (orderAmount != null) {
                    orderAmount.setValue(request.orderAmount);
                }
                acroForm.flatten();

                pdfDocument.save(outputPath);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
