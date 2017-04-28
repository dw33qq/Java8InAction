package lambdasinaction.mqtt.demo2;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by DW on 2017/4/19.
 */
public class Server {


    public static final String HOST = "tcp://183.230.40.39:6002";
//    public static final String HOST = "tcp://127.0.0.1:1883";

    public static final String TOPIC = "tokudu/yzq124";
    private static final String clientid ="5280490";

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "84661";
//    private String passWord = "sor2RMzF=Mv0SWX=o=e2563z8vk=";
    private String passWord = "shebei1";

    private MqttMessage message;

    public Server() throws MqttException {
        //MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttMessage message) throws MqttPersistenceException, MqttException{
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println(token.isComplete()+"========");
    }

    public static void main(String[] args) throws MqttException {
        Server server =  new Server();
        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        server.message.setPayload("eeeeeaaaaaawwwwww---".getBytes());


        Byte[] bytes = new Byte[10];

        bytes[0] = 0x03;
        System.out.println("1".getBytes());

        //server.publish(server.message);
        System.out.println(server.message.isRetained()+"------ratained状态");
    }


}
