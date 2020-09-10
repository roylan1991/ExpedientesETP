package com.roylan.expedientes_etp.security;

import com.roylan.expedientes_etp.database.services.GestionarUsuarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GestionarUsuarioImpl usuario;

    @Autowired
    private EncriptarPassword encriptar;

    //Configura la forma en que se realizará la autenticación, en este caso mediante usuarios de la BD.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuario).passwordEncoder(encriptar.getBCryptPasswordEncoder());
    }

    //Necesario para evitar que la seguridad se aplique a los recursos.
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/bootstrap/**", "/img/**", "/css/**", "/js/**");
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()

                .antMatchers("/nuevo_usuario").hasAnyAuthority("Administrador", "Supervisor")
                .antMatchers("/del_usuario").hasAnyAuthority("Administrador", "Supervisor")
                .antMatchers("/nuevo_centro").hasAuthority("Usuario")
                .antMatchers("/editar_centro").hasAuthority("Usuario")
                .antMatchers("/del_centro").hasAuthority("Usuario")
                .antMatchers("/nueva_especialidad_centro").hasAuthority("Usuario")
                .antMatchers("/del_especialidad_centro").hasAuthority("Usuario")
                .antMatchers("/nueva_especialidad").hasAuthority("Administrador")
                .antMatchers("/editar_especialidad").hasAuthority("Administrador")
                .antMatchers("/del_especialidad").hasAuthority("Administrador")
                .antMatchers("/editar_planilla_datos").hasAuthority("Usuario")
                .antMatchers("/del_planilla_datos").hasAuthority("Usuario")
                .antMatchers("/vaciar_planillas_datos").hasAuthority("Usuario")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/expedientes", true)
                .usernameParameter("usuario")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        /*Para evitar que se use una sesión de usuario por más de 1 persona.
        true: Si ya hay una sesión abierta, no deja usarla a otra persona.
        false: Cierra la sesión al que la usa y deja entrar a la otra persona.*/
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login?session");

        //Para cuando hagan una petición por el puerto 8080 te redireccione hacia 8443(https)
        // http.requiresChannel().anyRequest().requiresSecure().and().portMapper().http(8080).mapsTo(8443).;
    }

    //Para garantizar que las sesiones expiradas se limpien cuando se usa maxSessionsPreventsLogin
    @Bean
    public static HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}