package net.ensode.javaeebook.jaxws;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class FileAttachment {

    @WebMethod
    public void attachFile(DataHandler dataHandler) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("/tmp/logo.png");

            dataHandler.writeTo(fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
