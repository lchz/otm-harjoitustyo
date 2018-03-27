# Vaatimusmäärittely
## Soveluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa käymättömistään kursseista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttöjän, joilla kaikilla on oma yksilöllinen kurssilistansa.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _opiskelija_. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu _opettaja_.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri näkymästä
<kuva>

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään, onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän kurssilistaan, tai kaytyjen kurssien näkymään. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

* käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  * käyttäjätunnuksen täytyy olla uniikki, jonka pituus on vähintään 3 merkkiä. Siihen ei saa sisältyä erikoismerkkejä eikä ääkkösiä.
  * käyttäjätunnuksen yhteydessä pitää luoda salasana pituudeltaan vähintään 6 merkkiä. Salasanan täytyy sisältää aakkosia, numeroita sekä erikoismerkkejä, muttei ääkkösiä.
  * Lisäksi käyttäjällä pitää rekisteröidä käyttökelpoinen sähköposti

* käyttäjä voi kirjautua järjestelmään
  * kirjautuminen onnistuu syöttämällä olemassaoleva käyttäjätunnus tai sähköposti ja salasana kirjautumislomakkeelle
  * jos käyttäjää ei ole olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

* käyttäjä näkee omat kurssit, joilla käy tällä hetkellä

* käyttäjä voi lisätä uuden kurssin
  * luotu kurssi näkyy ainoastaan sen luoneelle käyttäjälle

* käyttäjä voi merkitä kurssin päädytyksi, jolloin kurssi häviää listalta

* käyttäjä voi tarkastaa kurssit, mitkä on käyty.

*  kättäjä voi kirjautua ulos järjstelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään mahdollisesti seuraavilla toiminnallisuuksilla

  * päätydyksi merkittyjen kurssien merkkaaminen käymättömiksi
  * kurssien tietojen editointi
  * kurssien järjestely tärkeysjärjestykseen
  * kurssien määrittely muille käyttäjille
  * käyttäjätiimit, jotka nkevät kaikki yhteiset kurssit
  * lisätään kenttä, johon on mahdollista merkitä tarkempia kurssiin liittyviä tietoja
  * käyttäjätunnuksen ja siihen liittyvien kurssien poisto
