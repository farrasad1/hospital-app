package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @PostMapping("/treatments")
    Treatment saveTreatment(@RequestBody Treatment treatment){
        return treatmentService.saveData(treatment);
    }

    @GetMapping("/treatments")
    List<Treatment> fetchTreatmentList() {
        return treatmentService.findAll();
    }

    @GetMapping("/treatments/{id}")
    Treatment findById (@PathVariable("id") Long treatmentId) {
        return treatmentService.findById(treatmentId);
    }

    @PatchMapping ("/treatments/{id}")
    Treatment updateTreatment(@RequestBody Treatment treatment, @PathVariable("id") Long treatmentId){
        return treatmentService.updateData(treatmentId, treatment);
    }

    @DeleteMapping ("/treatments/{id}")
    public String deleteTreatment(@PathVariable("id") Long treatmentId){
        treatmentService.deletebyId(treatmentId);
        return "Delete sukses";
    }
}
