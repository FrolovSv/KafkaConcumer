import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.StreamsConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ProducerSendRequest implements Runnable{

    private String topicName;
    private Properties props;

    public ProducerSendRequest(String topicName, Properties properties){

        this.topicName = topicName;

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
    }

    @Override
    public void run() {
        Producer<String, String> producer = new KafkaProducer<>(props);
        //отправка простого сообщения без Key
        try {
            while (true) {
                Thread.sleep(10000);
                StringBuilder msg = new StringBuilder();
                msg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!-- Sber -->\n" +
                        "<SrvGetPaymentOrder xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"SrvGetPaymentOrder.xsd\">\n" +
                        "<header>\n" +
                        "<messageID>0</messageID>\n" +
                        "<processing>\n" +
                        "<rqUid>00000000000000000000000000000000</rqUid>\n" +
                        "<rqTime>2001-12-17T09:30:47Z</rqTime>\n" +
                        "<SPName>ECH</SPName>\n" +
                        "<SCName>OD</SCName>\n" +
                        "</processing>\n" +
                        "</header>\n" +
                        "<request>\n" +
                        "<participant>\n" +
                        "<docID>originator11.12.2012number</docID>\n" +
                        "<docID>originator12.12.2012number</docID>\n" +
                        "<docID>originator13.12.2012number</docID>\n" +
                        "</participant>\n" +
                        "</request>\n" +
                        "</SrvGetPaymentOrder>");
                producer.send(new ProducerRecord<String, String>(topicName, msg.toString()));
                System.out.println("producer sent reqest topic = "+topicName +" "+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}