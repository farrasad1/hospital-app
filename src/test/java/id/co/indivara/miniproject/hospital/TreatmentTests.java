package id.co.indivara.miniproject.hospital;



import id.co.indivara.miniproject.hospital.controller.AuthenticationController;
import id.co.indivara.miniproject.hospital.controller.TreatmentController;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthentication;
import id.co.indivara.miniproject.hospital.dto.response.ResponseAuthenticationRequest;
import id.co.indivara.miniproject.hospital.dto.response.ResponseTreatmentList;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.repository.TreatmentRepository;
import id.co.indivara.miniproject.hospital.service.TreatmentService;

import id.co.indivara.miniproject.hospital.utilize.MapperConvertion;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

import java.util.List;
import java.util.Objects;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TreatmentTests {
    @Autowired
    private TreatmentController treatmentController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationController authenticationController;
    ResponseEntity<ResponseAuthentication> response;
    String jwt;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private TreatmentService treatmentService;

    @Before
    public void setUp(){
        response = authenticationController.authenticate(new ResponseAuthenticationRequest("admin","1234"));
        jwt = Objects.requireNonNull(response.getBody()).getToken();
    }

//    @Test
//    public void getAllTreatmentTest(){
//        List<ResponseTreatmentList> treatmentResponseEntity = treatmentController.viewTreatmentList();
//
//        Assertions.assertNotNull(treatmentResponseEntity);
//        Assertions.assertEquals(1L,treatmentResponseEntity.get(0).getTreatmentId());
//    }

    @Test
    public void getAllTreatmentTest() throws Exception {
        Treatment treatment = new Treatment();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hospital/treatments")
                        .header("Authorization", "Bearer " +jwt)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].treatment_id").value(1L));
    }

    @Test
    public void getTreatmentByIdTest() throws Exception {
        Treatment treatment = treatmentService.findById(1L);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/hospital/treatments/1")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " +jwt)
        ).andDo(print())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.treatmentId").isNotEmpty());
    }

    @Test
    public void saveTreatmentTest() throws Exception {
        Treatment treatment = new Treatment();
        treatment.setTreatmentCode("G-001");
        treatment.setTreatmentName("Tambal Gigi");
        treatment.setDescription("Giginya ditambal");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/hospital/treatments")
                        .header("Authorization", "Bearer " +jwt)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(treatment))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTreatmentTest() throws Exception {
        Treatment treatment = treatmentService.findById(4L);
        treatment.setTreatmentCode("G-002");
        treatment.setTreatmentName("Cabut Gigi");
        treatment.setDescription("Pencabutan gigi yang rusak dengan alat");
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/hospital/treatments/4")
                        .header("Authorization", "Bearer " +jwt)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(treatment))
        ).andDo(result -> {
            Treatment treatments = MapperConvertion.getData(result.getResponse().getContentAsString(), Treatment.class);
            Assertions.assertNotNull(treatments);
            Assertions.assertEquals(treatments.getTreatmentName(),treatment.getTreatmentName());
        })
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.treatmentId").isNotEmpty());
    }

    @Test
    public void deleteTrreatmentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/hospital/treatments/4")
                        .header("Authorization", "Bearer " +jwt)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }
}