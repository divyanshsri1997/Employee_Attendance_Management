package in.ac.a160303105075paruluniversity.myapp;

import java.util.Dictionary;
import java.util.Hashtable;

public class ApplicantLeaveData {

    public Dictionary<String, String> applicantData1 = new Hashtable<>();
    public Dictionary<String, String> applicantData2 = new Hashtable();

    ApplicantLeaveData(){

        applicantData1.put("applicantName","Saurabh Shah");
        applicantData1.put("fromDate","25 May 2020");
        applicantData1.put("toDate","30 May 2020");
        applicantData1.put("noOfDays","6");
        applicantData1.put("Saurabh Shah","applicantData1");
        applicantData1.put("applicationTitle","Leave Application");
        applicantData1.put("reason","Need to visit native place urgently");

        applicantData2.put("applicationTitle","Leave Application");
        applicantData2.put("applicantName","Sanjay");
        applicantData1.put("Sanjay","applicantData2");
        applicantData2.put("fromDate","22 May 2020");
        applicantData2.put("toDate","24 May 2020");
        applicantData2.put("noOfDays","3");
        applicantData2.put("reason","Sick Leave");

    }
    Dictionary<String, String> getApplicationDetails(String name){
        if (applicantData1.get(name) == "applicantData1"){
            return applicantData1;
        }
        else{
            return applicantData2;
        }
    }
}
