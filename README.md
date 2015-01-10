opp-projekt-2014
================

Privatni repozitorij projekta iz OPP-a.

Clanovi tima:
* Matija Folnovic
* Ivan Golik
* Paula Gombar
* Antea Hadviger (duhovni vodja!)
* Jelena Kopcic
* Ivan Paljak
* Magdalena Petak

Setup projekta
--------------

1. korak - ako vec nemate Javu, instalirajte je (minimalno Java 7, obavezno JDK, a ne JRE)
2. korak - ako vec nemate Eclipse, skinuti ga (Kepler ili novije)
3. korak - dodati update site `http://download.eclipse.org/technology/m2e/releases` i instalirati `m2e - Maven Integration for Eclipse`
  - restartati Eclipse nakon instalacije
4. korak - importati projekt
  - file -> import
  - maven -> existing maven project
  - kao root direktorij odabrati `ovaj_direktorij/Kod` (trebao bi pokazati 4 projekta pod Projects) i Finish
5. korak - podesiti JavaFX
  - desni klik na opp-projekt-client -> properties
  - java build path -> libraries -> add external JARs
  - nadete direktorij gdje vam je Java i odaberete `jre/lib/jfxrt.jar` ili `lib/jfxrt.jar`, i dodate to
6. korak - podesiti Eclipse da vam automatski refreshea datoteke kada pullate:
  - Ukljuciti Window -> Preferences -> General -> Workspace -> Refresh using native hooks or polling

- Server: hr.fer.opp.projekt.server.Application
- Client: hr.fer.opp.projekt.client.main.MainApp

Oboje mozete pokrenuti kao normalne java aplikacije (desni klik -> run as -> java application, ili krace `alt+shift+x j`).

Instalacija
-----------

1. korak - izvrsiti: `mvn clean package`
2. korak - u direktorij `opp-projekt-server/target` kreirana je datoteka `opp-projekt-server-1.0-SNAPSHOT.jar`
  - za pokretanje servera, potrebno je samo izvrsiti `java -jar opp-projekt-server-1.0-SNAPSHOT.jar`
3. korak - u direktorij `opp-projekt-client/target/jfx/app` kreirana je datoteka `opp-projekt-client-1.0-SNAPSHOT-jfx.jar`
  - za pokretanje klijenta, potrebno je izvrsiti `java -jar opp-projekt-client-1.0-SNAPSHOT-jfx.jar`
  - upozorenje: kada se premjestaju datoteke, potrebno je premjestiti i `lib` direktorij u `opp-projekt-client/target/jfx/app`
