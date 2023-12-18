package com.miempresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.miempresa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {

	@Autowired
    private UsuarioServicio userDet;

    @Autowired
    private BCryptPasswordEncoder bycryp;

    @Bean
    public BCryptPasswordEncoder passEncoder() {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDet).passwordEncoder(bycryp);
    }

    
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login"); 
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( 
                (authz) -> authz
                    .requestMatchers("/", "/verJuego/**").permitAll()
                    .requestMatchers("/listarJuegos", "/formJuego", "/guardarJuego").hasRole("ADMIN")
                    .requestMatchers("/editarJuego/**", "/mostrarJuego", "/eliminarJuego/**").hasRole("ADMIN") 
                    .anyRequest().authenticated())
            .formLogin((form) -> form
                    .loginPage("/iniciarsesion") 
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/iniciarsesion?error=true")
                )
            .logout((logout) -> logout
                    .logoutSuccessUrl("/iniciarsesion?logout=true")
                    .permitAll()
                );
        return http.build();
    }
}