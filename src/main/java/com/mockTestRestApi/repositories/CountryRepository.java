package com.mockTestRestApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockTestRestApi.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
