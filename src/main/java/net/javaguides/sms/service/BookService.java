package net.javaguides.sms.service;

import net.javaguides.sms.entity.Book;
import net.javaguides.sms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}