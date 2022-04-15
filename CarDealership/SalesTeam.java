// Omar Syed, Student Number: 500809837
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class SalesTeam 
{
    LinkedList<String> salespersons; 
    Random rand;

    /**
     * Constructor method that initializes the LinkedList and random variable
     */
    public SalesTeam()
    {
        salespersons = new LinkedList<String>();
        salespersons.add("Tim");
        salespersons.add("Bob");
        salespersons.add("Billy");
        salespersons.add("Timothy");
        salespersons.add("Jones");
        salespersons.add("Rob");
        rand = new Random();
    }

    /** 
     * @return returns a random sales person
     */
    public String getAnySalesPerson()
    {
        int i = rand.nextInt(6);
        return salespersons.get(i);
    }
    

    /**
     * 
     * @param i (int)
     * @return returns specific sales person at that index value
     */
    public String getSpecificSalesPerson(int i) 
    {
        return salespersons.get(i);
    }


    /**
     * 
     * @return all members in the sales team
     */
    public String displayAll() 
    {
        String salespeople = "Team: ";
        ListIterator<String> iter = salespersons.listIterator();
        for (int i = 0; i < salespersons.size(); i++) 
        {
            String element = iter.next();
            salespeople = salespeople + element + " ";
        }
        return salespeople;
    }

    /**
     * 
     * @return number of sales team members
     */
    public int teamSize() 
    {
        return salespersons.size();
    }

}