package com.example.pill_aider.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        TodayDate = ThreeDateToString(list.get(0),list.get(1),list.get(2));
        return TodayDate;
    }
//
//    public static void main(String[] args) {
////        String str = "10:01";
////        int a,b;
////        List<Integer> list = stringToTwoTime(str);
////        for(int i = 0;i<list.size();i++) {
////            System.out.println(list.get(i));
////        }
////        a=list.get(0);
////        b=list.get(1);
////        System.out.println(twoTimeToString(b,a));
//
////        System.out.println(getTodayDate());
//    }

}
