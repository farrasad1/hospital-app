package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends GenericRepository<Appointment>{
    @Query(value = "SELECT * " +
            "FROM trx_appointment a " +
            "JOIN mst_doctors d ON a.doctor_id = d.doctor_id " +
            "WHERE d.doctor_id = :doctorId", nativeQuery = true)
    List<Appointment> viewAppointmentByDoctorId(@Param("doctorId") Long doctorId);


}
