package org.ashtray.mobile.scheduler.util;

import android.util.Log;

public final class Utils {
    
    public static String toString(Object any) {
        if (any == null) {
            return "NULL";
        } else if (any instanceof String) {
            if (((String)any).length() == 0) {
                return "NULL";
            }
        }
        return any.toString();
    }
    
    public static void log(Object any) {
        String msg = toString(any);
        Log.wtf("@#@",msg );
    }
}
