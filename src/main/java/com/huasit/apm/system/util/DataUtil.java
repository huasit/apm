package com.huasit.apm.system.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class DataUtil {

    /**
     *
     */
    public static String getDate() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(new Date());
    }

    /**
     *
     */
    public static String getDateTime() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(new Date());
    }

    /**
     *
     */
    public static boolean stringIsEmpty(String str) {
        return str == null || "".equals(str) || "".equals(str.trim());
    }

    /**
     *
     */
    public static String toDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }



    /**
     *
     */
    public static int getCurrentFyear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if(month <= 2) {
            year--;
        }
        return year;
    }

    /**
     *
     */
    public static List<String> getDefaultUploadFyears() {
        Calendar calendar = Calendar.getInstance();
        List<String> list = new ArrayList<>();
        for (int n = -1; n <= 1; n++) {
            list.add(String.valueOf(calendar.get(Calendar.YEAR) + n));
        }
        return list;
    }

    /**
     *
     */
    public static String getFyYear(int year) {
        return "FY" + (year - 2000);
    }


    /**
     *
     */
    public static String getDefaultOrder() {
        return "id,desc";
    }


    /**
     *
     */
    public static Sort getOrderByStr(String order) {
        if (order == null || "".equals(order)) {
            order = getDefaultOrder();
        }
        String oid = order.split(",")[0];
        Sort.Direction ois = "desc".equals(order.split(",")[1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return new Sort(new Sort.Order(ois, oid));
    }


    /**
     *
     */
    public static Object getObjectFieldValue(Object object, String fieldName) {
        try {
            if (object == null) {
                return null;
            }
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    public static void setObjectFieldValue(Object object, String fieldName, Object value) {
        try {
            if (object == null) {
                return;
            }
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    public static int convertFMonthToVirtualMonth(int fmonth) {
        return fmonth < 4 ? 12 + fmonth : fmonth;
    }

    /**
     *
     */
    public static int convertVirtualMonthToFMonth(int virtualMonth) {
        return virtualMonth > 12 ? virtualMonth - 12 : virtualMonth;
    }

    /**
     *
     */
    public static <T> Specification<T> getSpecification() {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return query.getRestriction();
            }
        };
    }
}