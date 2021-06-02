
package inmo.InmobiliariaDaneff.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
    build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    
    
    
    @Override // concepto de autorizacion
    protected void configure(HttpSecurity http) throws Exception{
        
         http.authorizeRequests()
                .antMatchers("/editar/**","/agregar/**","/eliminar/**","/menu","/clientes","/agregar","/inversores","/editarinv/**","/exportarclientes","/exportarinversores","/eliminarinv/{idInversor}")
                    .hasRole("ADMIN")
                .antMatchers("/")
                 .permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/menu")
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                    .csrf().disable()
                 .logout()
                    .permitAll()
                    .logoutSuccessUrl("/login?logout");
        
        
    }
    
    
}
