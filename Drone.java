import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * @author      Eli H.  && Colin Vinarcik
 * @version     1.29
 * @since       12/8/2028
 */
public class Drone extends Vehicle {

    public static final DecimalFormat df2 = new DecimalFormat( "#.00" );
    final private double GAS_RATE = 1.33;
    /**
     * Default Contructor 
     */
    //============================================================================
    public Drone() {

    }
    
    //============================================================================

    /**
     * Constructor
     * 
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }
    
    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */
    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double x = 0;

        double distance = 0;

        for(Package pack : getPackages()) {
            x += pack.getPrice();
            int zip = getZipDest() - pack.getDestination().getZipCode();
            if(distance < Math.abs(zip)){
                distance = Math.abs(zip);
            }
        }



        return x - (GAS_RATE * distance);

    }

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     * 
     * @return Truck Report
     */
    @Override
    public String report() {
        String x = "==============Drone Report==========" +
                "\nLicense Plate No.: " + getLicensePlate() +
                "\nDestination: " + getZipDest() +
                "\nWeight Load: " + getCurrentWeight() + "/" +
                getMaxWeight() + "\nNet Profit: " + getProfit()
                + "\n==============================\n";
        String l  = "";
        for(Package pack: getPackages()){
            l += pack.shippingLabel();
        }

        return x + l;

    }
    
   

}
