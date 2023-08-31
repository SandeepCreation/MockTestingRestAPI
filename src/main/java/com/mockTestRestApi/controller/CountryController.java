package com.mockTestRestApi.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockTestRestApi.entities.Country;
import com.mockTestRestApi.services.CountryService;
import com.mockTestRestApi.services.impl.AddResponse;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries() {
		try {
			List<Country> countries = this.countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
		try {
			Country countryById = this.countryService.getCountryById(id);
			return new ResponseEntity<Country>(countryById, HttpStatus.FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {
		try {
			Country countryByName = this.countryService.getCountryByName(countryName);
			return new ResponseEntity<Country>(countryByName, HttpStatus.FOUND);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		try {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.countryService.addCountry(country));
		}catch(NoSuchElementException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country) {
		try {
			Country existcountry = countryService.getCountryById(id);
			existcountry.setCountryName(country.getCountryName());
			existcountry.setCountryCapital(country.getCountryCapital());
			return ResponseEntity.ok(this.countryService.updateCountry(existcountry));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id") int id) {
		return countryService.deleteCountry(id);

	}
}
