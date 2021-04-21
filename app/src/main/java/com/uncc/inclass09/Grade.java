package com.uncc.inclass09;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Inclass09
 * File Name:Grade.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */
@Entity(tableName = "Grades")
public class Grade {
    @PrimaryKey(autoGenerate = true)
    public int  id;

    @ColumnInfo(name = "course_name")
    public String courseName;

    @ColumnInfo(name = "course_number")
    public String courseNumber;

    @ColumnInfo(name = "credit_hours")
    public int creditHours;

    @ColumnInfo(name = "course_grade")
    public char courseGrade;

    public Grade(int id, String courseName, String courseNumber, int creditHours, char courseGrade) {
        this.id = id;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.courseGrade = courseGrade;
    }

    public Grade(String courseName, String courseNumber, int creditHours, char courseGrade) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.courseGrade = courseGrade;
    }

    public Grade(){

    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", creditHours=" + creditHours +
                ", courseGrade=" + courseGrade +
                '}';
    }
}
