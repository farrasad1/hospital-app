package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.dto.response.ResponseAppointmentDoctor;
import id.co.indivara.miniproject.hospital.entity.Appointment;
import id.co.indivara.miniproject.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    //USER ADMIN
    //CREATE APPOINTMENT
    @PostMapping("/appointment")
    Appointment saveAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveData(appointment);
    }

    //USER ADMIN
    //UPDATE APPOINTMENT
    @PutMapping("/appointment/{appointmentId}")
    Appointment updateAppointment(@RequestBody Appointment appointment, @PathVariable("appointmentId") Long appointmentId) {
        return appointmentService.updateData(appointmentId, appointment);
    }

    //USER DOCTOR & ADMIN
    //SEE APPOINTMENT BY ID
    @GetMapping("/appointment/{doctorId}")
    public List<ResponseAppointmentDoctor> getAppointmentByDoctorId(@PathVariable("doctorId") Long doctorId) {
        return appointmentService.viewAppointmentByDoctorId(doctorId);
    }

    //USER DOCTOR & ADMIN
    //SEE APPOINTMENT BY ID AND DATE
    @GetMapping("/appointment/{doctorId}/{date}")
    public List<ResponseAppointmentDoctor> getAppointmentByDocIdAndDate(
            @PathVariable("doctorId") Long doctorId,
            @PathVariable("date") Date date) {
        return appointmentService.viewAppointmentByDocIdAndDate(doctorId, date);
    }
}
