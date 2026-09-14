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
    public String getRecordId(){
        return  this.record_id;
    }
    public String getDoctorId(){
        return  this.doctor_id;
    }
    public String getPatientId(){
        return  this.patient_id;
    }
    public String getDiagnosisId(){
        return  this.diagnosis;
    }
    public String getTreatmentId(){
        return  this.treatment;
    }

    public void setDoctorId(String doctor_id){
        this.doctor_id = doctor_id;
    }
    public void setPatientId(String patient_id){
        this.patient_id = patient_id;
    }
    public void setRecordIdId(String record_id){
        this.record_id = record_id;
    }
    public void setTreatment(String treatment){
        this.treatment = treatment;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

}
