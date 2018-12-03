import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 * 
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {


    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        try {
            ArrayList<Vehicle> loadVehicles = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String l;
            while ((l = br.readLine()) != null){
                if(l.contains("Truck") == true){

                    int index = l.indexOf(",");
                    int index2 = l.indexOf(",", index);

                    String license = l.substring(index, l.indexOf(",", index));
                    double maxWeight = Double.parseDouble(l.substring(index2));
                    Truck t = new Truck(license, maxWeight);

                    loadVehicles.add(t);


                } else if(l.contains("Drone") == true) {

                    int index = l.indexOf(",");
                    int index2 = l.indexOf(",", index);

                    String license = l.substring(index, l.indexOf(",", index));
                    double maxWeight = Double.parseDouble(l.substring(index2));
                    Drone t = new Drone(license, maxWeight);
                    loadVehicles.add(t);

                } else if (l.contains("Cargo Plane")) {

                    int index = l.indexOf(",");
                    int index2 = l.indexOf(",", index);

                    String license = l.substring(index, l.indexOf(",", index));
                    double maxWeight = Double.parseDouble(l.substring(index2));
                    CargoPlane t = new CargoPlane(license, maxWeight);
                    loadVehicles.add(t);


                }

            }
            br.close();
            fr.close();
            return loadVehicles;



        } catch (IOException e){
            ArrayList<Vehicle> loadVehicles = new ArrayList<>();
            return loadVehicles;
        }
    }

    
    
    
    
    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        try {
            ArrayList<Package> loadPackages = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String l;
            while ((l = br.readLine()) != null){
                    int index = l.indexOf(",");
                    int index2 = l.indexOf(",", index);
                    int index3 = l.indexOf(",", index2);
                    int index4 = l.indexOf(",", index3);
                    int index5 = l.indexOf(",", index4);
                    int index6 = l.indexOf(",", index5);
                    int index7 = l.indexOf(",", index6);
                    int index8 = l.indexOf(",", index7);
                    int index9 = l.indexOf(",", index8);


                String id = l.substring(index, index2);
                    String name = l.substring(index2, index3);
                    double weight = Double.parseDouble(l.substring(index3, index4));
                    double price = Double.parseDouble(l.substring(index4, index5));
                    String adname = l.substring(index5, index6);
                    String address = l.substring(index6, index7);
                    String city = l.substring(index7, index8);
                    String state = l.substring(index8, index9);
                    int zipcode = Integer.parseInt(l.substring(index9));

                    ShippingAddress ship = new ShippingAddress(adname, address, city, state, zipcode);
                    Package t = new Package(id, name, weight, price, ship);

                    loadPackages.add(t);
            }

            br.close();
            fr.close();
            return loadPackages;



        } catch (IOException e){
            ArrayList<Package> loadPackage = new ArrayList<>();
            return loadPackage;
        }
    }
    
    
    
    
    

    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     * 
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
    	//TODO
    }

    
    
    
    
    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     * 
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
    	//TODO
    }

    
    
    
    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     * 
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String x;
            while ((x = br.readLine()) != null){
                if(x.contains("1") == true){
                    return true;
                } else {
                    return false;
                }

            }


        } catch (IOException e){
            return false;
        }
    }

    
    
    
    
    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * 
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        try {
            FileWriter fw = new FileWriter(file, false);
            for(Vehicle veh : vehicles) {
                String x = "";
                if(veh instanceof CargoPlane == true){
                    x += "Cargo Plane,";
                }
                if(veh instanceof Drone == true){
                    x += "Drone,";
                }
                if(veh instanceof Truck == true){
                    x += "Truck,";
                }
                x += veh.getLicensePlate() + ",";
                x += veh.getMaxWeight();

                fw.write(x);


            }


        } catch (IOException e){

        }
    }

    
    
    
    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        try {
            FileWriter fw = new FileWriter(file, false);
            for(Package pack : packages) {
                String x = "";
                x += pack.getID() + ",";
                x += pack.getProduct() + ",";
                x += pack.getWeight() + ",";
                x += pack.getPrice() + ",";
                x += pack.getDestination().getName() + ",";
                x += pack.getDestination().getAddress() + ",";
                x += pack.getDestination().getCity() + ",";
                x += pack.getDestination().getState() + ",";
                x += pack.getDestination().getZipCode();


                fw.write(x);


            }


        } catch (IOException e){

        }
    }

    
    
    
    /**
     * Saves profit to text file.
     * 
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
    	//TODO
    }

    
    
    
    
    /**
     * Saves number of packages shipped to text file.
     * 
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
    	//TODO
    }

    
    
    
    
    
    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     * 
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        try {
            FileWriter fw = new FileWriter(file, false);
            if(primeDay = true) {
                fw.write(1);
            } else {
                fw.write(0);
            }


        } catch (IOException e){

        }
    }
}