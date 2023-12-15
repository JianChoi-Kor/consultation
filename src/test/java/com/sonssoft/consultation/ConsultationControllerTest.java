package com.sonssoft.consultation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonssoft.consultation.dto.ConsultationRequestDto.ReadConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.SearchConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterFeedback;
import com.sonssoft.consultation.dto.FeedbackRequestDto.ModifyFeedback;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Student;
import com.sonssoft.consultation.enums.EmployeeState;
import com.sonssoft.consultation.enums.EmployeeType;
import com.sonssoft.consultation.enums.StudentState;
import com.sonssoft.consultation.repository.ConsultationInfoRepository;
import com.sonssoft.consultation.repository.EmployeeRepository;
import com.sonssoft.consultation.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ConsultationInfoRepository consultationInfoRepository;

    private Student testStudent;
    private Employee testConsultantEmployee;
    private Employee testManagerEmployee;
    private ConsultationInfo testConsultationInfo;

    private final String SUCCESS = "success";

    @BeforeEach
    void setStudent() {
        log.info("================= setStudent() =================");
        Student student = Student.builder()
                .name("student1")
                .state(StudentState.NORMAL)
                .build();
        testStudent = studentRepository.save(student);
    }

    @BeforeEach
    void setEmployee() {
        log.info("================= setEmployee() =================");
        Employee consultant = Employee.builder()
                .name("consultant1")
                .type(EmployeeType.CONSULTANT)
                .state(EmployeeState.NORMAL)
                .build();
        testConsultantEmployee = employeeRepository.save(consultant);

        Employee manager = Employee.builder()
                .name("manager1")
                .type(EmployeeType.MANAGER)
                .state(EmployeeState.NORMAL)
                .build();
        testManagerEmployee = employeeRepository.save(manager);
    }

    @BeforeEach
    void setConsultationInfo() {
        log.info("================= setConsultationInfo() =================");
        ConsultationInfo consultationInfo = ConsultationInfo.builder()
                .student(testStudent)
                .employee(testConsultantEmployee)
                .content("상담 내용입니다.")
                .build();
        testConsultationInfo = consultationInfoRepository.save(consultationInfo);
    }

    @DisplayName("상담 등록 테스트")
    @Test
    void registerConsultationInfoTest() throws Exception {
        // requestDto
        RegisterConsultation registerConsultation = new RegisterConsultation();
        registerConsultation.setStudentId(testStudent.getId());
        registerConsultation.setEmployeeId(testConsultantEmployee.getId());
        registerConsultation.setContent("상담 내용입니다.");

        String content = objectMapper.writeValueAsString(registerConsultation);

        mockMvc.perform(post("/consultation")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }

    @DisplayName("상담 피드백 등록 테스트")
    @Test
    void registerFeedbackTest() throws Exception {
        // requestDto
        RegisterFeedback registerFeedback = new RegisterFeedback();
        registerFeedback.setEmployeeId(testManagerEmployee.getId());
        registerFeedback.setContent("피드백 내용입니다.");

        String content = objectMapper.writeValueAsString(registerFeedback);

        mockMvc.perform(post("/consultation/" + testConsultationInfo.getId() + "/feedback")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }

    @DisplayName("상담 목록 조회 테스트")
    @Test
    void getConsultationInfoListTest() throws Exception {
        // requestDto
        SearchConsultation searchConsultation = new SearchConsultation();
        searchConsultation.setRecentYn(true);

        mockMvc.perform(get("/consultation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }

    @DisplayName("상담 상세 조회 테스트")
    @Test
    void getConsultationInfoTest() throws Exception {
        mockMvc.perform(get("/consultation/" + testConsultationInfo.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }

    @DisplayName("상담 읽음 처리 테스트")
    @Test
    void readConsultationTest() throws Exception {
        // requestDto
        ReadConsultation readConsultation = new ReadConsultation();
        readConsultation.setEmployeeId(testManagerEmployee.getId());

        String content = objectMapper.writeValueAsString(readConsultation);

        mockMvc.perform(post("/consultation/" + testConsultationInfo.getId() + "/read")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }

    @DisplayName("상담 피드백 수정 테스트")
    @Test
    void modifyFeedbackTest() throws Exception {
        // requestDto
        ModifyFeedback modifyFeedback = new ModifyFeedback();
        modifyFeedback.setEmployeeId(testManagerEmployee.getId());
        modifyFeedback.setContent("수정된 피드백 내용입니다.");

        String content = objectMapper.writeValueAsString(modifyFeedback);

        mockMvc.perform(post("/consultation/" + testConsultationInfo.getId() + "/feedback")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(SUCCESS)))
                .andDo(print());
    }
}
