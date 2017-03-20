package com.sooraj.springboot.configuration.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by SOORAJ on 11-12-2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.sooraj.springboot.security.*"})
public class MvcWebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private static final String baseResource = "/static/**";
    private static final String cssHome = "*.css";
    private static final String jsHome = "*.js";
    private static final String imageHome = "*.ico";
    private static final String font1 = "*.eot";
    private static final String font2 = "*.svg";
    private static final String font3 = "*.ttf";
    private static final String font4 = "*.woff";
    private static final String font5 = "*.woff2";
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Configuration
    @Order(1)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired(required = true)
        @Qualifier(value = "userDetailsService")
        private UserDetailsService userDetailsService;

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers(baseResource)
                    .antMatchers(jsHome)
                    .antMatchers(cssHome)
                    .antMatchers(imageHome)
                    .antMatchers(font1)
                    .antMatchers(font2)
                    .antMatchers(font3)
                    .antMatchers(font4)
                    .antMatchers(font5);

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(baseResource).access("permitAll")
                    .antMatchers("/css/**").access("permitAll")
                    .antMatchers("/js/**").access("permitAll")
                    .antMatchers("/fonts/**").access("permitAll")
                    .antMatchers("/login**").access("permitAll")
                    .antMatchers("/logout**").access("permitAll")
                    .antMatchers("/user/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
                    .antMatchers("/sadmin/**").access("hasRole('ROLE_SUPERADMIN')")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/welcome")
                    .failureUrl("/login?error")
                    .usernameParameter("username").passwordParameter("password")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .and()
                    .exceptionHandling().accessDeniedPage("/accessDenied");

        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            // with encryption
            //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
            // without encryption
            auth.userDetailsService(userDetailsService);
        }
    }

}
