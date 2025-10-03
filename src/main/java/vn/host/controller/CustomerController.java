package vn.host.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello (public)";
    }

    @GetMapping("/customers")
    public List<String> getCustomerList() {
        return List.of("Alice", "Bob", "Carol");
    }
}