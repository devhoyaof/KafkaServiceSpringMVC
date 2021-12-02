package com.kafka.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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
 * </PRE>
 */

@Getter
@Setter
public class Eai {
	private String cmd;
	private String newRequestId;
	private String newAccountType;
	private String newPayMentCode;
	private String newProductName;
	private String newMultiCode;
	private String newSmlsStlmDvCd;
	private String newSmlsStmlCmpnyCd;
	private String newBan;
	private String newAceNo;
	private String newProdNo;
	private String newEventYymm;
	private int newTotAmt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+9", pattern = "yyyy-MM-dd HH:mm")
	private Date newPurchaseDt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+9", pattern = "yyyy-MM-dd HH:mm")
	private Date newTransactionDt;
	private String newSelerCompany;
	private String newProductId;
	private int newProductPrice;
	private int newProductTax;
	private String newSellerName;
	private String newSellerEmail;
	private String anewSellerPhone;
	private String newSellerRegNumber;
	private String newPaymentName;
	private String newPurchaseId;
	private String newPrfRequestId;
	private String newPurchaseGen;
	private String newUnitIp;
	private String newMac;
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+9", pattern = "yyyy-MM-dd HH:mm")
	private Date newProvisioningTime;
	private String newTopmenuid;
	private String newProdtype;
	private String kafkaResult;


}
