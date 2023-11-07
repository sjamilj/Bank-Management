package DB;
 import java.sql.Connection;  
 import java.sql.DriverManager;  
 import java.sql.SQLException;  

 
 
public class DBConnection {  
   private static Connection conn;  
   
   public static String url = "jdbc:mysql://localhost:3306/bank";  
   private static final String user = "root";  
   
   
    
   public static Connection connect() throws SQLException{  
     try{  
       Class.forName("com.mysql.jdbc.Driver").newInstance();  
     }catch(ClassNotFoundException | InstantiationException | IllegalAccessException cnfe){  
       System.err.println("Error: "+cnfe.getMessage());  
     }  
     conn = DriverManager.getConnection(url,user,""); 

     return conn;  
   }  
   
   public static Connection getConnection() throws SQLException, ClassNotFoundException{  
     if(conn !=null && !conn.isClosed())  
       return conn;  
     connect();  
     return conn;  
   }  
 }   