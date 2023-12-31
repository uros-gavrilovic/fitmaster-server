package com.mastercode.fitmaster.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private MemberService memberService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + (1000 * 60 * 10 * 99)); // 10 minutes

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer(username).withIssuedAt(now).withExpiresAt(validity).sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        UserDTO user = null;
        try {
            user = memberService.findByUsername(decoded.getIssuer());
        } catch (Exception e) {
            // Do nothing.
        }
        if (user == null) {
            user = trainerService.findByUsername(decoded.getIssuer());
        }

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

    }
}