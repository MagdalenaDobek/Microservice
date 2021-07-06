Przygotowanie mikroserwisu bazującego na spring boot. Mikroserwis będzie umożliwiał dodawanie wartości do bazy danych i wyświetlenie jej zawartości. Mikroserwis powinien odpowiadać na żądanie http w formacie json.

Na żądanie postaci:
POST http://localhost:8080/message

Powinien odpowiadać statusem 200 i zawartością dodaną do bazy danych:
{id: "d819302f-4b86-4815-8d39-9966d441f76f",
content:"Wartosc"}

Żądanie powinno być zwalidowane (nie pusta wartość content).

Na żądanie http:
GET http://localhost:8080/message
Powinien zwrócić listę wcześniej dodanych wartość.



Na żądanie http:
PUT http://localhost:8080/message/d819302f-4b86-4815-8d39-9966d441f76f

Powinien odpowiadać statusem 200 i zmodyfikowaną zawartością.
{id: "d819302f-4b86-4815-8d39-9966d441f76f",
content:"Wartosc 2"}


Żądanie powinno być zwalidowane (nie pusta wartość content).



Na żądanie http:
GET http://localhost:8080/message/random/10
Powinien zwrócić listę 10 losowych wiadomości zapisanych w bazie.


Proszę przygotować test automatyczny conajmniej dla metody PUT i random.

Proszę skorzystać z JPA (np hibernate) i bazy danych sqlite. Do budowania i uruchomienia projektu proszę użyć maven.

Źródła wykonanego zadanie proszę przesłać do prywatnego repozytorium git (gitlab, github). Repozytorium proszę udostępnić użytkownikowi azadrozny@one2tribe.pl.