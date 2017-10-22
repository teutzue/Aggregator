import com.rabbitmq.client.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AggregatorReceiver {

    private final static String QUEUE_NAME = "aggregator";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages in Aggregator. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                AggregatorService as = new AggregatorService();
                AggregatorSender ag = new AggregatorSender();
                try {
                    JSONObject obj = as.processMessage(message);
                    ag.sendToClient(obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
