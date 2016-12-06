import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//This program finds out where you are located
// and tells you what you should wear based on the weather.
//Used weather underground as my API.
// TODO: 12/3/2016 - ranges of temperatures are as follows: <=30 is super cold,>30 AND <= 50 is mediocrely cold
//TODO: cont. >50 AND <=68 is medium temp. > 68 AND <= 80 is warm. >80 is hot. This will be the guide
//TODO: for how the database shoots back with what to wear.





public class Main  {

    public static void main(String[] args) {

        FinalDB finalDB = new FinalDB();
        finalDB.createTable();

        //create gui and instantiate it
        FinalProjectGUI gui = new FinalProjectGUI();


        String key = readKey();

        if (key == null) {
            System.out.println("There was an error with processing the key. This program will now shut.");
            exitProgram();
        }
        //this is my url for my hometown of Wisconsin Dells just as a test
        // the key is the part starting with 36
        // this will be the city and state search String urlForSanFran = "http://api.wunderground.com/api/36fce18324ae645a/conditions/q/"+stateName+"/"+cityName+".json";
        String urlForSanFran = "http://api.wunderground.com/api/36fce18324ae645a/conditions/q/WI/Wisconsin_Dells.json";
        String url = String.format(urlForSanFran, key);
        // after connecting the ASync up  create a new async client .
        AsyncHttpClient newAsyncClient = new DefaultAsyncHttpClient();

        newAsyncClient.prepareGet(url).execute(new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                String responseBody = response.getResponseBody();
                System.out.println(responseBody);

                try {
                    //converting the response String into a JSON object
                    JSONObject jsonObject = new JSONObject(responseBody);
                    //This JSONObject contains a JSON object with attribute current_observation
                    JSONObject currentObservation = jsonObject.getJSONObject("current_observation");
                    //JSON are comparable to hashmaps and lists
                    //currentobservations has an attribute named "temp_f" and from reading the docs, the value of the
                    // temp_f attribute is listed below
                    double current_temp = currentObservation.getDouble("temp_f");
                    System.out.println("Current temperature is " + current_temp);
                } catch (JSONException je) {
                    System.out.println("An error occurred while processing your response. I am unable to make a " +
                            "recommendation for you!");
                    exitProgram();
                }
                return null;
            }
        });
    }

    private static void exitProgram() {
        System.exit(0);
    }

    private static String readKey() {


        //use exceptions to handle if a key is entered wrong or not found
        try {
            BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
            String key = reader.readLine();
            if (key == null) {
                System.out.println("Key is not found. Either it was entered wrong or does not exist.");
                return null;
            }
            return key;
        } catch (IOException ioe) {
            System.out.println("Key file not found! Please create a file called key.txt in the root directory");
            System.exit(-1);
            return null;
        }
    }
}
