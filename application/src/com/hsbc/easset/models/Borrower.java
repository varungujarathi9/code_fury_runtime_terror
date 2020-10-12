package com.hsbc.easset.models;

import java.time.LocalDate;

public class Borrower {
	
	private int assetId;
	private String assetName;
	private String assetType;
	private String assetDesc;
	
	private int userId;
	private LocalDate dateOfissue;
	private LocalDate expectedReturnDate;
	private LocalDate actualReturnDate;
	private String adminAlert;
	private long lateAmount;
	
	public long getLateAmount() {
		return lateAmount;
	}
	public void setLateAmount(long lateAmount) {
		this.lateAmount = lateAmount;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LocalDate getDateOfissue() {
		return dateOfissue;
	}
	public void setDateOfissue(LocalDate dateOfissue) {
		this.dateOfissue = dateOfissue;
	}
	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(LocalDate actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public String getAdminAlert() {
		return adminAlert;
	}
	public void setAdminAlert(String adminAlert) {
		this.adminAlert = adminAlert;
	}
	
	

}
