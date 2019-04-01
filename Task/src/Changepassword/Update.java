package Changepassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	public static void main(String[] args) throws Exception {
		ResultSet rs= null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;	
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "root", "");
			pst = con.prepareStatement("select emailid,password from account where emailid=? And password=?"); 
//			pst1 = con.prepareStatement("update account set password=password+? where emailid=? ");
			pst1 = con.prepareStatement("update account "+" set password=?" + "where emailid=? and password=?");
			System.out.println("Enter emailid : ");
			String loginemailid = sc.next();
			System.out.println("Enter password : ");
			String loginpassword = sc.next();
			pst.setString(1, loginemailid);
			pst.setString(2, loginpassword);
			rs=pst.executeQuery();
			int x=0;
			if(rs.next()) {
				x=1;
			}if(x==1) {
				System.out.println("****************Login Successfully****************");
				System.out.println("Enter emailid::");
				String emailid=sc.next();
				System.out.println("Enter your old password");
				String password1=sc.next();
				System.out.println("Enter new Password");
				String npassword=sc.next();
				pst1.setString(1, npassword);
				pst1.setString(2,emailid);
				pst1.setString(3, password1);
				pst1.executeUpdate();
				System.out.println("Your Password Changed Successfully");
			}else if(x==0) {
				System.out.println("Emaild id and Password is Incorrect");
			}
		
}catch(Exception e) {
	e.printStackTrace();
}
	}//main
}//class

