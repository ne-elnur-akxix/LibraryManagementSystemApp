package net.javaguides.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.apache.tomcat.util.descriptor.web.WebXml;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{

        security
                .formLogin().
                loginPage("/login").permitAll().and()
                .formLogin()
                .failureForwardUrl("/register")
                .defaultSuccessUrl("/", true)
                .permitAll().and()
                .authorizeRequests(requests -> requests
                        .antMatchers("/", "/register", "/login").permitAll()
                        .antMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll);
        return security.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(7);}
}
