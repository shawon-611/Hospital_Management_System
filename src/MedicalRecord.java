public class MedicalRecord {

    private String record_id;
    private String patient_id;
    private String doctor_id;
    private String diagnosis;
    private String treatment;

    MedicalRecord(String record_id, String patient_id, String doctor_id, String diagnosis, String treatment)
    {
        this.record_id =  record_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }
}
