package sakila.address.vo;

public class City {
	private int cityId;
	private String city;
	private int countryId;
	private String country;
	private String lastUpdate;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", countryId=" + countryId + ", country=" + country
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
}
