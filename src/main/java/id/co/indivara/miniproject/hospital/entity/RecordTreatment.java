package id.co.indivara.miniproject.hospital.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trx_record_treatment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_treatment_id")
    private Long recordTreatmentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "appointment_id",referencedColumnName ="appointment_id" )
    private Appointment appointment;
}
