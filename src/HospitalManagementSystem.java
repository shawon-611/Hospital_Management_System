import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {
    private ArrayList<Patient> PatientList = new ArrayList<>();
    private ArrayList<Doctor> DoctorList = new ArrayList<>();
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();
    private ArrayList<MedicalRecord> MedicalRecordList = new ArrayList<>();
    private ArrayList<MedicalBill> MedicalBillList = new ArrayList<>();

    public ArrayList<Patient> getPatientList() {
        return PatientList;
    }

    public ArrayList<Doctor> getDoctorList() {
        return DoctorList;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return AppointmentList;
    }

    public void MainMenu() {
        //MainMenu
        int select_menu = -1;
        while (select_menu != 6) {
            select_menu = -1;
            Scanner input = new Scanner(System.in);
            System.out.println("Please choose from the following options:");
            System.out.println("1. PatientCRUD\n" + "2. DoctorCRUD\n" + "3. AppointmentCRUD\n" + "4. Medical Record CRUD\n" + "5. Medical Bill CRUD\n" + "6. Exit\n");
            while (select_menu < 1 || select_menu > 6) {
                System.out.print("\nEnter Menu Choice: ");
                try {
                    select_menu = input.nextInt();
                    System.out.println(" ");
                    if (select_menu < 1 || select_menu > 6) {
                        System.out.println("\nInvalid choice, Please Enter between 1 and 6\n");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Choice\n");
                    input.nextLine();
                }
            }
            switch (select_menu) {
                case 1: {
                    //PatientCRUD
                    Scanner scanner = new Scanner(System.in);

                    int choice = -1;
                    while (choice != 5) {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Create Patient\n" + "2. View Patient\n" + "3. Update Patient\n" + "4. Delete Patient\n" + "5. Exit From PatientCRUD\n");

                        while (choice < 1 || choice > 5) {
                            System.out.print("\nEnter your choice: ");
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(" ");
                                if (choice < 1 || choice > 5) {
                                    System.out.println("\nInvalid choice, Please Enter between 1 and 5\n");
                                }
                            } catch (Exception e) {
                                System.out.println("\nInvalid Choice");
                                scanner.nextLine();
                            }
                        }
                        switch (choice) {
                            case 1: {
                                try {
                                    System.out.print("Enter Patient Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Patient ID: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    for (Patient patient : PatientList) {
                                        if (patient.getID() == id) {
                                            throw new CustomException("\nPatient ID already exists\n");
                                        }
                                    }
                                    System.out.print("Enter Patient Phone Number: ");
                                    String phone_number = scanner.nextLine();
                                    System.out.print("Enter Patient Age: ");
                                    int age = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Patient Address: ");
                                    String address = scanner.nextLine();
                                    System.out.print("Enter Patient Diseases: ");
                                    String diseases = scanner.nextLine();
                                    System.out.print("Enter Patient Blood Group: ");
                                    String blood_group = scanner.nextLine();

                                    Patient patient = new Patient(name, id, phone_number, age, address, diseases, blood_group);
                                    PatientList.add(patient);
                                    System.out.println("\nPatient added successfully\n");
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                for (Patient patient : PatientList) {
                                    patient.displayInfo();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    System.out.println("Search Patient ID:  ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;

                                    for (Patient patient : PatientList) {
                                        if (patient.getID() == search_id) {
                                            found = true;
                                            System.out.print("Enter New Name: ");
                                            String new_name = scanner.nextLine();
                                            patient.setName(new_name);
                                            System.out.print("Enter New Phone Number: ");
                                            String new_phone_number = scanner.nextLine();
                                            patient.setPhone_Number(new_phone_number);
                                            System.out.print("Enter New Age: ");
                                            int new_age = scanner.nextInt();
                                            scanner.nextLine();
                                            patient.setAge(new_age);
                                            System.out.print("Enter New Address: ");
                                            String new_address = scanner.nextLine();
                                            patient.setAddress(new_address);
                                            System.out.print("Enter New Diseases: ");
                                            String new_diseases = scanner.nextLine();
                                            patient.setDiseases(new_diseases);
                                            System.out.print("Enter New Blood Group: ");
                                            String new_blood_group = scanner.nextLine();
                                            patient.setBlood_group(new_blood_group);
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nPatient not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    System.out.println("Search Patient ID:  ");
                                    int search_id = scanner.nextInt();
                                    boolean found = false;

                                    for (Patient patient : PatientList) {
                                        if (patient.getID() == search_id) {
                                            found = true;
                                            PatientList.remove(patient);
                                            System.out.println("\nPatient deleted successfully\n");
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nPatient not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nExiting From PatientCRUD Successfully\n");
                                break;
                            }
                            default: {
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
                    while (choice != 5) {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Assign Doctor\n" + "2. View Doctor\n" + "3. Update Doctor\n" + "4. Delete Doctor\n" + "5. Exit From DoctorCRUD\n");

                        while (choice < 1 || choice > 5) {
                            System.out.print("\nEnter your choice: ");
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(" ");
                                if (choice < 1 || choice > 5) {
                                    System.out.println("\nInvalid choice, Please Enter between 1 and 5\n");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid Choice");
                                scanner.nextLine();
                            }
                        }
                        switch (choice) {
                            case 1: {
                                try {
                                    System.out.print("Enter Doctor Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Doctor ID: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    for (Doctor doctor : DoctorList) {
                                        if (doctor.getID() == id) {
                                            throw new CustomException("\nDoctor ID already exists\n");
                                        }
                                    }
                                    System.out.print("Enter Doctor Phone Number: ");
                                    String phone_number = scanner.nextLine();
                                    System.out.print("Specialization of Doctor: ");
                                    String specialization = scanner.nextLine();
                                    System.out.print("Qualification of Doctor: ");
                                    String qualification = scanner.nextLine();
                                    System.out.print("Doctor's Salary: ");
                                    int salary = scanner.nextInt();
                                    scanner.nextLine();

                                    Doctor doctor = new Doctor(name, id, phone_number, specialization, qualification, salary);
                                    DoctorList.add(doctor);
                                    System.out.println("\nDoctor assigned successfully\n");
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                for (Doctor doctor : DoctorList) {
                                    doctor.displayInfo();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    System.out.println("Search Doctor ID:  ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;

                                    for (Doctor doctor : DoctorList) {
                                        if (doctor.getID() == search_id) {
                                            found = true;
                                            System.out.print("Enter New Name: ");
                                            String new_name = scanner.nextLine();
                                            doctor.setName(new_name);
                                            System.out.print("Enter New Phone Number: ");
                                            String new_phone_number = scanner.nextLine();
                                            doctor.setPhone_Number(new_phone_number);
                                            System.out.print("Enter new Doctor's Specialization: ");
                                            String new_specialization = scanner.nextLine();
                                            doctor.setSpecialization(new_specialization);
                                            System.out.print("Enter new Doctor's Qualification: ");
                                            String new_qualification = scanner.nextLine();
                                            doctor.setQualification(new_qualification);
                                            System.out.print("Enter New Doctor's Salary: ");
                                            int new_salary = scanner.nextInt();
                                            scanner.nextLine();
                                            doctor.setSalary(new_salary);
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nDoctor not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    System.out.println("Search Doctor ID:  ");
                                    int search_id = scanner.nextInt();
                                    boolean found = false;

                                    for (Doctor doctor : DoctorList) {
                                        if (doctor.getID() == search_id) {
                                            found = true;
                                            DoctorList.remove(doctor);
                                            System.out.println("\nAssigned doctor deleted successfully\n");
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nDoctor not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nExiting From DoctorCRUD Successfully\n");
                                break;
                            }
                            default: {
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
                    while (choice != 5) {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Create Appointment\n" + "2. View Appointment\n" + "3. Update Appointment\n" + "4. Delete Appointment\n" + "5. Exit From AppointmentCRUD\n");

                        while (choice < 1 || choice > 5) {
                            System.out.print("\nEnter your choice: ");
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(" ");
                                if (choice < 1 || choice > 5) {
                                    System.out.println("\nInvalid choice, Please Enter between 1 and 5\n");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid Choice");
                                scanner.nextLine();
                            }
                        }
                        switch (choice) {
                            case 1: {
                                try {
                                    System.out.print("Enter Appointment ID: ");
                                    int appointment_id = scanner.nextInt();
                                    scanner.nextLine();
                                    for (Appointment appointment : AppointmentList) {
                                        if (appointment.getAppointment_id() == appointment_id) {
                                            throw new CustomException("\nAppointment ID already exists\n");
                                        }
                                    }
                                    System.out.print("Enter Patient ID: ");
                                    int patient_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Doctor ID: ");
                                    int doctor_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Appointment Date: ");
                                    String date = scanner.nextLine();
                                    System.out.print("Appointment Time: ");
                                    String time = scanner.nextLine();
                                    System.out.print("Appointment Reason: ");
                                    String reason = scanner.nextLine();

                                    Appointment appointment = new Appointment(appointment_id, patient_id, doctor_id, date, time, reason);
                                    AppointmentList.add(appointment);
                                    System.out.println("\nAppointment Created Successfully\n");
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                for (Appointment appointment : AppointmentList) {
                                    appointment.displayInfo();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    System.out.println("Search Appointment ID: ");
                                    int search_id = scanner.nextInt();
                                    boolean found = false;

                                    for (Appointment appointment : AppointmentList) {
                                        if (appointment.getAppointment_id() == search_id) {
                                            found = true;
                                            System.out.print("Enter Patient ID: ");
                                            int new_patient_id = scanner.nextInt();
                                            scanner.nextLine();
                                            appointment.setPatient_id(new_patient_id);
                                            System.out.print("Enter Doctor ID: ");
                                            int new_doctor_id = scanner.nextInt();
                                            scanner.nextLine();
                                            appointment.setDoctor_id(new_doctor_id);
                                            System.out.print("Appointment Date: ");
                                            String new_date = scanner.nextLine();
                                            appointment.setDate(new_date);
                                            System.out.print("Appointment Time: ");
                                            String new_time = scanner.nextLine();
                                            appointment.setTime(new_time);
                                            System.out.print("Appointment Reason: ");
                                            String new_reason = scanner.nextLine();
                                            appointment.setReason(new_reason);
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nAppointment not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    System.out.println("Search Appointment ID: ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;
                                    for (Appointment appointment : AppointmentList) {
                                        if (appointment.getAppointment_id() == search_id) {
                                            found = true;
                                            AppointmentList.remove(appointment);
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nAppointment not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nExiting From AppointmentCRUD Successfully\n");
                                break;
                            }
                            default: {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    //MedicalRecordCRUD
                    Scanner scanner = new Scanner(System.in);
                    int choice = -1;
                    while (choice != 5) {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Create Medical Record\n" + "2. View Medical Record\n" + "3. Update Medical Record\n" + "4. Delete Medical Record\n" + "5. Exit\n");

                        while (choice < 1 || choice > 5) {
                            System.out.print("\nEnter your choice: ");
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(" ");
                                if (choice < 1 || choice > 5) {
                                    System.out.println("Invalid choice, Please Enter between 1 and 5");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid Choice");
                                scanner.nextLine();
                            }
                        }
                        switch (choice) {
                            case 1: {
                                try {
                                    System.out.print("Enter Medical Record ID: ");
                                    int record_id = scanner.nextInt();
                                    scanner.nextLine();
                                    for (MedicalRecord medicalRecord : MedicalRecordList) {
                                        if (medicalRecord.getRecordId() == record_id) {
                                            throw new CustomException("\nMedical Record ID already exists\n");
                                        }
                                    }
                                    System.out.print("Enter Patient ID: ");
                                    int patient_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Doctor ID: ");
                                    int doctor_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Diagnosis: ");
                                    String diagnosis = scanner.nextLine();
                                    System.out.print("Treatment: ");
                                    String treatment = scanner.nextLine();

                                    MedicalRecord medicalRecord = new MedicalRecord(record_id, patient_id, doctor_id, diagnosis, treatment);
                                    MedicalRecordList.add(medicalRecord);
                                    System.out.println("\nMedical Record Created Successfully\n");
                                    break;
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                for (MedicalRecord medicalRecord : MedicalRecordList) {
                                    medicalRecord.displayInfo();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    System.out.println("Search Record ID: ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;
                                    for (MedicalRecord medicalRecord : MedicalRecordList) {
                                        if (medicalRecord.getRecordId() == search_id) {
                                            found = true;
                                            System.out.print("Enter Patient ID: ");
                                            int new_patient_id = scanner.nextInt();
                                            scanner.nextLine();
                                            medicalRecord.setPatient_id(new_patient_id);
                                            System.out.print("Enter Doctor ID: ");
                                            int new_doctor_id = scanner.nextInt();
                                            scanner.nextLine();
                                            medicalRecord.setDoctor_id(new_doctor_id);
                                            System.out.print("Diagnosis: ");
                                            String new_diagnosis = scanner.nextLine();
                                            medicalRecord.setDiagnosis(new_diagnosis);
                                            System.out.print("Treatment: ");
                                            String new_treatment = scanner.nextLine();
                                            medicalRecord.setTreatment(new_treatment);
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nMedical Record not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    System.out.println("Search Record ID: ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;
                                    for (MedicalRecord medicalRecord : MedicalRecordList) {
                                        if (medicalRecord.getRecordId() == search_id) {
                                            found = true;
                                            MedicalRecordList.remove(medicalRecord);
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nMedical Record not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nExiting From Medical Record CRUD Successfully\n");
                                break;
                            }
                            default: {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    //MedicalBillCRUD
                    Scanner scanner = new Scanner(System.in);
                    int choice = -1;
                    while (choice != 5) {
                        choice = -1;
                        System.out.println("Enter what you want to do:");
                        System.out.println("1. Create Medical Bill\n" + "2. View Medical Bill\n" + "3. Update Medical Bill\n" + "4. Delete Medical Bill\n" + "5. Exit\n");

                        while (choice < 1 || choice > 5) {
                            System.out.print("\nEnter your choice: ");
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(" ");
                                if (choice < 1 || choice > 5) {
                                    System.out.println("Invalid choice, Please Enter between 1 and 5");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid Choice");
                                scanner.nextLine();
                            }
                        }
                        switch (choice) {
                            case 1: {
                                try {
                                    System.out.print("Enter Medical Bill ID : ");
                                    int bill_id = scanner.nextInt();
                                    scanner.nextLine();
                                    for (MedicalBill medicalBill : MedicalBillList) {
                                        if (medicalBill.getBill_id() == bill_id) {
                                            throw new CustomException("\nMedical Bill ID already exists\n");
                                        }
                                    }
                                    System.out.print("Enter Patient ID: ");
                                    int patient_id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Bill Amount: ");
                                    int amount = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Bill Date: ");
                                    String bill_date = scanner.nextLine();
                                    System.out.print("Enter Payment Status: ");
                                    String payment_status = scanner.nextLine();

                                    MedicalBill medicalBill = new MedicalBill(bill_id, patient_id, amount, bill_date, payment_status);
                                    MedicalBillList.add(medicalBill);
                                    System.out.println("\nMedical Bill added successfully\n");
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                for (MedicalBill medicalBill : MedicalBillList) {
                                    medicalBill.displayInfo();
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    System.out.println("Search Bill ID:  ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;

                                    for (MedicalBill medicalBill : MedicalBillList) {
                                        if (medicalBill.getBill_id() == search_id) {
                                            found = true;
                                            System.out.print("Enter New Patient ID: ");
                                            int new_patient_id = scanner.nextInt();
                                            scanner.nextLine();
                                            medicalBill.setPatient_id(new_patient_id);
                                            System.out.print("Enter New Amount: ");
                                            int new_amount = scanner.nextInt();
                                            scanner.nextLine();
                                            medicalBill.setAmount(new_amount);
                                            System.out.print("Enter New Bill Date: ");
                                            String new_bill_date = scanner.nextLine();
                                            medicalBill.setBill_date(new_bill_date);
                                            System.out.print("Enter New Payment Status: ");
                                            String new_payment_status = scanner.nextLine();
                                            medicalBill.setPayment_status(new_payment_status);
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nBill not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    System.out.println("Search Bill ID:  ");
                                    int search_id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean found = false;

                                    for (MedicalBill medicalBill : MedicalBillList) {
                                        if (medicalBill.getBill_id() == search_id) {
                                            found = true;
                                            MedicalBillList.remove(medicalBill);
                                            System.out.println("\nMedical Bill deleted successfully\n");
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        throw new CustomException("\nBill not found\n");
                                    }
                                } catch (CustomException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("\nInvalid Input, Please try again\n");
                                    scanner.nextLine();
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nExiting From Medical Bill CRUD Successfully\n");
                                break;
                            }
                            default: {
                                System.out.println("\nInvalid choice, Please try again\n");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("\nExiting Hospital Management System" + "\nThanks for using the management system\n");
                    break;
                }
                default: {
                    System.out.println("\nInvalid Menu Choice, Please try again\n");
                }
            }
        }
    }
}