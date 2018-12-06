# A-star-Pathfinding
Implementacja algorytmu A* w Javie / The implementation of A-star algorithm in Java

**PL:**

Jest to moja implementacja algorytmu A* - znajdowania najkrótszej ścieżki na mapie, z ominięciem przeszkód.

*Przed użyciem programu, należy wygenerować mapę za pomocą załączonej aplikacji "map_generator.exe".*

Program wczytuje mapę w postaci tablicy dwuwymiarowej z pliku "grid.txt". *Zera* na mapie odczytywane są jako pola, po których można się poruszać, *piątki* - jako przeszkody do ominięcia, a *trójkami* oznaczana jest najkrótsza droga prowadząca z punktu startowego (lewego górnego rogu mapy) do celu (prawego dolnego rogu). Program wykonuje ruchy w górę/dół i na boki - ruchy na skos są zabronione. W razie zablokowania drogi do celu przeszkodami, program kończy swoją pracę komunikatem o braku możliwoœci odnalezienia drogi; w przeciwnym wypadku program wypisuje mapę z zaznaczoną drogą do celu.



----------------------------------------------------------------------------



**EN:**

This is my implementation of the A-star algorithm - finding the shortest path on the map, avoiding obstacles.

*Before using this programme, you need to generate a map by "map_generator.exe" application.*

The programme reads a map in the form of a two-dimensional array from file "grid.txt". Zeroes on the map are read as fields on which you can move, *fives* - as obstacles to avoid, and *threes* marks the shortest path from the start point (top-left corner of the map) to the destination (bottom-right corner). The programme moves vertically or horizontally - diagonal movements are prohibited. In case of blocking the route to the destination point by obstacles, the programme exits with a message about inability to find a path; otherwise the programme prints the map with the path marked by threes.
