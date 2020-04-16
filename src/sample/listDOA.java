package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.classes.Cookies;

import java.sql.*;

public class listDOA {
    public static ObservableList<list> getAllRecordslist() throws ClassNotFoundException, SQLException {
        final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
        final String user = "sa";
        final String pass = "549657Ll";
        Connection con = DriverManager.getConnection(DB_URL, user, pass);
        Statement stmt1;
        stmt1 = con.createStatement();


        int lis = Cookies.getList_ID();
        Cookies coo = new Cookies();
        int accountID = Cookies.getAccountId();
        String sql3 = "SELECT List_ID from [Teacher.SupplyList] left join [Teacher.Accounts] on [Teacher.SupplyList].Account_ID=[Teacher.Accounts].AccountID where AccountID ="+ accountID;
        Statement stmt3;
        stmt3 = con.createStatement();
        ResultSet rs = stmt3.executeQuery(sql3);
//        while (rs.next()){
//            lis = rs.getInt(1);
//        }




        String sql = "SELECT List_ID, Supply_Name, Description, [T.SLI].Quantity\n" +
                "from [SchoolKits.SupplyInventory]\n" +
                "left join [Teacher.SupplyListItems] [T.SLI] on [SchoolKits.SupplyInventory].SupplyID = [T.SLI].Supply_ID WHERE List_ID="+ lis;

        try{

            ResultSet rs2 = stmt1.executeQuery(sql);
            ObservableList<list> invList = getInventoryObjects(rs2);
            return invList;

        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<list> getInventoryObjects(ResultSet rs) throws ClassNotFoundException, SQLException{
        try{
            ObservableList<list> invlist = FXCollections.observableArrayList();
            while(rs.next()){
                list lv = new list();

                lv.setListNum(rs.getInt("List_ID"));
                lv.setItemName(rs.getString("Supply_Name"));
                lv.setItemDescription(rs.getString("Description"));
                lv.setQuantity(rs.getInt("Quantity"));
                invlist.add(lv);

            }
            return  invlist;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }


}
