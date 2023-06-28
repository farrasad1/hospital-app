package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseDoctorList;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor){
        return new ResponseEntity<>( doctorService.saveData(doctor),HttpStatus.CREATED);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<ResponseDoctorList>> getDoctorList() {
        return new ResponseEntity<>(doctorService.viewDoctorList(), HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> findById (@PathVariable("id") Long doctorId) {
        return new ResponseEntity<>(doctorService.findById(doctorId),HttpStatus.OK);
    }

    @PutMapping ("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable("id") Long doctorId){
        return new ResponseEntity<>(doctorService.updateData(doctorId, doctor), HttpStatus.OK);
    }

    @DeleteMapping ("/doctors/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("id") Long doctorId){
        doctorService.deletebyId(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
