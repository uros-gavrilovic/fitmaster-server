package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.ChangePasswordRequest;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.model.User;
import com.mastercode.fitmaster.model.enums.Role;
import com.mastercode.fitmaster.service.EmailService;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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

    private final EmailService emailService;

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
    public <T extends User> ResponseEntity<T> login(@Valid @RequestBody UserDTO userDTO) {
        T user;

        if (userDTO instanceof TrainerDTO) {
            user = (T) trainerService.login(userDTO);
        } else if (userDTO instanceof MemberDTO) {
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
    public ResponseEntity<? extends User> register(@Valid @RequestBody User user) throws MessagingException {
        User createdUser;
        String mailText = "<p>Welcome to FitMaster! Your account has succesfully been created.<p><br>Please click on the following link to verify your account: http://localhost:8080/verify-account/";

        if (user instanceof Member) {
            createdUser = memberService.registerMember((Member) user);
            mailText += "member/";
        } else if (user instanceof Trainer) {
            createdUser = trainerService.registerTrainer((Trainer) user);
            mailText += "trainer/";
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        mailText += createdUser.getVerificationToken();
        emailService.sendVerificationEmail(createdUser.getEmail(), "Registration successful", mailText);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/change-password/{memberID}")
    public ResponseEntity<Member> changePassword(@PathVariable Long memberID,
                                                 @RequestBody ChangePasswordRequest request) {
        Member updatedMember = memberService.changePassword(memberID, request.getOldPassword(),
                request.getNewPassword());
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verify-account/{role}/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String role, @PathVariable String token) {
        boolean verified;

        switch (Role.valueOf(role.toUpperCase())) {
            case MEMBER:
                verified = memberService.verifyMemberAccount(token);
                break;
            case TRAINER:
                verified = trainerService.verifyTrainerAccount(token);
                break;
            default:
                return new ResponseEntity<>("Invalid role", HttpStatus.BAD_REQUEST);
        }

        if (verified) {
            return new ResponseEntity<>("Account verified", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Create a log-out method that invalidates the token.
}
