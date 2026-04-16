package com.java.authmanagerment.controller;


import com.java.authmanagerment.dto.response.AuthenticationResponse;
import com.java.authmanagerment.dto.response.IntrospectResponse;
import com.java.authmanagerment.dto.resquest.AuthenticationRequest;
import com.java.authmanagerment.dto.resquest.IntrospectRequest;
import com.java.authmanagerment.entity.User;
import com.java.authmanagerment.service.AuthenticationService;
import com.java.authmanagerment.service.UserService;
import com.java.authmanagerment.dto.response.ApiResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Cho phép frontend React gọi API
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    // Đăng ký
    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody @Valid User user) {

        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.register(user));

        return apiResponse;
    }

    // Đăng nhập

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    //Validate token
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
