package hr.fer.opp.projekt.client.communication;

import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

public interface EventChannel {
    <T> T sendAndWait(Zahtjev zahtjev);
}
