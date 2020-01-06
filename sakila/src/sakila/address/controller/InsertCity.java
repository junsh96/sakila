package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.CityDao;
import sakila.address.vo.City;

@WebServlet("/insertCity")
public class InsertCity extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String city = request.getParameter("city");
		int countryId = Integer.parseInt("countryId");
		System.out.println("result:"+city+countryId);
		cityDao = new CityDao();
		City c = new City();
		c.setCity(city);
		c.setCountryId(countryId);
		cityDao.insertCity(c);
	}
}
