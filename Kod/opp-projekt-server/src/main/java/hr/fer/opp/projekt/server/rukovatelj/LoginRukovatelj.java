package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import org.springframework.stereotype.Component;

@Component
public class LoginRukovatelj implements RukovateljZahtjevom<LoginZahtjev, LoginOdgovor> {
    @Override
    public LoginOdgovor handle(LoginZahtjev zahtjev) {
        return LoginOdgovor.fail("Login nije implementiran! :(");
    }
}
