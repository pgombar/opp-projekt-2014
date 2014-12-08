package hr.fer.opp.projekt.server;

import com.lloseng.ocsf.server.AbstractServer;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;
import hr.fer.opp.projekt.server.communication.EventServer;
import hr.fer.opp.projekt.server.rukovatelj.RukovateljZahtjevom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServer.class);

    @Autowired private Collection<RukovateljZahtjevom<? extends Zahtjev, ?>> rukovatelji;

    @Bean
    public AbstractServer eventServer() {
        return new EventServer(5000, rukovatelji);
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        AbstractServer server = context.getBean(AbstractServer.class);

        try {
            server.listen();

            LOGGER.info("Successfully started server!");

            latch.await();
        } catch (InterruptedException e) {
            LOGGER.trace("Interrupted", e);
            context.close();
        } catch (IOException e) {
            LOGGER.trace("Failed", e);
            context.close();
        } finally {
            context.close();
        }
    }
}