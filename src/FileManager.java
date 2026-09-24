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


}


