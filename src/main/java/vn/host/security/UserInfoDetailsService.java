package vn.host.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import vn.host.repo.UserRepository;

@Service @RequiredArgsConstructor
public class UserInfoDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserInfoUserDetails(user);
    }
}