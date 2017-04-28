package lambdasinaction.mqtt.demo3;

import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;

/**
 * Created by DW on 2017/4/19.
 */
public class MQTTFutureClient {


    private final static String CONNECTION_STRING = "tcp://183.230.40.39:6002";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
    private final static String CLIENT_ID = "5280490";
    public  static Topic[] topics = {
            new Topic("china/beijing", QoS.EXACTLY_ONCE),
            new Topic("china/tianjin", QoS.AT_LEAST_ONCE),
            new Topic("china/henan", QoS.AT_MOST_ONCE)};
    public final  static long RECONNECTION_ATTEMPT_MAX=6;
    public final  static long RECONNECTION_DELAY=2000;

    public final static int SEND_BUFFER_SIZE=2*1024*1024;//发送最大缓冲为2M


    public static void main(String[] args)   {
        //创建MQTT对象
        MQTT mqtt = new MQTT();
        try {
            //设置mqtt broker的ip和端口
            mqtt.setHost(CONNECTION_STRING);
            //连接前清空会话信息
            mqtt.setCleanSession(CLEAN_START);
            //设置重新连接的次数
            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
            //设置重连的间隔时间
            mqtt.setReconnectDelay(RECONNECTION_DELAY);
            //设置心跳时间
            mqtt.setKeepAlive(KEEP_ALIVE);
            //设置缓冲的大小
            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);


            mqtt.setClientId(CLIENT_ID);
            mqtt.setUserName("84661");
            mqtt.setPassword("shebei1");
            mqtt.setVersion("3.1.1");


            //获取mqtt的连接对象BlockingConnection
            final FutureConnection connection= mqtt.futureConnection();
            connection.connect();
            connection.subscribe(topics);
            while(true){
                Future<Message> futrueMessage=connection.receive();
                Message message =futrueMessage.await();


                System.out.println("MQTTFutureClient.Receive Message "+ "Topic Title :"+message.getTopic()+" context :"+String.valueOf(message.getPayloadBuffer()));
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{

        }
    }
}
