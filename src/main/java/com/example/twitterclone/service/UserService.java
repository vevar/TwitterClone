package com.example.twitterclone.service;

import com.example.twitterclone.domain.Role;
import com.example.twitterclone.domain.User;
import com.example.twitterclone.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{

    @Value("${values.string.project_name}")
    private String projectName;
    @Value("${values.string.activation_link}")
    private String activationLink;

    private final UserRepository userRepository;

    private final MailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to %s. Please, visit next link: %s%s",
                    user.getUsername(),
                    projectName,
                    activationLink,
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

    public Set<String> allRoles(){
        return Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null){
            return false;
        }
        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }
}
