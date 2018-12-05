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
     * The loadVehicle method.
     * The method searches for a vehicle of the specified type that is not full, and returns it.
     * If there are no vehicles of the specified type, it will return null.
     * You can specify either a Truck, Drone, or CargoPlane subclass.
     * If anything else is entered, it will search for the first, non-full vehicle in the array list and return it.
     * @param className the String name of vehicle subclass.
     * @param vehicles the array list of vehicle that will be searched.
     * @return the non-full vehicle of the specified type.
     */
    public Vehicle loadVehicle(String className, ArrayList<Vehicle> vehicles) {
        for (Vehicle temp: vehicles) {
            switch (className) {
                case "Truck":
                    if (temp instanceof Truck && !temp.isFull()) {
                        return temp;
                    }
                    break;
                case "Drone":
                    if (temp instanceof Drone && !temp.isFull()) {
                        return temp;
                    }
                    break;
                case "CargoPlane":
                    if (temp instanceof CargoPlane && !temp.isFull()) {
                        return temp;
                    }
                    break;
                default:
                    if (!temp.isFull()) {
                        return temp;
                    }
                    break;
            }
        }
        return null;
    }


    public static void printStatisticsReport(double numProfits, int numPackagesShipped, int numPackagesInWarehouse) {
        System.out.println("==========Statistics==========");
        System.out.printf("Profits: %25s%n", "$" + String.format("%.2f", numProfits));
        System.out.printf("Packages Shipped: %16d%n", numPackagesShipped);
        System.out.printf("Packages in Warehouse: %11d%n", numPackagesInWarehouse);
    }
    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {

        printStatisticsReport(1000.00, 50, 6);
    	//TODO
        Scanner s = new Scanner(System.in);
        String divider = "================";
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        ArrayList<Vehicle> vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        double profit = DatabaseManager.loadProfit(PROFIT_FILE);
        int packagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        boolean primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
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
                //Add package
                case "1":
                    while (true) {
                        try {
                            //Enter Package Statistics
                            System.out.println("Enter Package ID:");
                            String id = s.nextLine();
                            System.out.println("Enter Product Name:");
                            String productName = s.nextLine();
                            System.out.println("Enter Weight:");
                            double weight = s.nextDouble();
                            System.out.println("Enter Price:");
                            double price = s.nextDouble();
                            s.nextLine();
                            System.out.println("Enter Buyer Name:");
                            String buyerName = s.nextLine();
                            System.out.println("Enter Address:");
                            String address = s.nextLine();
                            System.out.println("Enter City:");
                            String city = s.nextLine();
                            System.out.println("Enter State:");
                            String state = s.nextLine();
                            System.out.println("Enter ZIP Code:");
                            int zipCode = s.nextInt();
                            s.nextLine();
                            Package p = new Package(id, productName, weight, price,
                                    new ShippingAddress(buyerName, address, city, state, zipCode));
                            packages.add(p);
                            System.out.println("\n\n" + p.shippingLabel());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Illegal input!");
                        }
                    }
                    break;
                //Add vehicle
                case "2":
                    //Add vehicle
                    System.out.println("Vehicle Options:" +
                            "\n1) Truck" +
                            "\n2) Drone" +
                            "\n3) Cargo Plane");
                    r = s.nextLine();
                    while (true) {
                        try {
                            int intR = Integer.parseInt(r);
                            //If the selection is out of range
                            if (intR < 0 || intR > 3) {
                                System.out.println("Error: Option not available.");
                                continue;
                            }
                            System.out.println("Enter License Plate No.:");
                            String licensePlate = s.nextLine();
                            s.next();
                            System.out.println("Enter Maximum Carry Weight:");
                            int carryWeight = s.nextInt();
                            s.nextLine();
                            Vehicle v;
                            switch (intR) {
                                case 1:
                                    //Add truck
                                    v = new Truck(licensePlate, carryWeight);
                                    break;
                                case 2:
                                    //Add drone
                                    v = new Drone(licensePlate, carryWeight);
                                    break;
                                case 3:
                                    //Add cargo plane
                                    v = new CargoPlane(licensePlate, carryWeight);
                                    break;
                                default:
                                    throw new NumberFormatException();
                            }
                            vehicles.add(v);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Option not available.");
                            continue;
                        }
                        break;
                    }
                    break;
                //Activate Prime day
                case "3":
                    primeDay = !primeDay;
                    if (primeDay) {
                        //Activate prime day
                        for (Package temp: packages) {
                            temp.setPrice(temp.getPrice() - (temp.getPrice() * PRIME_DAY_DISCOUNT));
                        }
                        primeString = "Deactivate Prime Day";
                    } else {
                        //Deactivate prime day
                        for (Package temp: packages) {
                            temp.setPrice(temp.getPrice() + (temp.getPrice() * PRIME_DAY_DISCOUNT));
                        }
                        primeString  = "Activate Prime Day";
                    }
                    break;
                //Send vehicle
                case "4":
                    Vehicle d = null;
                    if (vehicles.size() == 0) {
                        System.out.println("Error: No vehicles available.");
                        break;
                    }
                    if (packages.size() == 0) {
                        System.out.println("Error: No packages available.");
                        break;
                    }
                    while (true) {
                        System.out.println("Options:" +
                                "\n1) Send Truck" +
                                "\n2) Send Drone" +
                                "\n3) Send Cargo Plane" +
                                "\n4) Send First Available");
                        r = s.nextLine();
                        boolean hasVehicle;
                        switch (r) {
                            //Search for empty truck
                            case "1":
                                hasVehicle = false;
                                for (Vehicle temp: vehicles) {
                                    if (temp instanceof Truck && !temp.isFull()) {
                                        d = temp;
                                        hasVehicle = true;
                                        break;
                                    }
                                }
                                if (!hasVehicle) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    continue;
                                }
                                break;
                            //Search for empty drone
                            case "2":
                                hasVehicle = false;
                                for (Vehicle temp: vehicles) {
                                    if (temp instanceof Drone && !temp.isFull()) {
                                        d = temp;
                                        hasVehicle = true;
                                        break;
                                    }
                                }
                                if (!hasVehicle) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    continue;
                                }
                                break;
                            //Search for empty cargo plane
                            case "3":
                                hasVehicle = false;
                                for (Vehicle temp: vehicles) {
                                    if (temp instanceof CargoPlane && !temp.isFull()) {
                                        d = temp;
                                        hasVehicle = true;
                                        break;
                                    }
                                }
                                if (!hasVehicle) {
                                    System.out.println("Error: No vehicles of selected type are available.");
                                    continue;
                                }
                                break;
                            //Search for first empty vehicles
                            case "4":
                                for (Vehicle temp: vehicles) {
                                    if (!temp.isFull()) {
                                        d = temp;
                                        break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Error: Option not available.");
                                continue;
                        }
                        //Set the zip code
                        while (true) {
                            System.out.println("ZIP Code Options:" +
                                    "\n1) Send to first ZIP Code" +
                                    "\n2) Send to mode of ZIP Codes");
                            r = s.nextLine();
                            switch (r) {
                                //Send to first zip code
                                case "1":
                                    d.setZipDest(packages.get(0).getDestination().getZipCode());
                                    d.fill(packages);
                                    profit += d.getProfit();
                                    packagesShipped += d.getPackages().s
                 
                                    vehicles.remove(d);
                                    break;
                                //Send to mode zip code
                                case "2":

                                    break;
                                default:
                                    System.out.println("Error Option not available.");
                                    continue;
                            }
                            break;
                        }
                        break;
                    }
                    break;
                //Print statistics
                case "5":
                    break;
                //Exit Program
                case "6":
                    return;
                default:
                    System.out.println("Error: Option not available.");
            }
        }
    	
    
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}
