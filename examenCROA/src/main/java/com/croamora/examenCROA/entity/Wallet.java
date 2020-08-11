package com.croamora.examenCROA.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author croamora
 *
 */
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

	@Column
	private Integer influx;

	@Column
	private Integer output;

	@ManyToOne
	@JoinColumn(name = "id_loan")
	private Loan loan;

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
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the loan
	 */
	public Loan getLoan() {
		return loan;
	}

	/**
	 * @param loan the loan to set
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	/**
	 * @return the influx
	 */
	public Integer getInflux() {
		return influx;
	}

	/**
	 * @param influx the influx to set
	 */
	public void setInflux(Integer influx) {
		this.influx = influx;
	}

	/**
	 * @return the output
	 */
	public Integer getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(Integer output) {
		this.output = output;
	}
	
	
}