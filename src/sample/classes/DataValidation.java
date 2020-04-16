package sample.classes;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DataValidation
{
    private static int ACCOUNT_ID_COOKIE;
    private static String FIRST_NAME;
    private static String LAST_NAME;
    private static String EMAIL;

    public static void Validator(String EMAIL, String PASSWORD) throws Exception {
        String Email_Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String Password_Regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);



        if (!EMAIL.matches(Email_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Email must contain Emailname@domain.extension ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!PASSWORD.matches(Password_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Password should be 6-20 characters with at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”)");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            throw new IllegalArgumentException();
        }

    }

    public static void SignUP_Validator(String EMAIL, String F_Name, String L_Name, String Phone) throws Exception {
            String Email_Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            String Name_Regex = "^[a-zA-Z -]{1,20}$";
            String Phone_Regex = "^[0-9]{10}";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);

        if (!EMAIL.matches(Email_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Email must contain Emailname@domain.extension ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!F_Name.matches(Name_Regex) || !L_Name.matches(Name_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Incorrect format for name , A-Z");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Phone.matches(Phone_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Incorrect format for Phone Number , 0-9 (10)");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

    }

    public static void Password_Recovery_Validator(String Rec_Email, String Q1, String Q1_Answer, String Q2, String Q2_Answer)
    {
        String Email_Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String Answer_Format = "^[a-zA-Z0-9 ._-]{1,25}$";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);


        if (!Rec_Email.matches(Email_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Email must contain Emailname@domain.extension ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if(Q1.compareTo(Q2) == 0 || Q2.compareTo(Q1) == 0)
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Security Questions Cannot be the same");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Q1_Answer.matches(Answer_Format))
        {
            tray.setTitle("Unsuccessful Format");
            tray.setMessage("A-Z and Numbers in Answer Field 1");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Q2_Answer.matches(Answer_Format))
        {
            tray.setTitle("Unsuccessful Format");
            tray.setMessage("A-Z and Numbers in Answer Field 2");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

    }

    public static void Profile_Validator(String Q1, String Q2, String Q1_Answer, String Q2_Answer, String Password)
    {
        String Answer_Format = "^[a-zA-Z0-9 ._-]{1,25}$";
        String Password_Regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);

        if(Q1.compareTo(Q2) == 0 || Q2.compareTo(Q1) == 0)
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Security Questions Cannot be the same");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Q1_Answer.matches(Answer_Format))
        {
            tray.setTitle("Unsuccessful Format ");
            tray.setMessage("A-Z and Numbers in Answer Field 1");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Q2_Answer.matches(Answer_Format))
        {
            tray.setTitle("Unsuccessful Format ");
            tray.setMessage("A-Z and Numbers in Answer Field 2");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if (!Password.matches(Password_Regex))
        {
            tray.setTitle("Unsuccessful Password Format ");
            tray.setMessage("Must contain 6-20 characters, Capital & Lower with @#$% Symbols");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }
    }

    public static void setFName(String F_Name)
    {
        FIRST_NAME = F_Name;
    }

    public static String getFName()
    {
        return FIRST_NAME;
    }

    public static void setLname(String L_Name)
    {
        LAST_NAME = L_Name;
    }

    public static String getLName()
    {
        return LAST_NAME;
    }

    public static void setEmail(String S_email)
    {
        EMAIL = S_email;
    }
    public static String getEMAIL()
    {
        return EMAIL;
    }

    public static void setAccount_ID_Cookie(int account_id_cookie)
    {
        ACCOUNT_ID_COOKIE = account_id_cookie;
    }
    private static int getAccountIdCookie()
    {
        return ACCOUNT_ID_COOKIE;
    }
}
