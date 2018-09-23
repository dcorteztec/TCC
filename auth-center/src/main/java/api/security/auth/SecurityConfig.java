package api.security.auth;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import api.security.auth.jwt.JwtAuthenticationConfig;
import api.security.auth.jwt.JwtUsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired JwtAuthenticationConfig config;

    @Autowired
	DataSource dataSource;
    
    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
        dataSource(dataSource).
        usersByUsernameQuery("select username,password, enabled from users where username=?")
        .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
                //.withUser("admin").password("{noop}admin").roles("ADMIN", "USER").and()
                //.withUser("dcortez").password("{noop}opensuse10").roles("ADMIN","USER").and()
                //.withUser("empresa_parceira").password("{noop}opensuse10").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/actuator/**").permitAll()
                    .antMatchers(config.getUrl()).permitAll()
                    .anyRequest().authenticated();
    }
}

