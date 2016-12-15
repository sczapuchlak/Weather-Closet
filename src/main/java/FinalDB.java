import com.sun.glass.ui.Size;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class FinalDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/WeatherCloset";     //Connection string – where's the database?
    static final String USER = "sczapuchlak";
    static final String PASSWORD = "stardust123";
    private static final String TABLE_NAME = "femalesuggestions";
    private static final String ID_NUMBER = "idNumber";
    private static final String TOP_COL = "FemaleTopSuggestions";
    private static final String BOTTOM_COL = "FemaleBottomsSuggestions";
    private static final String SHOES_COL = "FemaleShoesSuggestions";
    private static final String ACCESSORIES_COL = "FemaleAccessoriesSuggestions";
    private static final String TEMP_COL = "FemaleTempSuggestions";
    private static final String OCCASION_COL = "FemaleOccasionColumn";
    Scanner newScanner = new Scanner(System.in);

    FinalDB() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drivers and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }


    }

    public void createTable(){

        try (Connection conn= DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
             Statement statement = conn.createStatement()) {
            //You should have already created a database via terminal/command prompt OR MySQL Workbench

            //Create a table in the database, if it does not exist already
            String createTableSQLTemplate= "CREATE TABLE IF NOT EXISTS TABLE_NAME (idNumber int NOT NULL, TopColumn varchar(20), BottomsColumn varchar(20)," +
                    "ShoesColumn varchar(20), AccessoriesColumn varchar(20), TemperatureColumn varchar(10), OccasionColumn varchar(10), Primary Key(idNumber))" ;
            String createTableSQL = String.format(createTableSQLTemplate,ID_NUMBER,TABLE_NAME,TOP_COL,BOTTOM_COL,SHOES_COL,ACCESSORIES_COL,TOP_COL,OCCASION_COL);

            System.out.println(createTableSQL);
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Female Clothing Suggestion Table!");


            statement.close();
            conn.close();


        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    void addDataToTable(){
        //Connect to the connection again
        // because of the databases being setup differently on my computer through intelliJ
        //as a last resort I used insert statements to make sure that the articles of clothing
        //were in fact added into the database



        try (Connection conn= DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
             Statement statement = conn.createStatement()) {
            //add data to table

            String preparedStatementInsert = "INSERT INTO TABLE_NAME(UUID(), TopColumn, BottomsColumn, ShoesColumn, AccessoriesColumn,TemperatureColumn, OccasionColumn ) VALUES(null,?,?,?,?,?,?)";
            PreparedStatement psInsert = conn.prepareStatement(preparedStatementInsert);
            psInsert = conn.prepareStatement(preparedStatementInsert,PreparedStatement.RETURN_GENERATED_KEYS);

            psInsert.setString(1,"Peacoat");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"High Heels");psInsert.setString(4,"Chunky Scarf");psInsert.setString(5,"super cold");psInsert.setString(6, "Business");
            psInsert.setString(1,"Anorak") ;psInsert.setString(2,"Skinny Jeans");psInsert.setString(3,"Moon Boots");psInsert.setString(4,"Furry Hat");psInsert.setString(5,"super cold");psInsert.setString(6, "Casual");
            psInsert.setString(1, "Fleece Jogger");psInsert.setString(2,"Sweatpants");psInsert.setString(3,"Athletic Shoes");psInsert.setString(4,"Beanie");psInsert.setString(5,"super cold");psInsert.setString(6, "Athletic");
            psInsert.setString(1,"Parka With A Dress");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"Wedged Booties");psInsert.setString(4,"Light Scarf");psInsert.setString(5,"super cold");psInsert.setString(6, "Event");
            psInsert.setString(1,"Trenchcoat");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"Wedged Booties");psInsert.setString(4,"Gloves");psInsert.setString(5,"cold");psInsert.setString(6, "Business");
            psInsert.setString(1,"Leather Jacket") ;psInsert.setString(2,"Jeans");psInsert.setString(3,"Hiking Boots");psInsert.setString(4,"Light Scarf");psInsert.setString(5,"cold");psInsert.setString(6, "Casual");
            psInsert.setString(1, "Fleece Jogger");psInsert.setString(2,"Yoga Pants");psInsert.setString(3,"Tennis Shoes");psInsert.setString(4,"Earmuffs");psInsert.setString(5,"cold");psInsert.setString(6, "Athletic");
            psInsert.setString(1,"Blazer");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"Chunky Heels");psInsert.setString(4,"Red Lipstick");psInsert.setString(5,"cold");psInsert.setString(6, "Event");
            psInsert.setString(1,"Blazer");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"Knee High Boots");psInsert.setString(4,"Hairbow");psInsert.setString(5,"medium");psInsert.setString(6, "Business");
            psInsert.setString(1,"Duster") ;psInsert.setString(2,"Black Leggings");psInsert.setString(3,"Moccassin Boots");psInsert.setString(4,"Fringed Purse");psInsert.setString(5,"medium");psInsert.setString(6, "Casual");
            psInsert.setString(1, "Long Sleeve Zip-Up");psInsert.setString(2,"Yoga Pants");psInsert.setString(3,"Running Shoes");psInsert.setString(4,"Fitbit");psInsert.setString(5,"medium");psInsert.setString(6, "Athletic");
            psInsert.setString(1,"Blazer");psInsert.setString(2,"Pencil Skirt");psInsert.setString(3,"Ballet Flats");psInsert.setString(4,"Earrings");psInsert.setString(5,"medium");psInsert.setString(6, "Event");
            psInsert.setString(1,"Printed Tunic");psInsert.setString(2,"Black Tights");psInsert.setString(3,"Knee High Boots");psInsert.setString(4,"Watch");psInsert.setString(5,"warm");psInsert.setString(6, "Business");
            psInsert.setString(1,"Band T-Shirt") ;psInsert.setString(2,"Jean Shorts");psInsert.setString(3,"Gladiator Sandals");psInsert.setString(4,"Fringed Purse");psInsert.setString(5,"warm");psInsert.setString(6, "Casual");
            psInsert.setString(1, "Tank Top");psInsert.setString(2,"Athletic Shorts");psInsert.setString(3,"Running Shoes");psInsert.setString(4,"Baseball Hat");psInsert.setString(5,"warm");psInsert.setString(6, "Athletic");
            psInsert.setString(1,"Dress");psInsert.setString(2,"Thigh high socks");psInsert.setString(3,"Wedged High Heels");psInsert.setString(4,"Necklace");psInsert.setString(5,"warm");psInsert.setString(6, "Event");
            psInsert.setString(1,"Billowy Tank Top");psInsert.setString(2,"Black Slacks");psInsert.setString(3,"Open Toed Heels");psInsert.setString(4,"Earrings");psInsert.setString(5,"hot");psInsert.setString(6, "Business");
            psInsert.setString(1,"Sundress") ;psInsert.setString(2,"Thigh High Socks");psInsert.setString(3,"Flip Flops");psInsert.setString(4,"Sunglasses");psInsert.setString(5,"hot");psInsert.setString(6, "Casual");
            psInsert.setString(1, "Tank Top");psInsert.setString(2,"Bicycle Shorts");psInsert.setString(3,"Tennis Shoes");psInsert.setString(4,"Headband");psInsert.setString(5,"hot");psInsert.setString(6, "Athletic");
            psInsert.setString(1,"Tank Top Blouse");psInsert.setString(2,"Pencil Skirt");psInsert.setString(3,"Espadrilles");psInsert.setString(4,"Lipstick");psInsert.setString(5,"hot");psInsert.setString(6, "Event");
            System.out.println("Values Added!");

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }



        public String selectingAccessories(){
        String finished =null;
        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {
            String selectAcces = "SELECT AccessoriesColumn FROM WeatherCloset.TABLE_NAME";
            ResultSet rsAccess = statement.executeQuery(selectAcces);
            ArrayList<String> AccessoryChoices = new ArrayList<String>();
            while (rsAccess.next()) {
               AccessoryChoices.add(rsAccess.getString(1));
               Collections.shuffle(AccessoryChoices);
               String AccessSuggest = AccessoryChoices.get(0);

                //print what to wear
                finished = "To accessorize you should wear a : "+AccessSuggest;
            }
        }     catch (SQLException se) {
        se.printStackTrace();


    }return finished;
}



    public String selectingSuperColdQueries() {

        String finished = null;
        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {

            //a little redundant, but until I figure out how to do it, this works
            //go through each category and print each query
            System.out.println("Super Cold Weather Gear !");
            String selectSuper = "SELECT * FROM WeatherCloset.TABLE_NAME  WHERE TemperatureColumn = 'super cold'";
            ResultSet rsSuperCold = statement.executeQuery(selectSuper);
            while (rsSuperCold.next()) {
                String topSC = rsSuperCold.getObject(2).toString();
                String bottomSC = rsSuperCold.getObject(3).toString();
                String shoesSC = rsSuperCold.getObject(4).toString();
                String accessSC = rsSuperCold.getObject(5).toString();
                finished = "You should wear a " + topSC + ", " + bottomSC + ", " + shoesSC + ", and " + accessSC + " !";
                ;
                System.out.println(finished);

            }
            rsSuperCold.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return (finished);
    }

    public String selectingColdQueries() {
        String finished = null;

        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {

            System.out.println("Cold Weather Gear! ");
            String selectCold = "SELECT t* FROM WeatherCloset.TABLE_NAME t  WHERE TemperatureColumn = 'cold'";
            ResultSet rsCold = statement.executeQuery(selectCold);
            while (rsCold.next()) {
                String topCC = rsCold.getObject(2).toString();
                String bottomCC = rsCold.getObject(3).toString();
                String shoesCC = rsCold.getObject(4).toString();
                String accessCC = rsCold.getObject(5).toString();
                //print what to wear

                finished = "You should wear a " + topCC + ", " + bottomCC + ", " + shoesCC + ", and " + accessCC + " !";


            }
            rsCold.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return finished;
    }
    public String selectingMediumQueries() {
        String finished = null;

        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {

            System.out.println( "Neutral Weather Gear! ");
                String selectMedium ="SELECT * FROM WeatherCloset.TABLE_NAME  WHERE TemperatureColumn = 'medium'";
                ResultSet rsMedium = statement.executeQuery(selectMedium);
                while (rsMedium.next()) {
                    String topM= rsMedium.getObject(2).toString();
                    String bottomM = rsMedium.getObject(3).toString();
                    String shoesM = rsMedium.getObject(4).toString();
                    String accessM = rsMedium.getObject(5).toString();
                    //print what to wear

                    finished= "You should wear a " + topM + ", " + bottomM + ", " + shoesM + ", and " + accessM + " !";

                }
            rsMedium.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return finished;

    }
    public String selectingWarmQueries() {
        String finished = null;
        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {

                System.out.println( "Warm Weather Gear! ");
                String selectWarm ="SELECT * FROM WeatherCloset.TABLE_NAME  WHERE TemperatureColumn = 'warm'";
                ResultSet rsWarm = statement.executeQuery(selectWarm);
                while (rsWarm.next()) {
                    String topW= rsWarm.getObject(2).toString();
                    String bottomW = rsWarm.getObject(3).toString();
                    String shoesW = rsWarm.getObject(4).toString();
                    String accessW = rsWarm.getObject(5).toString();
                    //print what to wear

                    finished = "You should wear a " + topW + ", " + bottomW + ", " + shoesW + ", and " + accessW + " !"
            ;


                }rsWarm.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return finished;
    }

    public String selectingHotQueries() {
        String finished = null;
        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {
            System.out.println("Hot Weather Gear! ");
            String selectHot = "SELECT * FROM WeatherCloset.TABLE_NAME  WHERE TemperatureColumn = 'hot'";
            ResultSet rsHot = statement.executeQuery(selectHot);
            while (rsHot.next()) {
                String topH = rsHot.getObject(2).toString();
                String bottomH = rsHot.getObject(3).toString();
                String shoesH = rsHot.getObject(4).toString();
                String accessH = rsHot.getObject(5).toString();
                //print what to wear
                finished = "You should wear a " + topH + ", " + bottomH + ", " + shoesH + ", and " + accessH + " !";
            }


            rsHot.close();
            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return finished;


    }}
