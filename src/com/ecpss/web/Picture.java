package com.ecpss.web;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Picture extends HttpServlet{
	private BufferedImage image;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{		
		
		String ispicture =request.getParameter("ispicture");  
		//System.out.println("ispicture-------------"+ispicture);
	  	if(ispicture==null){
	  		ispicture = "";
	  	}
	    response.setHeader("Pragma","No-cache");
	    response.setHeader("Cache-Control","no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");
	    int width=760, height=80;

	    if(ispicture!="" || !(ispicture.equals(""))){
	    	image = ImageIO.read(new FileInputStream(ispicture)); 
		    Graphics g = image.getGraphics();
		    
		    g.dispose();
		    ImageIO.write(image, "JPEG", response.getOutputStream());
	    }
	}
}
