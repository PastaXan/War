import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    // ADD ADDITIONAL CONSTANTS HERE, IF DESIRED...

    static Pile pile1;
    static Pile pile2;
    static int numTurns;

    // ADD ADDITIONAL VARIABLES HERE, IF DESIRED...

    public static void printPiles() {

        // For your convenience, this prints the cards in
        // pile1 and pile2 along with the turn number, as specified
        // by numTurns

        System.out.println("Turn: " + numTurns + System.lineSeparator());
        System.out.println("player1:");
        System.out.println(pile1.toString() + System.lineSeparator());
        System.out.println("player2:");
        System.out.println(pile2.toString());
        System.out.println("--------------------------------------");
    }

    public static void printWar() {

        // Call this method every time there is a tie in the ranks
        // of cards compared
        System.out.println("                                        *** WAR! ***" + System.lineSeparator());
    }

    // ADD ADDITIONAL METHODS HERE, IF DESIRED...

    public static void main(String[] args) {
        /*
        Pile pl = new Pile("3s 4s 7s Qc 3c 8d");
        for(int i=0; i<200; i++){
            pl.enqueue(new Card('3','d'));
            pl.dequeue();
            System.out.println(pl);
        }
        
         */

        if (args.length == 0) {
            System.out.println("Usage: java War <deck-file>");
            return;
        }

        // args[0] is the deck filename.
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            System.out.println("Loading deck from: " + args[0]);
            pile1 = new Pile(scanner.nextLine());
            pile2 = new Pile(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            return;
        }

        numTurns = 1;

        // TODO: ADD CODE HERE TO COMPLETE THIS METHOD...
        //System.out.println(new Pile("6h 3h Jc Ts 3c 7h 2h 9c 3s 7s 8c 5d Js Jh 6d Ah Qh 9d Ks 6c Kc Td 8h Th 9s Kd 3d Qs Jd 8d Ad Tc Qc 9h 2d 5h 5c 5s 6s 7c 4c 7d Kh 8s Ac 4h 2c Qd As 4d 4s 2s"));

        while(pile1.size()!=52&&pile2.size()!=52&&numTurns<=5000){
            printPiles();
            Card p1 = pile1.dequeue();
            Card p2 = pile2.dequeue();
            if(p1.compareTo(p2)>0){
                pile1.enqueue(p2);
                pile1.enqueue(p1);
            }
            else if(p1.compareTo(p2)<0){
                pile2.enqueue(p1);
                pile2.enqueue(p2);
            }
            else{
                printWar();
                pile2.enqueue(p1);
                pile2.enqueue(p2);
            }
            numTurns++;
        }


        if(numTurns==5001){
            System.out.println("draw!");
        }
        if(pile1.size()==52){
            System.out.println("player 1 wins!");
        }
        if(pile2.size()==52){
            System.out.println("player 2 wins!");
        }



    }
}