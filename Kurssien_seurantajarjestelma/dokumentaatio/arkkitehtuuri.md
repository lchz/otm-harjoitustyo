# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin 
pakkausrakenne on seuraava:

Pakkaus _otm.kurssien_seurantajarjestelma.ui_ sisältää JavaFX:llä toteutetun 
käyttöliittymän _otm.kurssien_seurantajarjestelma.domain_ sovelluslogiigan ja 
_otm.kurssien_seurantajarjestelma.dao_ tietojen pysyväistallennuksesta 
vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää

- kirjautuminen
- uuden käyttäjän luominen
- tulevien kurssien lista
- päättyneiden kurssien lista

Jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on 
näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu 
ohjelmallisesti luokassa [otm.kurssien_seurantajarjestelma.ui.JarjestelmaUi](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/ui/JarjestelmaUi.java). 

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Se ainoastaan 
kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _CourseServicen_ 
metodeja.

Kun sovelluksen tulevien kurssien listan tilanne muuttuu, eli uusi käyttäjä 
kirjautuu, kursseja merkitään päättyneiksi tai niitä luodaan, kutsutaan 
sovelluksen metodeja [redrawCourselist](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/ui/JarjestelmaUi.java#L290) 
ja [redrawFinishedlist](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/ui/JarjestelmaUi.java#L305), 
jotka renderöivät kurssilistanäkymät uudelleen sovellukslogiikalta saamansa 
näytettävien kurssien listojen perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [User](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/User.java) ja [Course](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/Course.java), jotka kuvaavat käyttäjiä ja käyttäjien kursseja:

![kuva luokkakaaviosta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/luokkakaavio.jpg)

Toiminnallisista kokonaisuuksista vastaa luokka [CourseService](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/CourseService.java). Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.

- boolean login(String username, String password)
- List getUnfinished()
- void createCourse(String content, User user)
- void markFinished(int id)

_CourseService_ pääsee käsiksi käyttäjien ja kurssien tietojen tallennuksesta 
vastaavan pakkausessa _otm.kurssien_seurantajarjestelma.dao_ sijaitsevien rajapinnat 
_CourseDao_ ja _UserDao_ toteuttavien luokkien kautta. Luokkien toteutuksen 
injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.
