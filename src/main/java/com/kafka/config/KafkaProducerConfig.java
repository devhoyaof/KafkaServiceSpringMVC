package com.kafka.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author : KimGwangMin
 * @name : KimGwangMin
 * <PRE>
 * </PRE>
 * @class : KafkaProducerConfig
 * date : 2021-11-12
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-12   오후 2:49     KimGwangMin (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */
@Component
public class KafkaProducerConfig {

	@Autowired
	Properties kafkaProp;

	public KafkaProducer<String, String> producerSetting() {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProp.getProperty("kafka.server"));
		props.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, kafkaProp.getProperty("kafka.max_block_ms_config"));
		props.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, kafkaProp.getProperty("kafka.max_request_size_config"));
		props.setProperty(ProducerConfig.ACKS_CONFIG, kafkaProp.getProperty("kafka.acks_config"));
		props.setProperty(ProducerConfig.RETRIES_CONFIG, kafkaProp.getProperty("kafka.retries_config"));
		props.setProperty(ProducerConfig.LINGER_MS_CONFIG, kafkaProp.getProperty("kafka.linger_ms_config"));
		props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProp.getProperty("kafka.buffer_memory_config"));
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


		// Producer 객체 생성
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		return producer;

	}
}
