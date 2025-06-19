

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckLoginn extends UnicastRemoteObject implements Logininterface
{
    public CheckLoginn()throws Exception
    {}
    
    @Override
    public boolean check(String id,String pass)throws RemoteException
    {
        try{

        Class.forName("com.mysql.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","root");
       PreparedStatement st=con.prepareStatement("select * from logintable where userID=? and password=?");
       st.setString(1,id);
       st.setString(2,pass);
       ResultSet rs=st.executeQuery();
       if(rs.next())
           return true;
       return false;
        }catch(Exception ex){
            ex.printStackTrace();
                    return false;   

        }
    }
   
}