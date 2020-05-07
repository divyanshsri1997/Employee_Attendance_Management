package in.ac.a160303105075paruluniversity.myapp;

public class HolidayManager {
    String name,type,day,month,year, currentDate;
    int currentDay,currentMonth,currentYear;
    String days[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    HolidayManager(String name,String type,String day, String month, String year, String currentDate){
        this.name = name;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.currentDate = currentDate;
    }
    public String displayCurrentDay(){
        currentDay = Integer.parseInt(currentDate.substring(0,2));
        currentMonth = Integer.parseInt(currentDate.substring(3,5));
        System.out.println("current month:"+currentMonth);
        currentYear = Integer.parseInt(currentDate.substring(6,10));
        System.out.println("current year:"+currentYear);
        return String.valueOf(currentDay);
    }
    public  String displayWeekDay(){
        int t[] = {0,3,2,5,0,3,5,1,4,6,2,4};
        currentYear -= (currentMonth<3)?1:0;
        int n = (currentYear + currentYear/4 - currentYear/100 + currentYear/400 + t[currentMonth - 1] + currentDay) % 7;
        System.out.println("value of n:"+n);
        return days[n];
    }

}
