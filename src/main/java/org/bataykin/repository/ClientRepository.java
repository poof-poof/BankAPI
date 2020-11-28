package org.bataykin.repository;

import org.bataykin.model.client.Client;
import org.bataykin.model.enums.CardType;
import org.bataykin.model.enums.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT cl FROM Client cl JOIN Card cd ON cl.cardId = cd.id AND cd.currency=:cur AND cd.type=:card_type")
    Page<Client> findAllClientsByCurrencyAndCardType(@Param("cur") final Currency currency,
                                                     @Param("card_type") final CardType cardType,
                                                     final Pageable pageable);
}
