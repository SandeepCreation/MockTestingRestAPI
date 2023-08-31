package com.mockTestRestApi.services;

import java.util.List;

import com.mockTestRestApi.entities.Country;
import com.mockTestRestApi.services.impl.AddResponse;

public interface CountryService {
	
	
	public List<Country> getAllCountries();
	
	public Country getCountryById(int id);
	
	public Country getCountryByName(String countryName);
	
	public Country addCountry(Country country);
	
	public Country updateCountry(Country country);
	
	public AddResponse deleteCountry(int id);

}
