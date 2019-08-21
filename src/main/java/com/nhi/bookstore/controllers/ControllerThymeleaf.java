package com.nhi.bookstore.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerThymeleaf {

    @RequestMapping("/success")
    public String getSuccess(){
        return "success";
    }
    @RequestMapping("/homepageUser")
    public String getHomepageUser(){
        return "homepageUser";
    }

    @RequestMapping("/homepageAdmin")
    public String getHomepageAdmin(){
        return "homepageAdmin";
    }

    @RequestMapping("/bookWaiting")
    public String getBookWaiting(){
        return "bookWaiting";
    }

    @RequestMapping("/userWaiting")
    public String getUserWaiting(){
        return "userWaiting";
    }

    @RequestMapping("/listUser")
    public String getListUser(){
        return "listUser";
    }

    @RequestMapping("/index1")
    public String getIndex1(){
        return "index1";
    }

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/myBook")
    public String getMyBook(){
        return "myBook";
    }


    @RequestMapping("/book/{id}")
    public String book(){
        return "book";
    }

    @RequestMapping("/bookUser/{id}")
    public String bookUser(){
        return "bookUser";
    }

    @RequestMapping("/bookAdmin/{id}")
    public String bookAdmin(){
        return "bookAdmin";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(){
        return "editBookForm";
    }
}
