/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Lenovo
 */
public class Invoice {
   private int id;
    private int userId;
    private int partnerId;
    private double amountMoney;
    private String status;
    private Timestamp time;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createBy;
    private Timestamp deleteAt;
    private String deleteBy;
    private boolean isDelete;
    private String debtType;

    public Invoice() {
    }

    public Invoice(int id, int userId, int partnerId, double amountMoney, String status, Timestamp time, Timestamp createdAt, Timestamp updatedAt, String createBy, Timestamp deleteAt, String deleteBy, boolean isDelete) {
        this.id = id;
        this.userId = userId;
        this.partnerId = partnerId;
        this.amountMoney = amountMoney;
        this.status = status;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createBy = createBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }
    
    public Invoice(int id, int userId, int partnerId, double amountMoney, String status, Timestamp time, Timestamp createdAt, Timestamp updatedAt, String createBy, Timestamp deleteAt, String deleteBy, boolean isDelete, String debtType) {
       this.id = id;
       this.userId = userId;
       this.partnerId = partnerId;
       this.amountMoney = amountMoney;
       this.status = status;
       this.time = time;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.createBy = createBy;
       this.deleteAt = deleteAt;
       this.deleteBy = deleteBy;
       this.isDelete = isDelete;
       this.debtType = debtType; // Khởi tạo giá trị cho trường mới
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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
    
    public String getDebtType() {
       return debtType;
   }

   public void setDebtType(String debtType) {
       this.debtType = debtType;
   }

 
}
