/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
User -> day du thong, day du, va chua rat nhieu method de xu ly 
UserDTO -> Data Transfer Object -> mot phien ban rut gon cua User, chi chua thong tin co ban, khong chua nhieu method phuc tap, dung de truyen thong tin
UserDAO -> Data Access Object -> La noi chua cac ham CRUD truy cap xuong Database
 */
public class UserDTO {

    private String userID;
    private String password;
    private String fullName;
    private String roleID;
    private boolean status;
    
    public UserDTO() {
    }
    
    public UserDTO(String userID, String password, String fullName, String roleID, boolean status) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
        this.status = status;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getRoleID() {
        return roleID;
    }
    
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
