package hr.fer.opp.projekt.server.communication;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.rukovatelj.RukovateljZahtjevom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventServer extends AbstractServer implements ApplicationContextAware {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventServer.class);

	private final String host;

	private final int port;

	private final int maxUsers;

	private final KorisnikRepository korisnikRepository;

	private final Map<Long, Connection> connections;

	private final AtomicInteger userCount = new AtomicInteger();

	private ApplicationContext applicationContext;

	@Autowired
	public EventServer(@Value("${application.host}") String host, @Value("${application.port}") int port,
					   @Value("${application.maxUsers}") int maxUsers, KorisnikRepository korisnikRepository) {
		super(port);

		this.host = host;
		this.port = port;
		this.maxUsers = maxUsers;
		this.korisnikRepository = korisnikRepository;
		this.connections = new HashMap<>();
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		LOGGER.info("Client {} connected!", client.getInetAddress());

		connections.put(client.getId(), new Connection(client));

		super.clientConnected(client);
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		LOGGER.info("Client {} disconnected!", client.getInetAddress());

		removeKorisnik(client);

		super.clientDisconnected(client);
	}

	@Override
	protected void serverStarted() {
		LOGGER.info("Server started at {}:{}", host, port);

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
	@Transactional
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Zahtjev zahtjev = (Zahtjev) msg;

		LOGGER.debug("Dosao zahtjev {}.", zahtjev);

		RukovateljZahtjevom<Zahtjev, ?> rukovatelj = findRukovatelj(zahtjev);

		LOGGER.debug("Rjesavam zahtjev {} s {}", zahtjev, rukovatelj);

		Connection connection = connections.get(client.getId());

		Odgovor odgovor = rukovatelj.handle(zahtjev, client, connection.korisnik);

		if (connection.korisnik != null) {
			korisnikRepository.setZadnjiPutAktivanFor(connection.korisnik.getId(), new Date());
		}

		try {
			LOGGER.debug("Odgovaram na zahtjev {} s {}.", zahtjev, odgovor);

			client.sendToClient(odgovor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean setKorisnik(ConnectionToClient client, Korisnik korisnik) {
		if (userCount.incrementAndGet() > maxUsers) {
			userCount.decrementAndGet();
			return false;
		} else {
			connections.get(client.getId()).korisnik = korisnik;
			return true;
		}
	}

	public void removeKorisnik(ConnectionToClient client) {
		Korisnik korisnik = connections.get(client.getId()).korisnik;
		korisnikRepository.setOnlineToFalseFor(korisnik.getId());

		userCount.decrementAndGet();
		connections.get(client.getId()).korisnik = null;
	}

	private RukovateljZahtjevom<Zahtjev, ?> findRukovatelj(Zahtjev zahtjev) {
		for (RukovateljZahtjevom rukovatelj : applicationContext.getBeansOfType(RukovateljZahtjevom.class).values()) {
			ParameterizedType genericSuperclass = (ParameterizedType) rukovatelj.getClass().getGenericInterfaces()[0];
			Class<?> clazz = (Class<?>) genericSuperclass.getActualTypeArguments()[0];

			if (clazz == zahtjev.getClass()) {
				return (RukovateljZahtjevom<Zahtjev, ?>) rukovatelj;
			}
		}

		return null;
	}

	private static class Connection {
		private final ConnectionToClient connection;

		private Korisnik korisnik = null;

		private Connection(ConnectionToClient connection) {
			this.connection = connection;
		}
	}

}
