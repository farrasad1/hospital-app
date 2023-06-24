package id.co.indivara.miniproject.hospital.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePatientList {
    @JsonProperty("patient_id")
    private Long patientId;
    @JsonProperty("nik")
    private Long nik;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("blood_type")
    private String bloodType;
}
