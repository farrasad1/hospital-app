package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalTreatmentRepository extends GenericRepository<MedicalTreatment>{
    List<MedicalTreatment> findRecordTreatmentAppointmentPatientPatientId(Long patientId);
}
