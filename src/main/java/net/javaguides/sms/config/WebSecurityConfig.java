package net.javaguides.sms.config;

import net.javaguides.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.apache.tomcat.util.descriptor.web.WebXml;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register").permitAll()
                .antMatchers( "/users", "/book_register", "/available_book", "/registerAdmin", "/borrowers", "/borrowers/new").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") // Указывает URL для выполнения логаута
                .logoutSuccessUrl("/login?logout") // Указывает URL для перенаправления после успешного логаута
                .invalidateHttpSession(true) // Очищает текущую сессию пользователя
                .deleteCookies("JSESSIONID") // Удаляет cookies, связанные с сессией
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(7);}
}
