package com.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.domain.Eai;
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



	void getEaiList(String callUUID) throws JsonProcessingException;

	ResponseEntity<Object> producer(Eai eai, String callUUID) throws JsonProcessingException;

	ResponseEntity<Object> reqSend(Eai eais, String callUUID) throws Exception;
}
