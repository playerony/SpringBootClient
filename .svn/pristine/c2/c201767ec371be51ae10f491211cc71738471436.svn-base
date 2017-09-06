package com.playerony.libraryV2.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 30, message="Title length should be between 2 and 30")
	private String title;
	
	public Book() {
		
	}
	
	public Book(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public Book(String title) {
		super();
		this.title = title;
	}
	
	/**
	 * 
	 * Getters
	 * 
	 * @return
	 * 
	 */

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * Setters
	 * 
	 * @return
	 * 
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * toString
	 * 
	 */
	
	public String toString() {
		return "Book[id=" + id + " title=" + title + "]"; 
	}
	
}
