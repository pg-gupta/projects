package com.chatapppoc.android.chatapppoc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {
    static String username = "";
    static String password = "";
    static String chatWith = "";

    public static Map<String, String>[] getSkills() {
        return skills;
    }

    public static void setSkills(Map<String, String>[] skills) {
        UserDetails.skills = skills;
    }

    static Map<String,String>[] skills;
    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getChatWith() {
        return chatWith;
    }

    @Override
    public String toString() {
        return "UserDetails{username='" + username + "\', password='" + password + "\', chatWith='" + chatWith + "\'}";
    }

}
