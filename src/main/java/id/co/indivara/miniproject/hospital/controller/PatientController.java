package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    Patient savePatient(@RequestBody Patient patient){
        return patientService.saveData(patient);
    }

    @GetMapping("/patients")
    List<Patient> fetchPatientList() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{id}")
    Patient findById (@PathVariable("id") Long patientId) {
        return patientService.findById(patientId);
    }

    @PatchMapping ("/patients/{id}")
    Patient updatePatient(@RequestBody Patient patient, @PathVariable("id") Long patientId){
        return patientService.updateData(patientId, patient);
    }

    @DeleteMapping ("/patients/{id}")
    public String deletePatient(@PathVariable("id") Long patientId){
        patientService.deletebyId(patientId);
        return "Delete sukses";
    }
}
