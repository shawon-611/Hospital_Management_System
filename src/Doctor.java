public class Doctor extends Person{

    private String specialization;
    private String qualification;
    private int salary;

    Doctor(String name, int id, String phone_number, String specialization, String qualification, int salary)
    {
        super(name, id, phone_number);
        this.specialization = specialization;
        this.qualification = qualification;
        this.salary = salary;
    }
    public String getSpecialization()
    {
        return specialization;
    }
    public String getQualification()
    {
        return qualification;
    }
    public int getSalary()
    {
        return salary;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }
    public void setQualification(String qualification)
    {
        this.qualification = qualification;
    }
    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    @Override
    public void displayInfo()
    {
        System.out.println("Doctor Name: "+ getName());
        System.out.println("Doctor ID: "+ getID());
        System.out.println("Doctor Phone Number: "+ getPhone_Number());
        System.out.println("Doctor's Specialization: "+ specialization);
        System.out.println("Doctor's Qualification is: "+ qualification);
        System.out.println("Doctor's Salary: "+ salary+"$");
    }
}
