package id.co.indivara.miniproject.hospital.repository;

import id.co.indivara.miniproject.hospital.entity.Treatment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends GenericRepository<Treatment>{
    @Query(value = "SELECT * FROM mst_treatments", nativeQuery = true)
    List<Treatment> viewTreatmentList();
}
