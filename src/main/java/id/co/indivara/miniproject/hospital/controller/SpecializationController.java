package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    @PostMapping("/specialization")
    Specialization saveSpecialization(@RequestBody Specialization specialization){
        return specializationService.saveData(specialization);
    }

    @GetMapping("/specialization")
    List<Specialization> fetchSpecializationList() {
        return specializationService.findAll();
    }

    @GetMapping("/specialization/{id}")
    Specialization findById (@PathVariable("id") Long specializationId) {
        return specializationService.findById(specializationId);
    }

    @PatchMapping ("/specialization/{id}")
    Specialization updateSpecialization(@RequestBody Specialization specialization, @PathVariable("id") Long specializationId){
        return specializationService.updateData(specializationId, specialization);
    }

    @DeleteMapping ("/specialization/{id}")
    public String deleteSpecialization(@PathVariable("id") Long specializationId){
        specializationService.deletebyId(specializationId);
        return "Delete sukses";
    }
}
