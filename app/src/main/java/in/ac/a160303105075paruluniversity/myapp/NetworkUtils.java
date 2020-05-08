package in.ac.a160303105075paruluniversity.myapp;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    static String getHolidays(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String holidayJSONString = null;
        try {
            URL requestURL = new URL("https://calendarific.p.rapidapi.com/holidays?year=2020&country=in");
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("x-rapidapi-host", "calendarific.p.rapidapi.com");
            urlConnection.setRequestProperty("x-rapidapi-key", "1f0e3734cemsh3ad0a08c7d8729cp18ccc9jsn6f333f8549ba");
            urlConnection.setRequestProperty("authorization", "Basic MTIzOjEyMw==");
            urlConnection.connect();
            holidayJSONString = getResponse(urlConnection);
        }catch(IOException e){
            e.printStackTrace();
        }
        return holidayJSONString;
    }


    static String getEmployeeData(){
        HttpURLConnection urlConnection = null;
        String employeeDataJSONString = null;
        try {
            URL requestURL = new URL("https://extendsclass.com/api/json-storage/bin/fbafcda");
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            employeeDataJSONString = getResponse(urlConnection);
        }catch(IOException e){
            e.printStackTrace();
        }
        return employeeDataJSONString;
    }


    static String getResponse( HttpURLConnection urlConnection){
        BufferedReader reader = null;
        String JSONString = null;
        try{
            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            else{
                System.out.println("Fetching data was successful");
            }
            JSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, JSONString);
        return JSONString;
    }

}
