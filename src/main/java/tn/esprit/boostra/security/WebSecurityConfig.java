package tn.esprit.boostra.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tn.esprit.boostra.entity.CustomOAuth2User;
import tn.esprit.boostra.service.CustomOAuth2UserService;
import tn.esprit.boostra.service.IUserService;
import tn.esprit.boostra.service.MyUserDetailsService;

@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private MyUserDetailsService userDetailsService;
  @Autowired private CustomOAuth2UserService oauthUserService;
  @Autowired private IUserService us;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().mvcMatchers("/swagger-ui/**", "/configuration/**",
                               "/swagger-resources/**", "/v2/api-docs");
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/registration")
        .permitAll()
        .antMatchers("/post/**")
        .access("hasRole('employe') ")
        .antMatchers("/tag/**")
        .access("hasRole('employe') ")
        .antMatchers("/getUserById/{idUser}")
        .access("hasRole('administrator') ")
        .antMatchers("/say**")
        .access("hasRole('administrator') or hasRole('moderator') ")
        .antMatchers("/getAll")
        .access("hasRole('administrator')")
        .antMatchers("/event/add-event")
        .access("hasRole('organizer')")
        .antMatchers("/", "/login", "/oauth/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable();

    http.oauth2Login()
        .loginPage("/oauth2/authorization/google")
        .userInfoEndpoint()
        .userService(oauthUserService)
        .and()
        .successHandler(new AuthenticationSuccessHandler() {
          @Override
          public void onAuthenticationSuccess(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Authentication authentication)
              throws IOException, ServletException {

            CustomOAuth2User oauthUser =
                (CustomOAuth2User)authentication.getPrincipal();
            String email = oauthUser.getEmail();
            String fname = oauthUser.getAttribute("given_name");
            String lname = oauthUser.getAttribute("family_name");
            String picture = oauthUser.getAttribute("picture");
            us.processOAuthPostLogin(email, fname, lname, picture);
            response.sendRedirect("/oauth/step2/" +
                                  email.replace("@gmail.com", ""));
          }
        });
  }
}
