package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalTreatmentRepository extends GenericRepository<MedicalTreatment>{
    @Query(value = "SELECT * FROM trx_medical_treatment a " +
            "JOIN trx_record_treatment b ON a.record_treatment_id = b.record_treatment_id "+
            "JOIN trx_appointment c ON b.appointment_id = c.appointment_id "+
            "WHERE c.patient_id = :patientId ", nativeQuery = true)
    List<MedicalTreatment> viewMedicalRecord(@Param("patientId") Long patientId);
}
