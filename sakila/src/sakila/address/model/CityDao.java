package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.db.DBHelp;
import sakila.address.vo.City;

public class CityDao {
	public List<City> selectCityListByCountry(int countryId){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT city_id, city FROM city WHERE country_id = ?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				System.out.println("city"+rs.getInt("city_id"));
				city.setCityId(rs.getInt("city_id"));
				city.setCity(rs.getString("city"));
				list.add(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		
		return list;
	}
	public List<City> selectCityList(int currentPage){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		String sql = "SELECT c.city_id,c.city,c.country_id,c.last_update,co.country FROM city as c JOIN country as co WHERE c.country_id = co.country_id ORDER BY city_id DESC LIMIT ?,?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City c = new City();
				c.setCityId(rs.getInt("city_id"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setCountryId(rs.getInt("country_id"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO city(city,country_id,last_update)VALUES(?,?,NOW())";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getCity());
			stmt.setInt(2, city.getCountryId());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(null, stmt, conn);
		}
	}
}
