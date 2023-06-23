package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseAppointmentDoctor;
import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class AppoinmentController {
    @Autowired
    private AppoinmentService appoinmentService;

    @PostMapping("/appointment")
    Appointment saveAppointment(@RequestBody Appointment appointment){
        return appoinmentService.saveData(appointment);
    }

    @GetMapping("/appointment/{doctorId}")
    public List<ResponseAppointmentDoctor> getAppointmentByDoctorId(@PathVariable ("doctorId") Long doctorId){
        return appoinmentService.viewAppointmentByDoctorId(doctorId);
    }
}
