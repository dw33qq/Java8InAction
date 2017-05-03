package lambdasinaction.mqtt.demo2;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;

/**
 * Created by DW on 2017/5/2.
 */
public class MqttPublishSample {

    public static void main(String[] args) {

        String topic        = "$dp";
        String content      = "{\"datastreams\":[{\"id\":\"abc\", \"datapoints\":[{\"value\": 30.2}]}]}";
        int qos             = 1;
        String broker       = "tcp://183.230.40.39:6002";
        String clientId     = "5280490";
        String userName = "84661";
//        String passWord = "shebei1";
        String passWord = "sor2RMzF=Mv0SWX=o=e2563z8vk=";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(userName);
            connOpts.setPassword(passWord.toCharArray());
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage();
            message.setQos(qos);

            byte[] bytes = new byte[2000];
            bytes[0] = 0x01;
            bytes[1] = (byte) (content.length() >>> 8);
            bytes[2] = (byte) (content.length() & 0xff);
            byte[] json = content.getBytes();

            System.arraycopy(json, 0, bytes, 3, json.length);

            message.setPayload(bytes);

            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
