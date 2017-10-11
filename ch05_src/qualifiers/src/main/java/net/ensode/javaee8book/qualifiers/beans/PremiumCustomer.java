package net.ensode.javaee8book.qualifiers.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import net.ensode.javaee8book.qualifiers.Premium;

@Named
@RequestScoped
@Premium
public class PremiumCustomer extends Customer {

    private Integer discountCode;

    public Integer getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Integer discountCode) {
        this.discountCode = discountCode;
    }
}
