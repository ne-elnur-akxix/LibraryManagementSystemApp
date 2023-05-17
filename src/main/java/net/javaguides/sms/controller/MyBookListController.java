package net.javaguides.sms.controller;

import net.javaguides.sms.entity.Book;
import net.javaguides.sms.service.BookService;
import net.javaguides.sms.service.MyBookListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookListController {

    private MyBookListService myBookListService;
    private BookService bookService;

    public MyBookListController(BookService bookService, MyBookListService myBookListService) {
        this.bookService = bookService;
        this.myBookListService = myBookListService;
    }

    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        bookService.save(new Book(id, myBookListService.getById(id).getIsbn(), myBookListService.getById(id).getName(), myBookListService.getById(id).getAuthor(),
                myBookListService.getById(id).getPrice(), myBookListService.getById(id).getPublisher()) );
        myBookListService.deleteById(id);
        return "redirect:/my_books";
    }

    @RequestMapping("/deleteFromMyBook/{id}")
    public String deleteFromMyBook(@PathVariable("id")int id){
        bookService.deleteById(id);
        return "redirect:/my_books";
    }
}

