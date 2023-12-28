public class GetData {

   static String dayText;

   static String getDayData(int today) {
        if (today == 1) {
            dayText = "Sunday";
            return "src\\data\\Sunday.json";
        } else if (today == 2) {
            dayText = "Monday";
            return "src\\data\\Monday.json";
        } else if (today == 3) {
            dayText = "Tuesday";
            return "src\\data\\Tuesday.json";
        } else if (today == 4) {
            dayText = "Wednesday";
            return "src\\data\\Wednesday.json";
        } else if (today == 5) {
            dayText = "Thursday";
            return "src\\data\\Thursday.json";
        } else if (today == 6) {
            dayText = "Friday";
            return "src\\data\\Friday.json";
        } else if (today == 7) {
            dayText = "Saturday";
            return "src\\data\\Saturday.json";
        }
        return null;
    }
}

