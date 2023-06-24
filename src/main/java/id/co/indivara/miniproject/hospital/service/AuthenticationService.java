package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthenticationRequest;
import id.co.indivara.miniproject.hospital.dto.response.ResponseRegisterRequest;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthentication;
import id.co.indivara.miniproject.hospital.entity.Role;
import id.co.indivara.miniproject.hospital.entity.User;
import id.co.indivara.miniproject.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public ResponseAuthentication registerDoctor(ResponseRegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DOCTOR)
                .build();
        userRepository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return ResponseAuthentication.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseAuthentication registerAdmin(ResponseRegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return ResponseAuthentication.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseAuthentication authenticate(ResponseAuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("Username Not Found"));
        var jwtToken =jwtService.generateToken(user);
        return ResponseAuthentication.builder()
                .token(jwtToken)
                .build();
    }
}
