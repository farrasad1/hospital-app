package id.co.indivara.miniproject.hospital;

import id.co.indivara.miniproject.hospital.controller.AuthenticationController;
import id.co.indivara.miniproject.hospital.controller.DoctorController;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthentication;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthenticationRequest;
import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repository.DoctorRepository;
import id.co.indivara.miniproject.hospital.service.DoctorService;
import id.co.indivara.miniproject.hospital.service.SpecializationService;
import id.co.indivara.miniproject.hospital.utilize.MapperConvertion;
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

import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DoctorTests {
    @Autowired
    private DoctorController doctorController;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationController authenticationController;
    ResponseEntity<ResponseAuthentication> response;
    String jwt;

    @Before
    public void setUp(){
        response = authenticationController.authenticate((new ResponseAuthenticationRequest("admin", "1234")));
        jwt = Objects.requireNonNull(response.getBody()).getToken();
    }

    @Test
    public void saveData() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setNik(Long.parseLong("234352363253"));
        doctor.setRegistrationNumber("REG-0012");
        doctor.setFullName("Faiz Ahmad");
        doctor.setGender("Male");
        doctor.setDateOfBirth(LocalDate.parse("1998-03-22"));
        doctor.setPhoneNumber("08987654321");
        doctor.setEmail("efpyi@example.com");
        Address address = new Address();
        address.setStreet("Jl. Keluarga");
        address.setCity("Cirebon");
        address.setProvince("Jawa Barat");
        address.setZip(Long.parseLong("23345"));
        doctor.setAddress(address);
        Specialization specialization = specializationService.findById(2L);
        doctor.setSpecialization(specialization);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/hospital/doctors")
                        .header("Authorization", "Bearer " +jwt)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(doctor))
        ).andDo(print()).andExpect(status().isOk());
    }
}
