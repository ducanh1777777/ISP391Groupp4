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
public class Notification {
    private int id;
    private String messageType;
    private String messageContent;
    private Timestamp created_at;
    private Timestamp update_at;
    private String create_by;
    private Timestamp delete_at;
    private String delete_by;
    private boolean is_delete;
    public Notification() {}

    public Notification(int id, String messageType, String messageContent, Timestamp created_at,
                        Timestamp update_at, String create_by, Timestamp delete_at,
                        String delete_by, boolean is_delete) {
        this.id = id;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.created_at = created_at;
        this.update_at = update_at;
        this.create_by = create_by;
        this.delete_at = delete_at;
        this.delete_by = delete_by;
        this.is_delete = is_delete;
    }
    
    public Notification(int userId, String messageType, String messageContent) {
    this.create_by = String.valueOf(userId); // Assuming 'create_by' holds the userId in your schema
    this.messageType = messageType;
    this.messageContent = messageContent;
   
    this.created_at = new Timestamp(System.currentTimeMillis());
    // Initialize other fields as required, possibly with default values
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Timestamp delete_at) {
        this.delete_at = delete_at;
    }

    public String getDelete_by() {
        return delete_by;
    }

    public void setDelete_by(String delete_by) {
        this.delete_by = delete_by;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    
}
