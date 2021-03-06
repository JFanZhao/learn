package org.ivan.learn.bigdata.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 09:45
 **/
public class CKafkaProducerDemo {
    public static void main(String args[]) {
        //加载kafka.properties。
        Properties properties = new Properties();
        //设置接入点，请通过控制台获取对应Topic的接入点。
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.202.60.30:9092");
        //消息队列Kafka版消息的序列化方式, 此处demo 使用的是StringSerializer。
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        //请求的最长等待时间。
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);
        //设置客户端内部重试次数。
        properties.put(ProducerConfig.RETRIES_CONFIG, 5);
        //设置客户端内部重试间隔。
        properties.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 3000);
        System.setProperty("java.security.auth.login.config",
                "/Users/ivan/dev/applications/git/learn/learn-bigdata-kafka/src/main/resources/ckafka_client_jaas.conf");

        System.out.println(System.getProperty("java.security.auth.login.config"));
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        properties.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        //构造Producer对象。
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        //构造一个消息队列Kafka版消息。
        String topic = "test_zyf"; //消息所属的Topic，请在控制台申请之后，填写在这里。
        String value = "this is ckafka msg value"; //消息的内容。
        try {
            //批量获取Future对象可以加快速度, 但注意, 批量不要太大。
            List<Future<RecordMetadata>> futureList = new ArrayList<>(128);
            for (int i = 100; i < 105; i++) {
                //发送消息，并获得一个Future对象。
                ProducerRecord<String, String> kafkaMsg = new ProducerRecord<>(topic,
                        "{\"id\":"+i+",\"name\":\"ivan"+i+"\",\"age\":"+i+"}");
                Future<RecordMetadata> metadataFuture = producer.send(kafkaMsg);
                futureList.add(metadataFuture);
            }
            producer.flush();
            for (Future<RecordMetadata> future : futureList) {
                //同步获得Future对象的结果。
                RecordMetadata recordMetadata = future.get();
                System.out.println("produce send ok: " + recordMetadata.toString());
            }
        } catch (Exception e) {
            //客户端内部重试之后，仍然发送失败，业务要应对此类错误。
            System.out.println("error occurred"+e.getLocalizedMessage());
        }
    }
}
