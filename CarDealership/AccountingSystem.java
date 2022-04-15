// Omar Syed, Student Number: 500809837
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/**
 * class receives and holds an arraylist of Transaction objects. It includes
 * various other methods that are needed when working with Transaction objects.
 */
public class AccountingSystem
{
    private Map<Transaction, Integer> transactions; //collection of Transaction objects
    private Set<Transaction> keySet; //a keyset used in iterating through the above Map
    Random rand; //Random object needed to create a random number generator
    private int tID; //the transaction ID number
    private Map<String, Integer> spSales; //HashMap stores the name of salesperson as a key and the number of cars they have sold as the value of the key

    /**
     * Constructor method for the AccountingSystem class. Initializes reference
     * variable for the collection of Transaction objects, as well as the Random object,
     * Map of salesperson and their sales, and the keySet for the Transactions map .
     */
    public AccountingSystem() {
        transactions = new HashMap<Transaction, Integer>();
        rand = new Random();
        spSales = new HashMap<String, Integer>();
        keySet = transactions.keySet();
    }

    /**
     * Random number generator used to create ID number
     * Creates Transaction object and increases number of cars
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salesPrice
     * @return transaction details
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salesPrice) 
    {
        tID = rand.nextInt(99) + 1;
        Transaction transaction = new Transaction(date, car, salesPerson, type, salesPrice, tID);
        if (type.equalsIgnoreCase("BUY")) //amount of sales made by a salesperson is incremented...
        {
            updateSPRanking(salesPerson); //... only if it is a purchase
        }
        transactions.put(transaction, tID);
        updateSPRanking(salesPerson);
        return transaction.display();
        
    }

    /**
     * @return transaction ID
     */
    public int gettID() 
    {
        return tID;
    }

    /**
     * Uses HashMap to rank and organize salesperson
     * @param salesperson
     */
    public void updateSPRanking(String salesperson)
    {
        int count = 0;
        if(spSales.containsKey(salesperson)) //if salesperson is on the map, the count of cars they've sold is incremented.
        {
            count = spSales.get(salesperson) + 1;
            spSales.put(salesperson, count);
        }
        else
        {
            spSales.put(salesperson, 1); //if salesperson isn't on the map, they are placed on the map with the # of cars they have sold (1) 

        }

    }


    /**
     * Calculates the HighestEarning sales person
     * Prints the Best Sales Person and carsSold
     */
    public void getHighestEarningSP()
    {
        if(transactions.size() != 0)
        {
            int mostCarsSold = 0;
            Set<String> keySet = spSales.keySet(); 
            for (String key : keySet)
            {
                int carsSold = spSales.get(key);
                if (carsSold > mostCarsSold)
                {
                    mostCarsSold = carsSold;
                }
            }
            for (String key : keySet)
            {
                int carsSold = spSales.get(key);
                if (carsSold == mostCarsSold)
                {
                    System.out.println("Best SP: " + key + " " + carsSold);
                }
            }
        }
        else 
        {
            System.out.println("There have been no transactions.");
        }

    }

    /**
     * Checks if id of the transactions match
     * @param id
     * @return wantedTransaction
     */
    public Transaction getTransaction(int id) 
    {
        boolean found = false;
        Transaction wantedTransaction = null; //initialized
        for (Transaction key : keySet)
        {
            int ID = transactions.get(key);
            if (id == ID) 
            {
                wantedTransaction = key;
                found = true;
            }
        }
        if(!found)
        {
            return null;
        }
        else 
        {
            return wantedTransaction;
        }
    }

    /**
     * Displays all transactions
     */
    public void displayAllTransactions()
    {
        if(transactions.size() != 0)
        {
            for (Transaction key : keySet)
            {
                Transaction transaction = key;
                System.out.println(transaction.display());
            }
        }
        else 
        {
            System.out.println("There are no transactions.");
        }
    }

    /**
     * All transactions in a month
     * @param month
     */
    public void displayTransactionsByMonth(int m)
    {
        boolean check = false;
        for(Transaction key : keySet)
        {
            Transaction t = key;
            int month = t.getDate().get(Calendar.MONTH);
            if(month == m)
            {
                System.out.println(t.display());
                check = true;
            }
        }
        if(!check)
        {
            System.out.println("There were no transactions made in that month. Try again.");
        }
    }
    
    /**
     * Total transactions happened in a given month
     * @param month
     * @return total carsSold
     */
    public int getMonthlyTotal(int m)
    {
        int carsSold = 0;
        for(Transaction key : keySet)
        {
            Transaction t = key;
            int month = t.getDate().get(Calendar.MONTH);
            if(month == m && (t.getTransactionType().equalsIgnoreCase("BUY")))
            {
                carsSold++;
            }
        }
        return carsSold;
    }


    /**
     * @param i
     * @return month linked with int i
     */
    public String getMonth(int i) {
        if(i >= 0 && i < 12)
        {
            return new DateFormatSymbols().getMonths()[i];
        }
        else 
        {
            return null;
        }
    }

    /**
     * Calculates best month
     * @return bestMonth and mostCarsSold
     */
    public String getBestMonth()
    {
        double mostCarsSold = 0; 
        String bestMonth = "";
        for (int i = 0; i < 12; i++)
        {
            double totalCarsSold = getMonthlyTotal(i);
            if (totalCarsSold > mostCarsSold)
            {
                mostCarsSold = totalCarsSold;
                bestMonth = getMonth(i);
                if(bestMonth == null)
                {
                    return "Month does not exist (getBestMethod, forLoop).";
                }
            }
        }
        return "Best month: " + bestMonth + ": - " + mostCarsSold;

    }


    /**
     * @return totalSales
     * @return total carsSole
     * @return avgSales
     * @return total carsReturned
     */
    public String getSalesInfo()
    {
        double totalSales = 0;
        int carsSold = 0, carsReturned = 0;
        for (Transaction key : keySet)
        {
            Transaction transaction = key;
            if(transaction.getTransactionType().equalsIgnoreCase("BUY"))
            {
                totalSales += transaction.getSalesPrice();
                carsSold++;
            }
            else 
            {
                carsReturned++;
            }
        }
        double avgSales = totalSales / 12;
        return "Total sales: " + totalSales + " Total sold: " + carsSold + " Avg sales: "
        + avgSales + " Total returned: " + carsReturned;
    }

    /**
     * 
     * @return SalesInfo and BestMonth
     */
    public String getSalesStats()
    {
        if(transactions.size() != 0)
        {
            return getSalesInfo() + " " + getBestMonth();

        }
        else
        {
            return "There have been no transactions yet.";
        }
    }
}