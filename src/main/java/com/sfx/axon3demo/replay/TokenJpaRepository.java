package com.sfx.axon3demo.replay;

import org.axonframework.eventhandling.tokenstore.jpa.TokenEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenEntry, TokenEntry.PK> {

}