package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    public static final String CREATE = "INSERT INTO registration (studentid, termid, crn) VALUES (?,?,?)";
    public static final String DROP = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
    public static final String WITHDRAW = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
    public static final String LIST = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    public RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(CREATE);
                ps.setString(1, String.valueOf(studentid));
                ps.setString(2, String.valueOf(termid));
                ps.setString(3, String.valueOf(crn));

                int counter = ps.executeUpdate();

                if(counter > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(DROP);
                ps.setString(1, String.valueOf(studentid));
                ps.setString(2, String.valueOf(termid));
                ps.setString(3, String.valueOf(crn));

                int counter = ps.executeUpdate();

                if(counter > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(WITHDRAW);
                ps.setString(1, String.valueOf(studentid));
                ps.setString(2, String.valueOf(termid));
                
                
            int counter = ps.executeUpdate();
                
            if(counter > 0){
                result = true;
            }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(LIST);
                ps.setString(1, String.valueOf(studentid));
                ps.setString(2, String.valueOf(termid));
                
                boolean hasresults = ps.execute();
                
                if (hasresults){
                    rs = ps.getResultSet();
                    result = DAOUtility.getResultSetAsJson(rs);
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
