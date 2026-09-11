public class Doctor extends Person{

    private String specialization;
    private String qualification;
    private int salary;

    Doctor(String name, int id, String phone_number, String address, String specialization, String qualification, int salary)
    {
        super(name, id, phone_number, address);
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
    public int setSalary(int salary)
    {
        this.salary = salary;
    }

}





