package dummy.artifact;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.socrata.api.Soda2Consumer;
import com.socrata.model.soql.SoqlQuery;

import model.FoodTruck;
import utils.ConfigParser;

public class TestFinder {
    private String configPath = null;
    private ConfigParser config = null;
    private Soda2Consumer consumer = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        config = new ConfigParser(configPath);
        consumer = Soda2Consumer.newConsumer(config.getServiceUrl());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void test1() {
        configPath = "/config1.properties";
        String[] queryValues = { "2", "11:00", "11:00" };
        SoqlQuery query = config.buildQuery(queryValues);
        List<FoodTruck> response;
        try {
            response = consumer.query(config.getResourceId(), query, FoodTruck.LIST_TYPE);
            for (FoodTruck truck : response) {
                System.out.printf("'%20s' '%s'%n", truck.getApplicant(), truck.getLocation());
            }
        } catch (Exception e) {
            fail("Not expected");
        }
    }

    @Test
    public final void test2() {
        configPath = "/config2.properties";
        String[] queryValues = { "3", "10:00", "10:00" };
        SoqlQuery query = config.buildQuery(queryValues);
        List<FoodTruck> response;
        try {
            response = consumer.query(config.getResourceId(), query, FoodTruck.LIST_TYPE);
            for (FoodTruck truck : response) {
                System.out.printf("'%20s' '%s'%n", truck.getApplicant(), truck.getLocation());
            }
        } catch (Exception e) {
            fail("Not expected");
        }
    }

}
