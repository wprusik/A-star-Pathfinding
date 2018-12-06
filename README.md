# A-star-Pathfinding
Implementacja algorytmu A* w Javie / The implementation of A-star algorithm in Java

**PL:**

Jest to moja implementacja algorytmu A* - znajdowania najkrótszej œcie¿ki na mapie, z ominiêciem przeszkód.

*Przed u¿yciem programu, nale¿y wygenerowaæ mapê za pomoc¹ za³¹czonej aplikacji "map_generator.exe".*

Program wczytuje mapê w postaci tablicy dwuwymiarowej z pliku "grid.txt". *Zera* na mapie odczytywane s¹ jako pola, po których mo¿na siê poruszaæ, *pi¹tki* - jako przeszkody do ominiêcia, a *trójkami* oznaczana jest najkrótsza droga prowadz¹ca z punktu startowego (lewego górnego rogu mapy) do celu (prawego dolnego rogu). Program wykonuje ruchy w górê/dó³ i na boki - ruchy na skos s¹ zabronione. W razie zablokowania drogi do celu przeszkodami, program koñczy swoj¹ pracê komunikatem o braku mo¿liwoœci odnalezienia drogi; w przeciwnym wypadku program wypisuje mapê z zaznaczon¹ drog¹ do celu.



----------------------------------------------------------------------------



**EN:**

This is my implementation of the A-star algorithm - finding the shortest path on the map, avoiding obstacles.

*Before using this programme, you need to generate a map by "map_generator.exe" application.*

The programme reads a map in the form of a two-dimensional array from file "grid.txt". Zeroes on the map are read as fields on which you can move, *fives* - as obstacles to avoid, and *threes* marks the shortest path from the start point (top-left corner of the map) to the destination (bottom-right corner). The programme moves vertically or horizontally - diagonal movements are prohibited. In case of blocking the route to the destination point by obstacles, the programme exits with a message about inability to find a path; otherwise the programme prints the map with the path marked by threes.
