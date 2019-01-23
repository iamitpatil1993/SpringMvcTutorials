/**
 * 
 */
package com.example.mvc.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
	
    private boolean receiveNewsletter;
    private String[] interests;
    private String favouriteWord;
	
	// Directly use java validation api annotations for bean validation, and spring will handle everything.
	@NotNull()
	//@NotEmpty(message = "Provide first name")
	@NotEmpty(message = "{spittle.firstname.notempty}")
	private String firstName;
	
	@NotNull()
	//@NotEmpty(message = "Provide last name")
	@NotEmpty(message = "{spittle.lastName.notempty}")
	private String lastName;
	
	@NotNull
	//@Size(max = 30, min = 4, message = "Username must be of size between {min} to {max}")
	@Size(max = 30, min = 4, message = "{spittle.username.size}")
	private String username;
	
	@NotNull
	//@NotEmpty(message = "Provide password")
	@NotEmpty(message = "{spittle.password.notempty}")
	//@Size(min = 5, max = 20, message = "Password must be of size between {min} to {max}")
	@Size(min = 5, max = 20, message = "{spittle.password.size}")
	private String password;
	
	@NotNull
	//@NotEmpty(message = "Provide email")
	@NotEmpty(message = "{spittle.email.notempty}")
	//@Email(message = "Provide valid email")
	@Email(message = "{spittle.email.email}")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isReceiveNewsletter() {
		return receiveNewsletter;
	}

	public void setReceiveNewsletter(boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}

	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}

	public String getFavouriteWord() {
		return favouriteWord;
	}

	public void setFavouriteWord(String favouriteWord) {
		this.favouriteWord = favouriteWord;
	}

}
