# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koorin pakkausrakenne on seuraava:

Pakkaus _otm.kurssien_seurantajarjestelma.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _otm.kurssien_seurantajarjestelma.domain_ sovelluslogiigan ja _otm.kurssien_seurantajarjestelma.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää

- kirjautuminen
- uuden käyttäjän luominen
- kurssilista
- käyneiden kurssien lista

Jokainen näistä on toteutettu omana Scene-oliona. 

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [User](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/User.java) ja [Course](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/Course.java), jotka kuvaavat käyttäjiä ja käyttäjien kursseja:

![kuva luokkakaaviosta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/luokkakaavio.jpg)

Toiminnallisista kokonaisuuksista vastaa luokka [CourseService](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/CourseService.java). Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.

- boolean login(String username, String password)
- List getUnfinished()
- void createCourse(String content, User user)
- void markFinished(int id)
