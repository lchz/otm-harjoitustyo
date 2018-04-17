# Arkkitehtuurikuvaus

## Sovellyslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [User](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/User.java) ja [Course](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/Course.java), jotka kuvaavat käyttäjiä ja käyttäjien kursseja:

![kuva luokkakaaviosta](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/dokumentaatio/kuvat/luokkakaavio.jpg)

Toiminnallisista kokonaisuuksista vastaa luokka [CourseService](https://github.com/lchz/otm-harjoitustyo/blob/master/Kurssien_seurantajarjestelma/src/main/java/otm/kurssien_seurantajarjestelma/domain/CourseService.java). Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.

- boolean login(String username, String password)
- List
