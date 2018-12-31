/**
 * 
 */
package com.example.mvc.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author amit Model class to represent Spittle.
 *
 */

public class Spittle {

	private Long id;
	private String message;
	private Date time;
	private Double latitude;
	private Double longitude;
	
	// Directly use java validation api annotations for bean validation, and spring will handle everything.
	@NotNull(message = "Provide first name")
	private String firstName;
	
	@NotNull(message = "Provide last name")
	private String lastName;
	
	@NotNull
	@Size(max = 30, min = 4, message = "Username must be of size between 4 to 30")
	private String username;
	
	@NotNull
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
