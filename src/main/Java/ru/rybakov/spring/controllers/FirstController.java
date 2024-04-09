package ru.rybakov.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname",required = false) String surname){
        if (name !=null & surname !=null){
            System.out.println(name + " " +surname);
        }
        return "first/hello";
    };
    @GetMapping("/goodbye")
    public String sayGoodbye(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("operation") String operation,
                             Model model){
        int resoult = 0;
        switch (operation){
            case "sum": resoult = a + b;
            break;
            case "del": resoult = a-b;
            break;
            case "umnojit": resoult = a*b;
            break;
        }
        model.addAttribute("resount", resoult);
        return "/first/calculator";
    }
}
