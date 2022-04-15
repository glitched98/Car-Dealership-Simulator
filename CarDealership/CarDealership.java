// Omar Syed, Student Number: 500809837
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;


public class CarDealership {
    //instance variables
    private ArrayList<Car> cars; 
    private boolean electricFilter; 
    private boolean AWDfilter;
    private boolean priceFilter;
    private double maxPrice;
    private double minPrice;
    SalesTeam salesTeam; 
    AccountingSystem accountingSystem; 
    Random rand; 
 

    public CarDealership() {
        cars = new ArrayList<Car>();
        salesTeam = new SalesTeam();
        accountingSystem = new AccountingSystem();
        rand = new Random();
    }

    /**
     * adds newCars into array list cars
     * @param newCars
     */
	public void addCars(ArrayList<Car> newCars) {
        for (int i = 0; i < newCars.size(); i++)
        {
            Car element = newCars.get(i);
            cars.add(element);
        }
    }
    
	/**
	 * Uses VIN of boughtCar and searches if VIN is found in array list, after car is bought, removes that car from array list
	 * if VIN not found, throws exception
	 * @param VIN
	 * @return Information of car linked to VIN, if VIN of car found
	 */
    public String buyCar(int VIN) 
    {
        if(cars.size() == 0) //if there are no cars in the arraylist, an exception is thrown
        {
            throw new IndexOutOfBoundsException("The arraylist of cars is empty.");
        }

        boolean found = false; //to check if Car with the certain VIN is found
        Car boughtCar = null;
        for(int i = 0; i < cars.size(); i++)
        {
            Car car = cars.get(i);
            if(car.getVIN() == VIN)
            {
                boughtCar = car;
                found = true;
                cars.remove(i);
            }
        }
        
        if(!found) 
        {
            throw new IllegalArgumentException("VIN not found"); //exception
        }
        
        String sp = salesTeam.getAnySalesPerson();
        int day = rand.nextInt(31) + 1, month = rand.nextInt(11); //day and month is randomly generated
        Calendar date = new GregorianCalendar(2019, month, day);
        return accountingSystem.add(date, boughtCar, sp, "BUY", boughtCar.getPrice()); 

    }
    


    /**
     * ID of purchased transaction and links that transaction ID to car purchased
     * @param transaction
     */
    public void returnCar(int transaction)
    {
        Transaction purchaseTransaction = accountingSystem.getTransaction(transaction);
        if(purchaseTransaction != null)//
        {
            Car car = purchaseTransaction.getCar();
            int month = purchaseTransaction.getDate().get(Calendar.MONTH);
            int day = purchaseTransaction.getDate().get(Calendar.DAY_OF_MONTH);
            int newDay = 0, increment = 28 - day;
            if(increment >= 0)
            {
                newDay = day + increment;
            }
            else 
            {
                newDay = day;
            }
            Calendar date = new GregorianCalendar(2019, month, newDay);
            System.out.println(accountingSystem.add(date, car, purchaseTransaction.getSalesPerson(), "RET", purchaseTransaction.getSalesPrice()));
            cars.add(car);
        }
        else 
        {
            throw new NullPointerException("Invalid transaction ID. Try again.");
        }
    }

    /**
     * Sets the electric filter.
     */
    public void filterByElectric() {
        this.electricFilter = true;
    }

    /**
     * Sets the AWD filter.
     */
    public void filterByAWD() {
        this.AWDfilter = true;
    }

    /**
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     */
    public void filterByPrice(double minPrice, double maxPrice) {
        this.priceFilter = true;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    /**
     * sets all filters to false
     */
    public void filterClear(){
        this.priceFilter = false;
        this.AWDfilter = false;
        this.electricFilter = false;
    }


    /**
     * Displays all cars in the array list based on selected filter
     */
    public void displayInventory() 
    {
        int count  = 0; //keeps count of all cars displayed
        for (int i = 0; i < cars.size(); i++) 
        {
            Car car = cars.get(i);
            if (priceFilter && AWDfilter && electricFilter)
            {
                double carPrice = car.getPrice();
                int carPower = car.getPower();
                if (carPrice >= minPrice && carPrice <= maxPrice && car.getAWD() && carPower == Vehicle.ELECTRIC_ENGINE) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if (AWDfilter && !priceFilter && !electricFilter) 
            {
                if (car.getAWD()) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(electricFilter && !AWDfilter && !priceFilter)
            {
                int carPower = car.getPower();
                if (carPower == Vehicle.ELECTRIC_ENGINE)
                {

                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(AWDfilter && priceFilter && !electricFilter) 
            {
                double carPrice = car.getPrice();
                if (carPrice >= minPrice && carPrice <= maxPrice && car.getAWD()) {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(AWDfilter && electricFilter && !priceFilter)
            {
                int carPower = car.getPower();
                if (car.getAWD() && carPower == Vehicle.ELECTRIC_ENGINE) {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }  
            else if(electricFilter && priceFilter && !AWDfilter) 
            {
                double carPrice = car.getPrice();
                int carPower = car.getPower();
                if (carPrice >= minPrice && carPrice <= maxPrice && carPower == Vehicle.ELECTRIC_ENGINE) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(priceFilter && !electricFilter && !AWDfilter) 
            {
                double carPrice = car.getPrice();
                if (carPrice >= minPrice && carPrice <= maxPrice) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else 
            {
                System.out.println(cars.indexOf(car)+ " " + car.display());
                count++;
                
            }
        }
        if(count == 0) 
        {
            System.out.println("No cars found.");
        }
    
    }

    /**
     * Sorts array list by price
     */
    public void sortByPrice() {
        Collections.sort(cars);
    }
    
    /**
     * Sorts array list by Safety Rating
     */
    public void sortBySafetyRating() {
        Collections.sort(cars, new CarSafetyRatingComparator());
    }

    /**
     * Sorts array list by Max Range
     */
    public void sortByMaxRange() {
        Collections.sort(cars, new CarMaxRangeComparator());
    }
}