# Sklep

Jest to prosta aplikacja typu sklep z koszykiem zakupowym (koszyk zapisywany w sesji), w której możemy wybierać różną liczbę produktów z kilku kategorii 

Działanie aplikacji można podejrzeć [pod tym linkem](http://my-shop-123.surge.sh/)

Kod frontendu dostępny jest [tutaj](https://github.com/pWydmuch/Shop-frontend)

Serwis Heroku, na którym wdrożony jest backend zasypia gdy nie jest aktywny przez 30 minut i budzi się po pierwszym żadaniu,
dlatego prawdopodobnie będzie trzeba trochę odczekać zanim aplikacja zacznie działać poprawnie. 

##### Niestety po zmienie serwisu hostującego bazę danych, przestało działać dodawanie do koszyka. Mogę jednak udostępnić swoje lokalne środowisko poprzez ngroka bo u mnie działa :) 

Aplikacja może być trochę wolna ponieważ nie zastosowałem żadnego mechanizmu cache'ującego.

### Użyte technologie
 
 * Backend:
    * Java
    * Spring Boot
    * Hibernate
    * MySql 
    * Maven
 * Frontend:    
    * Angular
    * HTML/CSS
    * Bootstrap