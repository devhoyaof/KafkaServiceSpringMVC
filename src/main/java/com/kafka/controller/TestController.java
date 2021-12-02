package com.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping(value = "/kafka", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> kafkaSend(@RequestBody String message) throws JsonProcessingException {
		String callUUID = UUID.randomUUID().toString();


		return kafkaProducerService.producer(message, callUUID);

	}

	@RequestMapping(value = "/eaiList", method = RequestMethod.POST)
	@ResponseBody
	public String eaiList() throws JsonProcessingException {
		String callUUID = UUID.randomUUID().toString();

		kafkaProducerService.getEaiList(callUUID);

		return "success";

	}

	@RequestMapping(value = "/insertData", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertData() throws Exception {
		//return kafkaProducerService.insertData();
		return false;
	}

}
