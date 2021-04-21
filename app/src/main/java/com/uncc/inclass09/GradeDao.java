package com.uncc.inclass09;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
/**
 * Inclass09
 * File Name:GradeDao.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

@Dao
public interface GradeDao {

    @Query("SELECT * from grades")
    List<Grade> getAll();

    @Insert
    void insertAll(Grade... grades);

    @Delete
    void delete(Grade grade);


}

