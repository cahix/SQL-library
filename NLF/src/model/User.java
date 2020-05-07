package model;

import data.DatabaseHandler;

public class User {

    public boolean isValidUser(String userName, String userPassword){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        return databaseHandler.isValidUserLogin(userName,userPassword);
    }
}