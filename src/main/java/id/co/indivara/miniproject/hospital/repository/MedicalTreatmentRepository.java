package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.MedicalTreatment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalTreatmentRepository extends GenericRepository<MedicalTreatment>{
//    @Query(value = "SELECT * FROM MedicalTreatment a " +
//            "JOIN RecordTreatment b ON a.recordTreatment.recordTreatmentId = b.recordTreatmentId "+
//            "JOIN Appointment c ON b.appointment.appointmentId = c.appointmentId "+
//            "WHERE c.patient.patientId = :patientId")
//    List<MedicalTreatment> viewMedicalRecord(@Param("patientId") Long patientId);

    List<MedicalTreatment> findRecordTreatmentAppointmentPatientPatientId(Long patientId);
}
