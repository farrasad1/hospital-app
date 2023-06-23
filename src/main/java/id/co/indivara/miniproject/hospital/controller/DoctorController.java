package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    Doctor saveDoctor(@RequestBody Doctor doctor){
        return doctorService.saveData(doctor);
    }

    @GetMapping("/doctors")
    List<Doctor> fetchDoctorList() {
        return doctorService.findAll();
    }

    @GetMapping("/doctors/{id}")
        Doctor findById (@PathVariable("id") Long doctorId) {
        return doctorService.findById(doctorId);
    }

    @PatchMapping ("/doctors/{id}")
    Doctor updateDoctor(@RequestBody Doctor doctor, @PathVariable("id") Long doctorId){
        return doctorService.updateData(doctorId, doctor);
    }

    @DeleteMapping ("/doctors/{id}")
    public String deleteDoctor(@PathVariable("id") Long doctorId){
        doctorService.deletebyId(doctorId);
        return "Delete sukses";
    }
}
