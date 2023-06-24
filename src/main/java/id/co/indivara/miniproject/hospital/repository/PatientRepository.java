package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends GenericRepository<Patient>{
    @Query(value = "SELECT * FROM mst_patients", nativeQuery = true)
    List<Patient> viewPatientList();
}
