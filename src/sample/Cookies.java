package sample;

public class Cookies
{
    private static String ACCOUNT_ID;
    private static String FIRST_NAME;
    private static String LAST_NAME;
    private static String EMAIL;
    private static String ACCOUNT_TYPE;

    public static void setUser(String Email, String ID, String F_Name, String L_Name, String Account_Type)
    {
        ACCOUNT_ID = ID;
        FIRST_NAME = F_Name;
        LAST_NAME = L_Name;
        EMAIL = Email;
        ACCOUNT_TYPE = Account_Type;
    }
    public static void setUser(String s_f_name, String s_l_name, String s_email, String type)
    {
        FIRST_NAME = s_f_name;
        LAST_NAME = s_l_name;
        EMAIL = s_email;
        ACCOUNT_TYPE = type;
    }

    public static void setAccountId(String accountId) {
        ACCOUNT_ID = accountId;
    }

    public static String getAccountId() {
        return ACCOUNT_ID;
    }

    public static String getFirstName() {
        return FIRST_NAME;
    }

    public static String getLastName() {
        return LAST_NAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getAccountType() {
        return ACCOUNT_TYPE;
    }


}
