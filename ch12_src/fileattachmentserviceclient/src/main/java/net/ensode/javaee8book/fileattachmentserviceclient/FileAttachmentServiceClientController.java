package net.ensode.javaee8book.fileattachmentserviceclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import net.ensode.javaeebook.jaxws.FileAttachment;
import net.ensode.javaeebook.jaxws.FileAttachmentService;

@Named
@RequestScoped
public class FileAttachmentServiceClientController {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/fileattachmentservice/"
            + "FileAttachmentService?wsdl")
    private FileAttachmentService fileAttachmentService;

    public void invokeWebService() {
        try {
            URL attachmentUrl = new URL("http://localhost:8080/fileattachmentserviceclient/resources/img/logo.png");

            FileAttachment fileAttachment = fileAttachmentService.
                    getFileAttachmentPort();

            InputStream inputStream = attachmentUrl.openStream();

            byte[] fileBytes = inputStreamToByteArray(inputStream);

            fileAttachment.attachFile(fileBytes);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
}
