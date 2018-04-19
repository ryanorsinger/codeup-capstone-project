package com.codeup.michero.services;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.User;
import com.codeup.michero.models.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader  implements UserDetailsService {
    private final UsersRepository users;

    public UserDetailsLoader(UsersRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No users found for " + username);
        }

        return new UserWithRoles(user);
    }
}

