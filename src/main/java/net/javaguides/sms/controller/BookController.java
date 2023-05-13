package net.javaguides.sms.controller;
import net.javaguides.sms.entity.Book;
import net.javaguides.sms.entity.MyBookList;
import net.javaguides.sms.service.BookService;
import net.javaguides.sms.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class BookController {
    public BookService service;

    private MyBookListService myBookListService;

    @Autowired
    public BookController(BookService bookService, MyBookListService myBookListService) {
        this.service = bookService;
        this.myBookListService = myBookListService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = service.getAllBook();
        ModelAndView m=new ModelAndView();
        m.setViewName("bookList");
        m.addObject("book", list);
        return new ModelAndView("bookList","book",list);
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList>list=myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "MyBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id")int id){
        Book b= service.getBookById(id);
        MyBookList mb=new MyBookList(
                b.getId(),
                b.getName(),
                b.getAuthor(),
                b.getPrice(),
                b.getTitle(),
                b.getIsbn());
        myBookListService.saveMyBook(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id")int id, Model model){
        Book b=service.getBookById(id);
        model.addAttribute("book",b);
        return"bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/available_books";
    }

    public void setMyBookListService(MyBookListService myBookListService) {
        this.myBookListService = myBookListService;
    }
}