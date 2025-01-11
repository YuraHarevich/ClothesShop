package com.Harevich.core.controller;


import com.Harevich.core.dto.UserLoginRecord;
import com.Harevich.core.dto.UserRegistrationRecord;
import com.Harevich.core.service.KeycloakUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class KeycloakUserApi {

    private final KeycloakUserService keycloakUserService;

    @PostMapping("/registration")
    public UserRegistrationRecord registration(@RequestBody UserRegistrationRecord userRegistrationRecord) {
        return keycloakUserService.createUser(userRegistrationRecord);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        keycloakUserService.deleteUserById(userId);
    }

    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        keycloakUserService.emailVerification(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody @Valid UserLoginRecord userLoginRecord) {
        return ResponseEntity.ok(keycloakUserService.getJwt(userLoginRecord));
    }
}
