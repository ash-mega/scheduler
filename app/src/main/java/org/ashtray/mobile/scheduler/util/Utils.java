package org.ashtray.mobile.scheduler.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {
    
    public static String toString(Object any) {
        if (any == null) {
            return "NULL";
        } else if (any instanceof String) {
            if (((String)any).length() == 0) {
                return "NULL";
            }
        } else if(any instanceof Date) {
            return new SimpleDateFormat("HH:mm:ss.sss").format(any);
        }
        return any.toString();
    }
    
    public static void log(Object any) {
        String msg = toString(any);
        Log.wtf("@#@",msg );
    }
}
