package miu.edu.pm.project.onlineshoppingcartsystem.user.config;

import miu.edu.pm.project.onlineshoppingcartsystem.user.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebSecurity
@Configuration
@EnableWebMvc
public class MySecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAunthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAunthenticationFilter jwtAunthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
               http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/generate-token","/user/","/user/test").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAunthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        DefaultSecurityFilterChain defaultSecurityFilterChain= http.build();
        return defaultSecurityFilterChain;
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService((this.userDetailsServiceImpl));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .cors()
//                .disable()
//                .authorizeHttpRequests()
//                .antMatchers("/generate-token","/user/","/user/test").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtAunthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }


}
