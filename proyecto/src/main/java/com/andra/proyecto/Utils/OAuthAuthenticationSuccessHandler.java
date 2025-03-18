package com.andra.proyecto.Utils;

import com.andra.proyecto.Entities.Role;
import com.andra.proyecto.Entities.Roles;
import com.andra.proyecto.Entities.Token;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Repository.RoleRepository;
import com.andra.proyecto.Repository.TokenRepository;
import com.andra.proyecto.Repository.UserRepository;
import com.andra.proyecto.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("onAuthenticationSuccess");

        // identify the provider
        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        Users user = new Users();
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            // google
            // google attributes
            user.setGmail(oauthUser.getAttribute("email").toString());
            user.setUsername(oauthUser.getAttribute("sub"));
            user.setFirstName(oauthUser.getAttribute("given_name").toString());
            user.setLastName(oauthUser.getAttribute("family_name").toString());
//            user.setRole(Roles.ADMIN);
//            user.getAuthorities();
        }
        else {
            logger.info("OAuthAuthenticationSuccessHandler: Unknown provider");
        }

        // Buscar si el usuario existe en la DB
        Users user2 = userRepository.findByUsername(user.getUsername());
        // Obtener el código del rol del usuario autenticado
        //String role = Objects.requireNonNull(user2).getRoles().get(0).getCode();

//        Token token = null;

        // Si el usuario no existe, crear el nuevo registro y enviarlo a la página de espera
        if (user2 == null) {
            try {
                userRepository.save(user);
//                token = userService.registerToken(user);
//                tokenRepository.save(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("user with the gmail: " + user.getGmail() + " saved!");
            new DefaultRedirectStrategy().sendRedirect(request, response, "/page/user");
        }
        // Si el usuario existe, enviarlo a su página correspondiente
        else if (Objects.requireNonNull(user2).getRoles().get(0).getCode().equals("ADMIN")) {
                new DefaultRedirectStrategy().sendRedirect(request, response, "/page/administrator");
        }
        else if (Objects.requireNonNull(user2).getRoles().get(0).getCode().equals("RESEN")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/page/inChargeResident");
        }
        else if (Objects.requireNonNull(user2).getRoles().get(0).getCode().equals("RESNO")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/page/resident");
        }
        else if (Objects.requireNonNull(user2).getRoles().get(0).getCode().equals("VISIT")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/page/visitant");
        }
        else if (Objects.requireNonNull(user2).getRoles().get(0).getCode().equals("VIGIL")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/page/vigilant");
        }
    }
}
