import java.sql.*;
import java.util.Scanner;

public class FinalDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/FinalsDB";     //Connection string – where's the database?
    static final String USER = "mikey";
    static final String PASSWORD = "mikedodge";
    private static final String TABLE_NAME = "femalesuggestions";
    private static final String ID_NUMBER = "idNumber";
    private static final String TOP_COL = "FemaleTopSuggestions";
    private static final String BOTTOM_COL = "FemaleBottomsSuggestions";
    private static final String SHOES_COL = "FemaleShoesSuggestions";
    private static final String ACCESSORIES_COL = "FemaleAccessoriesSuggestions";
    private static final String TEMP_COL = "FemaleTempSuggestions";
    private static final String OCCASION_COL = "FemaleOccasionColumn";
    Scanner newScanner = new Scanner(System.in);

    FinalDB(){
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
            String createTableSQLTemplate= "CREATE TABLE IF NOT EXISTS FinalsDB (idNumber int(20) PRIMARY KEY, TopColumn varchar(20), BottomsColumn varchar(20)," +
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
        try (Connection conn= DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
             Statement statement = conn.createStatement()) {
            //add data to table
            String preparedStatementInsert = "INSERT INTO TABLE_NAME VALUES(?,?,?,?,?,?)";
            PreparedStatement psInsert = conn.prepareStatement(preparedStatementInsert);
            //  psInsert.setInt(1,1);psInsert.setString(2,"Peacoat");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"High Heels");psInsert.setString(5,"Chunky Scarf");psInsert.setString(6,"super cold");psInsert.setString(7, "Business");
            //psInsert.setInt(1,2);psInsert.setString(2,"Anorak") ;psInsert.setString(3,"Black Slacks");psInsert.setString(4,"High Heels");psInsert.setString(5,"Chunky Scarf");psInsert.setString(6,"super cold");psInsert.setString(7, "Business");
            //psInsert.setInt(1,3);psInsert.setString(2, "Fleece Jogger");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"High Heels");psInsert.setString(5,"Chunky Scarf");psInsert.setString(5,"super cold");psInsert.setString(7, "Business");
            //psInsert.setInt(1,4);psInsert.setString(2,"Peacoat");psInsert.setString(3,"Black Slacks");psInsert.setString(4,"High Heels");psInsert.setString(5,"Chunky Scarf");psInsert.setString(6,"super cold");psInsert.setString(7, "Business");













        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
