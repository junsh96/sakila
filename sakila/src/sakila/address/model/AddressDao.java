package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sakila.address.db.DBHelp;
import sakila.address.vo.Address;

public class AddressDao {
	
	//address list 출력 -> 페이징 작업
	public List<Address> selectAddressList(int currentPage){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		String sql = "SELECT * FROM address ORDER BY address_id DESC limit ?,?";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
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
	
	//address추가
	public int insertAddress(Address address){
		int addressId = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO address(address,address2,district,city_id,postal_code,phone,last_update)VALUES(?,?,?,?,?,?,NOW())";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, address.getAddress());
			stmt.setString(2, address.getAddress2());
			stmt.setString(3, address.getDistrict());
			stmt.setInt(4, address.getCityId());
			stmt.setString(5, address.getPostalCode());
			stmt.setString(6, address.getPhone());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(null, stmt, conn);
		}
		
		return addressId;
	}
	//address총 갯수 확인
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT FROM address";
		try {
			conn = DBHelp.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelp.close(rs, stmt, conn);
		}
		return count;
	}
}
