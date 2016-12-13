

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FinalProjectGUI extends JFrame {
    private JTextField cityText;
    private JCheckBox businessCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox athleticCheckBox;
    private JCheckBox specialEventCheckBox;
    private JCheckBox allCheckBox;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton iMReadyToButton;
    private JButton iVeDecidedIButton;
    private JPanel rootPanel;
    private JTextField stateAbbreviation;
    public String cityFieldValue;
    public String stateFieldValue;



    protected FinalProjectGUI() {
        //store a reference to the controller object. need it to make requests to database

        super("Weather Closet");

        this.setContentPane(this.rootPanel);
        this.pack();
        this.setVisible(true);
        this.iVeDecidedIButton.addActionListener(new ActionListener() {
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
        if (yesRadioButton.isSelected()) {

        } else if (noRadioButton.isSelected()) {
        }


        //read from my key that was given to my from wunderground weather.
        String key = readKey();
        Scanner userInput = new Scanner(System.in);
        if (key == null) {
            System.out.println("There was an error with processing the key. This program will now shut.");
            exitProgram();
        }
        System.out.println("What city would you like?");
        String cityUser = userInput.nextLine();
        System.out.println("What state abbreviation?");
        final String stateUser = userInput.nextLine();
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
                    System.out.println("The current weather is " + weather);
                } catch (JSONException je) {
                    System.out.println("An error occurred while processing your response. I am unable to make a " +
                            "recommendation for you!");
                    exitProgram();
                }
                return null;
            }
        });


        //add an action listener to find out what city was typed in
        iMReadyToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reads the data from the text fields from the user.
                String cityFieldValue = cityText.getText();
                String stateFieldValue = stateAbbreviation.getText();

                //Clear input JTextFields
                cityText.setText("");
                stateAbbreviation.setText("");
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
    }}