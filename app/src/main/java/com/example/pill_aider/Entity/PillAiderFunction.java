package com.example.pill_aider.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PillAiderFunction {
    //eg: h=10,m=30 -> String:"10:30"
    public static String twoTimeToString(int h, int m) {
        return String.valueOf(h) + ":" + String.valueOf(m);
    }

    //eg: String:"10:30" -> h=10,m=30
    public static List<Integer> stringToTwoTime(String str) {
        List<Integer> list = new ArrayList<>();
        String h = "";
        String m = "";
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ':') {
                flag = true;
                continue;
            }
            if (!flag) {
                h += str.charAt(i);
            } else {
                m += str.charAt(i);
            }
        }
        list.add(Integer.parseInt(h));
        list.add(Integer.parseInt(m));
        return list;
    }

    //eg: y=2022,m=6,d=20 -> String:"2022-6-20"
    public static String ThreeDateToString(int y, int m, int d) {
        return String.valueOf(y) + "-" + String.valueOf(m) + "-" + String.valueOf(d);
    }

    //eg: String:"2022-6-20" -> y=2022,m=6,d=20
    public static List<Integer> stringToThreeDate(String str) {
//        System.out.println(str);
        List<Integer> list = new ArrayList<>();
        String y = "";
        String m = "";
        String d = "";
        int flag = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                flag++;
                continue;
            }
            if (flag == 1) {
                y += str.charAt(i);
            } else if (flag == 2) {
                m += str.charAt(i);
            } else {
                d += str.charAt(i);
            }
        }
//        System.out.println(y);
//        System.out.println(m);
//        System.out.println(d);
        list.add(Integer.parseInt(y));
        list.add(Integer.parseInt(m));
        list.add(Integer.parseInt(d));
        return list;
    }

    //获取今天的日期 "2022-6-20"
    public static String getTodayDate() {
        String TodayDate = String.valueOf(LocalDate.now());
        List<Integer> list = stringToThreeDate(TodayDate);
        TodayDate = ThreeDateToString(list.get(0), list.get(1), list.get(2));
        return TodayDate;
    }


    // Reminder to String:"1-1-1-1-1-1-1-TEST"
    public static String reminderToString(Reminder r) {
        String str="";
        if (r != null)
             str = String.valueOf(r.getItem_id()) + "-" +
                    r.getItem_name() + "-" +
                    String.valueOf(r.getNum_day()) + "-" +
                    String.valueOf(r.getDasage_per_time()) + "-" +
                    String.valueOf(r.getItem_type()) + "-" +
                    String.valueOf(r.getItem_time()) + "-" +
                    String.valueOf(r.getItem_rem()) + "-" +
                    r.getNotice();
        else str = "NONE";
        return str;
    }

    public static Reminder stringToReminder(String str){
        if (Objects.equals(str, "NONE")){
            Reminder r = new Reminder("1",1,1,1,1,1,"记得多喝热水");
            r.setItem_id(1);
            return r;
        }
        String id="";
        String name="";
        String Num_day="";
        String Dasage_per_time="";
        String Item_type="";
        String Item_time="";
        String Item_rem="";
        String Notice="";
        int flag = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                flag++;
                continue;
            }
            if (flag == 1) {
                id += str.charAt(i);
            } else if (flag == 2) {
                name += str.charAt(i);
            } else if (flag == 3) {
                Num_day += str.charAt(i);
            } else if (flag == 4) {
                Dasage_per_time += str.charAt(i);
            } else if (flag == 5) {
                Item_type += str.charAt(i);
            } else if (flag == 6) {
                Item_time += str.charAt(i);
            } else if (flag == 7) {
                Item_rem += str.charAt(i);
            } else {
                Notice += str.charAt(i);
            }
        }
        Reminder r = new Reminder(name,
                Integer.parseInt(Num_day),
                Integer.parseInt(Dasage_per_time),
                Integer.parseInt(Item_type),
                Integer.parseInt(Item_time),
                Integer.parseInt(Item_rem),
                Notice
                );
        r.setItem_id(Integer.parseInt(id));
        return r;
    }

//
//    public static void main(String[] args) {
//        String str = reminderToString(null);
//        System.out.println(str);
//        Reminder r = stringToReminder(str);
//        System.out.println(r.getNotice());
//        r.setNotice("TEST");
//        str = reminderToString(r);
//        System.out.println(str);
//        r=stringToReminder(str);
//        System.out.println(r.getNotice());
//    }

}
