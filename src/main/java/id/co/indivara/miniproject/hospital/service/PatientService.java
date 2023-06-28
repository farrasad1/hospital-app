package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.dto.response.ResponsePatientList;
import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService extends GenericService<Patient>{
    private final PatientRepository patientRepository;
    private final AddressService addressService;

    @Override
    public Patient saveData(Patient entity) {
        Address address = entity.getAddress();
        entity.setAddress(address);
        return patientRepository.save(entity);
    }

    public List<ResponsePatientList> viewPatientList(){
        List<Patient> patients = patientRepository.findAll();
        List<ResponsePatientList> responsePatientList = patients.stream().map(
                patient -> new ResponsePatientList(patient.getPatientId(), patient.getNik(), patient.getGender(), patient.getBloodType())
        ).collect(Collectors.toList());
        return responsePatientList;
    }
}
