# Vaatimusmäärittely
## Soveluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa käymättömistään kursseista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttöjän, joilla kaikilla on oma yksilöllinen kurssilistansa.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _opiskelija_. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu _opettaja_.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri näkymästä

![kuva käyttöliittymäluonnoksesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/vaatimusmaarittely.jpg)

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään, onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän kurssilistaan, tai kaytyjen kurssien näkymään. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

* käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  * käyttäjätunnuksen täytyy olla uniikki, jonka pituus on vähintään 3 merkkiä. Siihen ei saa sisältyä erikoismerkkejä eikä ääkkösiä.
  * käyttäjätunnuksen yhteydessä pitää luoda salasana pituudeltaan vähintään 6 merkkiä ilman ääkkösiä.
  * Lisäksi käyttäjällä pitää rekisteröidä käyttökelpoinen sähköposti

* käyttäjä voi kirjautua järjestelmään
  * kirjautuminen onnistuu syöttämällä olemassaoleva käyttäjätunnus ja salasana kirjautumislomakkeelle
  * jos käyttäjää ei ole olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

* käyttäjä näkee omat kurssit, joilla käy tällä hetkellä

* käyttäjä voi lisätä uuden kurssin
  * luotu kurssi näkyy ainoastaan sen luoneelle käyttäjälle

* käyttäjä voi merkitä kurssin päätyneeksi, jolloin kurssi häviää listalta

*  kättäjä voi kirjautua ulos järjstelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään mahdollisesti seuraavilla toiminnallisuuksilla

  * päätyneeksi merkittyjen kurssien merkkaaminen käymättömiksi
  * kurssien tietojen editointi
  * kurssien järjestely tärkeysjärjestykseen
  * kurssien määrittely muille käyttäjille
  * käyttäjätiimit, jotka nkevät kaikki yhteiset kurssit
  * lisätään kenttä, johon on mahdollista merkitä tarkempia kurssiin liittyviä tietoja
  * käyttäjätunnuksen ja siihen liittyvien kurssien poisto
