package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.AddressDao;

@WebServlet("/selectAddressCount")
public class SelectAddressCount {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("/application/json");
		addressDao = new AddressDao();
		int count = addressDao.selectAddressCount();
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		response.getWriter().write(jsonCount);
	}
}
