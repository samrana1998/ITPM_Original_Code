
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
public class student {
    
    
    public void insertUpdateDeleteStudent(char operation, int ID, String Academic_Year, String Semester, String Programme, String Group_ID, String Sub_Group_ID, String Generate_ID, String Generate_Sub_ID ){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
         if(operation == 'u'){
            try {
                ps = con.prepareStatement("UPDATE `student` SET `Academic_Year`= ?,`Semester`= ?,`Programme`= ?,`Group_ID`= ?,`Sub_group_ID`= ?,`Generate ID`= ?,`Generate Sub ID`= ? WHERE `ID`= ?");
                
                ps.setString(1, Academic_Year);
                ps.setString(2, Semester);
                ps.setString(3, Programme);
                ps.setString(4, Group_ID);
                ps.setString(5, Sub_Group_ID);
                ps.setString(6, Generate_ID);
                ps.setString(7, Generate_Sub_ID);
                ps.setInt(8, ID);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Student Data Updated");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         
         if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO student(ID, Academic_Year, Semester, Programme, Group_ID, Sub_group_ID, Generate ID, Generate Sub ID) VALUES (?,?,?,?,?,?,?,?)");
                ps.setInt(1, ID);
                ps.setString(2, Academic_Year);
                ps.setString(3, Semester);
                ps.setString(4, Programme);
                ps.setString(5, Group_ID);
                ps.setString(6, Sub_Group_ID);
                ps.setString(7, Generate_ID);
                ps.setString(8, Generate_Sub_ID);
                
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Student Data Inserted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
         if(operation == 'd'){
            try {
                ps = con.prepareStatement("DELETE FROM `student` WHERE `ID` = ?");
                
                
                ps.setInt(1, ID);
                
                if(ps.executeUpdate()> 0 ){
                    JOptionPane.showMessageDialog(null, "Student Data Deleted");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
   
}
    }

    public void fillStudentJtable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `student` WHERE concat (`ID`,`Academic_Year`,`Semester`,`Programme`,`Group_ID`,`Sub_group_ID`,`Generate ID`,`Generate Sub ID`) LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
           DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rs.next()){
                
                
               row = new Object[8];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6]= rs.getString(7);
                row[7]= rs.getString(8);
               
                model.addRow(row);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
