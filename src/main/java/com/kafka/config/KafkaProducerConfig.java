package com.kafka.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@Configuration
public class KafkaProducerConfig {

	@Autowired
	KafkaProducer<String, String> producer;

	@Bean
	public KafkaProducer<String, String> kafkaSetting() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.RETRIES_CONFIG, "0");
		properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
		properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "0");
		properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

		// Producer 객체 생성
		producer = new KafkaProducer<String, String>(properties);
		return producer;
	}

}
