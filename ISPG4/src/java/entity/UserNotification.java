/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class UserNotification {
    private int id;
    private Users userId;
    private Notification notificationId;
    private int id_delete;
    private Timestamp delete_at;
    
    // Constructors
    public UserNotification() {
    }

    public UserNotification(int id, Users userId, Notification notificationId, int id_delete, Timestamp delete_at) {
        this.id = id;
        this.userId = userId;
        this.notificationId = notificationId;
        this.id_delete = id_delete;
        this.delete_at = delete_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Notification getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Notification notificationId) {
        this.notificationId = notificationId;
    }

    public int getId_delete() {
        return id_delete;
    }

    public void setId_delete(int id_delete) {
        this.id_delete = id_delete;
    }

    public Timestamp getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Timestamp delete_at) {
        this.delete_at = delete_at;
    }

}
