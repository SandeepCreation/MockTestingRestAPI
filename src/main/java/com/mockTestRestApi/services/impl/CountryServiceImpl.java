package com.mockTestRestApi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockTestRestApi.entities.Country;
import com.mockTestRestApi.repositories.CountryRepository;
import com.mockTestRestApi.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepo;

	@Override
	public List<Country> getAllCountries() {
		return this.countryRepo.findAll();
	}

	@Override
	public Country getCountryById(int id) {
		List<Country> countryById = countryRepo.findAll();
		Country country = null;
		for (Country con : countryById) {
			if (con.getId() == id) {
				country = con;
			}
		}
		return country;
	}

	@Override
	public Country getCountryByName(String countryName) {
		List<Country> list = this.countryRepo.findAll();
		Country country = null;
		for (Country coun : list) {
			if (coun.getCountryName().equalsIgnoreCase(countryName)) {
				country = coun;
			}
		}
		return country;
	}

	@Override
	public Country addCountry(Country country) {
		country.setId(setMaxId());
		return this.countryRepo.save(country);
	}

	@Override
	public Country updateCountry(Country country) {
		return this.countryRepo.save(country);
	}

	@Override
	public AddResponse deleteCountry(int id) {
		this.countryRepo.deleteById(id);
		AddResponse ad = new AddResponse();
		ad.setId(id);
		ad.setMsg("Country Deleted");
		return ad;
	}

	public int setMaxId() {
		return this.countryRepo.findAll().size() + 1;
	}

}
