package in.ac.a160303105075paruluniversity.myapp.Model;

import java.util.Dictionary;
import java.util.Hashtable;

public class MyLeavesData {

    public Dictionary<String, String> approvedLeaveData1 = new Hashtable<>();
    public Dictionary<String, String> approvedLeaveData2 = new Hashtable<>();
    public Dictionary<String, String> pendingLeaveData = new Hashtable<>();

    public MyLeavesData(){

        approvedLeaveData1.put("Reason","For Personal Work");
        approvedLeaveData1.put("type","PL");
        approvedLeaveData1.put("date","11-May-2020");
        approvedLeaveData1.put("For Personal Work","approvedLeaveData1");

        approvedLeaveData2.put("Reason","Not well due to severe migrane");
        approvedLeaveData2.put("type","SL");
        approvedLeaveData2.put("date","15-May-2020");
        approvedLeaveData2.put("Not well due to severe migrane","approvedLeaveData2");

        pendingLeaveData.put("Reason","For Personal Work");
        pendingLeaveData.put("type","PL");
        pendingLeaveData.put("date","18-May-2020");
    }

}
