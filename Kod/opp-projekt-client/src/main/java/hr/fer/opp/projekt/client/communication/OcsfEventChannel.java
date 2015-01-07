package hr.fer.opp.projekt.client.communication;

import com.lloseng.ocsf.client.ObservableClient;
import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class OcsfEventChannel implements EventChannel, Observer {
    private final ObservableClient client;

    private final Queue<Odgovor> results = new ArrayBlockingQueue<Odgovor>(16);

    public OcsfEventChannel(final ObservableClient client) {
        this.client = client;
        this.client.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Odgovor: ");
        System.out.println(arg);

        if (arg instanceof Odgovor) {
            results.add((Odgovor) arg);
        }
    }

    @Override
    public <T> T sendAndWait(Zahtjev zahtjev) {
        try {
            client.sendToServer(zahtjev);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (results.isEmpty()) {}

        return (T) results.remove();
    }
}
