package net.javaguides.sms.controller;
import net.javaguides.sms.entity.Book;
import net.javaguides.sms.entity.MyBookList;
import net.javaguides.sms.service.BookService;
import net.javaguides.sms.service.MyBookListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
public class BookController {
    public BookService service;

    private MyBookListService myBookListService;


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

    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute Book book,
            @RequestParam("cover") MultipartFile coverFile
    ) {
        // Загрузка обложки, если выбран файл
        if (!coverFile.isEmpty()) {
            try {
                byte[] coverData = coverFile.getBytes();
                book.setCover(coverData);
            } catch (IOException e) {
                // Обработка ошибок при загрузке обложки
            }
        }

        service.save(book);
        return "redirect:/available_books";
    }

    @PostMapping("/upload-cover/{id}")
    public String uploadBookCover(
            @PathVariable int id,
            @RequestParam("cover") MultipartFile coverFile
    ) {
        // Получите книгу по идентификатору
        Book book = service.getBookById(id);
        if (book == null) {
            // Обработка ошибки, если книга не найдена
            return "redirect:/available_books";
        }

        try {
            // Загрузка обложки книги
            byte[] coverData = coverFile.getBytes();
            book.setCover(coverData);
            service.save(book);
        } catch (IOException e) {
            // Обработка ошибок при загрузке обложки книги
        }

        return "redirect:/available_books";
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
}

//Akmal