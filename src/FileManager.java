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


}


