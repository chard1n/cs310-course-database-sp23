package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;


public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                
                ResultSetMetaData metadata = rs.getMetaData();
                int numCol = metadata.getColumnCount();
                
                while (rs.next()){
                    
                    JsonObject obj = new JsonObject();
                    
                    for(int i = 1; i <= numCol; i++){
                        
                        String colName = metadata.getColumnName(i);
                        Object columnValue = rs.getString(i);
                        
                        obj.put(colName, columnValue);
                    }
                    
                    records.add(obj);
                }

                
            }

        }
            
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
