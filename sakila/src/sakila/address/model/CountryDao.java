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
}
