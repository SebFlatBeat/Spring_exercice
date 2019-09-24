package org.sid.cat_mvc_4.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("1234")).roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("123")).roles("USER");
    }
        /* auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login as principal, pass as credentials, active from users where login=?")
                .authoritiesByUsernameQuery("select login as principal, role as role from users_role where login=? ")
                .passwordEncoder(new MessageDigestPasswordEncoder("MD5"))
                .rolePrefix("ROLE_");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
