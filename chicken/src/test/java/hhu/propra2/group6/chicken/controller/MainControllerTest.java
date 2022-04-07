package hhu.propra2.group6.chicken.controller;

import hhu.propra2.group6.chicken.application.service.*;
import hhu.propra2.group6.chicken.configuration.MethodSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@Import({MethodSecurityConfiguration.class})
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    StudentService studentService;

    @MockBean
    ExamService examServiceService;
    @MockBean
    HolidayService holidayService;
    @MockBean
    ExamExistService examExistService;
    @MockBean
    LogService logService;


    OAuth2AuthenticationToken buildPrincipal(String role, String name) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("login", name);

        List<GrantedAuthority> authorities = Collections.singletonList(
                new OAuth2UserAuthority("ROLE_" + role.toUpperCase(), attributes));
        OAuth2User user = new DefaultOAuth2User(authorities, attributes, "login");
        return new OAuth2AuthenticationToken(user, authorities, "whatever");
    }


    @Test
    void index() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }


    @Test
    void adminLogin() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("tutor", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/tutor").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("tutor"));

    }

    @Test
    void orgaLogin() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("organisator", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/organisator").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("organisator"));
    }


}