package com.neelav.simplepaymentapp.service;


import com.neelav.simplepaymentapp.model.MyUserDetails;
import com.neelav.simplepaymentapp.model.User;
import com.neelav.simplepaymentapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //System.out.println("Username="+userName);

        Optional<User> user = userRepository.findByUserName(userName);

       return  user.map(MyUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not Found:"+userName));

    }
}
