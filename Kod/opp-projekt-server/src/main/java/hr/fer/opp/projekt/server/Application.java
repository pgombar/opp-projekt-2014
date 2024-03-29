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
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

@Configuration
@ComponentScan
@EnableTransactionManagement

@EnableAutoConfiguration
@EnableJpaRepositories
@EntityScan(value = "hr.fer.opp.projekt.common.model")
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServer.class);

    @Autowired private Collection<RukovateljZahtjevom<? extends Zahtjev, ?>> rukovatelji;

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
