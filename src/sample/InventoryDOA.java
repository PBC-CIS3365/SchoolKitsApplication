package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.*;

public class InventoryDOA {
    public static ObservableList<Inventory> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM [SchoolKits.SupplyInventory]";
        final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
        final String user = "sa";
        final String pass = "549657Ll";
        Connection con = DriverManager.getConnection(DB_URL, user, pass);
        Statement stmt1;
        stmt1 = con.createStatement();
        try{

            ResultSet rs = stmt1.executeQuery(sql);
            ObservableList<Inventory> invList = getInventoryObjects(rs);
            return invList;

        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Inventory> getInventoryObjects(ResultSet rs) throws ClassNotFoundException, SQLException{
        try{
            ObservableList<Inventory> invlist = FXCollections.observableArrayList();
            while(rs.next()){
                Inventory in = new Inventory();
                in.setItemName(rs.getString("Supply_Name"));
                in.setItemDescription(rs.getString("Description"));
                in.setItemBrand(rs.getString("Brand"));
                in.setSupplyID(rs.getInt("SupplyID"));
                invlist.add(in);

            }
            return  invlist;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

}
