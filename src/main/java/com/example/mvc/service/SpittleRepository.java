/**
 * 
 */
package com.example.mvc.service;

import java.util.List;
import java.util.Optional;

import com.example.mvc.dto.Spittle;

/**
 * @author amit
 * This is middle-tier (backend) service, which will act as a model for MVC to provide services to controller.
 *
 */
public interface SpittleRepository {

	public List<Spittle> findSpittles(Long max, Integer count);
	public Spittle findById(Long spittleId);
	public Long create(Spittle spittle);
	public Optional<Spittle> findByUsername(final String username);
}
