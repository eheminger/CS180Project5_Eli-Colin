import java.util.ArrayList;
import java.util.Comparator;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages = new ArrayList<>();


    /**
     * Default Constructor
     */
    //============================================================================
    public Vehicle() {
        zipDest = 0;
        maxWeight = 0;
        currentWeight = 0;
        licensePlate = "";
    }
    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle (String licensePlate, double maxWeight) {
        this.maxWeight = maxWeight;
        this.licensePlate = licensePlate;
    }
    //============================================================================


    public double getProfit(){
        return 1;
    }


    public String report(){
        String x = "";
        return x;
    }

    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }





    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }







    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return maxWeight;
    }





    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }






    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return currentWeight;
    }






    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return zipDest;
    }






    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }






    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }






    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {

        if(maxWeight >= currentWeight + pkg.getWeight()){
            packages.add(pkg);
            currentWeight += pkg.getWeight();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        for (int x = packages.size(); x > 0; x--){
            packages.remove(x);
        }
        currentWeight = 0;
    }


    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        if(maxWeight == currentWeight){
            return true;
        } else {
            return false;
        }
    }






    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */


    //abs value of the zip from the zip des
    public void fill(ArrayList<Package> warehousePackages) {


        ArrayList<Integer> x = new ArrayList<>();

        for(Package pack : warehousePackages) {
            int packZip = pack.getDestination().getZipCode();
            int zip = Math.abs(zipDest - packZip);
            x.add(zip);
        }


        ArrayList<Package> sending = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < warehousePackages.size(); i++){


            for (int y = 0; y < x.size(); y++) {
                if (x.get(y) == index) {
                    if (warehousePackages.get(y).getWeight() + currentWeight <= maxWeight) {
                        addPackage(warehousePackages.get(y));
                        sending.add(warehousePackages.get(y));
                    }
                }
            }


            int tempIndex = index;
            for (int y = 0; y < x.size();y++) {

                boolean stupid = true;

                if (y > index && stupid == true) {
                    stupid = false;
                    tempIndex = x.get(y);

                } else if (y > index) {
                    if(y < tempIndex){
                        tempIndex = y;
                    }
                }
            }

            index = tempIndex;

            if (isFull()) {
                break;
            }
            if (packages.size() == warehousePackages.size()) {
                break;
            }



        }


       /* for (int i = 0; i < warehousePackages.size(); i++) {
            ArrayList<Package> sending = new ArrayList<>();

            for (Package pack : warehousePackages) {
                if (pack.getDestination().getZipCode() == zipDest) {
                    if (pack.getWeight() + currentWeight <= maxWeight && !packages.contains(pack)) {
                        addPackage(pack);
                        sending.add(pack);
                    }
                }
            }

            ArrayList<Integer> zipCodes = new ArrayList<>();
            for (Package temp : warehousePackages) {
                if (temp.getDestination().getZipCode() != getZipDest()) {
                    zipCodes.add(temp.getDestination().getZipCode());
                }
            }

            int currentZipCode = this.getZipDest();
            int theZip = 1000000000;
            for (int temp : zipCodes) {
                if (temp > currentZipCode && temp < theZip) {
                    theZip = temp;
                }
            }
            currentZipCode = theZip;
            for (Package pack : warehousePackages) {
                if (pack.getDestination().getZipCode() == currentZipCode) {
                    if (currentWeight + pack.getWeight() <= maxWeight && !packages.contains(pack)) {
                        addPackage(pack);
                    }
                }
            }
            currentZipCode = this.getZipDest();
            theZip = 0;
            for (int temp : zipCodes) {
                if (temp < currentZipCode && temp > theZip) {
                    theZip = temp;
                }
            }
            currentZipCode = theZip;
            for (Package pack : warehousePackages) {
                if (pack.getDestination().getZipCode() == currentZipCode) {
                    if (currentWeight + pack.getWeight() <= maxWeight && !packages.contains(pack)) {
                        addPackage(pack);
                    }
                }
                if (isFull()) {
                    break;
                }
                if (packages.size() == warehousePackages.size()) {
                    break;
                }
            }
        }
        */
    }
}