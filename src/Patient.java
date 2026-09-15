public class Patient extends Person{

    private int age;
    private String address;
    private String diseases;
    private String blood_group;

    Patient(String name, int id, String phone_number,int age, String address, String diseases, String blood_group)
    {
        super(name, id, phone_number);
        this.age = age;
        this.address = address;
        this.diseases = diseases;
        this.blood_group = blood_group;
    }


    public int getAge()
    {
        return age;
    }
    public String getAddress()
    {
        return address;
    }
    public String getDiseases()
    {
        return diseases;
    }
    public String getBlood_Group()
    {
        return blood_group;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setDiseases(String diseases)
    {
        this.diseases = diseases;
    }
    public void setBlood_group(String blood_group)
    {
        this.blood_group = blood_group;
    }


    @Override
    public void displayInfo()
    {
        System.out.println("---Patient Details---\n");
        System.out.println("Patient Name: "+ getName());
        System.out.println("Patient ID: "+ getID());
        System.out.println("Patient Phone Number: "+ getPhone_Number());
        System.out.println("Patient Age: "+ age);
        System.out.println("Patient Address: "+ address);
        System.out.println("Patient Diseases: "+ diseases);
        System.out.println("Patient Blood Group: "+ blood_group);
        System.out.println(" ");
    }

}
///////

