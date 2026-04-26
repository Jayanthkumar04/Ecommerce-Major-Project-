package com.jayanth.ecommerce.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayanth.ecommerce.dto.NotificationRequestDto;
import com.jayanth.ecommerce.dto.OrderItemDto;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class InvoiceService {
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;

	
	public String invokeGenerateInvoice(NotificationRequestDto order)
	{
		
		String fileUrl="";
		
		try {
		    File invoice = generateInvoice(order);
		    
		     fileUrl += s3Service.uploadFile(invoice);

		    String subject = "Order Confirmed - " + order.getOrderTrackingNum();

		    String body = "Hi,\n\nYour order is successfully placed.\n\n"
		            + "Order ID: " + order.getOrderId() + "\n"
		            + "Tracking Number: " + order.getOrderTrackingNum() + "\n"
		            + "Amount: ₹" + order.getTotalPrice() + "\n\n"
		            +"Download Invoice : "+fileUrl+"\n\n"
		            + "Thank you for shopping with us!";
		    
		    
		    

		    System.out.println("email in verify payment "+order.getEmail());
		    invoice.delete();
		    
		    emailServiceImpl.sendOrderConfirmation(
		        order.getEmail(),
		        subject,
		        body
		        
		    );

		} catch (Exception e) {
		    e.printStackTrace();
		}

		
		return fileUrl;
		
	}
	
	
    private String loadHtmlTemplate() throws Exception {
        InputStream inputStream = getClass()
            .getClassLoader()
            .getResourceAsStream("templates/invoice.html");

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private String populateTemplate(String html, NotificationRequestDto order) throws Exception {

        String itemsHtml = "";

        for (OrderItemDto item : order.getItems()) {
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

    
    public File generateInvoice(NotificationRequestDto order) throws Exception {
           
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