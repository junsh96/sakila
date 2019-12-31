package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.AddressDao;
import sakila.address.vo.Address;

@WebServlet("/insertAddress")
public class InsertAddress extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		System.out.println("addressCon:"+ address);
		System.out.println("address2:"+ address2);
		System.out.println("district:"+ district);
		addressDao = new AddressDao();
		Address a = new Address();
		a.setAddress(address);
		addressDao.insertAddress(a);
	}
}
