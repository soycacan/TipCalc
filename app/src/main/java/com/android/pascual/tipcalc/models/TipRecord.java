package com.android.pascual.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pascual on 6/2/2016.
 */
public class TipRecord {
    private double bill;
    private int tipPercentage;
    private Date timestamp;


    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    //metodos especificos de la clase
    //propina
    public double getTip(){
        return bill*(tipPercentage/100d);
    }

    //formatea la fecha
    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        //devuelve el formato aplicado a nuestra fecha definida
        return simpleDateFormat.format(timestamp);
    }
}
