import java.sql.*;
import java.util.Scanner;

public class FinalDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/FinalsDB";     //Connection string – where's the database?
    static final String USER = "root";
    static final String PASSWORD = "itecitec";
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
            String createTableSQLTemplate= "CREATE TABLE IF NOT EXISTS femalesuggestions (idNumber int(20) PRIMARY KEY, TopColumn varchar(20), BottomsColumn varchar(20)," +
                    "ShoesColumn varchar(20), AccessoriesColumn varchar(20), TemperatureColumn varchar(10), OccasionColumn varchar(10))" ;
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
            String preparedStatementInsert = "INSERT INTO TABLE_NAME VALUES(?,?,?,?,?,?,?)";
            PreparedStatement psInsert = conn.prepareStatement(preparedStatementInsert);
            psInsert.setInt(1,1);psInsert.setString(2,"Peacoat");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"High Heels");psInsert.setString(5,"Chunky Scarf");psInsert.setString(6,"super cold");psInsert.setString(7, "Business");
            psInsert.setInt(1,2);psInsert.setString(2,"Anorak") ;psInsert.setString(3,"Skinny Jeans");psInsert.setString(4,"Moon Boots");psInsert.setString(5,"Furry Hat");psInsert.setString(6,"super cold");psInsert.setString(7, "Casual");
            psInsert.setInt(1,3);psInsert.setString(2, "Fleece Jogger");psInsert.setString(3,"Sweatpants");psInsert.setString(4,"Athletic Shoes");psInsert.setString(5,"Beanie");psInsert.setString(5,"super cold");psInsert.setString(7, "Athletic");
            psInsert.setInt(1,4);psInsert.setString(2,"Parka With A Dress");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"Wedged Booties");psInsert.setString(5,"Light Scarf");psInsert.setString(6,"super cold");psInsert.setString(7, "Event");
            psInsert.setInt(1,5);psInsert.setString(2,"Trenchcoat");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"Wedged Booties");psInsert.setString(5,"Gloves");psInsert.setString(6,"cold");psInsert.setString(7, "Business");
            psInsert.setInt(1,6);psInsert.setString(2,"Leather Jacket") ;psInsert.setString(3,"Jeans");psInsert.setString(4,"Hiking Boots");psInsert.setString(5,"Light Scarf");psInsert.setString(6," cold");psInsert.setString(7, "Casual");
            psInsert.setInt(1,7);psInsert.setString(2, "Fleece Jogger");psInsert.setString(3,"Yoga Pants");psInsert.setString(4,"Tennis Shoes");psInsert.setString(5,"Earmuffs");psInsert.setString(5,"cold");psInsert.setString(7, "Athletic");
            psInsert.setInt(1,8);psInsert.setString(2,"Blazer");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"Chunky Heels");psInsert.setString(5,"Red Lipstick");psInsert.setString(6,"cold");psInsert.setString(7, "Event");
            psInsert.setInt(1,9);psInsert.setString(2,"Blazer");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"Knee High Boots");psInsert.setString(5,"Hairbow");psInsert.setString(6,"medium");psInsert.setString(7, "Business");
            psInsert.setInt(1,10);psInsert.setString(2,"Duster") ;psInsert.setString(3,"Black Leggings");psInsert.setString(4,"Moccassin Boots");psInsert.setString(5,"Fringed Purse");psInsert.setString(6,"medium");psInsert.setString(7, "Casual");
            psInsert.setInt(1,11);psInsert.setString(2, "Long Sleeve Zip-Up");psInsert.setString(3,"Yoga Pants");psInsert.setString(4,"Running Shoes");psInsert.setString(5,"Fitbit");psInsert.setString(5,"medium");psInsert.setString(7, "Athletic");
            psInsert.setInt(1,12);psInsert.setString(2,"Blazer");psInsert.setString(3,"Pencil Skirt");psInsert.setString(4,"Ballet Flats");psInsert.setString(5,"Earrings");psInsert.setString(6,"medium");psInsert.setString(7, "Event");
            psInsert.setInt(1,13);psInsert.setString(2,"Printed Tunic");psInsert.setString(3,"Black Tights");psInsert.setString(4,"Knee High Boots");psInsert.setString(5,"Watch");psInsert.setString(6,"warm");psInsert.setString(7, "Business");
            psInsert.setInt(1,14);psInsert.setString(2,"Band T-Shirt") ;psInsert.setString(3,"Jean Shorts");psInsert.setString(4,"Gladiator Sandals");psInsert.setString(5,"Fringed Purse");psInsert.setString(6,"warm");psInsert.setString(7, "Casual");
            psInsert.setInt(1,15);psInsert.setString(2, "Tank Top");psInsert.setString(3,"Athletic Shorts");psInsert.setString(4,"Running Shoes");psInsert.setString(5,"Baseball Hat");psInsert.setString(5,"warm");psInsert.setString(7, "Athletic");
            psInsert.setInt(1,16);psInsert.setString(2,"Dress");psInsert.setString(3,"Thigh high socks");psInsert.setString(4,"Wedged High Heels");psInsert.setString(5,"Necklace");psInsert.setString(6,"warm");psInsert.setString(7, "Event");
            psInsert.setInt(1,17);psInsert.setString(2,"Billowy Tank Top");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"Open Toed Heels");psInsert.setString(5,"Earrings");psInsert.setString(6,"hot");psInsert.setString(7, "Business");
            psInsert.setInt(1,18);psInsert.setString(2,"Sundress") ;psInsert.setString(3,"Thigh High Socks");psInsert.setString(4,"Flip Flops");psInsert.setString(5,"Sunglasses");psInsert.setString(6,"hot");psInsert.setString(7, "Casual");
            psInsert.setInt(1,19);psInsert.setString(2, "Tank Top");psInsert.setString(3,"Bicycle Shorts");psInsert.setString(4,"Tennis Shoes");psInsert.setString(5,"Headband");psInsert.setString(5,"hot");psInsert.setString(7, "Athletic");
            psInsert.setInt(1,20);psInsert.setString(2,"Tank Top Blouse");psInsert.setString(3,"Pencil Skirt");psInsert.setString(4,"Espadrilles");psInsert.setString(5,"Lipstick");psInsert.setString(6,"hot");psInsert.setString(7, "Event");
            psInsert.executeUpdate();
            System.out.println("Values Added!");

            psInsert.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }



        public String selectingAccessories(){
        String finished =null;
        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                Statement statement = conn.createStatement()) {
            System.out.println("Hot Weather Gear! ");
            String selectAcces = "SELECT FemaleAccessoriesSuggestion FROM finalsdb.femalesuggestions";
            ResultSet rsAccess = statement.executeQuery(selectAcces);
            while (rsAccess.next()) {
                String accessH = rsAccess.getObject(5).toString();
                //print what to wear
                finished = "To accessorize you should wear a :"+ accessH;
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
            String selectSuper = "SELECT t.* FROM finalsdb.femalesuggestions t WHERE TemperatureColumn = 'super'";
            ResultSet rsSuperCold = statement.executeQuery(selectSuper);
            while (rsSuperCold.next()) {
                String topSC = rsSuperCold.getObject(2).toString();
                String bottomSC = rsSuperCold.getObject(3).toString();
                String shoesSC = rsSuperCold.getObject(4).toString();
                String accessSC = rsSuperCold.getObject(5).toString();
                finished = "You should wear a " + topSC + ", " + bottomSC + ", " + shoesSC + ", and " + accessSC + " !";
                ;
                System.out.println(finished);
                rsSuperCold.close();
            }

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
            String selectCold = "SELECT t.* FROM finalsdb.femalesuggestions t WHERE TemperatureColumn = 'cold'";
            ResultSet rsCold = statement.executeQuery(selectCold);
            while (rsCold.next()) {
                String topCC = rsCold.getObject(2).toString();
                String bottomCC = rsCold.getObject(3).toString();
                String shoesCC = rsCold.getObject(4).toString();
                String accessCC = rsCold.getObject(5).toString();
                //print what to wear

                finished = "You should wear a " + topCC + ", " + bottomCC + ", " + shoesCC + ", and " + accessCC + " !";

                rsCold.close();
            }

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
                String selectMedium ="SELECT t.* FROM finalsdb.femalesuggestions t WHERE TemperatureColumn = 'cold'";
                ResultSet rsMedium = statement.executeQuery(selectMedium);
                while (rsMedium.next()) {
                    String topM= rsMedium.getObject(2).toString();
                    String bottomM = rsMedium.getObject(3).toString();
                    String shoesM = rsMedium.getObject(4).toString();
                    String accessM = rsMedium.getObject(5).toString();
                    //print what to wear

                    finished= "You should wear a " + topM + ", " + bottomM + ", " + shoesM + ", and " + accessM + " !";
                    rsMedium.close();
                }
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
                String selectWarm ="SELECT t.* FROM finalsdb.femalesuggestions t WHERE TemperatureColumn = 'warm'";
                ResultSet rsWarm = statement.executeQuery(selectWarm);
                while (rsWarm.next()) {
                    String topW= rsWarm.getObject(2).toString();
                    String bottomW = rsWarm.getObject(3).toString();
                    String shoesW = rsWarm.getObject(4).toString();
                    String accessW = rsWarm.getObject(5).toString();
                    //print what to wear

                    finished = "You should wear a " + topW + ", " + bottomW + ", " + shoesW + ", and " + accessW + " !";
                    rsWarm.close();

                }
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
            String selectHot = "SELECT t.* FROM finalsdb.femalesuggestions t WHERE TemperatureColumn = 'hot'";
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
