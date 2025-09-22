package lk.ijse.orm_final.bo;


import lk.ijse.orm_final.bo.custom.impl.*;

public class BOFactory {
    public enum BOType {
        PROGRAM, STUDENT, DASHBOARD, ADDPROGRAM, VIEWALL, ADDPAYMENT, SIGNUP, LOGIN, SETTING, COURSE, INSTRUCTOR, LESSON, PAYMENT
    }

    public static Object getBO(BOType boType){
        return switch (boType) {
            case PROGRAM -> new CourseBOImpl();
            case STUDENT -> new StudentBOImpl();
            case DASHBOARD -> new DashboardBOImpl();
            case INSTRUCTOR -> new InstructorBOImpl();
            case VIEWALL -> new ViewAllBOImpl();
            case LESSON -> new LessonBOImpl();
            case SIGNUP -> new SignUpBOImpl();
            case LOGIN -> new LoginBOImpl();   // ✅ cast remove
            case SETTING -> new SettingBOImpl();
            case COURSE -> new CourseBOImpl();
            case PAYMENT -> new PaymentBOImpl();
            default -> null;
        };
    }
}