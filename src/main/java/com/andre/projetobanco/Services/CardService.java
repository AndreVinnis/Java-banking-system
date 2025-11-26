package com.andre.projetobanco.Services;

import com.andre.projetobanco.Domain.Card;
import com.andre.projetobanco.Repository.CardRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card findCardById(Long id){
        Optional<Card> card = cardRepository.findById(id);
        return card.orElseThrow(() -> new ObjectNotFoundException(card, "User not found"));
    }
}
