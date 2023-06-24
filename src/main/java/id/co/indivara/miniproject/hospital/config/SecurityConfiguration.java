package id.co.indivara.miniproject.hospital.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/hospital/addresses/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/specialization/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/doctors/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/patients/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/treatments/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/appointment/").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/appointment/{doctorId}").hasAnyAuthority("DOCTOR")
                .antMatchers("/hospital/record-treatment/**").hasAnyAuthority("DOCTOR")
                .antMatchers("/hospital/medical-treatment").hasAnyAuthority("DOCTOR")
                .antMatchers("/hospital/medical-treatment/{patientId}").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
