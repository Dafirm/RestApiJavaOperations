package com.ecommerceApp.EcommerceBackend.Service;


import com.ecommerceApp.EcommerceBackend.DTO.exception.AuthenticationFailException;
import com.ecommerceApp.EcommerceBackend.Models.AuthenticationToken;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return  tokenRepository.findByUser(user);
    }

    public User getUser(String token ){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        //i.e token is not null
        return authenticationToken.getUser();

    }

    public void authenticate(String token) {
        //null check
        if(Objects.isNull(token)){
            //throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }
}
