package sample;

import com.jfoenix.controls.JFXPasswordField;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DataValidation
{
    private static int ID;
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

    public static void SignUP_Validator(String EMAIL, String PASSWORD, String CONFIRM_PASSWORD) throws Exception {
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

        if (!PASSWORD.matches(CONFIRM_PASSWORD))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Passwords do not match");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
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

    public static void Profile_Validator(String First_Name, String Last_Name, String Area_Code, String Phone_Number, String Q1, String Q2, String Q1_Answer, String Q2_Answer)
    {
        String Name_Regex = "^[a-zA-Z -]{1,20}$";
        String Area_Code_Regex = "^[0-9]{5}";
        String Phone_Regex = "^[0-9]{10}";
        String Answer_Format = "^[a-zA-Z0-9 ._-]{1,25}$";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);

        if(!First_Name.matches(Name_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Name does not match format. A-Z");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if(!Last_Name.matches(Name_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Name does not match format. A-Z");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if(!Area_Code.matches(Area_Code_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Area Code does not match format. 4 digits 0-9");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }

        if(!Phone_Number.matches(Phone_Regex))
        {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Phone Number does not match format. 10 Digits 0-9");
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
    }

    public static void setID(int id) {
        ID = id;
    }

    public static int getID() {
        return ID;
    }
}
