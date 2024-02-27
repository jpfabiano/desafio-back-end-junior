package com.example.BlogJWT.controller;

import com.example.BlogJWT.Dtos.user.LoginResponseDTO;
import com.example.BlogJWT.Dtos.user.UserLoginDTO;
import com.example.BlogJWT.Dtos.user.UserRegisterDTO;
import com.example.BlogJWT.Dtos.user.UserResponse;
import com.example.BlogJWT.models.UserModel;
import com.example.BlogJWT.repositories.UserRepository;
import com.example.BlogJWT.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> fazerLogin (@RequestBody @Valid UserLoginDTO userLoginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDto.login(), userLoginDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<Object> registrar (@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        if (userRepository.findByLogin(userRegisterDTO.login()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login já existe");
        if (userRepository.findByUsername(userRegisterDTO.username()) != null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());
        UserModel userModel = new UserModel(userRegisterDTO.username(),userRegisterDTO.login(),encryptedPassword,userRegisterDTO.role());
        userRepository.save(userModel);
        UserResponse response = new UserResponse(userModel);
        response.add(linkTo(AuthenticationController.class).slash("login").withRel("Faça seu login"));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
