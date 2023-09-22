import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SieteYMedio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        double playerScore = 0;
        double computerScore = 0;

        while (true) {
            List<String> deck = createDeck();
            shuffleDeck(deck); // Mezclar la baraja antes de cada juego

            System.out.println("Tus cartas:");
            List<String> playerHand = dealHand(deck);
            displayHand(playerHand);
            playerScore += calculateCardValue(playerHand);

            System.out.println("Tu puntuación total: " + playerScore);

            if (playerScore >= 7.5) {
                System.out.println("¡Te has pasado de 7.5! Has perdido.");
                break;
            }

            System.out.println("¿Quieres otra carta? (s/n)");
            String choice = scanner.next().toLowerCase();

            if (choice.equals("n")) {
                break;
            }
        }

        // Turno de la computadora
        while (computerScore < 7.5 && computerScore < playerScore) {
            List<String> deck = createDeck();
            shuffleDeck(deck); // Mezclar la baraja antes de cada turno de la computadora

            List<String> computerHand = dealHand(deck);
            double newCardScore = calculateCardValue(computerHand);
            computerScore += newCardScore;
        }

        System.out.println("Puntuación de la computadora: " + computerScore);

        // Determinar al ganador
        if ((playerScore <= 7.5 && playerScore > computerScore) || (computerScore > 7.5)) {
            System.out.println("¡Ganaste!");
        } else {
            System.out.println("La computadora ganó.");
        }

        scanner.close();
    }

    private static List<String> createDeck() {
        List<String> deck = new ArrayList<>();
        String[] suits = {"Oros", "Copas", "Espadas", "Bastos"};
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "Sota", "Caballo", "Rey"};

        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " de " + suit);
            }
        }

        return deck;
    }

    private static void shuffleDeck(List<String> deck) {
        Collections.shuffle(deck);
    }

    private static List<String> dealHand(List<String> deck) {
        List<String> hand = new ArrayList<>();
        hand.add(deck.remove(deck.size() - 1));
        return hand;
    }

    private static void displayHand(List<String> hand) {
        for (String card : hand) {
            System.out.println(card);
        }
    }

    private static double calculateCardValue(List<String> hand) {
        double totalValue = 0;
        for (String card : hand) {
            String[] parts = card.split(" ");
            String value = parts[0];
            if (value.equals("Sota") || value.equals("Caballo") || value.equals("Rey")) {
                totalValue += 0.5;
            } else if (value.equals("1")) {
                totalValue += 1;
            } else {
                totalValue += Double.parseDouble(value);
            }
        }
        return totalValue;
    }
}
