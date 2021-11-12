package com.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : TestController
 * date : 2021-11-12
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-12   오후 3:06     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Controller
public class TestController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@RequestMapping(value = "/kafka", method = RequestMethod.POST)
	@ResponseBody
	public String kafkaSend(@RequestBody String message) throws JsonProcessingException {

		String callUUID = UUID.randomUUID().toString();

		kafkaProducerService.producer(message, callUUID);


		return "success";
	}

}
