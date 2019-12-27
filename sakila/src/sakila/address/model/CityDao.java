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
}
