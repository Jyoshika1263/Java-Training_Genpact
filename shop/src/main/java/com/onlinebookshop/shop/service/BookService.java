package com.onlinebookshop.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
 
import com.onlinebookshop.shop.model.Book;
import com.onlinebookshop.shop.repository.BookRepository;
 
@Service
public class BookService {
    private final BookRepository bookRepository;
 
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
 
    public void addBook(Book book) {
        bookRepository.save(book);
    }
 
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }
 
    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }
 
    public void updateBook(Book book) {
        bookRepository.update(book);
    }
 
    public void deleteById(int id) {
        bookRepository.delete(id);
    }
}
 