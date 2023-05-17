package net.javaguides.sms.controller;

import net.javaguides.sms.dto.BooksDTO;
import net.javaguides.sms.entity.Book;
import net.javaguides.sms.entity.MyBookList;
import net.javaguides.sms.service.BookService;
import net.javaguides.sms.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {

    public  static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/CoverImages";
    private final BookService bookService;
    private final MyBookListService myBookListService;

    @Autowired
    public BookController(BookService bookService, MyBookListService myBookListService) {
        this.bookService = bookService;
        this.myBookListService = myBookListService;
    }


    @GetMapping("/")
    public String home(){
        return "home";
    }

    List<String> listSort = new ArrayList<>();

    @RequestMapping(path = {"/available_books"})
    public String getAllBook(Model model, String keyword) {
        listSort.clear();
        listSort.add("By Id Asc");listSort.add("By Author Asc");listSort.add("By Name Asc");listSort.add("By Price Asc");listSort.add("By Publisher Asc");
        model.addAttribute("listS", listSort);

        if(keyword!=null) {
            switch (keyword){
                case "By Author Asc":
                    model.addAttribute("list", bookService.bRepo.findByOrderByAuthorAsc());
                    break;
                case "By Name Asc":
                    model.addAttribute("list", bookService.bRepo.findByOrderByNameAsc());
                    break;
                case "By Price Asc":
                    model.addAttribute("list", bookService.bRepo.findByOrderByPriceAsc());
                    break;
                case "By Publisher Asc":
                    model.addAttribute("list", bookService.bRepo.findByOrderByPublisherAsc());
                    break;
            }
        } else {
            model.addAttribute("list", bookService.getAllBooks());
        }
        return "bookList";
    }


    @GetMapping("/book_register")
    public String bookRegister(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("booksDTO", new BooksDTO());

        if (auth.getAuthorities().toString().contains("ADMIN")) {
            return "bookRegister";
        }
        return "home";
    }

    @PostMapping("/book_register")
    public String saveBook(@ModelAttribute("booksDTO") BooksDTO booksDTO,
                           @RequestParam("coverImage") MultipartFile coverFile,
                           @RequestParam("cvrName") String cvrName) throws IOException {

        Book books = new Book();
        books.setIsbn(booksDTO.getIsbn());
        books.setAuthor(booksDTO.getAuthor());
        books.setName(booksDTO.getName());
        books.setPrice(booksDTO.getPrice());
        books.setPublisher(booksDTO.getPublisher());

        String coverUUID;

        if (!coverFile.isEmpty()) {
            coverUUID = coverFile.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, coverUUID);
            Files.write(fileNameAndPath, coverFile.getBytes());
        } else {
            coverUUID = cvrName;
        }

        books.setCoverName(coverUUID);
        bookService.save(books);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList>list=myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "MyBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id")int id){
        Book b= bookService.getBookById(id);
        MyBookList mb=new MyBookList(
                b.getId(),
                b.getIsbn(),
                b.getName(),
                b.getAuthor(),
                b.getPrice(),
                b.getPublisher(),
                b.getCoverName());
        myBookListService.saveMyBook(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id")int id, Model model){
        Book b=bookService.getBookById(id);
        model.addAttribute("book",b);
        return"bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id){
        bookService.deleteById(id);
        return "redirect:/available_books";
    }
}

//Akmal