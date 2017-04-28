package lambdasinaction.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by DW on 2017/4/18.
 */
public class MqttPublishSample {


    public static void main(String[] args) {
        String broker       = "tcp://183.230.40.39:6602";
        String clientId     = "5280490";
       /* String topic        = "MQTT Examples";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String broker       = "tcp://183.230.40.39:6602";
//        String broker       = "tcp://iot.eclipse.org:1883";
        String clientId     = "5280490";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("84661");
            connOpts.setPassword("sor2RMzF=Mv0SWX=o=e2563z8vk=".toCharArray());
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            *//*MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);*//*
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }*/


        MqttClient client = null;
        try {
            client = new MqttClient(broker,clientId);

            MqttTopic topic = client.getTopic("tokudu/china");

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("84661");
            connOpts.setPassword("sor2RMzF=Mv0SWX=o=e2563z8vk=".toCharArray());
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            client.connect(connOpts);

            MqttMessage message = new MqttMessage("Hello World. Hello IBM".getBytes());
            message.setQos(1);
           // client.connect();
            while(true){
                MqttDeliveryToken token = topic.publish(message);
                while (!token.isComplete()){
                    token.waitForCompletion(1000);
                }
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }



}
