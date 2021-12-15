package com.kafka.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author : KimGwangMin
 * @name : KimGwangMin
 * <PRE>
 * </PRE>
 * @class : Eai
 * date : 2021-11-23
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-23   오전 11:06     KimGwangMin (hoyaof@lgupluspartners.co.kr)        최초작성
 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 삭제예정
 *
 * </PRE>
 */


public class Eai {
	@JsonProperty("reqstId")
	private String newRequestId;
	@JsonProperty("storeStlmKdCd")
	private String newAccountType;
	@JsonProperty("stlmMnsCd")
	private String newPayMentCode;
	@JsonProperty("storeApplNm")
	private String newProductName;
	@JsonProperty("cmpxStlmCd")
	private String newMultiCode;
	@JsonProperty("stlmDvCd")
	private String newSmlsStlmDvCd;
	@JsonProperty("stlmCmpnyCd")
	private String newSmlsStmlCmpnyCd;
	@JsonProperty("billAcntNo")
	private String newBan;
	@JsonProperty("aceno")
	private String newAceNo;
	@JsonProperty("prodNo")
	private String newProdNo;
	@JsonProperty("prssYymm")
	private String newEventYymm;
	@JsonProperty("trxAmt")
	private long newTotAmt;
	@JsonProperty("occrDttm")
	private String newPurchaseDt;
	@JsonProperty("rgstDttm")
	private String newTransactionDt;
	@JsonProperty("saleSpmlNm")
	private String newSelerCompany;
	@JsonProperty("storeSvcId")
	private String newProductId;
	@JsonProperty("saleTamt")
	private long newProductPrice;
	@JsonProperty("txamt")
	private long newProductTax;
	@JsonProperty("sellerNm")
	private String newSellerName;
	@JsonProperty("sellerEmailAddr")
	private String newSellerEmail;
	@JsonProperty("sellerAddr")
	private String newSellerAddress;
	@JsonProperty("sellerTelno")
	private String anewSellerPhone;
	@JsonProperty("commSaleDclNo")
	private String newSellerRegNumber;
	@JsonProperty("stlmMnsNm")
	private String newPaymentName;
	@JsonProperty("custOrderNo")
	private String newPurchaseId;
	@JsonProperty("bfrReqstId")
	private String newPreRequestId;
	@JsonProperty("blprocYn")
	private String newPurchaseGen;
	@JsonProperty("devcIp")
	private String newUnitIp;
	@JsonProperty("devcMacAddr")
	private String newMac;
	@JsonProperty("devcAutnDttm")
	private String newProvisioningTime;
	@JsonProperty("sysCreationDate")
	private String sysCreationDate;
	@JsonProperty("prdlstClssCd")
	private String newTopmenuid;
	@JsonProperty("svcKdCd")
	private String newProdtype;

	public String getNewRequestId() {
		return newRequestId;
	}

	public void setNewRequestId(String newRequestId) {
		this.newRequestId = newRequestId;
	}

	public String getNewAccountType() {
		return newAccountType;
	}

	public void setNewAccountType(String newAccountType) {
		this.newAccountType = newAccountType;
	}

	public String getNewPayMentCode() {
		return newPayMentCode;
	}

	public void setNewPayMentCode(String newPayMentCode) {
		this.newPayMentCode = newPayMentCode;
	}

	public String getNewProductName() {
		return newProductName;
	}

	public void setNewProductName(String newProductName) {
		this.newProductName = newProductName;
	}

	public String getNewMultiCode() {
		return newMultiCode;
	}

	public void setNewMultiCode(String newMultiCode) {
		this.newMultiCode = newMultiCode;
	}

	public String getNewSmlsStlmDvCd() {
		return newSmlsStlmDvCd;
	}

	public void setNewSmlsStlmDvCd(String newSmlsStlmDvCd) {
		this.newSmlsStlmDvCd = newSmlsStlmDvCd;
	}

	public String getNewSmlsStmlCmpnyCd() {
		return newSmlsStmlCmpnyCd;
	}

	public void setNewSmlsStmlCmpnyCd(String newSmlsStmlCmpnyCd) {
		this.newSmlsStmlCmpnyCd = newSmlsStmlCmpnyCd;
	}

	public String getNewBan() {
		return newBan;
	}

	public void setNewBan(String newBan) {
		this.newBan = newBan;
	}

	public String getNewAceNo() {
		return newAceNo;
	}

	public void setNewAceNo(String newAceNo) {
		this.newAceNo = newAceNo;
	}

	public String getNewProdNo() {
		return newProdNo;
	}

	public void setNewProdNo(String newProdNo) {
		this.newProdNo = newProdNo;
	}

	public String getNewEventYymm() {
		return newEventYymm;
	}

	public void setNewEventYymm(String newEventYymm) {
		this.newEventYymm = newEventYymm;
	}

	public long getNewTotAmt() {
		return newTotAmt;
	}

	public void setNewTotAmt(long newTotAmt) {
		this.newTotAmt = newTotAmt;
	}

	public String getNewPurchaseDt() {
		return newPurchaseDt;
	}

	public void setNewPurchaseDt(String newPurchaseDt) {
		this.newPurchaseDt = newPurchaseDt;
	}

	public String getNewTransactionDt() {
		return newTransactionDt;
	}

	public void setNewTransactionDt(String newTransactionDt) {
		this.newTransactionDt = newTransactionDt;
	}

	public String getNewSelerCompany() {
		return newSelerCompany;
	}

	public void setNewSelerCompany(String newSelerCompany) {
		this.newSelerCompany = newSelerCompany;
	}

	public String getNewProductId() {
		return newProductId;
	}

	public void setNewProductId(String newProductId) {
		this.newProductId = newProductId;
	}

	public long getNewProductPrice() {
		return newProductPrice;
	}

	public void setNewProductPrice(long newProductPrice) {
		this.newProductPrice = newProductPrice;
	}

	public long getNewProductTax() {
		return newProductTax;
	}

	public void setNewProductTax(long newProductTax) {
		this.newProductTax = newProductTax;
	}

	public String getNewSellerName() {
		return newSellerName;
	}

	public void setNewSellerName(String newSellerName) {
		this.newSellerName = newSellerName;
	}

	public String getNewSellerEmail() {
		return newSellerEmail;
	}

	public void setNewSellerEmail(String newSellerEmail) {
		this.newSellerEmail = newSellerEmail;
	}

	public String getNewSellerAddress() {
		return newSellerAddress;
	}

	public void setNewSellerAddress(String newSellerAddress) {
		this.newSellerAddress = newSellerAddress;
	}

	public String getAnewSellerPhone() {
		return anewSellerPhone;
	}

	public void setAnewSellerPhone(String anewSellerPhone) {
		this.anewSellerPhone = anewSellerPhone;
	}

	public String getNewSellerRegNumber() {
		return newSellerRegNumber;
	}

	public void setNewSellerRegNumber(String newSellerRegNumber) {
		this.newSellerRegNumber = newSellerRegNumber;
	}

	public String getNewPaymentName() {
		return newPaymentName;
	}

	public void setNewPaymentName(String newPaymentName) {
		this.newPaymentName = newPaymentName;
	}

	public String getNewPurchaseId() {
		return newPurchaseId;
	}

	public void setNewPurchaseId(String newPurchaseId) {
		this.newPurchaseId = newPurchaseId;
	}

	public String getNewPreRequestId() {
		return newPreRequestId;
	}

	public void setNewPreRequestId(String newPreRequestId) {
		this.newPreRequestId = newPreRequestId;
	}

	public String getNewPurchaseGen() {
		return newPurchaseGen;
	}

	public void setNewPurchaseGen(String newPurchaseGen) {
		this.newPurchaseGen = newPurchaseGen;
	}

	public String getNewUnitIp() {
		return newUnitIp;
	}

	public void setNewUnitIp(String newUnitIp) {
		this.newUnitIp = newUnitIp;
	}

	public String getNewMac() {
		return newMac;
	}

	public void setNewMac(String newMac) {
		this.newMac = newMac;
	}

	public String getNewProvisioningTime() {
		return newProvisioningTime;
	}

	public void setNewProvisioningTime(String newProvisioningTime) {
		this.newProvisioningTime = newProvisioningTime;
	}

	public String getSysCreationDate() {
		return sysCreationDate;
	}

	public void setSysCreationDate(String sysCreationDate) {
		this.sysCreationDate = sysCreationDate;
	}

	public String getNewTopmenuid() {
		return newTopmenuid;
	}

	public void setNewTopmenuid(String newTopmenuid) {
		this.newTopmenuid = newTopmenuid;
	}

	public String getNewProdtype() {
		return newProdtype;
	}

	public void setNewProdtype(String newProdtype) {
		this.newProdtype = newProdtype;
	}
}
