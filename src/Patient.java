public class Patient extends Person{

    private int age;
    private String diseases;
    private String blood_group;

    Patient(String name, int id, String phone_number, String address, int age, String diseases, String blood_group)
    {
        super(name, id, phone_number, address);
        this.age = age;
        this.diseases = diseases;
        this.blood_group = blood_group;
    }

    public int getAge()
    {
        return age;
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
        super.displayInfo();
        System.out.println("Patient Age: "+ age);
        System.out.println("Patient Diseases: "+ diseases);
        System.out.println("Patient Blood Group: "+ blood_group);
    }

}


