import org.asynchttpclient.*;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//This program finds out where you are located
// and tells you what you should wear based on the weather.
//Used weather underground as my API.
// TODO: 12/3/2016 - ranges of temperatures are as follows: <=30 is super cold,>30 AND <= 50 is mediocrely cold
//TODO: cont. >50 AND <=68 is medium temp. > 68 AND <= 80 is warm. >80 is hot. This will be the guide
//TODO: for how the database shoots back with what to wear.



public class Main  {
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        FinalDB weatherCloset = new FinalDB();
        weatherCloset.createTable();
        weatherCloset.addDataToTable();
        weatherCloset.selectingHotQueries();

        //create gui and instantiate it
        FinalProjectGUI gui = new FinalProjectGUI(weatherCloset);


    }}