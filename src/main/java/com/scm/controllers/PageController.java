package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {


  @RequestMapping("/home")
  public String home(Model model){
    System.out.println("Home Page");
    model.addAttribute("name","Sankalp");
    model.addAttribute("youtubechannel","bndkjfbkudarb");
    return "home";
  }

  //About

  @RequestMapping("/about")
  public String aboutPage(Model model){
    model.addAttribute("isLogin",true);
    System.out.println("This is About Page Loading");
    return "about";
  }

  //Service

  @RequestMapping("/services")
  public String servicesPage(){
    System.out.println("This is Services Page Loading");
    return "services";
  }

}
