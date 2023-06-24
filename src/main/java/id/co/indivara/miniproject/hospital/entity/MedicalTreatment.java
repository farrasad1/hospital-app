package id.co.indivara.miniproject.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trx_medical_treatment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_treatment_id")
    private Long medicalTreatmentId;

    @ManyToOne
    @JoinColumn(name = "record_treatment_id")
    private RecordTreatment recordTreatment;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    private String note;
}
