package com.onlinebookshop.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
 
import com.onlinebookshop.shop.model.Author;
import com.onlinebookshop.shop.repository.AuthorRepository;
 
 
@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
 
    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }
 
    public void updateAuthor(Author author) {
        authorRepository.update(author);
    }
    public void deleteById(int id) {
        authorRepository.delete(id);
    }
}