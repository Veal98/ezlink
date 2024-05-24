package cn.itmtx.ddd.ezlink.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    public static Long dateToLong(Date date) {
        if (Objects.isNull(date)) {
            return 0L;
        }

        return date.getTime();
    }

    public static Date longToDate(long currentTimeMillis) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // long转Date
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sd.format(new Date(currentTimeMillis)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
