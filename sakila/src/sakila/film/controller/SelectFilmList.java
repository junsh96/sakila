package sakila.film.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.model.FilmDao;
import sakila.film.vo.Film;

@WebServlet("/selectFilmList")
public class SelectFilmList extends HttpServlet{
	private FilmDao filmDao;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("/application/json;charest=utf-8");
		System.out.println("currentPage:"+request.getParameter("currentPage"));
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		filmDao = new FilmDao();
		List<Film> list = filmDao.selectFilmList(currentPage);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);
	}
}
