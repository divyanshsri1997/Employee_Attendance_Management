package in.ac.a160303105075paruluniversity.myapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchEmployeeData extends AsyncTask<Void,Void,String> {

    private WeakReference<TextView> nameTextView;
    private WeakReference<TextView> presentTextView;
    private WeakReference<TextView> absentTextView;
    private WeakReference<TextView> plTextView;
    private WeakReference<TextView> clTextView;
    private WeakReference<TextView> slTextView;

    FetchEmployeeData(TextView nameTextView,TextView presentTextView,TextView absentTextView,TextView plTextView, TextView clTextView,TextView slTextView){
        this.nameTextView = new WeakReference<>(nameTextView);
        this.presentTextView = new WeakReference<>(presentTextView);
        this.absentTextView = new WeakReference<>(absentTextView);
        this.plTextView = new WeakReference<>(plTextView);
        this.clTextView = new WeakReference<>(clTextView);
        this.slTextView = new WeakReference<>(slTextView);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return NetworkUtils.getEmployeeData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of names.
            JSONArray itemsArray = jsonObject.getJSONArray("Employees");
            int i = 0;
            String employeeName = null,present,absent,pl,cl,sl;

            while (i < itemsArray.length() && (employeeName == null)) {
                // Get the current item information.
                JSONObject employees = itemsArray.getJSONObject(i);
                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    employeeName = employees.getString("firstName");
                    nameTextView.get().setText(employeeName);
                    present = employees.getString("present")+"/22";
                    presentTextView.get().setText(present);
                    absent = employees.getString("absent")+"/22";
                    absentTextView.get().setText(absent);
                    pl = employees.getString("pl")+"/5";
                    plTextView.get().setText(pl);
                    cl = employees.getString("cl")+"/5";
                    clTextView.get().setText(cl);
                    sl = employees.getString("sl")+"/5";
                    slTextView.get().setText(sl);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }
        }catch(Exception e){
            System.out.println("Error fetching...");
        }
    }
}
