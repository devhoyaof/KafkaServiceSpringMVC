package com.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : KafkaProducerService
 * date : 2021-11-12
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-12   오후 3:08     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

public interface KafkaProducerService {

	ResponseEntity<Object> producer(String message, String callUUID) throws JsonProcessingException;

	void getEaiList(String callUUID) throws JsonProcessingException;

}
