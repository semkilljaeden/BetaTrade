package com.citi.trading;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="trade")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Trade 
{
    public enum Result
    {
        FILLED,
        PARTIALLY_FILLED,
        CANCELED,
        DONE_FOR_DAY,
        REJECTED
    };
    
    private int id;

    private Timestamp when;
    private String stock;
    private boolean buy;
    private int size;
    private double price;
    private Result result;

    public Trade() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStock() {
        return this.stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public boolean isBuy ()
    {
        return buy;
    }

    public void setBuy (boolean buy)
    {
        this.buy = buy;
    }

    @XmlTransient // we use the Date representation instead
    public Timestamp getWhen() {
        return this.when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }

    public Date getWhenAsDate ()
    {
        return new Date (when.getTime ());
    }
    
    public void setWhenAsDate (Date date)
    {
        when = new Timestamp (date.getTime ());
    }

    /**
    Helper to fix the timestamp to the current system time.
    */
    public void setToNow ()
    {
        when = new Timestamp (System.currentTimeMillis ());
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
