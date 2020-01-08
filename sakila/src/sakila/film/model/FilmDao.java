package sakila.film.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sakila.address.db.DBHelp;
import sakila.film.vo.Film;

public class FilmDao {
	public List<Film> selectFilmList(int currentPage){
		List<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		String sql = "SELECT f.film_id,f.title,f.description,f.release_year,f.rental_duration,f.rental_rate,f.replacement_cost,f.rating,f.special_features,f.last_update, l.name FROM film as f JOIN language as l WHERE f.language_id = l.language_id LIMIT ?,?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setFilmId(rs.getInt("film_id"));
				f.setDescription(rs.getString("description"));
				f.setLastUpdate(rs.getString("last_update"));
				f.setName(rs.getString("name"));
				f.setRating(rs.getString("rating"));
				f.setReleaseYear(rs.getInt("release_year"));
				f.setRentalDuration(rs.getInt("rental_duration"));
				f.setRentalRate(rs.getString("rental_rate"));
				f.setReplacementCost(rs.getString("replacement_cost"));
				f.setSpecialFeatures(rs.getString("special_features"));
				f.setTitle(rs.getString("title"));
				list.add(f);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	public List<Film> selectFilmOne(HttpServletRequest request) {
		List<Film> list = new ArrayList<Film>();
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		System.out.println("filmId:" +filmId);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT f.title,f.description,l.name FROM film as f JOIN language as l WHERE f.language_id = l.language_id AND f.film_id = ?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setName(rs.getString("name"));
				list.add(f);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
}
