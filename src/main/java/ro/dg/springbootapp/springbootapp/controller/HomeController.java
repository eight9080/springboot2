package ro.dg.springbootapp.springbootapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.dg.springbootapp.springbootapp.domain.model.Image;

@RestController
public class HomeController {

    @GetMapping
    public String greeting(@RequestParam(required = false, defaultValue = "")String name){
        return name.equals("") ?"Hey!" :"Hey, "+name+"!";
    }

}
