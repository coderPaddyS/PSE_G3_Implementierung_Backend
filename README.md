# Kit-Finder-Backend

Dieses Repository ist die Implementierung des Backends nach Spezifikation des Entwurfes des PSE-Projektes "Kit-Finder".

# Kompilierungsmindestanforderungen
Zum Kompilieren wird die Installation von `mariadb` bzw. `mysql` benötigt.
Hierfür ist das Anlegen eines Nutzers namens `mysql` mit dem in `backend/src/main/resources/application.properties` angegebenen, verschlüsselten Passwort notwendig.
Das benötigte Passwort muss dementsprechend bei uns angefragt werden.
Außerdem muss eine Datenbank namens `backenddb` (und `testdb` für die Tests) erstellt werden.
Dies ist möglich mit dem Befehl `CREATE DATABASE backenddb` bzw. `CREATE DATABASE testdb`, nachdem man in `mariadb` bzw. `mysql` als Nutzer `mysql` eingeloggt ist.

# Kompilierung
Sind die Kompilierungsmindestanforderungen erfüllt, so kann das Backend durch das Ausführen der Hauptklasse, `backend/src/main/java/de/itermori/pse/kitroomfinder/backend/BackendApplication`, gestartet werden.
