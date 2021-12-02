package com.kafka.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.config.KafkaProducerConfig;
import com.kafka.dao.MemberDAO;
import com.kafka.domain.Eai;
import com.kafka.service.KafkaProducerService;
import com.kafka.utils.DateUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
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

	/**
	 * @param message
	 * @param callUUID
	 * @throws JsonProcessingException
	 * @return
	 */
	@Override
	public ResponseEntity<Object> producer(String message, String callUUID) throws JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";


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
			return new ResponseEntity<Object>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		} finally {
			kafkaProducerConfig.producerSetting().flush();
			kafkaProducerConfig.producerSetting().close();
		}
	}


	@Override
	public void getEaiList(String callUUID) throws JsonProcessingException {
		String topic = "from_sdcb_sdcbdatacreated_message";
		String ID = UUID.randomUUID().toString();
		String DATE = DateUtil.getDateStr(DateUtil.getDateStr("yyyy-MM-dd HH:mm:ss"));
		String XAppName = "sdcb_app";

		List<Eai> result = memberDAO.eaiListAll();

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				topic, "payload", objectMapper.writeValueAsString(result)
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
