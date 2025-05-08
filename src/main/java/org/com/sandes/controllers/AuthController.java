package org.com.sandes.controllers;

import jakarta.validation.Valid;
import org.com.sandes.model.User;
import org.com.sandes.model.dtos.AuthenticationDto;
import org.com.sandes.model.dtos.LoginResponseDTO;
import org.com.sandes.model.dtos.RegisterDto;
import org.com.sandes.repositories.UserRepository;
import org.com.sandes.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(dto.login(), dto.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken(
                (User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto) {
        if(repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder()
                .encode(dto.password());
        User newUser = new User(dto.login(), encryptedPassword, dto.role());

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
