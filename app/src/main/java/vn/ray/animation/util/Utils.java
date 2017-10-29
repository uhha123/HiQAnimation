package vn.ray.animation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ray on 10/29/17.
 */

public class Utils {

    public static String timeFormat(long millis) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
            Date date = new Date(millis);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
