package com.sooraj.springboot.Util;

/**
 * Created by SOORAJ on 11-03-2017.
 */
public class CommonUtils {

    public static boolean isPositiveNumeric(String numberString){
        try {
            if(Integer.parseInt(numberString) > 0){
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
