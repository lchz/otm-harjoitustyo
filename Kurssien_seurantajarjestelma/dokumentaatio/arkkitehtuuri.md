# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin 
pakkausrakenne on seuraava:

![kuva pakkausrakenne](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/pakkausrakenne.jpg)

Pakkaus _otm.kurssienseurantajarjestelma.ui_ sisältää JavaFX:llä toteutetun 
käyttöliittymän _otm.kurssienseurantajarjestelma.domain_ sovelluslogiigan ja 
_otm.kurssienseurantajarjestelma.dao_ tietojen pysyväistallennuksesta 
vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää

- kirjautuminen
- uuden käyttäjän luominen
- tulevien kurssien lista
- päättyneiden kurssien lista

Jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on 
näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu 
ohjelmallisesti luokassa [otm.kurssienseurantajarjestelma.ui.JarjestelmaUi](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/ui/JarjestelmaUi.java). 

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Se ainoastaan 
kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _CourseServicen_ 
metodeja.

Kun sovelluksen tulevien kurssien listan tilanne muuttuu, eli uusi käyttäjä 
kirjautuu, kursseja merkitään päättyneiksi tai niitä luodaan, kutsutaan 
sovelluksen metodeja [redrawCourselist](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/ui/JarjestelmaUi.java#L290) 
ja [redrawFinishedlist](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/ui/JarjestelmaUi.java#L305), 
jotka renderöivät kurssilistanäkymät uudelleen sovellukslogiikalta saamansa 
näytettävien kurssien listojen perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [User](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/domain/User.java) 
ja [Course](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/domain/Course.java), 
jotka kuvaavat käyttäjiä ja käyttäjien kursseja:

![kuva luokkakaaviosta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/luokkakaavio.jpg)

Toiminnallisista kokonaisuuksista vastaa luokka [CourseService](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssienseurantajarjestelma/domain/CourseService.java). 
Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. 
Näitä ovat esim.

- boolean login(String username, String password)
- List getUnfinished()
- void createCourse(String content, User user)
- void markFinished(int id)

_CourseService_ pääsee käsiksi käyttäjien ja kurssien tietojen tallennuksesta 
vastaavan pakkausessa _otm.kurssienseurantajarjestelma.dao_ sijaitsevien 
rajapinnat _CourseDao_ ja _UserDao_ toteuttavien luokkien kautta. 
Luokkien toteutuksen injektoidaan sovelluslogiikalle konstruktorikutsun 
yhteydessä.

CourseServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![kuva pakkauskaavio](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/pakkauskaavio.jpg)

## Tietojen pysyväistallennus

Pakkauksen _otm.kurssienseurantajarjestelma.dao_ luokat _FileCourseDao_ ja 
_FileUserDao_ huolehtivat tietojen tallettamisesta tiedostoihin.

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman 
päätoiminnallisuuden osalta sekvenssikaaviona.

#### käyttäjän kirjaantuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja 
klikataan painiketta_loginButton_ etenee sovelluksen kontrolli seuraavasti:

![kuva kirjautumisesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/arkki-kirjautuminen.png)

#### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus, joka ei ole 
jo käytössä sekä nimi ja klikataan painiketta _create User_ etenee sovelluksen 
kontrolli seuraavasti:

![kuva käyttäjän luomisesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/arkki-kayttjanluominen.png)

#### Kurssin luominen

Uuden kurssin luovan _create course_-painikkeen klikkaamisen jälkeen sovelluksen
kontrolli etenee seuraavasti:

![kuva kurssin luomisesta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/arkki-kurssinluominen.png) 
