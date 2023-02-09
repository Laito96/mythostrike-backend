package com.mythostrike.model.game.activity.cards;

import com.mythostrike.model.game.activity.Card;

import java.util.HashMap;

public class CardSpaceRestrictedByName extends CardSpace {
    private final HashMap<String, Integer> acceptedCards;

    public CardSpaceRestrictedByName(HashMap<String, Integer> acceptedCards) {
        this.acceptedCards = acceptedCards;
    }


    @Override
    public void add(Card card) {
        String name = card.getName();
        cards.add(card);
        acceptedCards.put(name, acceptedCards.get(name) + 1);
    }

    public boolean accepts(Card card) {
        if (!acceptedCards.containsKey(card.getName())) {
            return false;
        }
        String name = card.getName();
        int count = 0;
        for (Card cardInstance : cards) {
            if (cardInstance.getName().equals(name)) {
                count++;
            }
        }
        return count < acceptedCards.get(name);
    }

    @Override
    public void subtractCard(Card card) {
        cards.remove(card);
        String name = card.getName();
        acceptedCards.put(name, Math.max(0, acceptedCards.get(name) - 1));
    }

    @Override
    public Card subtractCard(int index) {
        Card card = cards.get(index);
        cards.remove(card);
        String name = card.getName();
        acceptedCards.put(name, Math.max(0, acceptedCards.get(name) - 1));
        return card;
    }
}