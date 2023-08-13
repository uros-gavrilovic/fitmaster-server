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

    @GetMapping("/app-info")
    public ResponseEntity<Map<String, String>> getAppInfo() {
        Map<String, String> info = userService.getAppInfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @PostMapping("/login-trainer")
    public ResponseEntity<TrainerDTO> loginTrainer(@RequestBody TrainerDTO dto) {
        TrainerDTO trainerDTO = trainerService.login(dto);
        trainerDTO.setToken(userAuthenticationProvider.createToken(trainerDTO.getUsername()));
        return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
    }

    @PostMapping("/register-trainer")
    public ResponseEntity<TrainerDTO> registerTrainer(@RequestBody TrainerDTO dto) {
        TrainerDTO createdTrainer = trainerService.registerTrainer(dto);
        createdTrainer.setToken(userAuthenticationProvider.createToken(dto.getUsername()));
        return new ResponseEntity<>(createdTrainer, HttpStatus.OK);
    }

    // TODO: Create a log-out method that invalidates token.
}
