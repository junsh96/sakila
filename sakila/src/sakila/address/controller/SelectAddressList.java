package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.AddressDao;
import sakila.address.vo.Address;

@WebServlet("/selectAddressList")
public class SelectAddressList extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("applocation/json");
		addressDao = new AddressDao();
		System.out.println("currentPage:"+ request.getParameter("currentPage"));
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		List<Address> list = addressDao.selectAddressList(currentPage);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println("json"+jsonList);
		response.getWriter().write(jsonList);
	}
}
