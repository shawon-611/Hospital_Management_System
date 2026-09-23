import java.io.*;
import java.util.Scanner;

public class FileManager {

    private HospitalManagementSystem H;
    public FileManager(HospitalManagementSystem H) {
        this.H = H;
    }
    public void PatientFile(){
        //Patient File Created
        //----------------------
        File patient_file = new File("Patient_File.txt");
        try{
            if(patient_file.createNewFile()){
                System.out.println("File created: " + patient_file.getName());
            }
            else{
                System.out.println("File already exists: " + patient_file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        //Patient File Writer
        //-------------------
        if(!patient_file.exists()){
            return;
        }
        try {
            FileWriter patient_file_writer = new FileWriter(patient_file);
            for(Patient patient : H.getPatientList()) {
                patient_file_writer.write("Patient Name: " + patient.getName() + "\n");
                patient_file_writer.write("Patient ID: " + patient.getID() + "\n");
                patient_file_writer.write("Patient Phone Number: " + patient.getPhone_Number() + "\n");
                patient_file_writer.write("Patient Age: " + patient.getAge() + "\n");
                patient_file_writer.write("Patient Address: " + patient.getAddress() + "\n");
                patient_file_writer.write("Patient Diseases: " + patient.getDiseases() + "\n");
                patient_file_writer.write("Patient Blood Group: " + patient.getBlood_Group() + "\n");
                patient_file_writer.write("======================================================");
                patient_file_writer.write("\n");
            }
            patient_file_writer.close();

        } catch(IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        //Patient File Read
        try{
            Scanner sc = new Scanner(patient_file);
            while(sc.hasNextLine()) {
                String patient_data = sc.nextLine();
                System.out.println(patient_data);
            }
            sc.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
