# A-star
Implementacja algorytmu A* w Javie / The implementation of A-star algorithm in Java

**PL:**

Jest to moja implementacja algorytmu A* - znajdowania najkr�tszej �cie�ki na mapie, z omini�ciem przeszk�d.

*Przed u�yciem programu, nale�y wygenerowa� map� za pomoc� za��czonej aplikacji "map_generator.exe".*

Program wczytuje map� w postaci tablicy dwuwymiarowej z pliku "grid.txt". *Zera* na mapie odczytywane s� jako pola, po kt�rych mo�na si� porusza�, *pi�tki* - jako przeszkody do omini�cia, a *tr�jkami* oznaczana jest najkr�tsza droga prowadz�ca z punktu startowego (lewego g�rnego rogu mapy) do celu (prawego dolnego rogu). Program wykonuje ruchy w g�r�/d� i na boki - ruchy na skos s� zabronione. W razie zablokowania drogi do celu przeszkodami, program ko�czy swoj� prac� komunikatem o braku mo�liwo�ci odnalezienia drogi; w przeciwnym wypadku program wypisuje map� z zaznaczon� drog� do celu.



----------------------------------------------------------------------------



**EN:**

This is my implementation of the A-star algorithm - finding the shortest path on the map, avoiding obstacles.

*Before using this programme, you need to generate a map by "map_generator.exe" application.*

The programme reads a map in the form of a two-dimensional array from file "grid.txt". Zeroes on the map are read as fields on which you can move, *fives* - as obstacles to avoid, and *threes* marks the shortest path from the start point (top-left corner of the map) to the destination (bottom-right corner). The programme moves vertically or horizontally - diagonal movements are prohibited. In case of blocking the route to the destination point by obstacles, the programme exits with a message about inability to find a path; otherwise the programme prints the map with the path marked by threes.