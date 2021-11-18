package com.kafka.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.config.KafkaProducerConfig;
import com.kafka.service.KafkaProducerService;
import com.kafka.utils.DateUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
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


	/**
	 * @param message
	 * @param callUUID
	 * @throws JsonProcessingException
	 */
	@Override
	public void producer(String message, String callUUID) throws JsonProcessingException {
		String topic = "from_adcb_adcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "adcb_app";

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				topic, "payload", objectMapper.writeValueAsString(message)
		);
		record.headers().add("ID", ID.getBytes(StandardCharsets.UTF_8));
		record.headers().add("DESTINATION", topic.getBytes(StandardCharsets.UTF_8));
		record.headers().add("DATE", DATE.getBytes(StandardCharsets.UTF_8));
		record.headers().add("X-App-Name", XAppName.getBytes(StandardCharsets.UTF_8));
		record.headers().add("X-Global-Transation-ID", callUUID.getBytes(StandardCharsets.UTF_8));

		try {
			kafkaProducerConfig.producerSetting().send(record, new KafkaCallback());
			log.info(" kafka Message Info => {} ", String.valueOf(record));
			kafkaProducerConfig.producerSetting().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * KafkaCallBack method create
	 */
	class KafkaCallback implements Callback {
		@Override
		public void onCompletion(RecordMetadata metadata, Exception exception) {
			if(!ObjectUtils.isEmpty(metadata)) {
				System.err.println("===========================================================");
				log.info("Partition: {}, Offset: {}, " ,metadata.partition(), metadata.offset());
			} else {
				log.error("KafkaCallback - Exception");
				// Todo
				// 스케쥴러 서비스 들어갈 예정 [방식 확정 x]
			}
		}
	}
}
