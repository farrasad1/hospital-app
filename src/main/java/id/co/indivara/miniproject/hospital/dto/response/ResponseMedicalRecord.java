package id.co.indivara.miniproject.hospital.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMedicalRecord {
    @JsonProperty("record_treatment_id")
    private Long recordTreatmentId;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("patient_full_name")
    private String patientFullName;
    @JsonProperty("doctor_id")
    private String doctorFullName;
    @JsonProperty("symptoms")
    private String symptoms;
    @JsonProperty("treatment_name")
    private String treatmentName;
    @JsonProperty("note")
    private String note;
}
