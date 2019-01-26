import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    static HashMap<String, Driver> allDrivers = new HashMap<>();
    static HashMap<String, Customer> allCustomers = new HashMap<>();


    public static void main(String[] args) {

        init();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Driver menu ");
            System.out.println("2. Customer menu ");
            System.out.println("\n99. Exit");
            System.out.print("\n\nChoice :");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    driverSection();
                    break;
                case 2:
                    customerSection();
                    break;
                default:
                    System.out.println("Wrong choice !!");
            }
        } while (choice != 99);

    }

    static void driverSection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nDrivers section");
        System.out.println("\n\n\n\n");
        System.out.println("1. Add Driver ");
        System.out.println("2. View All Drivers ");
        System.out.println("3. Change Driver Status ");


        System.out.print("Choice :");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addDriver();
                break;
            case 2: {
                System.out.println("\n All Driver :");
                if (!allDrivers.isEmpty())
                    for (String driver : allDrivers.keySet()) {
                        System.out.println("Name: " + allDrivers.get(driver).getName() + " isOnline : " + allDrivers.get(driver).getisOnline() + " Rating: " + allDrivers.get(driver).getAvgRating());
                    }
                else
                    System.out.println("No drivers added yet");
            }
            break;
            case 3:
                changeDriverStatus();
                break;
            default:
                System.out.println("Wrong Choice!!");

        }
    }

    static void addDriver() {
        Scanner scanner = new Scanner(System.in);
        Driver driver = new Driver();

        try {
            System.out.print("Name :");
            String name = scanner.nextLine();
            driver.setName(name);
            driver.setAvgRating(5.0);
            driver.setIsOnline(true);

            allDrivers.put(driver.getName(), driver);
            System.out.println("Driver added..");
        } catch (Exception e) {
            System.out.println("ERROR ADDING DRIVER");
        }

    }

    static void changeDriverStatus() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter name :");
            String name = scanner.nextLine();
            System.out.print("Enter status :");
            boolean online = scanner.nextBoolean();

            Driver driver = allDrivers.get(name);
            driver.setIsOnline(online);

            allDrivers.replace(name, driver);
            System.out.println("Status  updated.");

        } catch (Exception e) {
            System.out.println("ERROR Changing Status");
        }

    }

    static void customerSection() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nCustomer section");
        System.out.println("\n\n\n\n");
        System.out.println("1. Add Customer ");
        System.out.println("2. Book Cab ");
        System.out.println("3. Give Rating to Driver ");


        System.out.print("Choice :");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                bookCab();
                break;
            case 3:
                giveRating();
                break;
            default:
                System.out.println("Wrong Choice!!");
        }

    }

    static void addCustomer() {

        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();

        try {
            System.out.print("Name :");
            String name = scanner.nextLine();
            customer.setName(name);
            customer.setAvgRating(5.0);

            allCustomers.put(customer.getName(), customer);
            System.out.println("Driver added..");
        } catch (Exception e) {
            System.out.println("ERROR ADDING DRIVER");
        }


    }

    static void bookCab() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Customer name :");
        String custName = sc.nextLine();

        Customer customer = allCustomers.get(custName);

        List<Driver> suitableDrivers = new ArrayList<>();

        try {
            System.out.println("Customer  average rating : " + customer.getAvgRating());

            for (String driverName : allDrivers.keySet()) {
                Driver d = allDrivers.get(driverName);
                if (d.getAvgRating() >= customer.getAvgRating() && d.getisOnline())
                    if (d.getCustRatings(customer) > 1)
                        suitableDrivers.add(d);
            }

            if (suitableDrivers.isEmpty()) {
                for (Driver driver : customer.getHasRodeWith()) {
                    suitableDrivers.add(driver);
                }
            }

            System.out.println("All available drivers are :");
            for (Driver driver : suitableDrivers) {

                System.out.println("Name :" + driver.getName() + " Rating : " + driver.getAvgRating());

            }
            if (suitableDrivers.isEmpty())
                System.out.println("NO AVAILABLE DRIVERS..");
        } catch (Exception e) {
            System.out.println("UNEXPECTED  ERROR... TRY AGAIN");
        }
    }

    static void giveRating(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Customer name: ");
        String custName = scanner.nextLine();
        System.out.println("Driver name : ");
        String driverName = scanner.nextLine();
        System.out.println("Rating : ");
        double rating = scanner.nextDouble();

        if(!allCustomers.containsKey(custName)||!allDrivers.containsKey(driverName)){
            System.out.println("INVALID NAME");
        }
        else
        {
            Customer customer = allCustomers.get(custName);
            Driver driver = allDrivers.get(driverName);

            driver.setCustRatings(customer,rating);
        }
    }

    static void init() {
        Driver driver = new Driver();
        driver.setName("d1");
        driver.setAvgRating(5.0);
        driver.setIsOnline(true);
        allDrivers.put(driver.getName(), driver);
        Driver driver2 = new Driver();
        driver2.setName("d2");
        driver2.setAvgRating(4.0);
        driver2.setIsOnline(true);
        allDrivers.put(driver2.getName(), driver2);
        Driver driver3 = new Driver();
        driver3.setName("d3");
        driver3.setAvgRating(4.5);
        driver3.setIsOnline(true);
        allDrivers.put(driver3.getName(), driver3);

        Customer customer = new Customer();
        customer.setName("c1");
        customer.setAvgRating(4.5);
        allCustomers.put(customer.getName(), customer);
        Customer customer2 = new Customer();
        customer2.setName("c2");
        customer2.setAvgRating(6.5);
        allCustomers.put(customer2.getName(), customer2);
    }
}
