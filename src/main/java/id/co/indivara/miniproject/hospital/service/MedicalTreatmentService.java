package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import id.co.indivara.miniproject.hospital.entity.RecordTreatment;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.repository.MedicalTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalTreatmentService extends GenericService<MedicalTreatment>{
    private final MedicalTreatmentRepository medicalTreatmentRepository;
    private final RecordTreatmentService recordTreatmentService;
    private final TreatmentService treatmentService;

    @Override
    public MedicalTreatment saveData(MedicalTreatment entity) {
        RecordTreatment recordTreatment = recordTreatmentService.findById(entity.getRecordTreatment().getRecordTreatmentId());
        Treatment treatment = treatmentService.findById(entity.getTreatment().getTreatmentId());
        entity.setRecordTreatment(recordTreatment);
        entity.setTreatment(treatment);
        return medicalTreatmentRepository.save(entity);
    }
}
