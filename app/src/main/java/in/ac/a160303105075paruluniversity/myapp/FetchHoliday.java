package in.ac.a160303105075paruluniversity.myapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchHoliday extends AsyncTask<String,Void,String> {

    private WeakReference<TextView> dayTextView;
    private WeakReference<TextView> holidayTextView;

    FetchHoliday(TextView dayTextView,TextView holidayTextView){
        this.dayTextView = new WeakReference<>(dayTextView);
        this.holidayTextView = new WeakReference<>(holidayTextView);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getHolidays(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {

            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of holidays.
            JSONArray itemsArray = jsonObject.getJSONArray("holidays");
            int i = 0;
            String holidayName = null;

            while (i < itemsArray.length() && (holidayName == null)) {
                // Get the current item information.
                JSONObject holidays = itemsArray.getJSONObject(i);
                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    holidayName = holidays.getString("name");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
                if (holidayName != "Workday") {
                    holidayTextView.get().setText("Work Day");
                    dayTextView.get().setText("Monday");
                }
            }
        }catch(Exception e){
                // If onPostExecute does not receive a proper JSON string,
                // update the UI to show failed results.
                holidayTextView.get().setText("No response");
                dayTextView.get().setText("");
        }
    }
}
