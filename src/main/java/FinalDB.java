import java.sql.*;
import java.util.Scanner;

public class FinalDB extends FinalProjectGUI {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/FinalsDB";     //Connection string – where's the database?
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
    void selectingQueries(){
        
            try(
                Connection conn= DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
                Statement statement = conn.createStatement()) {

              //if (){
                String selectAllSQL = "SELECT * FROM " + TABLE_NAME;
            ResultSet rsAll = statement.executeQuery(selectAllSQL);
                while (rsAll.next()) {
                    String name = rsAll.getObject(1).toString();
                    String gender = rsAll.getObject(2).toString();
                    System.out.println("The ID Number is " + name + " and you should wear a " + gender);
                    //Queries for Super cold weather and different Occasions
                    //"SELECT * FROM femalesuggestions WHERE TEMP = 'super' AND OCCASION = 'Business'";
                   // "SELECT * FROM "+ TABLE_NAME "WHERE TEMP = 'super' AND OCCASION = 'Casual'";
                   // "SELECT * FROM "+ TABLE_NAME "WHERE TEMP = 'super' AND OCCASION = 'Athletic'";
                  //  "SELECT * FROM "+ TABLE_NAME "WHERE TEMP = 'super' AND OCCASION = 'Event'";


            }
                rsAll.close();
                statement.close();
                conn.close();

        } catch (SQLException se) {
            se.printStackTrace();




        }
    }
}
