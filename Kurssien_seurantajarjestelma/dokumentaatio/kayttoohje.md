# Käyttöohje

Lataa tiedosto [kurssien_seurantajarjestelma.jar](https://github.com/lchz/otm-harjoitustyo/releases)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja todot tallettavien tiedostojen nimet. Tiedoston muoto on seuraava

	userFile=users.txt
	todoFile=todos.txt

## Ohjelman käytnnistäminen

Ohjelma käynnistetään komennolla

	java -jar Kurssien_seurantajarjestelma.jar

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

![kuva kirjautumisnäkymästä](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/kirjautumisnakyma.png)

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _Login_.

## Uuden käyttäjän luominen

Kirjautumiskäkymästä voi siirtyä uuden käyttäjän luomisnäkymään painamalla _create new user_.

Uusi käyttäjä luodaan syöttämällä oikeanmuotoiset tiedot syötekenttiin ja painamalla _create_.

![kuva uuden käyttäjän luomisesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/uudenkayttajanluominen.png)

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.
