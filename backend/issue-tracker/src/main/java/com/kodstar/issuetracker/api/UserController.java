package com.kodstar.issuetracker.api;


import com.kodstar.issuetracker.auth.ApplicationUser;
import com.kodstar.issuetracker.auth.ApplicationUserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("users")
public class UserController {

    private ApplicationUserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserDAO userDAO,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }
}