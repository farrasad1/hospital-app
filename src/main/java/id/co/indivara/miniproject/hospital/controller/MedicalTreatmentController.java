package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseMedicalRecord;
import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import id.co.indivara.miniproject.hospital.service.MedicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class MedicalTreatmentController {
    @Autowired
    private MedicalTreatmentService medicalTreatmentService;

    @PostMapping("/medical-treatment")
    MedicalTreatment saveAppointment(@RequestBody MedicalTreatment medicalTreatment){
        return medicalTreatmentService.saveData(medicalTreatment);
    }

    @GetMapping("/medical-treatment/history/{patientId}")
    public List<ResponseMedicalRecord> getMedicalRecord(@PathVariable ("patientId") Long patientId){
        return medicalTreatmentService.viewMedicalRecord(patientId);
    }
}
