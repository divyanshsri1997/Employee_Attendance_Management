package in.ac.a160303105075paruluniversity.myapp;

public class HolidayManager {
    private String name,type,day,month,year, currentDate;
    private int currentDay,currentMonth,currentYear;
    String days[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    HolidayManager(String holidayName,String type,String day, String month, String year, String currentDate){
        this.name = holidayName;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.currentDate = currentDate;
        currentDay = Integer.parseInt(currentDate.substring(0,2));
        currentMonth = Integer.parseInt(currentDate.substring(3,5));
        currentYear = Integer.parseInt(currentDate.substring(6,10));
    }
    String displayCurrentDay(){
        return String.valueOf(currentDay);
    }
    String displayWeekDay(){
        int t[] = {0,3,2,5,0,3,5,1,4,6,2,4};
        currentYear -= (currentMonth<3)?1:0;
        int n = (currentYear + currentYear/4 - currentYear/100 + currentYear/400
                + t[currentMonth - 1] + currentDay) % 7;
        return days[n];
    }
    String displayHoliday(){
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        if(d == currentDay && m == currentMonth && type.equals("[\"National holiday\"]")){
            return type.substring(3,19);
        }
        else{
            //System.out.println("In else block");
            return "Work Day";
        }
    }

}
