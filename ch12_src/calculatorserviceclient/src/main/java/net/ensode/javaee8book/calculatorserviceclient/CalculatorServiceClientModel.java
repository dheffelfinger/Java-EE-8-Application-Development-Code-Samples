package net.ensode.javaee8book.calculatorserviceclient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CalculatorServiceClientModel {

    private Integer addend1;
    private Integer addend2;

    private Integer minuend;
    private Integer subtrahend;

    public Integer getAddend1() {
        return addend1;
    }

    public void setAddend1(Integer addend1) {
        this.addend1 = addend1;
    }

    public Integer getAddend2() {
        return addend2;
    }

    public void setAddend2(Integer addend2) {
        this.addend2 = addend2;
    }

    public Integer getMinuend() {
        return minuend;
    }

    public void setMinuend(Integer minuend) {
        this.minuend = minuend;
    }

    public Integer getSubtrahend() {
        return subtrahend;
    }

    public void setSubtrahend(Integer subtrahend) {
        this.subtrahend = subtrahend;
    }

}
