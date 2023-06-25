package id.co.indivara.miniproject.hospital;

import id.co.indivara.miniproject.hospital.controller.AppoinmentController;
import id.co.indivara.miniproject.hospital.controller.AuthenticationController;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAppointmentDoctor;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthentication;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthenticationRequest;
import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.repository.AppointmentRepository;
import id.co.indivara.miniproject.hospital.service.AppoinmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DoctorUserTests {
    @Autowired
    private AppoinmentController appoinmentController;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppoinmentService appoinmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationController authenticationController;
    ResponseEntity<ResponseAuthentication> response;
    String jwt;

    @Before
    public void setUp(){
        response = authenticationController.authenticate(new ResponseAuthenticationRequest("doctor","1234"));
        jwt = Objects.requireNonNull(response.getBody()).getToken();
    }

    @Test
    public void getAppointmentByDoctorIdTest() throws Exception{
        List<ResponseAppointmentDoctor> responseAppointmentDoctors = appoinmentService.viewAppointmentByDoctorId(1L);
        System.out.println(jwt);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/appointment/1")
                .header("Authorization", "Bearer " + jwt)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].appointment_id").isNotEmpty());
    }

    @Test
    public void getAppointmentByDocIdAndDateTest() throws Exception{
        List<ResponseAppointmentDoctor> responseAppointmentDoctors = appoinmentService.viewAppointmentByDocIdAndDate(1L,  Date.valueOf("2023-06-22"));
        System.out.println(jwt);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/appointment/1?date=2023-06-22")
                .header("Authorization", "Bearer " + jwt)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].appointment_id").isNotEmpty());
    }
}
