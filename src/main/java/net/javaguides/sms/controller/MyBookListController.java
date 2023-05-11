package net.javaguides.sms.controller;


import net.javaguides.sms.service.MyBookListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookListController {

    private MyBookListService service;


    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id")int id){
        service.deleteById(id);
        return"redirect:/my_books";
    }
}
