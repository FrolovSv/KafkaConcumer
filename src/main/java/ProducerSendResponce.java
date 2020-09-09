import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.StreamsConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ProducerSendResponce{

    private String topicName;
    private StringBuilder msg;
    private Properties props;

    public ProducerSendResponce(StringBuilder msg, String topicName, Properties properties){

        this.topicName = topicName;
        this.msg = msg;

        //Assign topicName to string variable
        //System.out.println("Producer topic=" + topicName);

        // create instance for properties to access producer configs
        props = new Properties();
        //Assign localhost id
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProperty("URL"));
        props.put("acks", properties.getProperty("ACKS"));
        props.put("retries", properties.getProperty("RETRIES"));
        props.put("batch.size", properties.getProperty("BATCH_SIZE"));
        props.put("linger.ms", properties.getProperty("LINGER_MS"));
        props.put("buffer.memory", properties.getProperty("BUFFER_MEMORY"));
        props.put("key.serializer",properties.getProperty("KEY_SERIALIZE"));
        props.put("value.serializer",properties.getProperty("VALUE_SERIALIZER"));

        Producer<String, String> producer = new KafkaProducer<>(props);
        //отправка простого сообщения без Key
        producer.send(new ProducerRecord<String, String>(topicName,msg.toString()));
        System.out.println("producer sent responce in topic = "+topicName +" "+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}