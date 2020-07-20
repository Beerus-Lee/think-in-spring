package org.own.think.in.spring.i18n;

import org.springframework.context.MessageSource;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String meassage = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";

        MessageFormat messageFormat = new MessageFormat(meassage);
        String result = messageFormat.format(new Object[]{ planet, new Date(), event});
        System.out.println(result);
        //重置messgeFormat
        meassage = "this is a {0},{1},{2}";
        messageFormat.applyPattern(meassage);
        result = messageFormat.format(new Object[] {"hello world","555","666"});
        System.out.println(result);

        //重置locale
        messageFormat.setLocale(Locale.ENGLISH);
        meassage = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(meassage);
        result = messageFormat.format(new Object[]{ planet, new Date(), event});
        System.out.println(result);

        //重置format
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{ planet, new Date(), event});
        System.out.println(result);


    }
}
