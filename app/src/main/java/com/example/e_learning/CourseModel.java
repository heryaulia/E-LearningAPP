package com.example.e_learning;

public class CourseModel {

    String courseName;
    String lessonsamount;
    int image;

    public CourseModel(String courseName, String lessonsamount, int image) {
        this.courseName = courseName;
        this.lessonsamount = lessonsamount;
        this.image = image;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLessonsamount() {
        return lessonsamount;
    }

    public int getImage() {
        return image;
    }
}
