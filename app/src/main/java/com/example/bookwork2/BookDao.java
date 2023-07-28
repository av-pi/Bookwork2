package com.example.bookwork2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM book WHERE shelf = :shelf")
    LiveData<List<Book>> getShelf(String shelf);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAllBooks();
}
