package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.dto.response.ResponseMedicalRecord;
import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import id.co.indivara.miniproject.hospital.entity.RecordTreatment;
import id.co.indivara.miniproject.hospital.entity.Treatment;
import id.co.indivara.miniproject.hospital.repository.MedicalTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ResponseMedicalRecord> viewMedicalRecord(Long patientId){
        List<MedicalTreatment> medicalTreatments = medicalTreatmentRepository.findRecordTreatmentAppointmentPatientPatientId(patientId);
        List<ResponseMedicalRecord> responseMedicalRecord = medicalTreatments.stream().map(
                medicalTreatment -> new ResponseMedicalRecord(
                        medicalTreatment.getRecordTreatment().getRecordTreatmentId(),
                        medicalTreatment.getRecordTreatment().getAppointment().getDate(),
                        medicalTreatment.getRecordTreatment().getAppointment().getPatient().getFullName(),
                        medicalTreatment.getRecordTreatment().getAppointment().getDoctor().getFullName(),
                        medicalTreatment.getRecordTreatment().getAppointment().getSymptoms(),
                        medicalTreatment.getTreatment().getTreatmentName(),
                        medicalTreatment.getNote())
        ).collect(Collectors.toList());
        return responseMedicalRecord;
    }
}
