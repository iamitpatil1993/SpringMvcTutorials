/**
 * 
 */
package com.example.mvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.mvc.dto.Spittle;

/**
 * @author amit
 * 
 */

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

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
}
