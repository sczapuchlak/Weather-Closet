

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FinalProjectGUI extends JFrame {
    private JTextField cityText;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton iMReadyToButton;
    private JButton iVeDecidedIButton;
    private JPanel rootPanel;
    private JTextField stateAbbreviation;
    private JLabel resultsLabel;
    private JLabel clothingResults;
    private JLabel resultsLabel2;
    private JLabel accessoryResult;
    public String cityFieldValue;
    public String stateFieldValue;

    private boolean yesSelectAcc;
    private boolean noSelectAcc;



    protected FinalProjectGUI(final FinalDB weatherCloset) {


        super("Weather Closet");

        this.setContentPane(this.rootPanel);
        this.pack();
        this.setVisible(true);
        iVeDecidedIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



    //make a button group for display accessories to ensure only radio button can be pressed
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(yesRadioButton);
        buttonGroup2.add(noRadioButton);
        //register a listener for the radio buttons
        //yesRadioButton.addActionListener();
        //noRadioButton.addActionListener();
        //check to see if the yes or no radio buttons are checked
        yesRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (yesRadioButton.isSelected()){
                    accessoryResult.setText(weatherCloset.selectingAccessories());
                }
            }
       });
       noRadioButton.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               noSelectAcc =noRadioButton.isSelected();

           }
       });



    //add an action listener to find out what city was typed in
        this.iMReadyToButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // reads the data from the text fields from the user.
            final String cityFieldValue = cityText.getText();
            final String stateFieldValue = stateAbbreviation.getText();

            //Clear input JTextFields
            cityText.setText("");
            stateAbbreviation.setText("");

            //read from my key that was given to my from wunderground weather.
            String key = readKey();
            Scanner userInput = new Scanner(System.in);
            if (key == null) {
                System.out.println("There was an error with processing the key. This program will now shut.");
                exitProgram();
            }
            //System.out.println("What city would you like?");
            //String cityUser = userInput.nextLine();
            //System.out.println("What state abbreviation?");
            //final String stateUser = userInput.nextLine();
            // the key is the part starting with 36
            // this will be the city and state search
            String urlForCheck = "http://api.wunderground.com/api/36fce18324ae645a/conditions/q/" + stateFieldValue + "/" + cityFieldValue + ".json";
            //String urlForSanFran = "http://api.wunderground.com/api/36fce18324ae645a/conditions/q/WI/Wisconsin_Dells.json";
            String url = String.format(urlForCheck, key);
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
                        String weather = currentObservation.getString("weather");
                       String results = ("The current weather is " + weather+" and the current temperature is "+ current_temp+
                               " in "+cityFieldValue+" ," +stateFieldValue+" !");
                        resultsLabel.setText(results);

                        if (current_temp<= 30){
                           resultsLabel2.setText("Brrrr! ");
                           clothingResults.setText(weatherCloset.selectingSuperColdQueries());

                        }
                        if (current_temp>30 && current_temp <= 50){
                            resultsLabel2.setText("It's still kind of chilly!");
                            clothingResults.setText(weatherCloset.selectingColdQueries());
                        }
                        if (current_temp>50 && current_temp<= 68){
                            resultsLabel2.setText("It's starting to get warmer!");
                            clothingResults.setText(weatherCloset.selectingMediumQueries());
                        }
                        if (current_temp>68 && current_temp<= 80){
                            resultsLabel2.setText("It's kinda toasty, isn't it?");
                            clothingResults.setText(weatherCloset.selectingWarmQueries());
                        }
                        if (current_temp>80){
                            resultsLabel2.setText("WHY IS IT SO HOT!?");
                            clothingResults.setText(weatherCloset.selectingHotQueries());
                        }
                    } catch (JSONException je) {
                        System.out.println("An error occurred while processing your response. I am unable to make a " +
                                "recommendation for you!");
                        exitProgram();
                    }
                    return null;

                }
            });
        }
    });}



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
        }}


}


