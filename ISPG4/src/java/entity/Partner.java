/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Partner {
     private int id;
    private int userid; // Thêm trường userId vào đây
    private String partnerName;
    private String partnerPhone;
    private String partnerEmail;
    private String partnerAddress;
    private double amountMoney;

    public Partner() {
    }

    public Partner(int id, int userid, String partnerName, String partnerPhone, String partnerEmail, String partnerAddress, double amountMoney) {
        this.id = id;
        this.userid = userid;
        this.partnerName = partnerName;
        this.partnerPhone = partnerPhone;
        this.partnerEmail = partnerEmail;
        this.partnerAddress = partnerAddress;
        this.amountMoney = amountMoney;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    public String getPartnerAddress() {
        return partnerAddress;
    }

    public void setPartnerAddress(String partnerAddress) {
        this.partnerAddress = partnerAddress;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }
}
