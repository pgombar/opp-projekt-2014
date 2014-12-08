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

1. korak - ako vec nemate Javu, instalirajte je (minimalno Java 7)
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

(podlozno promjenama)\\
Server: hr.fer.opp.projekt.server.Application
Client: hr.fer.opp.projekt.client.Main

Oboje mozete pokrenuti kao normalne java aplikacije (desni klik -> run as -> java application, ili krace `alt+shift+x j`)
