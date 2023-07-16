package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final TrainerService trainerService;
    private final MemberService memberService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login-trainer")
    public ResponseEntity<TrainerDTO> loginTrainer(@RequestBody TrainerDTO dto) {
        TrainerDTO trainerDTO = trainerService.login(dto);
        trainerDTO.setToken(userAuthenticationProvider.createToken(trainerDTO.getUsername()));
        return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<TrainerDTO> register(@RequestBody TrainerDTO dto) {
        TrainerDTO createdTrainer = trainerService.register(dto);
        createdTrainer.setToken(userAuthenticationProvider.createToken(dto.getUsername()));
        return new ResponseEntity<>(createdTrainer, HttpStatus.OK);
    }

}
