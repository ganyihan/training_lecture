package com.example.training_lecture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    @ResponseBody
    @CrossOrigin
    @PostMapping("/test")
    public String test(@RequestParam("name") String userName,@RequestParam("password") String password) {
        System.out.println(userName);
        System.out.println(password);
        return "200";
    }
}
