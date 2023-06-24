package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthentication;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthenticationRequest;
import id.co.indivara.miniproject.hospital.dto.response.ResponseRegisterRequest;
import id.co.indivara.miniproject.hospital.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register/admin")
    public ResponseEntity<ResponseAuthentication> registerAdmin(
            @RequestBody ResponseRegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/register/doctors")
    public ResponseEntity<ResponseAuthentication> registerDoctor(
            @RequestBody ResponseRegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.registerDoctor(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthentication> authenticate(
            @RequestBody ResponseAuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
