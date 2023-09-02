package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.model.User;
import com.mastercode.fitmaster.model.enums.Role;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
 *
 * @author Uroš Gavrlović
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    /**
     * Private field that holds an instance of the UserService.
     * This service is responsible for managing user-related operations.
     */
    private final UserService userService;

    /**
     * Private field that holds an instance of the TrainerService.
     * The TrainerService is responsible for managing trainer-related operations.
     */
    private final TrainerService trainerService;

    /**
     * Private field that holds an instance of the MemberService.
     * The MemberService is responsible for managing member-related operations.
     */
    private final MemberService memberService;

    /**
     * Private field that holds an instance of the UserAuthenticationProvider.
     * This provider is used for authenticating and validating user credentials.
     */
    private final UserAuthenticationProvider userAuthenticationProvider;

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
     * @param userDTO The UserDTO containing login credentials.
     *
     * @return A ResponseEntity containing the logged-in Trainer with an authentication token and a status code of OK (200).
     */
    @PostMapping("/login")
    public <T extends User> ResponseEntity<T> loginUser(@Valid @RequestBody UserDTO userDTO) {
        T user;

        if (userDTO.getRole().equals(Role.TRAINER)) {
            user = (T) trainerService.login(userDTO);
        } else if (userDTO.getRole().equals(Role.MEMBER)) {
            user = (T) memberService.login(userDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setToken(userAuthenticationProvider.createToken(user.getUsername()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Registers a new fitness trainer/member and generates an authentication token.
     *
     * @param user The member/trainer object containing registration details.
     *
     * @return A ResponseEntity containing the registered member/trainer with an authentication token and a status code of OK (200).
     *
     * @throws MethodArgumentNotValidException if the Trainer/Member object is invalid.
     */
    @PostMapping("/register")
    public <T extends User> ResponseEntity<T> registerUser(@Valid @RequestBody T user) {
        T createdUser;

        if (user instanceof Trainer) {
            createdUser = (T) trainerService.registerTrainer((Trainer) user);
        } else if (user instanceof Member) {
            createdUser = (T) memberService.registerMember((Member) user);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        createdUser.setToken(userAuthenticationProvider.createToken(user.getUsername()));
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    // TODO: Create a log-out method that invalidates the token.
}
