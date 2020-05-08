package in.ac.a160303105075paruluniversity.myapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.ref.WeakReference;

public class FetchHoliday extends AsyncTask<Void,Void,String> {

    private WeakReference<TextView> dayTextView;
    private WeakReference<TextView> holidayTextView;
    private WeakReference<TextView> weekDayTextView;
    private String currentDate;
    private HolidayManager holidayManager;

    FetchHoliday(TextView dayTextView,TextView weekDayTextView,TextView holidayTextView, String currentDate){
        this.dayTextView = new WeakReference<>(dayTextView);
        this.holidayTextView = new WeakReference<>(holidayTextView);
        this.weekDayTextView = new WeakReference<>(weekDayTextView);
        this.currentDate = currentDate;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return NetworkUtils.getHolidays();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {

            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of holidays.
            JSONObject responseObject = jsonObject.getJSONObject("response");
            JSONArray itemsArray = responseObject.getJSONArray("holidays");
            int i = 0;
            String holidayName = null, day, month, year,type;

            while (i < itemsArray.length()) {
                JSONObject holidays = itemsArray.getJSONObject(i);
                try {
                    holidayName = holidays.getString("name");
                    type = holidays.getString("type");
                    JSONObject date = holidays.getJSONObject("date");
                    JSONObject datetime = date.getJSONObject("datetime");
                    day = datetime.getString("day");
                    month = datetime.getString("month");
                    year = datetime.getString("year");
                    holidayManager = new HolidayManager(holidayName,type,day,month,year,currentDate);
                    holidayTextView.get().setText(holidayManager.displayHoliday());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
            updateUI();
        }catch(Exception e){
                // If onPostExecute does not receive a proper JSON string,
                // update the UI to show failed results.
                holidayTextView.get().setText("No response");
        }
    }
    public void updateUI(){
        dayTextView.get().setText(holidayManager.displayCurrentDay());
        weekDayTextView.get().setText(holidayManager.displayWeekDay());
    }
}
