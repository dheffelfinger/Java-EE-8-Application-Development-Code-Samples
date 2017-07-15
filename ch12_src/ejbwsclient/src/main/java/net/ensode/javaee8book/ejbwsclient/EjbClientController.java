package net.ensode.javaee8book.ejbwsclient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import net.ensode.javaee8book.ejbws.DecToHexBeanService;

@Named
@RequestScoped
public class EjbClientController {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/DecToHexBeanService/DecToHexBean?wsdl")
    private DecToHexBeanService decToHexBeanService;

    @Inject
    private EjbClientModel ejbClientModel;

    private String hexVal;

    public void convertIntToHex() {
        hexVal = decToHexBeanService.getDecToHexBeanPort().
                convertDecToHex(ejbClientModel.getIntVal());
    }

    public String getHexVal() {
        return hexVal;
    }

    public void setHexVal(String hexVal) {
        this.hexVal = hexVal;
    }

}
