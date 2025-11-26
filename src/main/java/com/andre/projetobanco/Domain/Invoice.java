package com.andre.projetobanco.Domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "tb_invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalValue;
    private YearMonth dueDate;
    private YearMonth closingDate;
    private Boolean isClosed;
    private Boolean isPaid;

    public Invoice() {
    }

    public Invoice(Long id, BigDecimal totalValue, YearMonth dueDate, YearMonth closingDate) {
        this.id = id;
        this.totalValue = totalValue;
        this.dueDate = dueDate;
        this.closingDate = closingDate;
        isClosed = false;
        isPaid = false;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public YearMonth getDueDate() {
        return dueDate;
    }

    public void setDueDate(YearMonth dueDate) {
        this.dueDate = dueDate;
    }

    public YearMonth getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(YearMonth closingDate) {
        this.closingDate = closingDate;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void addValue(BigDecimal value) {
        totalValue = totalValue.add(value);
    }

    public void closeInvoice() {
        isClosed = true;
    }

    public  void payInvoice(){
        isPaid = true;
    }

    public BigDecimal calculateLateFee(){
        return BigDecimal.ZERO;
    }
}
