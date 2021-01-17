package com.rbs.oidcapiclient.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAuth2LoginConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .authorizeRequests(authz -> authz .anyRequest().authenticated() )
	 * .oauth2ResourceServer(oauth2 -> oauth2.jwt()); }
	 */
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests((requests) -> requests.anyRequest().authenticated())
	            .oauth2ResourceServer()
	            .jwt()
	            .jwtAuthenticationConverter(new AADJwtBearerTokenAuthenticationConverter());
	    }
	
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and() // (1)
//            .authorizeRequests().anyRequest().fullyAuthenticated() // (2)
//            .and()
//            .oauth2ResourceServer().jwt(); // (3)
//    }
//    
    
    
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    String issuerUri;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().fullyAuthenticated()
//                )
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer
//                                .jwt(jwt ->
//                                        jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))
//                                )
//                );
//    }    
}