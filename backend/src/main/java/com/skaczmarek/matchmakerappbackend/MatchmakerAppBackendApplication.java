package com.skaczmarek.matchmakerappbackend;
import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@SpringBootApplication
@Configuration
public class MatchmakerAppBackendApplication implements RepositoryRestConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(MatchmakerAppBackendApplication.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Game.class);
    }
}


