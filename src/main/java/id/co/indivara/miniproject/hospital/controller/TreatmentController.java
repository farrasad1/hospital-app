package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseTreatmentList;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @PostMapping("/treatments")
    public ResponseEntity<Treatment> saveTreatment(@RequestBody Treatment treatment) {
        return new ResponseEntity<>(treatmentService.saveData(treatment), HttpStatus.CREATED);
    }

    @GetMapping("/treatments")
    public ResponseEntity<List<ResponseTreatmentList>> viewTreatmentList() {
        return new ResponseEntity<>(treatmentService.viewTreatmentList(), HttpStatus.OK);
    }

    @GetMapping("/treatments/{id}")
    public ResponseEntity<Treatment> findById(@PathVariable("id") Long treatmentId) {
        return new ResponseEntity<>(treatmentService.findById(treatmentId), HttpStatus.OK);
    }

    @PutMapping("/treatments/{id}")
    public ResponseEntity<Treatment> updateTreatment(@RequestBody Treatment treatment, @PathVariable("id") Long treatmentId) {
        return new ResponseEntity<>(treatmentService.updateData(treatmentId, treatment), HttpStatus.OK);
    }

    @DeleteMapping("/treatments/{id}")
    public ResponseEntity<HttpStatus> deleteTreatment(@PathVariable("id") Long treatmentId) {
        treatmentService.deletebyId(treatmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
