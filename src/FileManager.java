import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private HospitalManagementSystem H;
    public FileManager(HospitalManagementSystem H) {
        this.H = H;
    }
    //Patient ArrayList to File
    public void PatientFile(){

        File patient_file = new File("Patient_File.txt");
        try{
            if(!patient_file.exists()){
                patient_file.createNewFile();
            }
            //Patient File Write
            FileWriter patient_fileWriter = new FileWriter(patient_file);

            for (Patient patient : H.getPatientList()) {

                patient_fileWriter.write("---Patient Details---\n");
                patient_fileWriter.write(patient.getName() + "\n");
                patient_fileWriter.write(patient.getID() + "\n");
                patient_fileWriter.write(patient.getPhone_Number() + "\n");
                patient_fileWriter.write(patient.getAge() + "\n");
                patient_fileWriter.write(patient.getAddress() + "\n");
                patient_fileWriter.write(patient.getDiseases() + "\n");
                patient_fileWriter.write(patient.getBlood_Group() + "\n");
                patient_fileWriter.write("======================================================\n");

            }
            patient_fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    //Load Patient Data ArrayList to File
    public void loadPatientFile() {
        File patient_file = new File("Patient_File.txt");
        try {
            if(!patient_file.exists()){
                return;
            }
            Scanner sc = new Scanner(patient_file);
            while(sc.hasNextLine()) {
                sc.nextLine();
                String name = sc.nextLine();
                int id = Integer.parseInt(sc.nextLine());   //Covert String to Integer
                String phone_number = sc.nextLine();
                int age = Integer.parseInt(sc.nextLine());
                String address = sc.nextLine();
                String diseases  = sc.nextLine();
                String blood_group = sc.nextLine();
                sc.nextLine();

                Patient patient = new Patient(name, id, phone_number, age, address, diseases, blood_group);
                H.getPatientList().add(patient);
            }
            sc.close();

        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Doctor ArrayList to File
    public void DoctorFile() {
        File doctor_file = new File("Doctor_File.txt");
        try{
            if(!doctor_file.exists()){
                doctor_file.createNewFile();
            }
            //Doctor File Write
            FileWriter doctor_fileWriter = new FileWriter(doctor_file);

            for (Doctor doctor : H.getDoctorList()) {

                doctor_fileWriter.write("---Doctor Details---\n");
                doctor_fileWriter.write(doctor.getName() + "\n");
                doctor_fileWriter.write(doctor.getID() + "\n");
                doctor_fileWriter.write(doctor.getPhone_Number() + "\n");
                doctor_fileWriter.write(doctor.getSpecialization() + "\n");
                doctor_fileWriter.write(doctor.getQualification() + "\n");
                doctor_fileWriter.write(doctor.getSalary() + "\n");
                doctor_fileWriter.write("======================================================\n");

            }
            doctor_fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Load Doctor Data ArrayList to File
    public void loadDoctorFile() {
        File doctor_file = new File("Doctor_File.txt");
        try {
            if(!doctor_file.exists()){
                return;
            }
            Scanner sc = new Scanner(doctor_file);
            while(sc.hasNextLine()) {
                sc.nextLine();
                String name = sc.nextLine();
                int id = Integer.parseInt(sc.nextLine());   //Covert String to Integer
                String phone_number = sc.nextLine();
                String specialization = sc.nextLine();
                String qualification  = sc.nextLine();
                int salary = Integer.parseInt(sc.nextLine());
                sc.nextLine();

                Doctor doctor = new Doctor(name, id, phone_number, specialization, qualification, salary);
                H.getDoctorList().add(doctor);
            }
            sc.close();

        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Appointment ArrayList to File
    public void AppointmentFile() {
        File appointment_file = new File("Appointment_File.txt");
        try{
            if(!appointment_file.exists()){
                appointment_file.createNewFile();
            }
            //Doctor File Write
            FileWriter appointment_fileWriter = new FileWriter(appointment_file);

            for (Appointment appointment : H.getAppointmentList()) {

                appointment_fileWriter.write("---Appointment Details---\n");
                appointment_fileWriter.write(appointment.getAppointment_id() + "\n");
                appointment_fileWriter.write(appointment.getPatient_id() + "\n");
                appointment_fileWriter.write(appointment.getDoctor_id() + "\n");
                appointment_fileWriter.write(appointment.getDate() + "\n");
                appointment_fileWriter.write(appointment.getTime() + "\n");
                appointment_fileWriter.write(appointment.getReason() + "\n");
                appointment_fileWriter.write("======================================================\n");

            }
            appointment_fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Load Doctor Data ArrayList to File
    public void loadAppointmentFile() {
        File appointment_file = new File("Appointment_File.txt");
        try {
            if(!appointment_file.exists()){
                return;
            }
            Scanner sc = new Scanner(appointment_file);
            while(sc.hasNextLine()) {
                sc.nextLine();
                int appointment_id = Integer.parseInt(sc.nextLine());   //Covert String to Integer
                int patient_id = Integer.parseInt(sc.nextLine());
                int doctor_id = Integer.parseInt(sc.nextLine());
                String date = sc.nextLine();
                String time = sc.nextLine();
                String reason  = sc.nextLine();
                sc.nextLine();

                Appointment appointment = new Appointment(appointment_id, patient_id, doctor_id, date, time, reason);
                H.getAppointmentList().add(appointment);
            }
            sc.close();

        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //MedicalBill ArrayList to File
    public void MedicalBillFile() {
        File medicalBill_file = new File("MedicalBill_File.txt");
        try{
            if(!medicalBill_file.exists()){
                medicalBill_file.createNewFile();
            }
            //Medical Bill File Write
            FileWriter medicalBill_fileWriter = new FileWriter(medicalBill_file);

            for (MedicalBill medicalBill : H.getMedicalBillList()) {

                medicalBill_fileWriter.write("---Medical Bill Details---\n");
                medicalBill_fileWriter.write(medicalBill.getBill_id() + "\n");
                medicalBill_fileWriter.write(medicalBill.getPatient_id() + "\n");
                medicalBill_fileWriter.write(medicalBill.getAmount() + "\n");
                medicalBill_fileWriter.write(medicalBill.getBill_date() + "\n");
                medicalBill_fileWriter.write(medicalBill.getPayment_status() + "\n");
                medicalBill_fileWriter.write("======================================================\n");

            }
            medicalBill_fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}


