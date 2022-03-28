package com.skaczmarek.matchmakerappbackend.repository;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
