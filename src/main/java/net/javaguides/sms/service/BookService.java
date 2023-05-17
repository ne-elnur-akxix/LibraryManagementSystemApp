package net.javaguides.sms.service;



import net.javaguides.sms.entity.Book;
import net.javaguides.sms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public BookRepository bRepo;

    public BookService(BookRepository bRepo) {
        this.bRepo = bRepo;
    }

    public void save(Book book) {
        // Проверка, что bRepo не является null
        if (bRepo != null) {
            bRepo.save(book);
        } else {
            // Обработка случая, когда bRepo равно null
            throw new IllegalStateException("BookRepository is not initialized");
        }
    }

    public List<Book> getAllBook() {
        return bRepo.findAll();
    }


    public Book getBookById(int id) {
        return bRepo.findById(id).get();
    }

    public void deleteById(int id) {
        bRepo.deleteById(id);
    }

    public List<Book> getByKeyword(String keyword) {
        return bRepo.findByKeyword(keyword);
    }

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) bRepo.findAll();
        return list;
    }
}

