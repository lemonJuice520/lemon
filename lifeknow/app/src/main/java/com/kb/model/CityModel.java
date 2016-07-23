package com.kb.model;

public class CityModel {
	private int CityId;
	public String name;
	public String pinyi;

	public CityModel(String name, String pinyi) {
		super();
		this.name = name;
		this.pinyi = pinyi;
	}

	public CityModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}

	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
}
