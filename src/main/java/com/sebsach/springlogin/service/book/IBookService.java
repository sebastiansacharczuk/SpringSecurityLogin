package com.sebsach.springlogin.service.book;

import com.sebsach.springlogin.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    void saveOrUpdate(Book book);
    Optional<Book> getById(int id);
    List<Book> getAll();
    void deleteById(int id);
}
