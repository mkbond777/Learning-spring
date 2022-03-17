package com.manishkumar.lil.learningspring.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by DT2(m.kumar) on 14/03/22.
 */
@Component
public class DateUtils {
    public Date createDateFromDateString(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(StringUtils.hasText(dateString)){
            try{
                date = format.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
}
