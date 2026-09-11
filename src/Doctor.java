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

}
