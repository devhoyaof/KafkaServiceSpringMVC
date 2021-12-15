package com.kafka.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kafka.config.KafkaProducerConfig;
import com.kafka.dao.MemberDAO;
import com.kafka.domain.Dcb;
import com.kafka.domain.Eai;
import com.kafka.domain.NexgridDTO;
import com.kafka.domain.Payload;
import com.kafka.service.KafkaProducerService;
import com.kafka.utils.DateUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : KafkaProducerServiceImpl
 * date : 2021-11-12
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-12   오후 3:11     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

	private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
	static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private KafkaProducerConfig kafkaProducerConfig;

	@Autowired
	private MemberDAO memberDAO;

	private ObjectMapper mapper;

	@Override
	public ResponseEntity<Object> reqSend(Eai eais, String callUUID) throws Exception {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";
		String setPayload = "";

		Map<String, Object> map = new HashMap<>();
		map.put("ID", ID);
		map.put("DESTINATION", topic);
		map.put("DATE", DATE);
		map.put("X-App-Name", XAppName);
		map.put("X-Global-Transation-ID", callUUID);
		JSONObject headersData = new JSONObject(map);

		setPayload = objectMapper.writeValueAsString(eais);

		JSONObject payload = new JSONObject();
		payload.put("headers", headersData);
		payload.put("payload", setPayload);


		System.err.println("push data = " + payload);

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				topic, payload.toString()
		);

		try {
			kafkaProducerConfig.producerSetting().send(record, new KafkaCallback()).get();
			log.info(" kafka Message Info => {} ", record);
			return new ResponseEntity<Object>(payload, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(payload, HttpStatus.BAD_REQUEST);
		} finally {
			kafkaProducerConfig.producerSetting().flush();
			kafkaProducerConfig.producerSetting().close();
		}
	}


/*	@Override
	public ResponseEntity<Object> reqSend(String reqMessage, String callUUID) th rows ExecutionException, InterruptedException, JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";

		List<Header> headers = new ArrayList<>();
		headers.add(new RecordHeader("ID", UUID.randomUUID().toString().getBytes()));
		headers.add(new RecordHeader("DATE", DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss")).getBytes()));
		headers.add(new RecordHeader("DESTINATION", topic.getBytes()));
		headers.add(new RecordHeader("X-App-Name", "sdcb_app".getBytes()));
		headers.add(new RecordHeader("X-Global-Transation-ID", callUUID.getBytes()));

		ProducerRecord<String, String> record = new ProducerRecord<>(
				topic, 0, "sdcb", reqMessage, headers
		);

		kafkaProducerConfig.producerSetting().send(record, new KafkaCallback()).get();
		log.info(" kafka Message Info => {} ", record);

		return new ResponseEntity<Object>(reqMessage, HttpStatus.OK);
	}*/

	@Override
	public ResponseEntity<Object> producer(Eai eai, String callUUID) throws JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";


		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				topic, "payload", objectMapper.writeValueAsString(eai)
		);

		kafkaProducerConfig.producerSetting().send(record, new KafkaCallback());
		log.info(" kafka Message Info => {} ", record);

		return new ResponseEntity<Object>(eai, HttpStatus.OK);
	}




	@Override
	public void getEaiList(String callUUID) throws JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";

		List<Eai> result = memberDAO.eaiListAll();

		objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

		Dcb dcb = new Dcb();
		NexgridDTO nexgridDTO = new NexgridDTO();
		Payload payload = new Payload();
		System.out.println("======================================");
		System.out.println(objectMapper.writeValueAsString(payload));
		System.out.println("======================================");

		for(Eai data : result) {
			nexgridDTO.setEai(data);
//			payload.setNexgridDTO(nexgridDTO);
//			dcb.setNexgridDTO(nexgridDTO);
//			payload.setDcb(dcb);

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(
					topic, "payload", objectMapper.writeValueAsString(payload)
			);

			record.headers().add("ID", ID.getBytes(StandardCharsets.UTF_8));
			record.headers().add("DESTINATION", topic.getBytes(StandardCharsets.UTF_8));
			record.headers().add("DATE", DATE.getBytes(StandardCharsets.UTF_8));
			record.headers().add("X-App-Name", XAppName.getBytes(StandardCharsets.UTF_8));
			record.headers().add("X-Global-Transation-ID", callUUID.getBytes(StandardCharsets.UTF_8));

			if(result.size() > 0) {
				try {
					kafkaProducerConfig.producerSetting().send(record, new KafkaCallback()).get();
					log.info(" kafka Message Info => {} ", record);
					for(int i=0; i < result.size(); i++) {
						memberDAO.eaiColumnUpdate(result.get(i));
					}
	//			return new ResponseEntity<Object>(result, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					// Todo
	//			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
				} finally {
					kafkaProducerConfig.producerSetting().flush();
					kafkaProducerConfig.producerSetting().close();
				}
			} else {
				log.info("kafka Message not data");
				kafkaProducerConfig.producerSetting().close();
			}
		}
	}



	/*@Override
	public void getEaiList(String callUUID) throws JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";

		List<Eai> result = memberDAO.eaiListAll();

		//최종 완성될 JSONObject 선언(전체)
		JsonObject jsonObject = new JsonObject();

		//person의 JSON정보를 담을 Array 선언
		JsonArray personArray = new JsonArray();

		for(int i=0; i<result.size(); i++) {
			JsonObject dtoInfo = new JsonObject();
			JsonObject jsonObject1 = new JsonObject();
			//person의 한명 정보가 들어갈 JSONObject 선언
			JsonObject personInfo = new JsonObject();
			personInfo.addProperty("REQST_ID", result.get(i).getNewRequestId());
			personInfo.addProperty("STORE_STLM_KD_CD", result.get(i).getNewAccountType());
			personInfo.addProperty("STLM_MMS_CD", result.get(i).getNewPayMentCode());
			personInfo.addProperty("STORE_APPL_NM", result.get(i).getNewProductName());
			personInfo.addProperty("CMPX_STLM_CD", result.get(i).getNewMultiCode());
			personInfo.addProperty("STLM_DV_CD", result.get(i).getNewSmlsStlmDvCd());
			personInfo.addProperty("STLM_CMPNY_CD", result.get(i).getNewSmlsStmlCmpnyCd());
			personInfo.addProperty("BILL_ACNT_NO", result.get(i).getNewBan());
			personInfo.addProperty("ACENO", result.get(i).getNewAceNo());
			personInfo.addProperty("PROD_NO", result.get(i).getNewProdNo());
			personInfo.addProperty("PRSS_YYMM", result.get(i).getNewEventYymm());
			personInfo.addProperty("TRX_AMT", result.get(i).getNewTotAmt());
			personInfo.addProperty("OCCR_DTTM", result.get(i).getNewPurchaseDt());
			personInfo.addProperty("RGST_DTTM", result.get(i).getNewTransactionDt());
			personInfo.addProperty("SALE_SPML_NM", result.get(i).getNewSelerCompany());
			personInfo.addProperty("STORE_SVC_ID", result.get(i).getNewProductId());
			personInfo.addProperty("SALE_TAMT", result.get(i).getNewProductPrice());
			personInfo.addProperty("TXAMT", result.get(i).getNewProductTax());
			personInfo.addProperty("SELLER_NM", result.get(i).getNewSellerName());
			personInfo.addProperty("SELLER_EMAIL_ADDR", result.get(i).getNewSellerEmail());
			personInfo.addProperty("SELLER_ADDR", result.get(i).getNewSellerAddress());
			personInfo.addProperty("SELLER_TELNO", result.get(i).getAnewSellerPhone());
			personInfo.addProperty("COMM_SALE_DCL_NO", result.get(i).getNewSellerRegNumber());
			personInfo.addProperty("STLM_MNS_NM", result.get(i).getNewPaymentName());
			personInfo.addProperty("CUST_ORDER_NO", result.get(i).getNewPurchaseId());
			personInfo.addProperty("BFR_REQST_ID", result.get(i).getNewPreRequestId());
			personInfo.addProperty("BLPROC_YN", result.get(i).getNewPurchaseGen());
			personInfo.addProperty("DEVC_IP", result.get(i).getNewUnitIp());
			personInfo.addProperty("DEVC_MAC_ADDR", result.get(i).getNewMac());
			personInfo.addProperty("DEVC_AUTN_DTTM", result.get(i).getNewProvisioningTime());
			personInfo.addProperty("SYS_CREATION_DATE", "2021-12-06 13:30:56");
			personInfo.addProperty("PRDLST_CLSS_CD", result.get(i).getNewTopmenuid());
			personInfo.addProperty("SVC_KD_CD", result.get(i).getNewProdtype());
			jsonObject1.add("nexgEventDTO", personInfo);
			dtoInfo.add("nexgridDTO", jsonObject1);
			personArray.add(dtoInfo);
			dtoInfo = new JsonObject();
			dtoInfo.add("DCB", personArray);
			jsonObject.add("payload", dtoInfo);

			//JSONObject를 String 객체에 할당
		}

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				topic, "payload", objectMapper.writeValueAsString(jsonObject.get("payload").toString())
		);

		record.headers().add("ID", ID.getBytes(StandardCharsets.UTF_8));
		record.headers().add("DESTINATION", topic.getBytes(StandardCharsets.UTF_8));
		record.headers().add("DATE", DATE.getBytes(StandardCharsets.UTF_8));
		record.headers().add("X-App-Name", XAppName.getBytes(StandardCharsets.UTF_8));
		record.headers().add("X-Global-Transation-ID", callUUID.getBytes(StandardCharsets.UTF_8));

		if(result.size() > 0) {
			try {
				kafkaProducerConfig.producerSetting().send(record, new KafkaCallback()).get();
				log.info(" kafka Message Info => {} ", record);
				for(int i=0; i < result.size(); i++) {
					memberDAO.eaiColumnUpdate(result.get(i));
				}
//			return new ResponseEntity<Object>(result, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				// Todo
//			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
			} finally {
				kafkaProducerConfig.producerSetting().flush();
				kafkaProducerConfig.producerSetting().close();
			}
		} else {
			log.info("kafka Message not data");
			kafkaProducerConfig.producerSetting().close();
		}
	}*/

	/**
	 * KafkaCallBack method create
	 */
	class KafkaCallback implements Callback {
		final Logger logger = LoggerFactory.getLogger(KafkaCallback.class);
		@Override
		public void onCompletion(RecordMetadata recordMetadata, Exception e) {
			if(e == null) {
				logger.info("================================================================== Success ===================================================================");
				logger.info("SuccessFully recieved the datils as : \n "+
							"Topic: {} " + recordMetadata.topic() + "\n" +
							"Partition: {} " +  recordMetadata.partition() + "\n" +
							"Offset: {} " + recordMetadata.offset() + "\n" +
							"Timestamp: {} " + recordMetadata.timestamp());
				logger.info("==============================================================================================================================================");
			} else {
				logger.info("=================================================================== fail =====================================================================");
				logger.error("Can't producer,getting error{} " , e.getMessage(), e);
				logger.info("==============================================================================================================================================");
			}
		}
	}
}
