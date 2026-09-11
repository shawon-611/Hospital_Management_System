public abstract class Person {

    private String name;
    private int id;
    private String phone_number;
    private String address;

    Person(String name, int id, String phone_number, String address)
    {
        this.name = name;
        this.id = id;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }
    public int getID()
    {
        return id;
    }
    public String getPhone_Number()
    {
        return phone_number;
    }
    public String getAddress()
    {
        return address;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setPhone_Number(String phone_number)
    {
        this.phone_number = phone_number;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public abstract void displayInfo();




}
