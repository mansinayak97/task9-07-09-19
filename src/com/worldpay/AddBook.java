package com.worldpay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	PreparedStatement ps=null;
	Connection con=null;
	public void init(){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		 ps=con.prepareStatement("insert into books(bookId,bookName,authorName,genre,price) values(?,?,?,?,?)");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void destroy(){
		try{
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId=request.getParameter("bookId");
		String bookName=request.getParameter("bookName");
		String authorName=request.getParameter("authorName");
		String genre=request.getParameter("genre");
		String price=request.getParameter("price");
		
		int bId=Integer.parseInt(bookId);
		int bPrice=Integer.parseInt(price);
		try {
			ps.setInt(1,bId);
			ps.setString(2,bookName);
			ps.setString(3,authorName);
			ps.setString(4,genre);
			ps.setInt(5,bPrice);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("Book Entry Successfull");
	}

}
