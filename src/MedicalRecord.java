public class MedicalRecord {

    private int record_id;
    private int patient_id;
    private int doctor_id;
    private String diagnosis;
    private String treatment;

    MedicalRecord(int record_id, int patient_id, int doctor_id, String diagnosis, String treatment)
    {
        this.record_id =  record_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }
    public int getRecordId(){
        return  this.record_id;
    }
    public int getPatient_id(){
        return  this.patient_id;
    }
    public int getDoctor_id(){
        return  this.doctor_id;
    }

    public String getDiagnosisId(){
        return  this.diagnosis;
    }
    public String getTreatmentId(){
        return  this.treatment;
    }

    public void setPatient_id(int patient_id){
        this.patient_id = patient_id;
    }
    public void setDoctor_id(int doctor_id){
        this.doctor_id = doctor_id;
    }
    public void setRecordIdId(int record_id){
        this.record_id = record_id;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    public void setTreatment(String treatment){
        this.treatment = treatment;
    }

    public void displayInfo() {
        System.out.println("---Medical Records---\n");
        System.out.println("Record ID: " + record_id);
        System.out.println("Patient ID: " + patient_id);
        System.out.println("Doctor ID: " + doctor_id);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Treatment: " + treatment);
        System.out.println(" \n");
    }
}
