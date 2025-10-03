package vn.host.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/users/me")
    public Object me(@AuthenticationPrincipal UserDetails user) {
        return new Object(){
            public final String username = user.getUsername();
            public final Object authorities = user.getAuthorities();
        };
    }
}