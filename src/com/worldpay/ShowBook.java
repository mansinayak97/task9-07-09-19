package com.worldpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowBook")
public class ShowBook extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookName = request.getParameter("choice");
		if(bookName.endsWith("pdf"))
			response.setContentType("application/pdf");
		else if(bookName.endsWith("doc"))
			response.setContentType("application/msword");	
		FileInputStream fis = new FileInputStream("G:/Books/E-Books"+bookName);
		byte b[] = new byte[fis.available()];
		fis.read(b);
		fis.close();
		ServletOutputStream out = response.getOutputStream();
		out.write(b);
		out.close();
		
	}

}
