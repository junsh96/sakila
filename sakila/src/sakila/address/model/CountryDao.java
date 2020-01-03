package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.db.DBHelp;
import sakila.address.vo.Country;

public class CountryDao {
	public List<Country> selectCountryListAll(){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM country ORDER BY country_id";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				country.setCountryId(rs.getInt("country_id"));
				country.setCountry(rs.getString("country"));
				list.add(country);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs,stmt,conn);
		}
		return list;
	}
	public void insertCountry(Country country) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO country(country, last_update) VALUES(?,now())";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(null, stmt, conn);
		}
	}
	
	public List<Country> selectCountryList(int currentPage){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int rowPerPage = 10;
		System.out.println("cunnertPage:"+currentPage);
		int beginRow = (currentPage-1)*rowPerPage;
		String sql = "SELECT * FROM country ORDER BY country_id DESC LIMIT ?,?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
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
}
