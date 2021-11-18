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
	public KafkaProducer<String, String> producerSetting() {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "nex_grid");
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9090,localhost:9091,localhost:9092");
		props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "0");
		props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
		props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "0");
		props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

		// Producer 객체 생성
		producer = new KafkaProducer<String, String>(props);
		return producer;
	}

}
