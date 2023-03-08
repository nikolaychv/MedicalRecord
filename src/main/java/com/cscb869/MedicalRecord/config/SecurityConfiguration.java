package com.cscb869.MedicalRecord.config;

import com.cscb869.MedicalRecord.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").hasAnyAuthority("ADMIN")
                .antMatchers("/patients").hasAnyAuthority("ADMIN")
                .antMatchers("/patients/create").hasAnyAuthority("ADMIN")
                .antMatchers("/patients/create-patient").hasAnyAuthority("ADMIN")
                .antMatchers("/patients/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/patients/edit-patient/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/patients/delete/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/create").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/create-doctor").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/edit-doctor/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/delete/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/doctors/search-doctors").hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations").hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations/create").hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations/create-medical-examination")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations/edit-medical-examination/{id}")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/medical-examinations/delete/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses").hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/create").hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/create-diagnosis")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/edit-diagnosis/{id}")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/delete/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/diagnoses/search-diagnoses").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets/create").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets/create-sick-sheet").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets/edit-sick-sheet/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/sick-sheets/delete/{id}").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and();
    }
}
