package id.co.indivara.miniproject.hospital.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAppointmentDoctor {
    private Long appointmentId;
    private String symptoms;
    private Date date;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("blood_type")
    private String bloodType;
}
