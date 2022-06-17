package com.example.pill_aider.Entity;

import java.util.ArrayList;
import java.util.List;

public class PillAiderFunction {
    public static String twoTimeToString(int h,int m){
        return String.valueOf(h)+":"+String.valueOf(m);
    }

    public static List<Integer> stringToTwoTime(String str){
        List<Integer> list = new ArrayList<>();
        String h = "";
        String m = "";
        boolean flag = false;
        for(int i =0 ;i<str.length();i++){
            if(str.charAt(i)==':') {
                flag = true;
                continue;
            }
            if(!flag){
                h += str.charAt(i);
            }
            else{
                m += str.charAt(i);
            }
        }
        list.add(Integer.parseInt(h));
        list.add(Integer.parseInt(m));
        return list;
    }
//
//    public static void main(String[] args) {
//        String str = "10:01";
//        int a,b;
//        List<Integer> list = stringToTwoTime(str);
//        for(int i = 0;i<list.size();i++) {
//            System.out.println(list.get(i));
//        }
//        a=list.get(0);
//        b=list.get(1);
//        System.out.println(twoTimeToString(b,a));
//    }

}
