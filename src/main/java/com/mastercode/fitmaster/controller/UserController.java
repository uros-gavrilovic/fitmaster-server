package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @param dto The TrainerDTO containing login credentials.
     *
     * @return A ResponseEntity containing the logged-in TrainerDTO with an authentication token and a status code of OK (200).
     */
    @PostMapping("/login-trainer")
    public ResponseEntity<TrainerDTO> loginTrainer(@RequestBody TrainerDTO dto) {
        TrainerDTO trainerDTO = trainerService.login(dto);
        trainerDTO.setToken(userAuthenticationProvider.createToken(trainerDTO.getUsername()));
        return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
    }

    /**
     * Registers a new fitness trainer and generates an authentication token.
     *
     * @param dto The TrainerDTO containing registration details.
     *
     * @return A ResponseEntity containing the registered TrainerDTO with an authentication token and a status code of OK (200).
     */
    @PostMapping("/register-trainer")
    public ResponseEntity<TrainerDTO> registerTrainer(@RequestBody TrainerDTO dto) {
        TrainerDTO createdTrainer = trainerService.registerTrainer(dto);
        createdTrainer.setToken(userAuthenticationProvider.createToken(dto.getUsername()));
        return new ResponseEntity<>(createdTrainer, HttpStatus.OK);
    }

    // TODO: Create a log-out method that invalidates the token.
}
