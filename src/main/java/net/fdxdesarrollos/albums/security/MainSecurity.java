package net.fdxdesarrollos.albums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.fdxdesarrollos.albums.security.jwt.JwtEntryPoint;
import net.fdxdesarrollos.albums.security.jwt.JwtTokenFilter;
import net.fdxdesarrollos.albums.security.service.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {
    
	@Autowired
    UserDetailServiceImp userDetailsServiceImp;

    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    
    
    AuthenticationManager authenticationManager;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	builder.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder);
    	authenticationManager = builder.build();
    	http.authenticationManager(authenticationManager);
    	
    	http.csrf(csrf -> csrf.disable());
    	http.cors(Customizer.withDefaults());
    	http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**",
												    			"/recovery/**",
												    			"/configuration/**").permitAll().anyRequest().authenticated());
    	
    	http.exceptionHandling(exc -> exc.authenticationEntryPoint(jwtEntryPoint));
    	http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
    
    
    
    /*
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/recovery/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }*/
}
