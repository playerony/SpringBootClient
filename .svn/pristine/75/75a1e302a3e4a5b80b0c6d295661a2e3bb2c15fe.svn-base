package com.playerony.libraryV2.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Author {
	private Long id;
	
	@NotNull
	@Size(min = 2, max=30, message="Firstname length should be between 2 and 30")
	private String firstName;
	
	@NotNull
	@Size(min = 2, max=30, message="Lastname length should be between 2 and 30")
	private String lastName;
	
	@NotNull
	@Size(min = 1, message="Gender length should be 1")
	private String gender;
	
	private Integer age;
	
	@NotNull
	@Size(min = 11, message="Pesel length should be 11")
	private String pesel;
	private Integer bookID;
	
	public Author() {
		
	}
	
	public Author(String firstName, String lastName, String gender, Integer age, String pesel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.pesel = pesel;
	}

	public Author(String firstName, String lastName, String gender, Integer age, String pesel, Integer bookID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.pesel = pesel;
		this.bookID = bookID;
	}

	public Author(Long id, String firstName, String lastName, String gender, Integer age, String pesel) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.pesel = pesel;
	}

	public Author(Long id, String firstName, String lastName, String gender, Integer age, String pesel,
			Integer bookID) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.pesel = pesel;
		this.bookID = bookID;
	}

	/**
	 * 
	 * Getters and setters
	 * 
	 * @return
	 * 
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	/**
	 * 
	 * toString
	 * 
	 */
	
	public String toString() {
		return "Author[id=" + this.id + " firstName=" + this.firstName + " lastName=" + this.lastName + " gender=" + this.gender + " age=" + this.age + " pesel=" + this.pesel + " bookID=" + bookID + "]";
	}
	
}
