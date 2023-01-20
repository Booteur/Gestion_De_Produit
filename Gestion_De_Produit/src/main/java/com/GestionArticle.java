package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GestionArticle")
public class GestionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Hashtable<String, Double> prixArt;
    Hashtable<String, String> nomArt;
    	
    public void init(ServletConfig config) throws ServletException{
    	prixArt = new Hashtable<String, Double>();
    	nomArt = new Hashtable<String, String>();
    	nomArt.put("A1", "Smartphone Sumsung J7");
    	nomArt.put("A2", "Smartphone Ivertec A7");
    	nomArt.put("A3", "Power Bank132MA");
    	nomArt.put("A4", "Kit MMD 14A");
    	nomArt.put("A5", "Iphone 13Pro");
    	
    	prixArt.put("A1", 13.5);
    	prixArt.put("A2", 30.55);
    	prixArt.put("A3", 20.35);
    	prixArt.put("A4", 100.0);
    	prixArt.put("A5", 150.0);
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		double tva = Double.parseDouble(request.getParameter("tva"));
		int qt = Integer.parseInt(request.getParameter("QT"));
		String article = request.getParameter("article");
		String nomArticle = nomArt.get(article);
		double prix = prixArt.get(article);
		double ptot=prix*qt*(1+tva);
		out.println("<html> <body>");
		out.println("<h1> Facturation Commande </h1>");
		out.println("<b> Article Command� :" +nomArticle +"<br>" + "Quantite Commande : " + qt);
		out.println("<br> Prix Unitaire = " + prix);
		out.println("<br> <b> Prix Total = " + ptot);
		
		if(qt>=5) {
			out.println("<br> **** Remis : "+ptot*0.05);
			out.println("<br> **** Prix Net : "+ptot*0.95);
		}
		out.println("</body> </html>");
	}

}