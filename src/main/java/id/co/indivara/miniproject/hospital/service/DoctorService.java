package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService extends GenericService<Doctor>{
    private final DoctorRepository doctorRepository;
    private final SpecializationService specializationService;
    private final AddressService addressService;

    @Override
    public Doctor saveData(Doctor entity) {
        Specialization specialization = specializationService.findById(entity.getSpecialization().getSpecializationId());
        Address address = entity.getAddress();
        entity.setSpecialization(specialization);
        entity.setAddress(address);
        return doctorRepository.save(entity);
    }
}
