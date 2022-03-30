package org.example.dao.daoutil;

import org.example.dao.*;
import org.example.dao.impl.*;


public abstract class DaoFactory {
    static {
        try {
            System.out.println("Loading driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded.");

        }catch (ClassNotFoundException e){
            System.out.println("Driver loading filed");
            e.printStackTrace();
        }
    }

    public static ManagerDao getManagerDaoSQL(){
        return new ManagerDaoImpl();
    }
    public static MentorDao getMentorDaoSQL(){
        return new ManagerDaoImpl();
    }
    public static StudentDao getStudentDaoSQL(){
        return new StudentDaoImpl();
    }
    public static GroupDao getGroupDaoSQL(){
        return new GroupDaoImpl();
    }
    public static CourseFormatDao getCourseFormatDaoSQL(){
        return new CourseFormatDaoImpl();
    }
    public static CourseDao getCourseDaoSQL(){
        return new CourseDaoImpl();
    }
    public static AddressDao getAddressDaoSQL(){return new AddressDaoImpl();}
}
