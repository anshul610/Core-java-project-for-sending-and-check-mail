package CheckInbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Display {
	public static void main(String[] args) throws Exception {
		ResultSet rs = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;

		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "root", "");
			pst = con.prepareStatement("select emailid,password from account where emailid=? And password=?");

			while (true) {
				System.out.println("****************Login With Id Password****************");

				System.out.println("Enter emailid : ");
				String emailid = sc.next();
				System.out.println("Enter password : ");
				String password = sc.next();
				pst.setString(1, emailid);
				pst.setString(2, password);
				rs = pst.executeQuery();
				int x = 0;
				if (rs.next()) {
					x = 1;
				}
				if (x == 1) {
					System.out.println("****************Login Successfully****************");
					//show for mail query
		           pst1=con.prepareStatement("select * from mail where sender=?");
		           pst1.setString(1, emailid);//error myself
			
				    rs=pst1.executeQuery();
				    System.out.println("****************Inbox****************");
				    while(rs.next()){
				   System.out.println(rs.getString("sender"));	
				    System.out.println(rs.getString("receiver"));//error myself
				    System.out.println(rs.getString("subject"));//error myself
				    System.out.println(rs.getString("message"));//error myself
				    System.out.println(rs.getString("date"));//error myself
				    }

				} else if (x == 0) {
					System.out.println("Email Id and Password Incorrect");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main
}// class