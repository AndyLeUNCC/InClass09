package com.uncc.inclass09;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GradeDao {

    @Query("SELECT * from grades")
    List<Grade> getAll();

    @Insert
    void insertAll(Grade... grades);

    @Delete
    void delete(Grade grade);


}

