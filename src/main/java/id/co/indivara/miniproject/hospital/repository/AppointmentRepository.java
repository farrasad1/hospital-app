package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends GenericRepository<Appointment>{
    List<Appointment> findByDoctorDoctorId(Long doctorId);
    List<Appointment> findByDoctorDoctorIdAndDate(Long doctorId, Date date);
}
