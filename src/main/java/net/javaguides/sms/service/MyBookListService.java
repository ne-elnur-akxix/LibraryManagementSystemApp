package net.javaguides.sms.service;


import net.javaguides.sms.entity.MyBookList;
import net.javaguides.sms.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {
    @Autowired
    private MyBookRepository mybook;

    public void saveMyBook(MyBookList book){
        mybook.save(book);
    }
    public List<MyBookList> getAllMyBooks(){
        return mybook.findAll();
    }
    public void deleteById(int id) {
        mybook.deleteById(id);
    }
}