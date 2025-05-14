# Problem plecakowy - solver

## Opis programu

Program rozwiązuje dyskretny problem plecakowy, wykorzystując zachłanny algorytm. Generuje zestaw przedmiotów o losowych wagach i wartościach, a następnie znajduje optymalną kombinację przedmiotów mieszczących się w plecaku o zadanej pojemności.

## Działanie programu

1. **Generowanie przedmiotów**:
   - Program losuje przedmioty o wartościach i wagach z zadanego zakresu
   - Możliwość ustawienia ziarna (seed) dla powtarzalności wyników

2. **Rozwiązywanie problemu**:
   - Sortuje przedmioty według stosunku wartości do wagi (malejąco)
   - Wybiera przedmioty zaczynając od tych o najlepszym stosunku
   - Uwzględnia maksymalną możliwą liczbę każdego przedmiotu

3. **Prezentacja wyników**:
   - Wyświetla listę wszystkich wygenerowanych przedmiotów
   - Pokazuje wybrane przedmioty w rozwiązaniu
   - Podsumowuje całkowitą wartość i wagę

## Struktura klas

- **Main** - obsługa interakcji z użytkownikiem
- **Problem** - generuje i przechowuje zestaw przedmiotów, zawiera logikę rozwiązania
- **Item** - reprezentuje pojedynczy przedmiot (id, wartość, waga)
- **Result** - przechowuje i formatuje wyniki rozwiązania

## Metoda rozwiązania

Program stosuje podejście zachłanne, które nie gwarantuje zawsze optymalnego rozwiązania, ale jest efektywne obliczeniowo i daje dobre przybliżenie.
