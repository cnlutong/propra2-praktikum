package hhu.propra2.group6.chicken.controller;

import hhu.propra2.group6.chicken.application.service.*;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.exam.FormattedExam;
import hhu.propra2.group6.chicken.domain.holiday.FormattedHoliday;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;
    @MockBean
    StudentService studentService;
    @MockBean
    ExamService examService;
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
    void UnauthorizedWithoutLogin() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().is3xxRedirection());
    }


    @Test
    void userLogin() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/student").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/student"));
    }

    @Test
    void urlaubanmeldungGet() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/urlaubanmeldung").session(session))
                .andExpect(status().isOk());
    }

//    @Test
//    void urlaubanmeldungPost() throws Exception {
//        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute(
//                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                new SecurityContextImpl(principal));
//
//        mockMvc.perform(post("/urlaubanmeldung").session(session))
//                .andExpect(status().isOk());
//    }

    @Test
    void klausuranmeldungGet() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/klausuranmeldung").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("klausuranmeldung"));
    }

    @Test
    void klausurenGet() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/klausuren").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("klausuren"));
    }

    @Test
    void klausurangelegtGet() throws Exception {
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/klausurangelegt").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("klausurangelegt"));
    }


    @Test
    void urlaubanmeldungPost() throws Exception {

        FormattedHoliday formattedHoliday = mock(FormattedHoliday.class);
        when(holidayService.insertRed(any(), any(), any())).thenReturn(anyString());

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/urlaubanmeldung").
                        flashAttr("formattedHoliday", formattedHoliday).
                        flashAttr("handle", "yiwan103").
                        session(session).with(csrf()))
                .andExpect(status().isOk())
                .andExpectAll(model().attribute("urlaubform", formattedHoliday)
                        , model().attribute("error", "true"));
    }

    @Test
    void urlaubanmeldungPost_1() throws Exception {

        FormattedHoliday formattedHoliday = mock(FormattedHoliday.class);
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));


        when(formattedHoliday.toTimePeriod()).thenReturn(timePeriod);
        when(studentService.getOAIDByName(anyString())).thenReturn(3L);
        when(holidayService.insertRed(any(), any(), any())).thenReturn("insertRed complete");

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/urlaubanmeldung")
                        .flashAttr("formattedHoliday", formattedHoliday)
                        .flashAttr("handle", "yiwan103")
                        .session(session).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }


    @Test
    void klausuranmeldungPost() throws Exception {

        Student student = studentService.create(100L, "yiwan103");
        Exam exam = mock(Exam.class);

        when(examExistService.findByLsfid(any())).thenReturn(exam);
        when(studentService.getStudent(any())).thenReturn(student);


        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/klausuranmeldung")
                        .flashAttr("handle", "yiwan103")
                        .flashAttr("klausur", "123").session(session).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/student"));
    }

    @Test
    void klausurenPost() throws Exception {
        Student student = studentService.create(100L, "yiwan103");
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 5, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0));
        FormattedExam formattedExam = mock(FormattedExam.class);

        when(formattedExam.getName()).thenReturn("LOL");
        when(formattedExam.toTimePeriod()).thenReturn(timePeriod);
        when(formattedExam.getOffline()).thenReturn(true);
        when(formattedExam.getLsfid()).thenReturn("123456");

        when(examExistService.getEventNameByLsfId(anyString())).thenReturn(true);

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/klausuren")
                        .flashAttr("formattedExam", formattedExam)
                        .flashAttr("handle", "yiwan103")
                        .session(session).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/klausurangelegt"));
        ;
    }

    @Test
    void klausurenPost_1() throws Exception {
        Student student = studentService.create(100L, "yiwan103");
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 5, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0));
        FormattedExam formattedExam = mock(FormattedExam.class);

        when(formattedExam.getName()).thenReturn("LOL");
        when(formattedExam.toTimePeriod()).thenReturn(timePeriod);
        when(formattedExam.getOffline()).thenReturn(true);
        when(formattedExam.getLsfid()).thenReturn("123456");

        when(examExistService.getEventNameByLsfId(anyString())).thenReturn(false);

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/klausuren")
                        .flashAttr("formattedExam", formattedExam)
                        .flashAttr("handle", "yiwan103")
                        .session(session).with(csrf()))
                .andExpect(status().isOk()).andExpect(view().name("klausuren"));
    }

    @Test
    void urlaubabmeldungPost() throws Exception {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        when(holidayService.findById(any())).thenReturn(holiday1);

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/urlaubabmedlung/123456")
                        .flashAttr("handle", "yiwan103")
                        .session(session).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/student"));
    }

    @Test
    void klausurabmeldungPost() throws Exception {
        Exam exam1 = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        when(examService.findById(any())).thenReturn(exam1);
        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(post("/klausurabmedlung/123456")
                        .flashAttr("handle", "yiwan103")
                        .session(session).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/student"));
    }

    @Test
    void studentlogin() throws Exception {

        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 9, 30), LocalDateTime.of(2022, 3, 24, 10, 0));
        Holiday holiday2 = new Holiday(2L, timePeriod2, 3L);

        List<Holiday> holidayList = List.of(holiday1, holiday2);

        int redsum = 0;

        Exam exam1 = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        Exam exam2 = new Exam(100001L, "wangzherongyao", new TimePeriod(LocalDateTime.of(2022, 3, 25, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0)), false, 3L);
        List<Exam> examList = List.of(exam1, exam2);

        when(studentService.isRegister(any())).thenReturn(true);
        when(holidayService.findAllByStudentID(any())).thenReturn(holidayList);
        when(studentService.getStudentRedSum(any())).thenReturn(redsum);
        when(examService.findAllByStudentID(any())).thenReturn(examList);

        OAuth2AuthenticationToken principal = buildPrincipal("student", "yiwan103");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(principal));

        mockMvc.perform(get("/student")
                        .flashAttr("handle", "yiwan103").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("student"))
                .andExpectAll(model().attribute("myholiday", holidayList)
                        , model().attribute("summe", redsum)
                        , model().attribute("restUrlaub", 240 - redsum)
                        , model().attribute("klausuren", examList));
    }

}
