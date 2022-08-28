package cardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

import DATATYPE.Card;
import DATATYPE.Player;

public class SetupGame {

    private Stack<Card> cards;
    private List<Player> players = new ArrayList<Player>();
    private Map<Player, List<Card>> cardsPlayerMap = new HashMap<Player, List<Card>>();
    private int numberOfCardsPerPlayer = 3;
    private int numberOfPlayers = 4;

    public SetupGame() {

        cards = Card.getPackOfCards();
        Card.shuffleCards(cards);

    }

    public SetupGame(int numberOfCardsPerPlayer, int NumberOfPlayer) {

        this();
        this.numberOfCardsPerPlayer = numberOfCardsPerPlayer;
        this.numberOfPlayers = NumberOfPlayer;

    }

    public void createUser() {

        if (players.size() != 0) {
            players.clear();
        }

        for (int i = 1; i <= numberOfPlayers; i++) {
            Player user = new Player(i);
            user.setPlayerName("P" + i);
            players.add(user);
        }
    }

    public boolean distributeCardToPlayer() {

        try {
            for (Player player : players) {

                List<Card> temp = new ArrayList<Card>();
                for (int i = 1; i <= numberOfCardsPerPlayer; i++) {
                    temp.add(cards.pop());
                }
                Collections.sort(temp);
                cardsPlayerMap.put(player, temp);
            }
        } catch (EmptyStackException e) {
            System.out.println("Not able to distribute card to each player equally");
            return false;
        }
        return true;
    }

    public void validateAllRule() {

        boolean isSame = allAreSame();
        if (!isSame) {
            System.out.println("Winner not decide yet same card");
            if (!isSequence()) {
                System.out.println("Winner not decide yet in isSequence ");
                if (!isTwoCardSame()) {
                    System.out.println("Winner not decide yet in isTwoCardSame ");
                    System.out.println("Winner in top order card ");
                    TopOrderCard();
                    getWinner();
                } else {
                    System.out.println("Winner in isTwoCardSame ");
                    getWinner();
                }

            } else {
                System.out.println("Winner in isSequence ");
                getWinner();
            }

        } else {
            System.out.println("Winner in same card ");
            getWinner();
        }

    }

    public void TopOrderCard() {
        int highvalue = -1;
        Player highPlayer = null;
        Set<String> notInTopOrder = new HashSet<String>();
        for (int i = 0; i < numberOfCardsPerPlayer; i++) {
            for (Entry<Player, List<Card>> player : getCardsPlayerMap().entrySet()) {
                Player p = player.getKey();
                List<Card> cards = player.getValue();
                int value = cards.get(i).getNumber().getCardOrder();
                if (!notInTopOrder.contains(p.getPlayerName())) {
                    highvalue = value;
                    highPlayer = p;
                    break;
                }
            }
            for (Entry<Player, List<Card>> inPlayer : getCardsPlayerMap().entrySet()) {
                Player p1 = inPlayer.getKey();
                List<Card> inCards = inPlayer.getValue();
                if (!notInTopOrder.contains(p1.getPlayerName()) && !p1.equals(highPlayer)) {
                    int value2 = inCards.get(i).getNumber().getCardOrder();
                    if (highvalue < value2) {
                        notInTopOrder.add(highPlayer.getPlayerName());
                        highvalue = value2;
                        highPlayer = p1;
                    } else if (highvalue > value2) {
                        notInTopOrder.add(p1.getPlayerName());
                    }
                }
            }
            if (notInTopOrder.size() >= (numberOfPlayers - 1)) {
                break;
            }
        }
        highPlayer.setResult("W");
    }

    public void getWinner() {

        for (Player player : getCardsPlayerMap().keySet()) {
            if (player.getResult() != null && player.getResult().equals("W")) {
                System.out.println("winner is :" + player.toString());
                break;
            }

        }

    }

    private boolean allAreSame() {

        for (Entry<Player, List<Card>> player : this.getCardsPlayerMap().entrySet()) {

            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (checkRule.isAllCardSame(cards)) {
                p.setResult("W");
                return true;

            }

        }

        return false;
    }

    private boolean isSequence() {

        for (Entry<Player, List<Card>> player : getCardsPlayerMap().entrySet()) {

            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (checkRule.isCardInSequence(cards)) {
                p.setResult("W");
                return true;

            }

        }

        return false;
    }

    private boolean isTwoCardSame() {

        for (Entry<Player, List<Card>> player : this.getCardsPlayerMap().entrySet()) {
            Player p = player.getKey();
            List<Card> cards = player.getValue();
            if (checkRule.twoCardSame(cards)) {
                p.setResult("W");
                return true;

            }

        }

        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Map<Player, List<Card>> getCardsPlayerMap() {
        return cardsPlayerMap;
    }

    public int getNumberOfCardsPerPlayer() {
        return numberOfCardsPerPlayer;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

}
