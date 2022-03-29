package org.example.dao.daoutil;

import java.time.LocalDate;

public class Log {
    public static String info(String className, String targetName, String message){
        return String.format("%s [INFO] ------ %s ------ %s ------%s", LocalDate.now(),className,targetName,message);
    }

    public static String error(String className, String targetName, String message){
        return String.format("%s [ERROR] ------ %s ------ %s ------%s", LocalDate.now(),className,targetName,message);
    }


}