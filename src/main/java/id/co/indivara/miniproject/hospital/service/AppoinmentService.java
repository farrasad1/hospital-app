package id.co.indivara.miniproject.hospital.service;

import id.co.indivara.miniproject.hospital.dto.response.ResponseAppointmentDoctor;
import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.entity.Doctor;
import id.co.indivara.miniproject.hospital.entity.Patient;
import id.co.indivara.miniproject.hospital.entity.RecordTreatment;
import id.co.indivara.miniproject.hospital.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppoinmentService extends GenericService<Appointment> {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Override
    public Appointment saveData(Appointment appointment) {
        RecordTreatment recordTreatment = new RecordTreatment();
        Doctor doctor = doctorService.findById(appointment.getDoctor().getDoctorId());
        Patient patient = patientService.findById(appointment.getPatient().getPatientId());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        recordTreatment.setAppointment(appointment);
        appointment.setRecordTreatment(recordTreatment);
        return appointmentRepository.save(appointment);
    }

    public List<ResponseAppointmentDoctor> viewAppointmentByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.viewAppointmentByDoctorId(doctorId);
        List<ResponseAppointmentDoctor> responseAppointmentDoctors = appointments.stream().map(appointment -> new ResponseAppointmentDoctor(appointment.getAppointmentId(), appointment.getDate(), appointment.getPatient().getFullName(), appointment.getPatient().getBloodType(), appointment.getSymptoms())
        ).collect(Collectors.toList());
        return responseAppointmentDoctors;
    }

    public List<ResponseAppointmentDoctor> viewAppointmentByDocIdAndDate(Long doctorId, Date date) {
        List<Appointment> appointments = appointmentRepository.viewAppointmentByDocIdAndDate(doctorId, date);
        List<ResponseAppointmentDoctor> responseAppointmentDoctors = appointments.stream().map(
                appointment -> new ResponseAppointmentDoctor(appointment.getAppointmentId(), appointment.getDate(), appointment.getPatient().getFullName(), appointment.getPatient().getBloodType(), appointment.getSymptoms())
        ).collect(Collectors.toList());
        return responseAppointmentDoctors;
    }
}
