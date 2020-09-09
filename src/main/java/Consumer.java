import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class Consumer implements Runnable{

    private Properties properties;
    private String clientId;
    private String groupId;
    private String topicName;

    public Consumer(String clientId, Properties properties) throws Exception{
        this.properties = properties;
        this.clientId = clientId;
        this.topicName = properties.getProperty("TOPICNAME_REQEST");
        this.groupId = properties.getProperty("GROUPID");

        if (topicName.equals("") || groupId.equals("") || clientId.equals("")) {
            System.out.println("Enter topic name, groupId, clientId");
            return;
        } else {
//            System.out.println("Consummer = "+clientId+"; is listening...");
            System.out.println("Topic name, groupId, clientId - is entered");
        }
    }

    @Override
    public void run() {
        try {
            Properties props = new Properties();

            props.put("bootstrap.servers", properties.getProperty("URL"));
            props.put("group.id", properties.getProperty("GROUPID"));
            props.put("client.id", clientId);
            props.put("enable.auto.commit", properties.getProperty("ENABLE_AUTO_COMMIT"));
            props.put("auto.commit.interval.ms", properties.getProperty("AUTO_COMMIT_INTERVAL_MS"));
            props.put("session.timeout.ms", properties.getProperty("SESSION_TIMEOUT_MS"));
            //props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
            //props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
            props.put("key.deserializer", properties.getProperty("KEY_DESERIALIZER"));
            props.put("value.deserializer", properties.getProperty("VALUE_DESERIALIZER"));
            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

            consumer.subscribe(Arrays.asList(topicName));
            //System.out.println("Subscribed to topic=" + topicName + ", group=" + groupId + ", clientId=" + clientId);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Duration duration = Duration.ofMillis(Integer.valueOf(properties.getProperty("POLL_TIME_Duration", "100")));

            // looping until ctrl-c
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for (ConsumerRecord<String, String> record : records) {
                    // print the offset,key and value for the consumer records.
                    //System.out.printf("group=" + groupId + ", clientId=" + clientId + " - offset = %d, key = %s, value = %s,  time = %s \n",
                    //        record.offset(), record.key(), record.value(), sdf.format(new Date()));
                    System.out.println("Massege received in topic = "+topicName +" "+ sdf.format(new Date()));
                    ProducerSendResponce producerResponce = new ProducerSendResponce(
                            ConvertMsg(record.value()),
                            properties.getProperty("TOPICNAME_RESPONCE"),
                            properties);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public StringBuilder ConvertMsg(String Msg){
        RequestXML requestXML = new RequestXML(new ByteArrayInputStream(Msg.getBytes()));

        StringBuilder responce = new StringBuilder();
        responce.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!-- Sber -->\n" +
                "<SrvGetPaymentOrder xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"SrvGetPaymentOrder.xsd\">\n" +
                "\t<header>\n" +
                "\t\t\t<messageID>"+requestXML.getMessageID()+"</messageID>\n" +
                "\t\t\t<processing>\n" +
                "\t\t\t\t<rqUid>"+requestXML.getRqUid()+"</rqUid>\n" +
                "\t\t\t\t<rqTime>"+requestXML.getRqTime()+"</rqTime>\n" +
                "\t\t\t\t<SCName>"+requestXML.getSCName()+"</SCName>\n" +
                "\t\t\t\t<SPName>"+requestXML.getSPName()+"</SPName>\n" +
                "\t\t\t</processing>\n" +
                "\t</header>\n" +
                "\t<responce>\n" +
                "\t\t<status>\n" +
                "\t\t\t<statusCode>0</statusCode>\n" +
                "\t\t\t<error>\n" +
                "\t\t\t\t<errorCode>0</errorCode>\n" +
                "\t\t\t\t<errorMessage>errorMessage</errorMessage>\n" +
                "\t\t\t</error>\n" +
                "\t\t</status>\n" +
                "\t\t<participantResults>\n" +
                "\t\t\t<participant>\n");
        for (int i = 0; i < requestXML.getParticipantDocID().size();i++) {
            responce.append(
                    "\t\t\t\t<docInfo>\n" +
                            "\t\t\t\t\t<docID>" + requestXML.getParticipantDocID().get(i) + "</docID>\n" +
                            "\t\t\t\t\t<paymentId>2121312313123</paymentId>\n" +
                            "\t\t\t\t\t<rypeCode>5278476789748247572874892757289479832</rypeCode>\n" +
                            "\t\t\t\t\t<rpBank>\n" +
                            "\t\t\t\t\t</rpBank>\n" +
                            "\t\t\t\t\t</rcBank>\n" +
                            "\t\t\t\t\t</rcBank>\n" +
                            "\t\t\t\t</docInfo>\n");
        }
        responce.append("\t\t\t</participant>\n" +
                "\t\t</participantResults>\n" +
                "\t</responce>\n" +
                "</SrvGetPaymentOrder>");

        return responce;
    }
}
