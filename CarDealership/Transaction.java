// Omar Syed, Student Number: 500809837
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction 
{
    //Instance Variables
	private int id; 
    private Calendar date; 
    private Car car; 
    private String salesperson; 
    private String type; 
    private double salesPrice; 


    /**
     * Initializes all instance variables
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @param ID
     */
    public Transaction(Calendar date, Car car, String salesPerson, String type, double salePrice, int ID) {
        this.date = date;
        this.setCar(car);
        this.salesperson = salesPerson;
        this.type = type;
        this.setSalesPrice(salePrice);
        this.id = ID;
    }

    /**
     * 
     * @return salesPrice
     */
    public double getSalesPrice() {
        return salesPrice;
    }
    /**
     * 
     * @return type of transaction
     */
    public String getTransactionType() {
        return type;
    }

    /**
     * sets salesPrice
     * @param salesPrice
     */
    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * 
     * @return salesperson
     */
    public String getSalesPerson() {
        return salesperson;
    }

    /**
     * 
     * @return car
     */
    public Car getCar() {
        return car;
    }

    /**
     * changes the Car object
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @return ID
     */
    public int getID() {
        return id;
    }

    /**
     * @return date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * 
     * @return id, date, type, salesperson, and car details
     */
 
    public String display() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return  ("ID: " + id + " " + sdf.format(date.getTime()) + " " + type + " Salesperson: " + salesperson + " Car: " + car.display());
    }
    
}