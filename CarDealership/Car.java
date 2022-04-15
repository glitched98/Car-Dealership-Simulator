// Omar Syed, Student Number: 500809837
import java.util.Comparator;
/**
 * Car class extends the Vehicle class
 * implements the Comparable interface
 */
public class Car extends Vehicle implements Comparable <Car>
{
    //creating instance variables
    private int model; //model of the car
    private int maxRange; //maximum range of the car
    private double safetyRating; //safety rating of the car
    private boolean AWD; //Variable to determine whether a car is All Wheel Drive
    private double price; //price of the car
    public static final int SEDAN = 0;
    public static final int SUV = 1;
    public static final int SPORTS = 2;
    public static final int MINIVAN = 3;

    /**
     * 
     * @param mfr
     * @param color
     * @param model
     * @param i
     * @param safetyRating
     * @param maxRange
     * @param AWD
     * @param price
     */
    public Car(String mfr, String color, String model, String i, double safetyRating, int maxRange, boolean AWD, double price) {
        super(mfr, color, i, 4); //super call for the Vehicle constructor method
        setModelType(model); 
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }

    /**
     * 
     * @param input (Model type)
     */
    public void setModelType(String input) 
    {
        if(input.equalsIgnoreCase("SEDAN")) 
        {
            this.model = SEDAN;
        }
        else if(input.equalsIgnoreCase("SUV"))
        {
            this.model = SUV;
        }
        else if (input.equalsIgnoreCase("SPORTS"))
        {
            this.model = SPORTS;
        }
        else 
        {
            this.model = MINIVAN;
        }
    }

    /**
     * 
     * @return ModelType
     */
    public String getModelType()
    {
        int i = getModel();
        if(i == SEDAN) 
        {
            return "SEDAN";
        }
        else if(i == SUV)
        {
            return "SUV";
        }
        else if (i == SPORTS)
        {
            return "SPORTS";
        }
        else 
        {
            return "MINIVAN";
        }
    }

    /**
     * @return model 
     */
    public int getModel() {
        return model;
    }

    /**
     * displays information about the car
     * @return information about car from super class
     * ModelType, price, safetyRating, maxRange
     */
    public String display() {
        return super.display() + " " + getModelType() + " " + price + "$ SF: " + safetyRating + " RNG:" + maxRange; 
        // Overrides method from Vehicle Class
    }

    /**
     * Checks if other vehicle is same as this vehicle
     * @param other vehicle 
     */
    public boolean equals(Object other) {
        Car otherCar = (Car) other;
        if (super.equals(otherCar)) {
            if (this.model == otherCar.model && AWD == (otherCar.AWD)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * Comparable interface to compare the prices of two cars:
     * @param other car
     * @return how prices compare to each other
     */
    
    public int compareTo(Car other) {
        if (price < other.price) {return -1;} 
        if (price > other.price) {return 1;}
        return 0;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return if vehicle is AWD or not
     */
    public boolean getAWD() {
        return AWD;
    }

    /**
     * @return safetyRating
     */
    public double getSafetyRating() {
        return safetyRating;
    }

    /**
     * @return maxRange
     */
    public int getMaxRange() {
        return maxRange;
    }
}

/**
 * compares the safety ratings of car a and car b
 */
class CarSafetyRatingComparator implements Comparator<Car>
{
	public int compare(Car a, Car b)
	{
		if (a.getSafetyRating() < b.getSafetyRating()) {return 1;}
		if (a.getSafetyRating() > b.getSafetyRating()) {return  -1;}
		else {return 0;}
	}
}

/**
 * compares the max range of car a and car b
 */
class CarMaxRangeComparator implements Comparator<Car>
{
	public int compare(Car a, Car b)
	{
		if (a.getMaxRange() < b.getMaxRange()) {return 1;}
		if (a.getMaxRange() > b.getMaxRange()) {return  -1;}
		else {return 0;}
	}
}