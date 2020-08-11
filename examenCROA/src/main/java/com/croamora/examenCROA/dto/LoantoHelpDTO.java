package com.croamora.examenCROA.dto;

/**
 * @author croamora
 */
public class LoantoHelpDTO {

	private Integer idLead;
	private String name;
	private String reason;
	private String description;
	private Integer amountNeeded;
	private Integer amountReised;
	/**
	 * @return the idLead
	 */
	public Integer getIdLead() {
		return idLead;
	}
	/**
	 * @param idLead the idLead to set
	 */
	public void setIdLead(Integer idLead) {
		this.idLead = idLead;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the amountNeeded
	 */
	public Integer getAmountNeeded() {
		return amountNeeded;
	}
	/**
	 * @param amountNeeded the amountNeeded to set
	 */
	public void setAmountNeeded(Integer amountNeeded) {
		this.amountNeeded = amountNeeded;
	}
	/**
	 * @return the amountReised
	 */
	public Integer getAmountReised() {
		return amountReised;
	}
	/**
	 * @param amountReised the amountReised to set
	 */
	public void setAmountReised(Integer amountReised) {
		this.amountReised = amountReised;
	}
	
}