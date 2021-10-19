# Robson

## Uruchomienie
Projekt jest projektem mavenowym.
Należy go skompilować i uruchomić testy wpisując:
-       mvn compile 
        mvn test
Testy (znajdujące się w folderze test_programs), które są stworzone testują dwie klasy oraz klasę główną.
Po uruchomieniu testów można skompilować i uruchomić pliki javowe, które zostały stworzone za pomocą:
-       javac [nazwa pliku]
        java [nazwa pliku wykonywalnego bez .class]

Nazwy plików javowych do których testy zapiszą wyniki testowania metody toJava :
- wynikAdditionTest.java
- wynikGcd.java
- wynikFibbonaciTest.java
- wynikUninitializedVariableTest.java

## Struktura projektu
### Interface Expression
Zawiera dwie metody:
-       double wykonaj(Map<String, Double> globalVariables) throws BladWykonania;
  Metoda ta zwraca wynik danego wyrażenia (według tabelki z treści). 
  Nazwa metody jest taka sama jak nazwa metody w głównej klasie Robson, aby podkreślić,
  że jest to analogiczna metoda tylko dla klas obrazujących każde wyrażenie.
  
-       String toJava(Counter funCount);
  Metoda ta zwraca dane wyrażenie zapisane w Javie w postaci funkcji.
  Nazwa metody jest taka sama jak nazwa metody w głównej klasie Robson, aby podkreślić,
  że jest to analogiczna metoda tylko dla klas obrazujących każde wyrażenie.
  
### Robson
Klasa główna zawierająca metody:
-       void fromJSON(String filename) throws NieprawidlowyProgram
Metoda wczytująca dane z pliku JSON.
Scieżka do pliku musi zawierać foldery które istnieją.

-       void toJSON(String filename)

Metoda zapisująca program w Robsonie do pliku JSON.
Scieżka do pliku musi zawierać foldery które istnieją. Jeśli podany plik nie istnieje to zostanie stworzony.

-       void toJava(String filename)

Metoda zapisująca program w Robsonie do pliku Java.
Scieżka do pliku musi zawierać foldery które istnieją. Jeśli podany plik nie istnieje to zostanie stworzony.
Metoda zapisuje program w Robsonie do pliku Java, który jest "standalone" i można go skompilować i uruchomić (wypisuje 
wynik programu)

-       double wykonaj() throws BladWykonania
Metoda wykonująca wczytany program z pliku JSON (najpeirw trzeba wywołać fromJSON aby móc tą metodę wywołać).

### Klasy "tools"

- Casting - biblioteka zawierająca metody rzutujące double na boolean i boolean na double
- Counter - licznik
- FunctionSignatureWriter - zapisuje sygnaturę funkcji i dodaje do nazwy funkcji numer, tak żeby
każda powstała funkcja miała oryginalny numer
- FunctionWriter - klasa zawierająca metody, które zwracają "treść" metod 
  (toDouble, toBool, main, getGlobalVariables), importy i deklarację mapy "globalVariables" zapisanych w Javie
  
### Pozostałe klasy

Każda klasa obsługuje dane wyrażenie z języka Robson (według tabelki z treści) i rozszerza
interface "Expression".

    