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

@WebServlet("/selectFilmOne")
public class SelectFilmOne extends HttpServlet {
	private FilmDao filmDao;
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("/application/json; charest=utf-8");
		int filmId = Integer.parseInt((request.getParameter("filmId")));
		System.out.println("filmIdCon:"+filmId);
		filmDao = new FilmDao();
		List<Film> list = filmDao.selectFilmOne(request);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);
	}

}
