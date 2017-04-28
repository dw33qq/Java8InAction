package lambdasinaction.mqtt;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import java.net.URISyntaxException;

/**
 * Created by DW on 2017/4/17.
 */
public class MqttTest {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("183.230.40.39", 6002);
        mqtt.setClientId("5280490");
        mqtt.setUserName("84661");
        mqtt.setPassword("shebei1");
        mqtt.setVersion("3.1.1");
        mqtt.setKeepAlive(new Short("120").shortValue() );

//        mqtt.isCleanSession()
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();


        Message message = connection.receive();
        System.out.println(message.getTopic());
        byte[] payload = message.getPayload();
     // process the message then:
//        message.ack();

        connection.publish("2323", "Hello".getBytes(), QoS.EXACTLY_ONCE, false);


        connection.disconnect();
    }
}
