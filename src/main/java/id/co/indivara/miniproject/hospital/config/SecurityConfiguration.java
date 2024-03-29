package id.co.indivara.miniproject.hospital.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
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
                .antMatchers("/api/auth/").permitAll()
                .antMatchers("/v2/").permitAll()
                .antMatchers("/webjars/").permitAll()
                .antMatchers("/api/v1/auth/").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/v3/api-docs/").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/swagger-resources/").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/swagger-ui/").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/").permitAll()
                .antMatchers("/hospital/addresses/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/specialization/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/doctors/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/patients/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/treatments/**").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/appointment/").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/appointment/{appointmentId}").hasAnyAuthority("ADMIN")
                .antMatchers("/hospital/appointment/{doctorId}").permitAll()
                .antMatchers("/hospital/appointment/{doctorId}/{date}").permitAll()
                .antMatchers("/hospital/record/treatment/**").hasAnyAuthority("DOCTOR")
                .antMatchers("/hospital/medical/treatment").hasAnyAuthority("DOCTOR")
                .antMatchers("/hospital/medical/treatment/history/{patientId}").permitAll()
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
