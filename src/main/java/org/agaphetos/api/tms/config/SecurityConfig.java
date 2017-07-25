package org.agaphetos.api.tms.config;


import org.agaphetos.api.tms.security.RestUnauthorizedEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"org.agaphetos.api.tms.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public static final String REMEMBER_ME_KEY = "rememberme_key";

    public SecurityConfig() {
        super();
        logger.info("loading SecurityConfig ................................................ ");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
        
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/failure").permitAll()
                .antMatchers("/Security/Questions/**").permitAll()
                .antMatchers("/Security/Answers/**").permitAll()
                .antMatchers("/Security/FirstLog/**").permitAll()
                .antMatchers("/Security/Validate/**").permitAll()
                .antMatchers("/Security/Update/**").permitAll()
                .antMatchers("/Security/Confirm/**").permitAll()
                .antMatchers("/Security/Tokens/**").permitAll()
                .antMatchers(HttpMethod.GET, "/User/**").permitAll()
                .antMatchers(HttpMethod.POST, "/User/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.PUT, "/User/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.DELETE, "/User/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers("/Users/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers("/UserRoles/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.GET, "/ApplicationType/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ApplicationType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.PUT, "/ApplicationType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.DELETE, "/ApplicationType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.GET, "/TaskStatus/**").permitAll()
                .antMatchers(HttpMethod.POST, "/TaskStatus/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.PUT, "/TaskStatus/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.DELETE, "/TaskStatus/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.GET, "/TaskType/**").permitAll()
                .antMatchers(HttpMethod.POST, "/TaskType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.PUT, "/TaskType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers(HttpMethod.DELETE, "/TaskType/**").hasAnyAuthority("Administrator", "Manager", "Supervisor")
                .antMatchers("/Task/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/authenticate")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices)
                .key(REMEMBER_ME_KEY)
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and();
    }
}
