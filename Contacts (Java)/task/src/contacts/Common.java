package contacts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {
   private final String timeCreate;
    String timeLastUpdate;
    public String number;


    public Common( String number) {
        this.timeCreate = createTime();
        this.number = number;
    }

    public Common() {

        this.timeCreate = createTime();
        this.timeLastUpdate = createTime();
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTimeCreate() {
       return this.timeCreate;
    }


    public String getTimeLastUpdate() {
       return this.timeLastUpdate;
    }

    public void setTimeLastUpdate(String timeLastUpdate) {
        this.timeLastUpdate = timeLastUpdate;
    }

   public String createTime(){
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
       Calendar calendar = Calendar.getInstance();
       Date currentDate = calendar.getTime();
       return dateFormat.format(currentDate);
   }
}
