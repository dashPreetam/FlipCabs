import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Customer {

    private String Name;
    private double avgRating;
    private HashMap<Driver, Double> hasRodeWith;

    public Customer() {

        Name = new String();
        avgRating = 5.0;
        hasRodeWith = new HashMap<>();

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getAvgRating() {
        return avgRating;
    }

    private void setAvgRating() {

        int numberOfRatings = hasRodeWith.size();
        double sum=0.0;
        for (Driver driver:hasRodeWith.keySet()) {
            sum=sum+hasRodeWith.get(driver);
        }
        avgRating = sum/numberOfRatings;

    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public List<Driver> getHasRodeWith() {
        List<Driver> drivers = new LinkedList<>();
        for (Driver driver:hasRodeWith.keySet()) {

            drivers.add(driver);

        }
        return drivers;
    }

    public void setHasRodeWith(Driver driver, double rating) {
        hasRodeWith.put(driver,rating);
        setAvgRating();
    }
}
