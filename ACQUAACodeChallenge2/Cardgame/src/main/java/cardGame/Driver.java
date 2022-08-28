package cardGame;

import java.io.*;
import java.util.List;

import DATATYPE.Card;
import DATATYPE.CardNumber;


public class Driver {

    public static void main(String[] args) {

        SetupGame setup = new SetupGame();
        setup.createUser();
        setup.getPlayers().stream().forEach(System.out::println);
        setup.getCards().stream().forEach(System.out::println);
        System.out.println(setup.getCards().size());
        setup.distributeCardToPlayer();
        setup.getCardsPlayerMap().entrySet().stream().forEach(System.out::println);
        System.out.println(setup.getCards().size());
        setup.validateAllRule();
    }

}
