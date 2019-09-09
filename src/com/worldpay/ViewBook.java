package com.worldpay;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File f=new File("G:/Books/E-Books");
        String[] files = f.list();
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<body>");
        for(String book:files)
        {
        	out.print("<a href='ShowBook.java?choice="+book+"'>"+book+"</a>");
        	out.print("<br>");
        }
        
        out.print("</body>");
        
        out.print("</html>");

	}

}
