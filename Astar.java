package astar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wojciech Prusik
 */

public class Astar {

    static final int startX = 0;
    static final int startY = 0;
    public static int goalX = 19;
    public static int goalY = 19;
    public static int tempX, tempY;
    static int X, Y;    // bieżąca pozycja
    static int[][] map = new int[20][20];
    public static ArrayList<ArrayList<Square>> auxMap = new ArrayList<>();    // tworzenie dwuwymiarowej tablicy obiektów pełniącej rolę mapy interaktywnej
    public static boolean success = false;
    
    public static void main(String[] args) throws FileNotFoundException {
        
        printInitialMessage();
        
        loadMapFromFile();
        fillAuxiliaryMap();
        
        // Ustawiam program na pozycji początkowej
        X = startX;
        Y = startY;
        auxMap.get(X).get(Y).list = 3;
        auxMap.get(X).get(Y).setHeuristics(X, Y);
        auxMap.get(X).get(Y).parentX = X;
        auxMap.get(X).get(Y).parentY = Y;
        
        
        System.out.print("\nSzukam drogi do celu...... ");
        
        // Tutaj odgrywa się całe przedstawienie :)
        try
        {
            main_loop:
            while (!((X == goalX) && (Y == goalY)))
            {
                move();
                
                // Sprawdzam warunki zakończenia algorytmu:                
                if ((X == goalX) && (Y == goalY))       // sukcesem
                {
                    System.out.print(" Odnaleziono drogę do celu!\n\n");
                    success = true;
                }
                
                if ((!((X == goalX) && (Y == goalY))) && isOpenListEmpty())     // niepowodzeniem
                {
                    if (!areThereAnyEmptySquaresAround())
                    {
                        System.out.print("Znalezienie drogi do celu niemożliwe :-(\n");
                        break main_loop;
                    }
                }   
            }
        }
        catch (Exception ex)
        {
            System.out.println("Ups! Coś poszło nie tak...\n" + ex);
        }
        
        
        // sprawdzam, czy program doszedł do celu. Jeśli tak, wraca do punktu startowego wyznaczając najkrótszą możliwą trasę.
        if (success)
        {
            System.out.println("Wracam do domu...");
            goHome();
        }
        
        return;       
    }
    
    static public void printInitialMessage()
    {
        System.out.println("Implementacja algorytmu A* - Wojciech Prusik");
        System.out.println("Naszym punktem startowym jest lewy górny róg mapy, celem programu jest wyznaczenie najkrótszej drogi prowadzącej do prawego dolnego rogu tejże. Możemy poruszać się jedynie w górę-dół oraz prawo-lewo, ruchy na skos są zabronione.");
        System.out.println("Przed uruchomieniem programu, należy wygenerować mapę za pomocą programu map_generator.exe.");
        System.out.println("\nLEGENDA MAPY:");
        System.out.println("0  - pole, po którym możemy się poruszać");
        System.out.println("5  - przeszkoda");
        System.out.println("3  - oznaczenie najkrótszej ścieżki prowadzącej do celu");
        System.out.println("");
    }
    
    static public void loadMapFromFile() throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("grid.txt"));
        
        
        for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++)
                    map[i][j] = scan.nextInt();   
        
        scan.close();
        System.out.println("Mapa została wczytana z pliku.\n");
    }
    
    static public void fillAuxiliaryMap()
    {
        // WYPEŁNIANIE MAPY PUSTYMI OBIEKTAMI KLASY SQUARE
        for (int i=0; i<20; i++)
            auxMap.add(new ArrayList<Square>()); 
        
        for (int i=0; i<20; i++)
            for (int j=0; j<20; j++)
                auxMap.get(i).add(new Square());
        
        // PRZEPISYWANIE WCZYTANEJ MAPY
        for (int i=0; i<20; i++)
            for (int j=0; j<20; j++)
                auxMap.get(i).get(j).list = map[i][j];
        
        // WYPISANIE MAPY POMOCNICZEJ
        System.out.println("\nWypisuję mapę:\n");
        for (int i=0; i<20; i++)
        {
            for (int j=0; j<20; j++)
            {
                System.out.print(auxMap.get(i).get(j).list + " ");
            }
            System.out.print("\n");
        }
        
    }
    
    static public void move()
    {
        // sprawdzam po kolei, czy kolejne sąsiadujące kratki nie wykraczają poza mapę, czy nie są przeszkodami oraz czy nie ma ich na liście zamkniętej. Jeśli wszystkie warunki są spełnione, dodaję kratkę do listy otwartej.
                
        for (int i=0; i < 4; i++)
        {
            if (i==0)
            {
                tempX = X-1;
                tempY = Y;
            }
            else if (i==1)
            {
                tempX = X+1;
                tempY = Y;
            }
            else if (i==2)
            {
                tempX = X;
                tempY = Y+1;
            }
            else if (i==3)
            {
                tempX = X;
                tempY = Y-1;
            }
            
            
            if (!((tempX < 0 || tempX > 19 || tempY < 0 || tempY > 19) || (map[tempX][tempY] == 5) || (auxMap.get(tempX).get(tempY).list == 3)))
            {
                // sprawdzam, czy kratka jest już na liście otwartej. Jeśli tak, sprawdzam czy jej wartość fpoz jest większa niż ta wyliczona po przejściu z obecnego rodzica. W takim przypadku podmieniam rodzica, koszt dojścia i wartość fpoz w tej kratce.
                if (auxMap.get(tempX).get(tempY).list == 1)
                {
                    double tempFpoz = auxMap.get(X).get(Y).costSum + 1 + Math.sqrt(Math.pow(tempY - goalX, 2) + Math.pow(tempY - goalY, 2));
                    if (auxMap.get(tempX).get(tempY).fpoz > tempFpoz)
                    {
                        auxMap.get(tempX).get(tempY).setParents(X, Y);
                        auxMap.get(tempX).get(tempY).costSum = auxMap.get(X).get(Y).costSum + 1;
                        auxMap.get(tempX).get(tempY).setHeuristics(tempX, tempY);
                    }
                }
                else
                {
                    auxMap.get(tempX).get(tempY).list = 1;
                    auxMap.get(tempX).get(tempY).setParents(X, Y);
                    auxMap.get(tempX).get(tempY).costSum = auxMap.get(X).get(Y).costSum + 1;
                    auxMap.get(tempX).get(tempY).setHeuristics(tempX, tempY);
                }
            } 
        }
        
        // po dodaniu kratek na listę otwartą, znajduję tę z najmniejszą wartością fpoz i dodaję ją do listy zamkniętej
        double temp = Double.MAX_VALUE;
        for (int i=0; i<auxMap.size(); i++)
        {
            for (int j=0; j<auxMap.get(i).size(); j++)
            {
                    if ((auxMap.get(i).get(j).list == 1) && (auxMap.get(i).get(j).fpoz < temp))
                    {
                        tempX = i;
                        tempY = j;
                        temp = auxMap.get(i).get(j).fpoz;
                    }
            }
        }
        
        if (temp != Double.MAX_VALUE)         // zabezpieczenie przed błędem wykroczenia poza granice mapy w przypadku braku możliwości wykonania pierwszego kroku
            auxMap.get(tempX).get(tempY).list = 3;
        
        // zmieniam aktualną pozycję
        X = tempX;
        Y = tempY;
    }
    
    static public boolean isOpenListEmpty()
    {
        boolean tempFlag = true;
        
        outer_loop:
        for (int i=0; i<auxMap.size(); i++)
            for (int j=0; j<auxMap.get(i).size(); j++)
            {
                if (auxMap.get(i).get(j).list == 1)
                {
                    tempFlag = false;
                    break outer_loop;
                }
            }
        
        return tempFlag;
    }
    
    static public boolean areThereAnyEmptySquaresAround()
    {
        boolean tempFlag = false;
        
        for (int i=0; i < 4; i++)
        {
            if (i==0)
            {
                tempX = X-1;
                tempY = Y;
            }
            if (i==1)
            {
                tempX = X+1;
                tempY = Y;
            }
            if (i==2)
            {
                tempX = X;
                tempY = Y+1;
            }
            if (i==3)
            {
                tempX = X;
                tempY = Y-1;
            }
            
            
            if (!((tempX < 0 || tempX > 19 || tempY < 0 || tempY > 19) || (map[tempX][tempY] == 5) || (auxMap.get(tempX).get(tempY).list == 3)))
            {
                if (auxMap.get(tempX).get(tempY).list == 0)
                    tempFlag = true;
            } 
        }
        
        return tempFlag;
    }
    
    static public void goHome()
    {   
        map[goalX][goalY] = 3;
        int tempParentX =0 , tempParentY = 0;
        while (X != startX || Y != startY)
        {   
            tempParentX = auxMap.get(X).get(Y).parentX;
            tempParentY = auxMap.get(X).get(Y).parentY;
            X = tempParentX;
            Y = tempParentY;
            map[X][Y] = 3;
        }
        
        
        // wypisuję mapę
        for (int i=0; i < 20; i++)
        {
            for (int j=0; j < 20; j++)
                System.out.print(map[i][j] + " ");
            
            System.out.print("\n");
        }
                
    }
}

class Square extends Astar    // klasa definiująca pojedyncze pole na mapie
{
    int list;     // 1 - lista otwarta; 3 - lista zamknięta; 0 - puste pole; 5 - przeszkoda
    int parentX, parentY;
    double fpoz;
    int costSum;   // koszt dojścia do tej kratki (koszt dojścia rodzica + 1)
    double heuristics;
    
    Square()
    {
        this.list = 0;
    }
    
    public void setHeuristics(int posX, int posY)
    {
        this.heuristics = Math.sqrt(Math.pow((posX - goalX), 2) + Math.pow((posY - goalY), 2));
        this.fpoz = this.costSum + this.heuristics;
    }
    
    public void setParents(int parentX, int parentY)
    {
        this.parentX = parentX;
        this.parentY = parentY;
    }
}
