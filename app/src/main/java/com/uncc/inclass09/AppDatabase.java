package com.uncc.inclass09;


import androidx.room.Database;
import androidx.room.RoomDatabase;
/**
 * Inclass09
 * File Name:AppDatabase.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */
@Database(entities = {Grade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradeDao gradeDao();
}
