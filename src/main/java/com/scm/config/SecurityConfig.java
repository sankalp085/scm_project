package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

  //user create and login using java code with in memory service

  // @Bean
  // public UserDetailsService userDetailsService() {

  //  UserDetails user1 =  User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN","USER").build();
  //  UserDetails user2 =  User.withDefaultPasswordEncoder().username("user123").password("user123").build();

  //   var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
  //   return inMemoryUserDetailsManager;


  // }

  @Autowired
  private SecurityCustomUserDetailService userDetailService;


//Configuration of Authentication Provider Spring Security
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //Object of User detail service
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    // Object of password encoder
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;

  }

  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

    //Configuration
    //configure URLs as to which ones will remain public and which ones will remain private
    // httpSecurity.authorizeHttpRequests(authorize->{authorize.requestMatchers("/home","/register","/login","/services").permitAll();});
    httpSecurity.authorizeHttpRequests(authorize->{authorize.requestMatchers("/user/**").authenticated();
    authorize.anyRequest().permitAll();
    });

    //form default login 
    //if we want to change related to form login 
    httpSecurity.formLogin(formlogin->{
      formlogin.loginPage("/login");
      formlogin.loginProcessingUrl("/authenticate");
      formlogin.successForwardUrl("/user/dashboard");
      // formlogin.failureForwardUrl("/login/error=true");
      formlogin.usernameParameter("email");
      formlogin.passwordParameter("password");

      // formlogin.failureHandler((request, response, exception) -> {
      //   throw new UnsupportedOperationException("Inimplemented method 'onAuthenticationFailure'");
      // });
      // formlogin.successHandler((request, response, authentication) -> {
      //   throw new UnsupportedOperationException();
      // });



    });

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.logout(logoutForm->{
      logoutForm.logoutUrl("/do-logout");
      logoutForm.logoutSuccessUrl("/login?logout=true");
    });


    return httpSecurity.build();

  }


  //Configuration of Password Encoder Spring Security
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



}
