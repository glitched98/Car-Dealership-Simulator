// Omar Syed, Student Number: 500809837
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Vehicle {
    // Instance Variables
    private String mfr; // manufacturer
    private String color; 
    private int power; 
    public static final int GAS_ENGINE = 0;
    public static final int DIESEL_ENGINE = 1;
    public static final int ELECTRIC_ENGINE = 2;
    private int numWheels; 
    private int VIN; // vehicle ID
    Random rand = new Random(); //Random Object
    //Set that stores all generated VIN numbers
    private static Set<Integer> vinNumbers = new TreeSet<Integer>(); 


    /**
     * Initializes Instance Variables
     * Randomly generates VIN
     * @param mfr
     * @param color
     * @param i
     * @param numWheels
     */
    public Vehicle(String mfr, String color, String i, int numWheels) {
        this.mfr = mfr;
        this.color = color;
        setEngineType(i);
        this.numWheels = numWheels;
        this.VIN = rand.nextInt(400) + 100;
        while(vinNumbers.contains(VIN))
        {
            this.VIN = rand.nextInt(400) + 100;
            vinNumbers.add(VIN); 
        }
    }
    /**
     * 
     * @return VIN
     */
    public int getVIN() {
        return VIN;
    }


    /**
     * Sets engine type using input
     * @param input
     */
    public void setEngineType(String input) 
    {
        if(input.equalsIgnoreCase("GAS_ENGINE")) 
        {
            this.power = GAS_ENGINE;
        }
        else if(input.equalsIgnoreCase("DIESEL_ENGINE"))
        {
            this.power = DIESEL_ENGINE;
        }
        else 
        {
            this.power = ELECTRIC_ENGINE;
        }
    }


    /**
     * @return the Engine Type
     */
    public String getEngineType() 
    {
        int i = getPower();
        
        if(i == 0) 
        {
            return "GAS_ENGINE";
        }
        else if(i == 1)
        {
            return "DIESEL_ENGINE";
        }
        else
        {
            return "ELECTRIC_ENGINE";
        } 
    }


    /**
     * sets engine power
     * @param power
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return power type 
     */
    public int getPower() {
        return power;
    }


    /**
     * @return number of Wheels
     */
    public int getNumWheels() {
        return numWheels;
    }

    /**
     * sets the numWheels
     * @param numWheels
     */
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    /** 
     * @return the mfr (manufacturer)
     */
    public String getMfr() {
        return mfr;
    }

    /**
     * sets the name of the manufacturer
     * @param mfr
     */
    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    /**
     * @return color 
     */
    public String getColor() {
        return color;
    }

    /**
     * sets the color
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Method compares this vehicle to other vehicle object
     * @returns boolean value of whether the two objects are equal or not
     */

    public boolean equals(Object other) {
        if (other != null) 
        {
            Vehicle otherVehicle = (Vehicle) other;
            if (mfr.equals(otherVehicle.mfr) && color.equals(otherVehicle.color) && numWheels == otherVehicle.numWheels && VIN == otherVehicle.VIN)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else 
        {
            return false;
        }

    }

    /**
     * @return VIN, mfr and color
     */
    public String display() {
        return "VIN: " + VIN + " " + mfr + " " + color;
    }

}