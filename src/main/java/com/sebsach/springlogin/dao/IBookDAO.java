package com.sebsach.springlogin.dao;

import com.sebsach.springlogin.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    void saveOrUpdate(Book book);
    void deleteById(int id);
}
