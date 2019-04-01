package Register;
import java.sql.*;
import java.util.*;

public class Register {
public static void main(String[] args) throws Exception {
Scanner sc = new Scanner(System.in);
	Class.forName("com.mysql.jdbc.Driver");
	try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb","root", "");
			PreparedStatement pst=con.prepareStatement("insert into account values(?,?,?,?)");){
		    System.out.println("Connected");
		    System.out.println("Enter name: ");
			String name = sc.next();
			System.out.println("Enter emailid : ");
			String emailid = sc.next();
			System.out.println("Enter password : ");
			String password = sc.next();
			pst.setInt(1, 0);
			pst.setString(2, name);
			pst.setString(3, emailid);
			pst.setString(4, password);
			pst.executeUpdate();
			System.out.println("Account Created Successfully.");
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
}
