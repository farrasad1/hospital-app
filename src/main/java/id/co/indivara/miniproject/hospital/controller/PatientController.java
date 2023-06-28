package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponsePatientList;
import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.saveData(patient), HttpStatus.CREATED);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<ResponsePatientList>> getPatientList(){
        return new ResponseEntity<>(patientService.viewPatientList(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity <Patient> findById (@PathVariable("id") Long patientId) {
        return new ResponseEntity<>(patientService.findById(patientId), HttpStatus.OK);
    }

    @PutMapping ("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable("id") Long patientId){
        return new ResponseEntity<>(patientService.updateData(patientId, patient), HttpStatus.OK);
    }

    @DeleteMapping ("/patients/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") Long patientId){
        patientService.deletebyId(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
