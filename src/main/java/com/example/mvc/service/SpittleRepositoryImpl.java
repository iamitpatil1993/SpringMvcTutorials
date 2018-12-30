/**
 * 
 */
package com.example.mvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.mvc.dto.Spittle;

/**
 * @author amit
 * 
 */

@Component
public class SpittleRepositoryImpl implements SpittleRepository {
	private static final Logger logger = LoggerFactory.getLogger(SpittleRepositoryImpl.class);
	private final List<Spittle> spittles = new ArrayList<>(10);
	
	@Override
	public List<Spittle> findSpittles(Long max, Integer count) {
		List<Spittle> spittles = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Double id = Math.random() * max;
			Spittle spittle =  new Spittle();
			spittle.setId(id.longValue());
			spittle.setMessage("Spittle :: " + (i + 1));
			spittle.setTime(new Date());
			spittles.add(spittle);
		}
		return spittles;
	}
	
	@Override
	public Spittle findById(Long spittleId) {
		List<Spittle> spittles = findSpittles(spittleId, 1);
		Spittle spittle = spittles.get(0);
		spittle.setId(spittleId);
		return spittle;
	}

	@Override
	public Long create(Spittle spittle) {
		spittle.setId(System.currentTimeMillis());
		spittles.add(spittle);
		return spittle.getId();
	}

	@Override
	public Optional<Spittle> findByUsername(String username) {
		logger.info("Inside findByUsername with username :: " + username);
		Optional<Spittle> spittle = spittles.stream().filter(tempSpittle -> tempSpittle.getUsername().equals(username)).findFirst();
		logger.info("spittle found :: " + spittle);
		return spittle;
	}
}
