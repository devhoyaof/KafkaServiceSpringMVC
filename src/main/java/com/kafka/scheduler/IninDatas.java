package com.kafka.scheduler;

import com.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author : KimGwangMin
 * @name : KimGwangMin
 * <PRE>
 * </PRE>
 * @class : IninDatas
 * date : 2021-11-30
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-30   오전 10:10     KimGwangMin (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Service("anotherObject")
public class IninDatas {

	@Autowired
	KafkaProducerService kafkaProducerService;

	public void loadIpAddress() {
		try {
			String callUUID = UUID.randomUUID().toString();
			kafkaProducerService.getEaiList(callUUID);
		} catch (Exception ex) {
		}
	}
}
