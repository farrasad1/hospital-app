package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.RecordTreatment;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.service.RecordTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
public class RecordTreatmentController {
    @Autowired
    private RecordTreatmentService recordTreatmentService;

    @PostMapping("/record-treatment")
    public ResponseEntity<RecordTreatment> saveRecordTreatment(@RequestBody RecordTreatment recordTreatment){
        return new ResponseEntity<>(recordTreatmentService.saveData(recordTreatment), HttpStatus.CREATED);
    }
}
