package com.kodstar.issuetracker.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;





@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO{


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers= Lists.newArrayList(

                new ApplicationUser(
                        passwordEncoder.encode("password"),
                        "admin",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode("password"),
                        "kodstar",
                        true,
                        true,
                        true,
                        true
                )

        );
        return  applicationUsers;
    }
}
