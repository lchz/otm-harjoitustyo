# Kurssien seurantajärjestelmä

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa päättymättömistään kursseista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen kurssilistansa.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kayttoohje.md)

[Testikattavuus](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/testikattavuus.png)

[Arkkitehtuurikuvaus](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Kurssien_seurantajarjestelma-app](https://github.com/lchz/otm-harjoitustyo/releases/tag/v0.5-alpha)

## Komentorivitoiminnot

### Projektin suorittaminen

Projektin koodin pystyy suorittamaan komennolla

	mvn compile exec:java -Dexec.mainClass=otm.kurssienseurantajarjestelma.ui.JarjestelmaUi

### Testaus

Testit suoritetaan komennolla

	mvn test

Testikattavuusraportti luodaan komennolla

	mvn test jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

	mvn package

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Kurssien_seurantajarjestelma-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

	mavn javadoc:javadoc

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon checkstyle.sml määrittelemät tarkistukset suoritetaan komennolla

	mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
