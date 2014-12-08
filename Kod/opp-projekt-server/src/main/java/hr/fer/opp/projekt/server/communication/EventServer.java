package hr.fer.opp.projekt.server.communication;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;
import hr.fer.opp.projekt.server.rukovatelj.RukovateljZahtjevom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class EventServer extends AbstractServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServer.class);

    private final Collection<RukovateljZahtjevom<?, ?>> rukovatelji;

    public EventServer(int port, Collection<RukovateljZahtjevom<?, ?>> rukovatelji) {
        super(port);

        this.rukovatelji = rukovatelji;
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        LOGGER.info("Client {} connected!", client.getInetAddress());

        super.clientConnected(client);
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        LOGGER.info("Client {} disconnected!", client.getInetAddress());

        super.clientDisconnected(client);
    }

    @Override
    protected void serverStarted() {
        LOGGER.info("Server started");

        super.serverStarted();
    }

    @Override
    protected void serverStopped() {
        LOGGER.info("Server stopped");

        super.serverStopped();
    }

    @Override
    protected void serverClosed() {
        LOGGER.info("Server closed");

        super.serverClosed();
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Zahtjev zahtjev = (Zahtjev) msg;

        LOGGER.debug("Dosao zahtjev {}.", zahtjev);

        RukovateljZahtjevom<Zahtjev, ?> rukovatelj = findRukovatelj(zahtjev);

        LOGGER.debug("Rjesavam zahtjev {} s {}", zahtjev, rukovatelj);

        Odgovor odgovor = rukovatelj.handle(zahtjev);

        try {
            LOGGER.debug("Odgovaram na zahtjev {} s {}.", zahtjev, odgovor);

            client.sendToClient(odgovor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RukovateljZahtjevom<Zahtjev, ?> findRukovatelj(Zahtjev zahtjev) {
        for(RukovateljZahtjevom<?, ?> rukovatelj : rukovatelji) {
            ParameterizedType genericSuperclass = (ParameterizedType) rukovatelj.getClass().getGenericInterfaces()[0];
            Class<?> clazz = (Class<?>) genericSuperclass.getActualTypeArguments()[0];

            if (clazz == zahtjev.getClass()) {
                return (RukovateljZahtjevom<Zahtjev, ?>) rukovatelj;
            }
        }

        return null;
    }
}
