package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseMedicalRecord;
import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import id.co.indivara.miniproject.hospital.service.MedicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class MedicalTreatmentController {
    @Autowired
    private MedicalTreatmentService medicalTreatmentService;

    @PostMapping("/medical/treatment")
    public ResponseEntity<MedicalTreatment> saveAppointment(@RequestBody MedicalTreatment medicalTreatment){
        return new ResponseEntity<>(medicalTreatmentService.saveData(medicalTreatment), HttpStatus.CREATED);
    }

    @GetMapping("/medical/treatment/history/{patientId}")
    public ResponseEntity<List<ResponseMedicalRecord>> getMedicalRecord(@PathVariable ("patientId") Long patientId){
        return new ResponseEntity<>(medicalTreatmentService.viewMedicalRecord(patientId), HttpStatus.OK);
    }
}
