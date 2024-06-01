package com.socialnetwork.sg.controller;

import com.socialnetwork.sg.model.dto.AuthenticationRequest;
import com.socialnetwork.sg.service.AuthenticationService;
import com.socialnetwork.sg.model.dto.RegisterRequest;
import com.socialnetwork.sg.config.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(new ResponseWrapper(service.register(request),"Success","User Authenticated"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(new ResponseWrapper(service.authenticate(request),"Success","User Authenticated"));
    }
}
