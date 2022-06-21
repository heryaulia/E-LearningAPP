package com.example.e_learning;

public class User {

    private String fullName;
    private String nim;
    private String jurusan;
    private String semester;

    public User() {
    }

    public User(String fullName, String nim, String jurusan, String semester) {
        this.fullName = fullName;
        this.nim = nim;
        this.jurusan = jurusan;
        this.semester = semester;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getSemester() {
        return semester;
    }
}
