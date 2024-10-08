package org.example.demo.pdf_document.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.demo.pdf_document.pdf.model.request.Request;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    public static final String DATE = "[Date]";
    public static final String ORDER_AMOUNT = "[principal_amount]";

    public void replacePlaceholders(String pdfPath, String outputPath, Request request) throws IOException {
        File file = new File(pdfPath);

        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pageText = pdfStripper.getText(document);

            float x = 100; // Horizontal position
            float y = 700; // Initial vertical position (adjust as necessary)

            for (PDPage page : document.getPages()) {
                // Clear existing content before adding new text
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true);

                // Replace placeholders with actual values from the request
                String updatedText = pageText
                        .replace(DATE, request.lastName)
                        .replace(ORDER_AMOUNT, request.orderAmount);

                // Split the updated text into lines
                String[] lines = updatedText.split("\n");

                // Write each line to the PDF
                for (String line : lines) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);

                    contentStream.newLineAtOffset(x, y);
                    contentStream.showText(line);
                    contentStream.endText();

                    y -= 15;
                }

                contentStream.close();
            }

            document.save(outputPath);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
