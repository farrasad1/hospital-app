package id.co.indivara.miniproject.hospital.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDoctorList {
    @JsonProperty("doctor_id")
    private Long doctorId;
    @JsonProperty("registration_number")
    private String registrationNumber;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("specialization_id")
    private Long specializationId;
}
