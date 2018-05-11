# Käyttöohje

Lataa tiedosto [kurssien_seurantajarjestelma.jar](https://github.com/lchz/otm-harjoitustyo/releases)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja todot tallettavien tiedostojen nimet. Tiedoston muoto on seuraava

	userFile=users.txt
	todoFile=todos.txt

## Ohjelman käytnnistäminen

Ohjelma käynnistetään komennolla

	java -jar kurssienseurantajarjestelma-1.0-SNAPSHOT.jar

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

![kuva kirjautumisnäkymästä](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/kirjautumisnakyma.png)

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _Login_.

## Uuden käyttäjän luominen

Kirjautumiskäkymästä voi siirtyä uuden käyttäjän luomisnäkymään painamalla _create new user_.

Uusi käyttäjä luodaan syöttämällä oikeanmuotoiset tiedot syötekenttiin ja painamalla _create_.

![kuva uuden käyttäjän luomisesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/uudenkayttajanluominen.png)

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Kurssien luominen ja päättyneeksi merkkaaminen

Onnistuneen kirjautumisen myötä siirrytään käyttäjien olemassolevat kurssit 
listaavaan näkymään 

![kuva kurssienLuominenNakyma](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/kurssienLuominenNakyma.png)

Näkymä mahdollistaa olemassaolevien kurssien merkkaamisen päättyneeksi 
painikkeella _finished_ sekä uusien kurssien luomisen kirjoittamalla 
syötekenttään kurssin kuvauksen ja painamalla _create_.

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, käyttäjä kirjautuu 
ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.
