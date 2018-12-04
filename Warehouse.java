import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
    	//TODO
        Scanner s = new Scanner(System.in);
        String divider = "================";
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        ArrayList<Vehicle> vehicles;
        ArrayList<Package> packages;
        double profit;
        int packagesShipped;
        boolean primeDay;
        String primeString;

        if (primeDay) {
            primeString = "Deactivate Prime Day";
        } else {
            primeString = "Activate Prime Day";
        }
    	
    	//2) Show menu and handle user inputs
    	while (true) {

            System.out.println("========Options=========" +
                    "\n1) Add Package" +
                    "\n2) Add Vehicle" +
                    "\n3) " + primeString +
                    "\n4) Send Vehicle" +
                    "\n5) Print Statistics" +
                    "\n6) Exit" +
                    "\n======================");
            String r = s.nextLine();

            switch(r) {
                case "1":

                    while (true) {
                        try {
                            System.out.println("Enter Package ID:");
                            String id = s.nextLine();
                            System.out.println("Enter Product Name:");
                            String productName = s.nextLine();
                            s.next();
                            System.out.println("Enter Weight:");
                            double weight = s.nextDouble();
                            System.out.println("Enter Price:");
                            double price = s.nextDouble();
                            s.next();
                            System.out.println("Enter Buyer Name:");
                            String buyerName = s.nextLine();
                            System.out.println("Enter Address:");
                            String address = s.nextLine();
                            System.out.println("Enter City:");
                            String city = s.nextLine();
                            System.out.println("Enter State:");
                            String state = s.nextLine();
                            s.next();
                            System.out.println("Enter ZIP Code:");
                            int zipCode = s.nextInt();
                            s.next();
                            Package p = new Package(id, productName, weight, price,
                                    new ShippingAddress(buyerName, address, city, state, zipCode));
                            System.out.println("\n\n" + p.shippingLabel());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Illegal input!");
                        }
                    }
                    break;

                case "2":
                    System.out.println("Vehicle Options:" +
                            "\n1) Truck" +
                            "\n2) Drone" +
                            "\n3) Cargo Plane");
                    r = s.nextLine();
                    while (true) {
                        try {
                            int intR = Integer.parseInt(r);

                            if (intR < 0 || intR > 3) {
                                System.out.println("Error: Option not available.");
                                continue;
                            }

                            System.out.println("Enter License Plate No.:");
                            String licensePlate = s.nextLine();
                            s.next();
                            System.out.println("Enter Maximum Carry Weight:");
                            int carryWeight = s.nextInt();
                            s.next();

                            Vehicle v;

                            switch (intR) {
                                case 1:
                                    v = new Truck(licensePlate, carryWeight);
                                    break;
                                case 2:
                                    v = new Drone(licensePlate, carryWeight);
                                    break;
                                case 3:
                                    v = new CargoPlane(licensePlate, carryWeight);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Option not available.");
                            continue;
                        }
                        break;
                    }
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Error: Option not available.");

            }
        }
    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}
