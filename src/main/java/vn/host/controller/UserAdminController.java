package vn.host.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.host.entity.Role;
import vn.host.entity.Users;
import vn.host.repo.RoleRepository;
import vn.host.repo.UserRepository;

import java.util.Set;

@RestController @RequestMapping("/admin") @RequiredArgsConstructor
public class UserAdminController {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    @PostMapping("/seed")
    public String seed() {
        var roleAdmin = roleRepo.findByName("ROLE_ADMIN").orElseGet(() ->
                roleRepo.save(Role.builder().name("ROLE_ADMIN").build()));
        var roleUser = roleRepo.findByName("ROLE_USER").orElseGet(() ->
                roleRepo.save(Role.builder().name("ROLE_USER").build()));

        if (!userRepo.existsByUsername("admin")) {
            userRepo.save(Users.builder()
                    .username("admin")
                    .password(encoder.encode("123456"))
                    .roles(Set.of(roleAdmin))
                    .build());
        }
        if (!userRepo.existsByUsername("user")) {
            userRepo.save(Users.builder()
                    .username("user")
                    .password(encoder.encode("123456"))
                    .roles(Set.of(roleUser))
                    .build());
        }
        return "Seeded.";
    }
}