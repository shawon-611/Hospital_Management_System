import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static void main(String[] args) {
        HospitalManagementSystem H = new HospitalManagementSystem();
        File patient_file = new File("Patient_File.txt");
        try{
            if(patient_file.createNewFile()){
                System.out.println("File created: " + patient_file.getName());
            }
            else{
                System.out.println("File already exists: " + patient_file.getName());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        if(!patient_file.exists()){
            return;
        }
        try {
            FileWriter patient_file_writer = new FileWriter(patient_file);
            for(Patient patient : H.getPatientList()) {
                patient_file_writer.write("Patient Name: "+ patient.getName());
                patient_file_writer.write("Patient ID: "+ patient.getID());
                patient_file_writer.write("Patient Phone Number: "+ patient.getPhone_Number());
                patient_file_writer.write("Patient Age: "+ patient.getAge());
                patient_file_writer.write("Patient Address: "+ patient.getAddress());
                patient_file_writer.write("Patient Diseases: "+ patient.getDiseases());
                patient_file_writer.write("Patient Blood Group: "+ patient.getBlood_Group());
            }
            patient_file_writer.close();

        } catch(IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        try{
            Scanner sc = new Scanner(patient_file);
            while(sc.hasNextLine()) {
                String patient_data = sc.nextLine();
                System.out.println(patient_data);
            }
            sc.close();
        } catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}
