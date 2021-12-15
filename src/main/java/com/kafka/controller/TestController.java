package com.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.domain.Eai;
import com.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author : KimGwangMin
 * @name : KimGwangMin
 * <PRE>
 * </PRE>
 * @class : TestController
 * date : 2021-11-12
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-12   오후 3:06     KimGwangMin (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Controller
public class TestController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

/*	@RequestMapping(value = "/kafka", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> kafkaSend(@RequestBody Eai eais) throws IOException {
		String callUUID = UUID.randomUUID().toString();


		return kafkaProducerService.producer(eais, callUUID);

	}*/

	@RequestMapping(value = "postMan", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> producer(@RequestBody Eai reqMessage) throws Exception {
		String callUUID = UUID.randomUUID().toString();

		return kafkaProducerService.reqSend(reqMessage, callUUID);

	}

	@RequestMapping(value = "/eaiList", method = RequestMethod.POST)
	@ResponseBody
	public String eaiList() throws JsonProcessingException {
		String callUUID = UUID.randomUUID().toString();

		kafkaProducerService.getEaiList(callUUID);

		return "success";

	}

	/*@RequestMapping(value ="/tojson", method = RequestMethod.POST)
	@ResponseBody
	public String toJson() {

		//최종 완성될 JSONObject 선언(전체)
		JsonObject jsonObject = new JsonObject();
		//person의 JSON정보를 담을 Array 선언
		JsonArray personArray = new JsonArray();
		JsonObject jsonObject1 = new JsonObject();

		//person의 한명 정보가 들어갈 JSONObject 선언
		JsonObject personInfo = new JsonObject();

		personInfo.addProperty("RELS_ID_NM", "sampleId");
		personInfo.addProperty("BLPROC_YN", "nexg_adcb_test");
		jsonObject1.add("nexgEventDTO", personInfo);
		personArray.add(jsonObject1);

		jsonObject1 = new JsonObject();
		personInfo = new JsonObject();
		personInfo.addProperty("RELS_ID_NM", "sampleId2");
		personInfo.addProperty("BLPROC_YN", "nexg_adcb_test2");
		jsonObject1.add("nexgEventDTO", personInfo);
		personArray.add(jsonObject1);

		jsonObject.add("payload", personArray);
		//JSONObject를 String 객체에 할당
		JsonObject jsonInfo = jsonObject;

		System.out.print(jsonInfo);

		return "success";
	}*/

	@RequestMapping(value = "/insertData", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertData() throws Exception {
		//return kafkaProducerService.insertData();
		return false;
	}

}
