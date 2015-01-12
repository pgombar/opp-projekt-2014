package hr.fer.opp.projekt.client.communication;

import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.util.ZahtjevOdgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import com.lloseng.ocsf.client.ObservableClient;

public class OcsfEventChannel implements EventChannel, Observer {
    private final ObservableClient client;

    private final Map<String, Odgovor> results = new ConcurrentHashMap<String, Odgovor>();

    public OcsfEventChannel(final ObservableClient client) {
        this.client = client;
        this.client.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Odgovor: ");
        System.out.println(arg);

        if (arg instanceof ZahtjevOdgovor) {
        	ZahtjevOdgovor zo = (ZahtjevOdgovor) arg;
            results.put(zo.getZahtjev().getZahtjevId(), zo.getOdgovor());
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T sendAndWait(Zahtjev zahtjev) {
        try {
            client.sendToServer(zahtjev);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!results.containsKey(zahtjev.getZahtjevId())) {
        }

        return (T) results.get(zahtjev.getZahtjevId());
    }
}
