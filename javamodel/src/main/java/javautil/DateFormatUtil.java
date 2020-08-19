package javautil;

import com.study.javamodel.javaapi.SimpleDateformat.ZoneIdEnum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @className
 * @Description TODO 以下都是线程安全的
 * @Author 付林虎
 * @Date 2020/8/19 17:57
 * @Version V1.0
 */
public class DateFormatUtil {
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal();
    public static final DateFormat getDateFormat(String patter){
        DateFormat dateFormat = threadLocal.get();
        if(dateFormat==null){
            threadLocal.set(new SimpleDateFormat(patter));
        }
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }


    public static Date convertStr2Date(String strDate, String dateFormatter, ZoneIdEnum timezone) {
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(dateFormatter);
        LocalDate parseDate = LocalDate.parse(strDate,ofPattern);
        ZoneId zone = ZoneId.of(timezone.getZoneId());
        Instant instant = parseDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String convertDate2Str(String dateFormatter,long date) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(date / 1000L, 0, ZoneOffset.ofHours(8));
        String time  = dateTime.format(DateTimeFormatter.ofPattern(dateFormatter));
        return time;
    }


}
