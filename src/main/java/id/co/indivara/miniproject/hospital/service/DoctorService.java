package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.dto.response.ResponseDoctorList;
import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Specialization;
import id.co.indivara.miniproject.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService extends GenericService<Doctor> {
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

    public List<ResponseDoctorList> viewDoctorList() {
        List<Doctor> doctors = doctorRepository.viewDoctorList();
        List<ResponseDoctorList> responseDoctorList = doctors.stream().map(
                doctor -> new ResponseDoctorList(doctor.getDoctorId(), doctor.getRegistrationNumber(), doctor.getGender(), doctor.getSpecialization().getSpecializationId())
        ).collect(Collectors.toList());
        return responseDoctorList;
    }
}
