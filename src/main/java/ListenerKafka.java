public class ListenerKafka {

    public static void main(final String[] args) throws Exception {
        Property property = new Property();

        // Для отправки запросов в kafka
        new Thread(new ProducerSendRequest(
                property.getProperties().getProperty("TOPICNAME_REQEST"),
                property.getProperties()))
                .start();


//        if (streaming>1){
//            ProcessBuilder processBuilder = new ProcessBuilder("cmd","/c","");
//        }

        int streaming = Integer.valueOf(property.getProperties().getProperty("NUMBER_OF_CONSUMERS"));
        for(int i = 0; i < streaming; i++) {
            new Thread(new Consumer("client" + (i+1), property.getProperties())).start();
        }
    }

}
