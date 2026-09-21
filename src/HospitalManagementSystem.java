import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {

    private ArrayList<Patient> PatientList = new ArrayList<>();
    private ArrayList<Doctor> DoctorList = new ArrayList<>();
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public void MainMenu() {

        int select_menu = -1;
        while(select_menu != 4) {
            select_menu = -1;
            Scanner input = new Scanner(System.in);
            System.out.println("Please choose from the following options:");
            System.out.println("1. PatientCRUD\n" + "2. DoctorCRUD\n" + "3. AppointmentCRUD\n" + "4. Exit\n");
            while(select_menu<1 || select_menu>4)
            {
                System.out.print("\nEnter Menu Choice: ");
                try {
                    select_menu = input.nextInt();
                    System.out.println(" ");
                    if(select_menu<1 || select_menu>5)
                    {
                        System.out.println("Invalid choice, Please Enter between 1 and 4");
                    }
                }catch (Exception e){
                    System.out.println("Invalid Choice");
                    input.nextLine();
                }
            }
            switch(select_menu)
            {
                case 1: {
                    //PatientCRUD
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
                            }
                        }
                        switch (choice)
                        {
                            case 1:
                            {
                                try{
                                    System.out.print("Enter Patient Name: ");
                                    String name = scanner.next();
                                    System.out.print("Enter Patient ID: ");
                                    int id = scanner.nextInt();
                                    for(Patient patient : PatientList) {
                                        if(patient.getID() == id) {
                                            throw new CustomException("Patient ID already exists");
                                        }
                                    }
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
                                    System.out.println("\nPatient added successfully\n");
                                    break;
                                } catch(CustomException e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                            case 2:
                            {
                                for(Patient patient : PatientList)
                                {
                                    patient.displayInfo();
                                }
                                break;
                            }
                            case 3:
                            {
                                try{
                                    System.out.println("Search Patient ID:  ");
                                    int search_id = scanner.nextInt();

                                    for(Patient patient : PatientList)
                                    {
                                        if(patient.getID() == search_id)
                                        {
                                            System.out.print("Enter New Name: ");
                                            String new_name = scanner.next();
                                            patient.setName(new_name);
                                            System.out.print("Enter New Phone Number: ");
                                            String new_phone_number = scanner.next();
                                            patient.setPhone_Number(new_phone_number);
                                            System.out.print("Enter New Age: ");
                                            int new_age = scanner.nextInt();
                                            patient.setAge(new_age);
                                            System.out.print("Enter New Address: ");
                                            String new_address = scanner.next();
                                            patient.setAddress(new_address);
                                            System.out.print("Enter New Diseases: ");
                                            String new_diseases = scanner.next();
                                            patient.setDiseases(new_diseases);
                                            System.out.print("Enter New Blood Group: ");
                                            String new_blood_group = scanner.next();
                                            patient.setBlood_group(new_blood_group);
                                        }
                                        else {
                                            System.out.println("Patient not found");
                                            break;
                                        }
                                    }
                                } catch(Exception e)
                                {
                                    System.out.println("Invalid Input, Please try again");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4:
                            {
                                try{
                                    System.out.println("Search Patient ID:  ");
                                    int search_id = scanner.nextInt();

                                    for(Patient patient : PatientList)
                                    {
                                        if(patient.getID() == search_id)
                                        {
                                            PatientList.remove(patient);
                                            System.out.println("Patient deleted successfully\n");
                                        }
                                        else {
                                            System.out.println("Patient not found");
                                            break;
                                        }
                                    }
                                }catch(Exception e)
                                {
                                    System.out.println("Invalid Input, Please try again");
                                }
                                break;
                            }
                            case 5:
                            {
                                System.out.println("Exiting Hospital Management System" + "\nThanks for using the management system");
                                break;
                            }
                            default:
                            {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    //DoctorCRUD
                    Scanner scanner = new Scanner(System.in);
                    int choice = -1;
                    while(choice != 5)
                    {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Assign Doctor\n" + "2. View Doctor\n" + "3. Update Doctor\n" + "4. Delete Doctor\n" + "5. Exit\n");

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
                            }
                        }
                        switch (choice)
                        {
                            case 1:
                            {
                                System.out.print("Enter Doctor Name: ");
                                String name = scanner.next();
                                System.out.print("Enter Doctor ID: ");
                                int id = scanner.nextInt();
                                System.out.print("Enter Doctor Phone Number: ");
                                String phone_number = scanner.next();
                                System.out.print("Specialization of Doctor: ");
                                String specialization = scanner.next();
                                System.out.print("Qualification of Doctor: ");
                                String qualification = scanner.next();
                                System.out.print("Doctor's Salary: ");
                                int salary = scanner.nextInt();

                                Doctor doctor = new Doctor(name, id, phone_number, specialization, qualification, salary);
                                DoctorList.add(doctor);
                                System.out.println("\nDoctor assigned successfully\n");
                                break;
                            }
                            case 2:
                            {
                                for(Doctor doctor : DoctorList)
                                {
                                    doctor.displayInfo();
                                }
                                break;
                            }
                            case 3:
                            {
                                try{
                                    System.out.println("Search Doctor ID:  ");
                                    int search_id = scanner.nextInt();

                                    for(Doctor doctor : DoctorList)
                                    {
                                        if(doctor.getID() == search_id)
                                        {
                                            System.out.print("Enter New Name: ");
                                            String new_name = scanner.next();
                                            doctor.setName(new_name);
                                            System.out.print("Enter New Phone Number: ");
                                            String new_phone_number = scanner.next();
                                            doctor.setPhone_Number(new_phone_number);
                                            System.out.print("Enter new Doctor's Specialization: ");
                                            String new_specialization = scanner.next();
                                            doctor.setSpecialization(new_specialization);
                                            System.out.print("Enter new Doctor's Qualification: ");
                                            String new_qualification = scanner.next();
                                            doctor.setQualification(new_qualification);
                                            System.out.print("Enter New Doctor's Salary: ");
                                            int new_salary = scanner.nextInt();
                                            doctor.setSalary(new_salary);
                                        }
                                        else {
                                            System.out.println("Doctor not found");
                                            break;
                                        }
                                    }
                                } catch(Exception e)
                                {
                                    System.out.println("Invalid Input, Please try again");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4:
                            {
                                try{
                                    System.out.println("Search Doctor ID:  ");
                                    int search_id = scanner.nextInt();

                                    for(Doctor doctor : DoctorList)
                                    {
                                        if(doctor.getID() == search_id)
                                        {
                                            DoctorList.remove(doctor);
                                            System.out.println("Assigned doctor deleted successfully\n");
                                        }
                                        else {
                                            System.out.println("Doctor not found");
                                            break;
                                        }
                                    }
                                }catch(Exception e)
                                {
                                    System.out.println("Invalid Input, Please try again");
                                    break;
                                }
                            }
                            case 5:
                            {
                                System.out.println("Exiting Hospital Management System" + "\nThanks for using the management system");
                                break;
                            }
                            default:
                            {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    //AppointmentCRUD
                    Scanner scanner = new Scanner(System.in);
                    int choice = -1;
                    while(choice!=5)
                    {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Create Appointment\n" + "2. View Appointment\n" + "3. Update Appointment\n" + "4. Delete Appointment\n" + "5. Exit\n");

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
                            }
                        }
                        switch(choice)
                        {
                            case 1:
                            {
                                System.out.print("Enter Appointment ID: ");
                                int appointment_id = scanner.nextInt();
                                System.out.print("Enter Patient ID: ");
                                int patient_id = scanner.nextInt();
                                System.out.print("Enter Doctor ID: ");
                                int doctor_id = scanner.nextInt();
                                System.out.print("Appointment Date: ");
                                String date = scanner.next();
                                System.out.print("Appointment Time: ");
                                String time = scanner.next();
                                System.out.print("Appointment Reason: ");
                                String reason = scanner.next();

                                Appointment appointment = new Appointment(appointment_id, patient_id, doctor_id, date, time, reason);
                                AppointmentList.add(appointment);
                                System.out.println("\nAppointment Created Successfully\n");
                                break;
                            }
                            case 2:
                            {
                                for(Appointment appointment : AppointmentList)
                                {
                                    appointment.displayInfo();
                                }
                                break;
                            }
                            case 3:
                            {
                                try{
                                    System.out.println("Search Appointment ID: ");
                                    int search_id = scanner.nextInt();

                                    for(Appointment appointment : AppointmentList)
                                    {
                                        if(appointment.getAppointment_id() == search_id)
                                        {
                                            System.out.print("Enter Patient ID: ");
                                            int new_patient_id = scanner.nextInt();
                                            appointment.setPatient_id(new_patient_id);
                                            System.out.print("Enter Doctor ID: ");
                                            int new_doctor_id = scanner.nextInt();
                                            appointment.setDoctor_id(new_doctor_id);
                                            System.out.print("Appointment Date: ");
                                            String new_date = scanner.next();
                                            appointment.setDate(new_date);
                                            System.out.print("Appointment Time: ");
                                            String new_time = scanner.next();
                                            appointment.setTime(new_time);
                                            System.out.print("Appointment Reason: ");
                                            String new_reason = scanner.next();
                                            appointment.setReason(new_reason);
                                        }
                                        else {
                                            System.out.println("Appointment not found");
                                            break;
                                        }
                                    }
                                }catch(Exception e) {
                                    System.out.println("Invalid Input, Please try again");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4:
                            {
                                try{
                                    System.out.println("Search Appointment ID: ");
                                    int search_id =  scanner.nextInt();
                                    for(Appointment appointment : AppointmentList)
                                    {
                                        if(appointment.getAppointment_id() == search_id)
                                        {
                                            AppointmentList.remove(appointment);
                                        }
                                        else {
                                            System.out.println("Appointment not found");
                                            break;
                                        }
                                    }
                                } catch(Exception e) {
                                    System.out.println("Invalid Input, Please try again");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5:
                            {
                                System.out.println("Exiting Hospital Management System" + "\nThanks for using the management system");
                                break;
                            }
                            default:
                            {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Exiting Hospital Management System" + "\nThanks for using the management system");
                    break;
                }
                default: {
                    System.out.println("\nInvalid Menu Choice, Please try again\n");
                }
            }
        }
    }
}