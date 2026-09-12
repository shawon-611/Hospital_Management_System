public class Appointment {

    private int appointment_id;
    private int patient_id;
    private int doctor_id;
    private String date;
    private String time;
    private String reason;

    public Appointment(int appointment_id, int patient_id, int doctor_id, String date, String time, String reason)
    {
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }
    public int getAppointment_id()
    {
        return appointment_id;
    }
    public int getPatient_id()
    {
        return patient_id;
    }
    public int getDoctor_id()
    {
        return doctor_id;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    public String getReason()
    {
        return reason;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void displayInfo()
    {
        System.out.println("---Appointment Details---\n");
        System.out.println("Appointment ID: "+ appointment_id);
        System.out.println("Patient ID: "+ patient_id);
        System.out.println("Doctor ID: "+ doctor_id);
        System.out.println("Appointment Date: "+ date);
        System.out.println("Appointment Time: "+ time);
        System.out.println("Reason: "+ reason);
        System.out.println(" ");
    }
}
