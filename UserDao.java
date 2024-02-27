package com.example.contactsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(Users user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    Users login(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    Users getUserByUsername(String username);
}
    // Other user operations


