package com.croamora.examenCROA.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author croamora
 *
 */
@Entity
public class PersonType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String shortDescLabel;

	@Column
	private String type;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the shortDescLabel
	 */
	public String getShortDescLabel() {
		return shortDescLabel;
	}

	/**
	 * @param shortDescLabel the shortDescLabel to set
	 */
	public void setShortDescLabel(String shortDescLabel) {
		this.shortDescLabel = shortDescLabel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	
}