import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static ArrayList<Patient> PatientList = new ArrayList<>();
    private ArrayList<Doctor> DoctorList = new ArrayList<>();
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public static void PatientCRUD()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create Patient\n" + "2. View Patient\n" + "3. Update Patient\n" + "4. Delete Patient\n");

        System.out.print("Enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
        }catch (Exception e){
           // System.out.println(e.getCause());
        }

        switch (choice){
            case 1:
                System.out.print("Enter Patient Name: ");
                String name = scanner.next();
                System.out.print("Enter Patient ID: ");
                int id = scanner.nextInt();
                System.out.print("Enter Patient Phone Number: ");
                String phone_number = scanner.next();
                System.out.print("Enter Patient Age: ");
                int age = scanner.nextInt();
                System.out.print("Enter Patient Address: ");
                String address = scanner.next();
                System.out.print("Enter Patient Diseases: ");
                String diseases = scanner.next();
                System.out.print("Enter Patient Blood Group: ");
                String blood_group = scanner.next();

                Patient patient = new Patient(name, id, phone_number, age, address, diseases, blood_group);
                PatientList.add(patient);
                System.out.println("Patient added successfully\n");
                break;
            default:
                System.out.println("Invalid selection! Select a valid option.");
        }

    }
}
