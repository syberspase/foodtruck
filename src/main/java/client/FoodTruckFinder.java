package client;

import java.util.List;
import java.util.Scanner;

import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;

import exceptions.FinderException;
import model.FoodTruck;
import utils.ConfigParser;
import utils.DateTimeUtil;

/**
 * Client to find open food trucks of now
 * to run: 
 * $ javac FoodTruckFinder.java && java FoodTruckFinder
 */
public class FoodTruckFinder {
    private ConfigParser config;
    private Soda2Consumer consumer;
    
    /**
     * Constructor
     * @throws FinderException
     */
    public FoodTruckFinder() throws FinderException {
        // Init consumer with config
        config = new ConfigParser(null);
        consumer = Soda2Consumer.newConsumer(config.getServiceUrl());
    }

    /**
     * Finds first or next page of open food trucks
     */
    public void findOpenFoodTrucks() {
        try {
            // Construct SoQL query
            // sample data: String[] queryValues = { "3", "10:00", "10:00" };
            String[] queryValues = { DateTimeUtil.getDayOrder() + "", DateTimeUtil.getTime24(), DateTimeUtil.getTime24() };
            SoqlQuery query = config.buildQuery(queryValues);
            
            // Handle response
            List<FoodTruck> response = consumer.query(config.getResourceId(), query, FoodTruck.LIST_TYPE);
            for (FoodTruck truck : response) {
                System.out.printf("'%20s' '%s'%n", truck.getApplicant(), truck.getLocation());
            }
        } catch (SodaError e) {
            // Not expected so far
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // Not expected so far
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Client main function
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            FoodTruckFinder finder = new FoodTruckFinder();
            while (true) {
                System.out.println("Please press enter for next 5 results, press q to quit");
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("q"))
                    break;
                finder.findOpenFoodTrucks();
            }
        } catch(Exception e) {
            System.out.println("Exiting");
        }
    }
}
