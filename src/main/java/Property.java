import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property
{
    private static String URL;
    private static String TOPICNAME_REQEST;
    private static String TOPICNAME_RESPONCE;
    private static String GROUPID;
    private static String CLIENTID;
    private static String ENABLE_AUTO_COMMIT;
    private static String AUTO_COMMIT_INTERVAL_MS;
    private static String SESSION_TIMEOUT_MS;
    private static String KEY_DESERIALIZER;
    private static String VALUE_DESERIALIZER;
    private static Integer POLL_TIME_Duration;
    private static Integer NUMBER_OF_CONSUMERS;

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public Property() throws IOException
    {
        //Properties props = new Properties();
        properties.load(new FileInputStream(new File("ConfigConsumer.properties")));

//        NUMBER_OF_CONSUMERS = Integer.valueOf(properties.getProperty("NUMBER_OF_CONSUMERS","1"));
//        URL = properties.getProperty("URL", "localhost:9092");
//        TOPICNAME_REQEST = properties.getProperty("TOPICNAME_REQEST","out-topic");
//        TOPICNAME_RESPONCE = properties.getProperty("TOPICNAME_RESPONCE","in-topic");
//        GROUPID = properties.getProperty("GROUPID","testGroup01");
//        CLIENTID = properties.getProperty("CLIENTID","Client01");
//        ENABLE_AUTO_COMMIT = properties.getProperty("ENABLE_AUTO_COMMIT","true");
//        AUTO_COMMIT_INTERVAL_MS = properties.getProperty("AUTO_COMMIT_INTERVAL_MS","1000");
//        SESSION_TIMEOUT_MS = properties.getProperty("SESSION_TIMEOUT_MS","30000");
//        KEY_DESERIALIZER = properties.getProperty("KEY_DESERIALIZER","org.apache.kafka.common.serialization.StringDeserializer");
//        VALUE_DESERIALIZER = properties.getProperty("VALUE_DESERIALIZER","org.apache.kafka.common.serialization.StringDeserializer");
//        POLL_TIME_Duration = Integer.valueOf(properties.getProperty("POLL_TIME_Duration","100"));
        // Предположим, что в настройках находится список целых через точку с запятой
        //String[] parts = props.getProperty("SOME_INT_ARRAY").split(";");
        //SOME_INT_ARRAY = new int[parts.length];
//        for (int i = 0; i < parts.length; ++i)
//        {
//            SOME_INT_ARRAY[i] = Integer.valueOf(parts[i]);
//        }
//        properties.setProperty("NUMBER_OF_CONSUMERS", String.valueOf(NUMBER_OF_CONSUMERS));
//        properties.setProperty("URL", String.valueOf(URL));
//        properties.setProperty("TOPICNAME_REQEST", String.valueOf(TOPICNAME_REQEST));
//        properties.setProperty("TOPICNAME_RESPONCE", String.valueOf(TOPICNAME_RESPONCE));
//        properties.setProperty("GROUPID", String.valueOf(GROUPID));
//        properties.setProperty("CLIENTID", String.valueOf(CLIENTID));
//        properties.setProperty("ENABLE_AUTO_COMMIT", String.valueOf(ENABLE_AUTO_COMMIT));
//        properties.setProperty("AUTO_COMMIT_INTERVAL_MS", String.valueOf(AUTO_COMMIT_INTERVAL_MS));
//        properties.setProperty("SESSION_TIMEOUT_MS", String.valueOf(SESSION_TIMEOUT_MS));
//        properties.setProperty("KEY_DESERIALIZER", String.valueOf(KEY_DESERIALIZER));
//        properties.setProperty("VALUE_DESERIALIZER", String.valueOf(VALUE_DESERIALIZER));
//        properties.setProperty("POLL_TIME_Duration", String.valueOf(POLL_TIME_Duration));

    }
}