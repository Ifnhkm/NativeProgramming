package com.example.passwordkeeper;

public class PakmadModal {
    private String entiti;
    private String userName;
    private String password;
    private String description;
    private int id;


    public PakmadModal(String entiti, String userName, String password, String description, int id) {
        this.entiti = entiti;
        this.userName = userName;
        this.password = password;
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    // creating getter and setter methods
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntiti() {
        return entiti;
    }

    public void setEntiti(String entiti) {
        this.entiti = entiti;
    }

    // constructor
    public PakmadModal(String entiti, String userName, String password, String description) {
        this.entiti = entiti;
        this.userName = userName;
        this.password = password;
        this.description = description;
    }
}


