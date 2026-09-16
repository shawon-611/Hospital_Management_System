import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {

    private ArrayList<Patient> PatientList = new ArrayList<>();
    private ArrayList<Doctor> DoctorList = new ArrayList<>();
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public void PatientCRUD()
    {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while(choice != 5)
        {
            choice = -1;
            System.out.println("Enter what you want to do:");
            System.out.println("1. Create Patient\n" + "2. View Patient\n" + "3. Update Patient\n" + "4. Delete Patient\n" + "5. Exit\n");

            while(choice<1 || choice>5)
            {
                System.out.print("\nEnter your choice: ");
                try {
                    choice = scanner.nextInt();
                    System.out.println(" ");
                    if(choice<1 || choice>5)
                    {
                        System.out.println("Invalid choice, Please Enter between 1 and 4");
                    }
                }catch (Exception e){
                    System.out.println("Invalid Choice");
                    scanner.nextLine();
                    //choice = -1;
                }
            }

            switch (choice)
            {
                case 1:
                {
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
                }
                case 2:
                {
                    for(Patient patient : PatientList)
                    {
                        patient.displayInfo();
                    }
                }



        }



        }

    }
}