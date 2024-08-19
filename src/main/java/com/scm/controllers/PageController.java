package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

  @Autowired
  private UserService userService;


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
//contact
@GetMapping("/contact")
  public String contactPage(){
    System.out.println("This is Contact Page Loading");
    return "contact";
  }

  //login
@GetMapping("/login")
  public String loginPage(){
    System.out.println("This is login Page Loading");
    return "login";
  }
  //register
@GetMapping("/register")
  public String registerPage(Model model){

    UserForm userForm = new UserForm();
    //we can add default data too
    // userForm.setName("Sankalp");
    // userForm.setAbout("Hey there Write Something about you");

    model.addAttribute("userForm", userForm);
    System.out.println("This is register Page Loading");
    return "register";
  }

  //processing register
  @PostMapping(value = "do-register")
  public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){

    System.out.println("Processing Register");
    System.out.println(userForm);
    // fetch form data
    // UserForm
    //validate from data
    if (rBindingResult.hasErrors()) {
      return "register";
    }



    //save to data through UserService
    //converting userForm => User
    // User user = User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .phoneNumber(userForm.getPhoneNumber())
    // // .profilePic("")
    // .build();

    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    // user.setProfilePic(userForm.getProfilePic());


    User saveduser = userService.saveUser(user);
    System.out.println("user saved");
    //message = "Registration Successful"

    Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
    session.setAttribute("message", message);
    //Redirection to login page

    return "redirect:/register";
  }


}
