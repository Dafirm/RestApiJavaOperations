package com.ecommerceApp.EcommerceBackend.Service;

import com.ecommerceApp.EcommerceBackend.DTO.exception.AuthenticationFailException;
import com.ecommerceApp.EcommerceBackend.DTO.exception.CustomException;
import com.ecommerceApp.EcommerceBackend.DTO.user.ResponseDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignInDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignInResponseDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignupDto;
import com.ecommerceApp.EcommerceBackend.Models.AuthenticationToken;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {

        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){
            throw new CustomException("user already present");
        };
//hash the password here
        String encryptedpassword = signupDto.getPassword();
        try{
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//create new user with sign up
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);

        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success","user successfully created");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //Find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }
        try{
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if(Objects.isNull(token)){
            throw new CustomException("token is not present");
        }
        return new SignInResponseDto("success", token.getToken());

    }
}
