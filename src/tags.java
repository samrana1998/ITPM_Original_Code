
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class tags {
    public void insertUpdateDeleteStudent(char operation, int Tag_ID, String Subject_Type, String Tag_Code, String Tag_Name){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if(operation == 'u'){
            try {
                ps = con.prepareStatement("UPDATE `tags` SET `Subject_type`= ?,`Tag_code`= ?,`Tag_name`= ? WHERE `Tag_ID`= ?");
                
                ps.setString(1, Subject_Type);
                ps.setString(2, Tag_Code);
                ps.setString(3, Tag_Name);
               
                ps.setInt(4, Tag_ID);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Tag is  Updated");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(tags.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         
         if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO `tags`(`Tag_ID`, `Subject_type`, `Tag_code`, `Tag_name`) VALUES (?,?,?,?)");
                ps.setInt(1, Tag_ID);
                ps.setString(2, Subject_Type);
                ps.setString(3, Tag_Code);
                ps.setString(4, Tag_Name);
               
                
                
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Student Data Inserted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(tags.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         if(operation == 'd'){
            try {
                ps = con.prepareStatement("DELETE FROM `tags` WHERE `Tag_ID` = ?");
                
                
                ps.setInt(1, Tag_ID);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Student Data Deleted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(tags.class.getName()).log(Level.SEVERE, null, ex);
            }
   
}
    }

    public void fillStudentJtable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `tags` WHERE concat (`Tag_ID`,`Subject_Type`,`Tag_Code`,`Tag_Name`) LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
           DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rs.next()){
                
                
               row = new Object[4];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                
               
                model.addRow(row);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddTags.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

    
