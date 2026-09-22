public class MedicalBill {
    private int bill_id;
    private int patient_id;
    private int amount;
    private String bill_date;
    private String payment_status;

    MedicalBill(int bill_id, int patient_id, int amount, String bill_date, String payment_status)
    {
        this.bill_id = bill_id;
        this.patient_id = patient_id;
        this.amount = amount;
        this.bill_date = bill_date;
        this.payment_status = payment_status;
    }

    public int getBill_id()
    {
        return bill_id;
    }
    public int getPatient_id()
    {
        return patient_id;
    }
    public int getAmount()
    {
        return amount;
    }
    public String getBill_date()
    {
        return bill_date;
    }
    public String getPayment_status()
    {
        return payment_status;
    }

    public void setBill_id(int bill_id)
    {
        this.bill_id = bill_id;
    }
    public void setPatient_id(int patient_id)
    {
        this.patient_id = patient_id;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    public void setBill_date(String bill_date)
    {
        this.bill_date = bill_date;
    }
    public void setPayment_status(String payment_status)
    {
        this.payment_status = payment_status;
    }

    public void displayInfo()
    {
        System.out.println("---Bill Details---\n");
        System.out.println("Bill ID: "+ bill_id);
        System.out.println("Patient ID: "+ patient_id);
        System.out.println("Bill Amount: "+ amount +"$");
        System.out.println("Bill Date: "+ bill_date);
        System.out.println("Payment Status: "+ payment_status);
        System.out.println(" ");
    }

}
