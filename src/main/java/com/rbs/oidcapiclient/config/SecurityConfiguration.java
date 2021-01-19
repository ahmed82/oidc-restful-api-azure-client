package com.rbs.oidcapiclient.config;
//package com.rbs.botmanager.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("bot-user")
//			.password(PasswordEncoder().encode("Test1@34")).roles("admin")
//			.authorities("Viewer","Approver","Editor");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.cors().and()
//        .csrf().disable()
//		.authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers("/api/**").authenticated()
//            .antMatchers("/util/**","/api/util/**").permitAll()
//            .antMatchers("/static/**","/public/**").permitAll()
//            .antMatchers("/css/**","/js/**","index.html","/login**","/favicon.ico").permitAll()
//			//.antMatchers("intents").hasRole("Admin")
//          .anyRequest().authenticated()
//				.and()
//			.httpBasic()
//				.and()
//			.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
//
//	@Bean
//	PasswordEncoder PasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	/*
//	 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
//	 * configuration = new CorsConfiguration();
//	 * configuration.setAllowedOrigins(Arrays.asList("*"));
//	 * configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
//	 * "DELETE")); configuration.setAllowCredentials(true);
//	 * configuration.setAllowedHeaders(Arrays.asList("Authorization",
//	 * "Cache-Control", "Content-Type")); UrlBasedCorsConfigurationSource source =
//	 * new UrlBasedCorsConfigurationSource();
//	 * source.registerCorsConfiguration("/**", configuration); return source; }
//	 */
//
//}
