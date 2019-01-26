import java.util.HashMap;

public class Driver {
    
    private String Name;
    private double avgRating;
    private HashMap<Customer, Double> custRatings;
    private boolean isOnline;

    public Driver() {
        Name = new String();
        avgRating = 5.0;
        custRatings = new HashMap<>();
        isOnline = true;
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
        
        int numberOfRatings = custRatings.size();
        double sum=0.0;
        for (Customer customer:custRatings.keySet()) {
            sum=sum+custRatings.get(customer);
        }
        avgRating = sum/numberOfRatings;
        
    }


    void setAvgRating(double rating) {

        avgRating = rating;

    }

    public double getCustRatings(Customer customer) {

        if (custRatings.containsKey(customer))
            return custRatings.get(customer);
        else
            return 5.0;
    }

    public void setCustRatings(Customer customer, double rating) {
        if(custRatings.isEmpty()||!custRatings.containsKey(customer))
            custRatings.put(customer,Double.valueOf(rating));
        else
        {
            double currentRating =  custRatings.get(customer);
            double avg = (currentRating+Double.valueOf(rating))/2;
            custRatings.replace(customer,avg);
        }
        
        setAvgRating();
    }

    public boolean getisOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }
}
