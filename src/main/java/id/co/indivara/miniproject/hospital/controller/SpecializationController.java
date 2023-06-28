package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    @PostMapping("/specialization")
    public ResponseEntity<Specialization> saveSpecialization(@RequestBody Specialization specialization){
        return new ResponseEntity<>(specializationService.saveData(specialization), HttpStatus.CREATED);
    }

    @GetMapping("/specialization")
    public ResponseEntity<List<Specialization>> fetchSpecializationList() {
        return new ResponseEntity<>(specializationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/specialization/{id}")
    public ResponseEntity<Specialization> findById (@PathVariable("id") Long specializationId) {
        return new ResponseEntity<>(specializationService.findById(specializationId), HttpStatus.OK);
    }

    @PutMapping ("/specialization/{id}")
    public ResponseEntity<Specialization> updateSpecialization(@RequestBody Specialization specialization, @PathVariable("id") Long specializationId){
        return new ResponseEntity<>(specializationService.updateData(specializationId, specialization), HttpStatus.OK);
    }

    @DeleteMapping ("/specialization/{id}")
    public ResponseEntity<HttpStatus> deleteSpecialization(@PathVariable("id") Long specializationId){
        specializationService.deletebyId(specializationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
