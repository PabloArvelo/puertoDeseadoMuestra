package com.puertodeseado.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeguridadConfiguracion {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;



    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // de esta manera configuramos la seguridad de los endpoints con las anotaciones en el controlador...
    // esto es porque tenemos en esta clase, la anotación @EnableMethodSecurity
    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   LoginValidationFilter loginValidationFilter) throws Exception {
        return httpSecurity
                //.csrf(csrf -> csrf.disable()) // Temporalmente mientras pruebas
                .addFilterBefore(loginValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login",
                                "/logout",
                                "/finSesion",
                                "/css/**",
                                "/script/**",
                                "/img/**",
                                "/recover-account",
                                "/reset-password",
                                "/recupero").permitAll()
                        .requestMatchers("/seguridad/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true") // Redirige al login con error
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/finSesion")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true))
                .build();
    }




    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // para hacer las pruebas ahora usanmos este, luego usaremos BCryiptPasswordEncoder

        return new BCryptPasswordEncoder();

    }



}
