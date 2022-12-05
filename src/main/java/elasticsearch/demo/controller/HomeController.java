package elasticsearch.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "hello world";
    }

}
