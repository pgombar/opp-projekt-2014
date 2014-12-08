package hr.fer.opp.projekt.client.communication;

import com.lloseng.ocsf.client.AbstractClient;

public final class EventClient extends AbstractClient {
    public EventClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println("Dobio od servera:");
        System.out.println(msg);
    }
}
