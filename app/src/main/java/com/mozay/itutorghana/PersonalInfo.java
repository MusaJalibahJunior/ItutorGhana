package com.mozay.itutorghana;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class PersonalInfo {

    private String TeacherName;
    private String TeacherEmail;
    private String TeacherDateofbirth;
    private String TeacherNationality;
    private String Teacherlocation;
    private String TeacheryearsofExp;
    private String Teachersubjectofesp;
    private String Teacherbefs;
    private String Phonenum;
    private String userId;
    private String profilepictureUri;

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
    }

    public void setTeacherDateofbirth(String teacherDateofbirth) {
        TeacherDateofbirth = teacherDateofbirth;
    }

    public void setTeacherNationality(String teacherNationality) {
        TeacherNationality = teacherNationality;
    }

    public void setTeacherlocation(String teacherlocation) {
        Teacherlocation = teacherlocation;
    }

    public void setTeacheryearsofExp(String teacheryearsofExp) {
        TeacheryearsofExp = teacheryearsofExp;
    }

    public void setTeachersubjectofesp(String teachersubjectofesp) {
        Teachersubjectofesp = teachersubjectofesp;
    }

    public void setTeacherbefs(String teacherbefs) {
        Teacherbefs = teacherbefs;
    }

    public void setPhonenum(String phonenum) {
        Phonenum = phonenum;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProfilepictureUri(String profilepictureUri) {
        this.profilepictureUri = profilepictureUri;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public String getTeacherDateofbirth() {
        return TeacherDateofbirth;
    }

    public String getTeacherNationality() {
        return TeacherNationality;
    }

    public String getTeacherlocation() {
        return Teacherlocation;
    }

    public String getTeacheryearsofExp() {
        return TeacheryearsofExp;
    }

    public String getTeachersubjectofesp() {
        return Teachersubjectofesp;
    }

    public String getTeacherbefs() {
        return Teacherbefs;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfilepictureUri() {
        return profilepictureUri;
    }
}
