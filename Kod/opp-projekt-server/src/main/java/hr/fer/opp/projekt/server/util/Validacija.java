package hr.fer.opp.projekt.server.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Validacija {
    private final List<String> greske = new ArrayList<>();

    public void dodaj(String format, Object... argumenti) {
        greske.add(String.format(format, argumenti));
    }

    public void nijePrazan(String vrijednost, String format, Object... argumenti) {
        if (!StringUtils.hasText(vrijednost)) {
            dodaj(format, argumenti);
        }
    }

    public boolean imaGreske() {
        return !greske.isEmpty();
    }

    public List<String> getGreske() {
        return Collections.unmodifiableList(greske);
    }
}
