package net.javaguides.sms.service;


import net.javaguides.sms.entity.Book;
import net.javaguides.sms.entity.MyBookList;
import net.javaguides.sms.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {
    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBook(MyBookList book){
        myBookRepository.save(book);
    }
    public List<MyBookList> getAllMyBooks(){
        return myBookRepository.findAll();
    }
    public void deleteById(int id) {
        myBookRepository.deleteById(id);
    }
    public MyBookList getById(int id) { return myBookRepository.getById(id); }
}