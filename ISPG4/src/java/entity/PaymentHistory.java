/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author ducanh
 */
public class PaymentHistory {
    private int id;
    private int invoiceId;
    private double amountMoney;
    private String debt;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createBy;
    private Timestamp deleteAt;
    private String deleteBy;
    private boolean isDelete;

  
    public PaymentHistory() {
    }

    public PaymentHistory(int id, int invoiceId, double amountMoney, String debt, String status, Timestamp createdAt, Timestamp updatedAt, String createBy, Timestamp deleteAt, String deleteBy, boolean isDelete) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.amountMoney = amountMoney;
        this.debt = debt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createBy = createBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    
    
}
