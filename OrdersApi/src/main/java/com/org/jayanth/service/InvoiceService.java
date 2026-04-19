package com.org.jayanth.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.org.jayanth.entity.Order;
import com.org.jayanth.entity.OrderItems;

@Service
public class InvoiceService {

    private String loadHtmlTemplate() throws Exception {
        InputStream inputStream = getClass()
            .getClassLoader()
            .getResourceAsStream("templates/invoice.html");

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private String populateTemplate(String html, Order order) throws Exception {

        String itemsHtml = "";

        for (OrderItems item : order.getOrderItems()) {
            itemsHtml += "<tr>"
                    + "<td>" + item.getName() + "</td>"
                    + "<td>" + item.getQuantity() + "</td>"
                    + "<td>Rs " + item.getUnitPrice() + "</td>"
                    + "<td>Rs " + (item.getQuantity() * item.getUnitPrice()) + "</td>"
                    + "</tr>";
        }

        html = html.replace("{{orderId}}", String.valueOf(order.getOrderId()));
        html = html.replace("{{tracking}}", order.getOrderTrackingNum());
        html = html.replace("{{email}}", order.getEmail());
        html = html.replace("{{items}}", itemsHtml);
        html = html.replace("{{total}}", String.valueOf(order.getTotalPrice()));
        html = html.replace("{{logo}}",getBase64Image());
        return html;
    }

    public File generateInvoice(Order order) throws Exception {
           
        String html = loadHtmlTemplate();
        html = populateTemplate(html, order);
        String fileName = "invoice_" + order.getOrderId() + ".pdf";
        File file = new File(fileName);

        OutputStream os = new FileOutputStream(file);

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(os);
        builder.run();

        return file;
    }
    
    private String getBase64Image() throws Exception {
        InputStream is = getClass()
            .getClassLoader()
            .getResourceAsStream("templates/logo.png");

        byte[] bytes = is.readAllBytes();

        return Base64.getEncoder().encodeToString(bytes);
    }
}