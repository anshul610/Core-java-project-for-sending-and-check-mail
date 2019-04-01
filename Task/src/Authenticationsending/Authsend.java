package Authenticationsending;

import java.sql.*;
import java.util.*;

public class Authsend {
	public static void main(String[] args) throws Exception {
		ResultSet rs = null;
		PreparedStatement pst = null;
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
					System.out.println("**********Login Successfully**********");
					System.out.println("Want to Send Mail to your Friend");
					System.out.println("Enter Reciever ID");
					String receiver = sc.next();
					System.out.println("Enter Suject Course");
					String subject = sc.next();
					System.out.println("Enter Message");
					String message = sc.next();
					java.util.Date date = new java.util.Date();
					long time = date.getTime();
					java.sql.Date sqldate = new java.sql.Date(time);
					pst = con.prepareStatement("insert into mail values (?,?,?,?,?,?)");
					//pst = con.prepareStatement("insert into mail (sender,receiver,subject,message,Date)values (?,?,?,?,?,?)");
					pst.setInt(1, 0);
					pst.setString(2, emailid);
					pst.setString(3, receiver);
					pst.setString(4, subject);
					pst.setString(5, message);
					pst.setDate(6, sqldate);
					pst.executeUpdate();
					System.out.println("Email send suceesfully");
					System.out.println("Send Another Email Press Y");
					String nextmail = sc.nextLine();
					if (nextmail.equals("Y") || nextmail.equals("y")) {
						continue;
					} else {
						break;
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
