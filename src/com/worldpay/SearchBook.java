package com.worldpay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	PreparedStatement ps=null;
	Connection con=null;
	public void init(){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		 ps=con.prepareStatement("Select bookName,authorName,genre,price from books where genre=?");
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String genre=request.getParameter("genre");
		try {
			ps.setString(1,genre);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(0)+" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
