// Omar Syed, Student Number: 500809837
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Uses user commands to print output
 */
public class CarDealershipSimulator {
    public static void main(String[] args) {
        // Create a CarDealership object
        CarDealership carShop = new CarDealership();
        // Then create an (initially empty) array list of type Car
        ArrayList<Car> cars = new ArrayList<Car>();
        // Then create some new car objects of different types
        try 
        {
            cars = readFiles("cars.txt"); //reads the list of cars from input file and adds them into arraylist of cars
        } 
        //next line catches exception when input file does not exist
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found.");
        }
        //next line catches exception when input of wrong type is given
        catch(InputMismatchException p) 
        {
            System.out.println("Check input file for input of wrong type when constructing car object.");
        }

        String command = " ";
        Scanner in = new Scanner(System.in);
        while (!command.equals("Q")) {
            command = in.nextLine();
            String[] commandLine = command.split(" ");
            try {
            if(commandLine[0].equalsIgnoreCase("RET") && (commandLine.length == 2))
            {
                String secondLine = commandLine[1];
                Scanner check = new Scanner (secondLine);
                int tID = 0;
                try {
                    if(check.hasNextInt())
                    {
                        tID = check.nextInt();
                        carShop.returnCar(tID);
                    }
                    else 
                    {   
                        System.out.println("Invalid input for transaction ID. Try again!");
                    }
                } catch (NullPointerException e) 
                {
                    System.out.println(e.getMessage());
                }
                check.close();
            }
            else if (commandLine[0].equalsIgnoreCase("L")) {
                carShop.displayInventory();
            } else if (commandLine[0].equalsIgnoreCase("ADD")) {
                carShop.addCars(cars);
            } else if (commandLine[0].equalsIgnoreCase("Q")) {
                return;
            }  
            else if (commandLine[0].equalsIgnoreCase("BUY") && (commandLine.length == 2)) {
                int VIN = 0;
                Scanner check = new Scanner(commandLine[1]);
                if(!check.hasNextInt())
                {
                    System.out.println("Invalid VIN. Try again.");
                }
                else 
                {
                    try
                    {
                        VIN = Integer.parseInt(commandLine[1]);
                        System.out.println(carShop.buyCar(VIN));
                    }
                    catch(IllegalArgumentException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        System.out.println(e.getMessage());
                    } 
                } 
                check.close();
            } 
            else if (commandLine[0].equalsIgnoreCase("SPR")) {
                carShop.sortByPrice();
            } else if (commandLine[0].equalsIgnoreCase("SSR")) {
                carShop.sortBySafetyRating();
            } else if (commandLine[0].equalsIgnoreCase("SMR")) {
                carShop.sortByMaxRange();
            } else if (commandLine[0].equalsIgnoreCase("FCL")) {
                carShop.filterClear();
            } else if (commandLine[0].equalsIgnoreCase("FEL")) {
                carShop.filterByElectric();
            } 
            else if(commandLine[0].equalsIgnoreCase("SALES") && (commandLine.length == 1))
            {
                carShop.accountingSystem.displayAllTransactions();
            }
            else if(commandLine[0].equalsIgnoreCase("SALES") && commandLine[1].equalsIgnoreCase("TEAM"))
            {
                System.out.println(carShop.salesTeam.displayAll()); 
            }
            else if(commandLine[0].equalsIgnoreCase("SALES") && commandLine[1].equalsIgnoreCase("TOPSP"))
            {
                carShop.accountingSystem.getHighestEarningSP(); 
            }
            else if(commandLine[0].equalsIgnoreCase("SALES") && commandLine[1].equalsIgnoreCase("STATS"))
            {
                System.out.println(carShop.accountingSystem.getSalesStats()); 
            }
            else if(commandLine[0].equalsIgnoreCase("SALES") && (commandLine.length == 2))
            {
                String line = commandLine[1];
                Scanner check = new Scanner(line);
                if(check.hasNextInt())
                {
                    int m = check.nextInt();
                    if(m >= 0 && m < 12)
                    {
                        carShop.accountingSystem.displayTransactionsByMonth(m);
                    }
                    else 
                    {
                        System.out.println("Invalid month provided. Try again.");
                    }
                }
                else 
                {
                    System.out.println("Invalid input. Try again.");
                }
                check.close();
            }
            else if (commandLine[0].equalsIgnoreCase("FAW")) {
                carShop.filterByAWD();
            } else if (commandLine[0].equalsIgnoreCase("FPR") && (commandLine.length == 3)) {
                double maxPrice = Double.parseDouble(commandLine[2]);
                double minPrice = Double.parseDouble(commandLine[1]);
                if (minPrice < maxPrice && maxPrice > minPrice
                        && !(minPrice == maxPrice && minPrice > 0 && maxPrice > 0)) {
                    carShop.filterByPrice(minPrice, maxPrice);
                } else {
                    System.out.println("Invalid price range. Try again! E.g. minimum price first, maximum price second!");
                }

            } 
            else if(commandLine.length == 0)
                {
                    System.out.println("Please type valid commands.");
                }
            else {
                System.out.println("Invalid command and/or arguments. Try again.");
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("No command given. Try again!");
        }
        }
    }

    /**
     * @param filename the name of the input file
     * @return array cars
     * @throws FileNotFoundException
     * @throws InputMismatchException
     */
    public static ArrayList<Car> readFiles(String filename) throws FileNotFoundException, InputMismatchException
    {
      File inputFile = new File(filename);
      Scanner in = new Scanner(inputFile);
      int rechargeTime = 0;
      ArrayList<Car> cars = new ArrayList<Car>();
      while(in.hasNextLine())
      {
        String line = in.nextLine();
        Scanner lin = new Scanner(line);
        String mfr = lin.next();
        String color = lin.next();
        String model = lin.next();
        String i = lin.next();
        double safetyRating = lin.nextDouble();
        int maxRange = lin.nextInt();
        String awd = lin.next();
        boolean AWD = false;
        if(awd.equals("AWD")) 
        {
            AWD = true;
        }
        double price = lin.nextDouble();
        if(i.equalsIgnoreCase("ELECTRIC_MOTOR"))
        {
            rechargeTime = lin.nextInt();
            ElectricCar car = new ElectricCar(mfr, color, model, i, safetyRating, maxRange, AWD, price, rechargeTime, "lithium");
            cars.add(car);
        }
        else
        {
            Car car = new Car(mfr, color, model, i, safetyRating, maxRange, AWD, price);
            cars.add(car);
        }
      }
      in.close();
      return cars;
    }
}