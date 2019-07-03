package com.example.rxjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BooksDao {
    @Insert
    void addBook(Book book);

    @Query("select * from Book")
    Flowable<List<Book>> getBooks();
}
