package com.skaczmarek.matchmakerappbackend.repository;

import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findPlayerByUser_UserId(long userId);
}
