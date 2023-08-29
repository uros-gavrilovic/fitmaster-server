package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The UserController class handles HTTP requests related to user authentication and registration.
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final TrainerService trainerService;
    @Autowired
    private final MemberService memberService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * Retrieves information about the fitness application.
     *
     * @return A ResponseEntity containing application information and a status code of OK (200).
     */
    @GetMapping("/app-info")
    public ResponseEntity<Map<String, String>> getAppInfo() {
        Map<String, String> info = userService.getAppInfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    /**
     * Logs in a fitness trainer and generates an authentication token.
     *
     * @param dto The UserDTO containing login credentials.
     *
     * @return A ResponseEntity containing the logged-in Trainer with an authentication token and a status code of OK (200).
     */
    @PostMapping("/login-trainer")
    public ResponseEntity<Trainer> loginTrainer(@Valid @RequestBody UserDTO dto) {
        Trainer trainer = trainerService.login(dto);
        trainer.setToken(userAuthenticationProvider.createToken(trainer.getUsername()));
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    /**
     * Registers a new fitness trainer and generates an authentication token.
     *
     * @param trainer The Trainer object containing registration details.
     *
     * @return A ResponseEntity containing the registered trainer with an authentication token and a status code of OK (200).
     *
     * @throws MethodArgumentNotValidException if the Trainer object is invalid.
     */
    @PostMapping("/register-trainer")
    public ResponseEntity<Trainer> registerTrainer(@Valid @RequestBody Trainer trainer) {
        Trainer createdTrainer = trainerService.registerTrainer(trainer);
        createdTrainer.setToken(userAuthenticationProvider.createToken(trainer.getUsername()));
        return new ResponseEntity<>(createdTrainer, HttpStatus.OK);
    }

    // TODO: Create a log-out method that invalidates the token.
}
