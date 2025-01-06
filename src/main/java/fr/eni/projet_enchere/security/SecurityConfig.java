package fr.eni.projet_enchere.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(
				new ClearSiteDataHeaderWriter(Directive.ALL));
		http.authorizeHttpRequests(
				(authorize) -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers("/encheres").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/utilisateur/signin").permitAll()
				.requestMatchers("/utilisateur").hasAnyRole("ADMIN", "MEMBRE").anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).formLogin(form -> form.loginPage("/login").permitAll())
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
						.addLogoutHandler(clearSiteData).logoutSuccessUrl("/"));
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager
				.setUsersByUsernameQuery("SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.pseudo, r.ROLE FROM utilisateurs u \r\n"
				+ "join ROLES r ON u.administrateur = r.is_admin \r\n" + "WHERE u.pseudo = ?");
		return jdbcUserDetailsManager;
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
