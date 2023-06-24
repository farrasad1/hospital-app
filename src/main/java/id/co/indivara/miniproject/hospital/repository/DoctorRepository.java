package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends GenericRepository<Doctor>{
    @Query(value = "SELECT * FROM mst_doctors", nativeQuery = true)
    List<Doctor> viewDoctorList();
}
