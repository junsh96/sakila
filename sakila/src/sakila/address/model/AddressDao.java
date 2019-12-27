package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.db.DBHelp;
import sakila.address.vo.Address;

public class AddressDao {
	
	
	public List<Address> selectAddressList(){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM address limit 100";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Address address = new Address();
				address.setAddressId(rs.getInt("address_id"));
				address.setAddress(rs.getString("address"));
				address.setAddress2(rs.getString("address2"));
				address.setDistrict(rs.getString("district"));
				address.setPostalCode(rs.getString("postal_code"));
				address.setPhone(rs.getString("phone"));
				address.setLastUpdate(rs.getString("last_update"));
				list.add(address);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
}
