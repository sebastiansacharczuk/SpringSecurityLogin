package com.sebsach.springlogin.service.book;

import com.sebsach.springlogin.dao.IBookDAO;
import com.sebsach.springlogin.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }

}
