package sample;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DataValidation
{
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

    public static void SignUP_Validator(String EMAIL, String PASSWORD, String CONFIRM_PASSWORD, String s) throws Exception {
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
}
