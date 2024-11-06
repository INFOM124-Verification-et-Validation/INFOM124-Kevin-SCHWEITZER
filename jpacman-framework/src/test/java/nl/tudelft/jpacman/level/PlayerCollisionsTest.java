package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerCollisionsTest {
    private PlayerCollisions playerCollisions;
    private Player player;
    private final PacManSprites pacManSprites = new PacManSprites();

    @BeforeEach
    void setUp() {
        playerCollisions = new PlayerCollisions();
        player = new Player(pacManSprites.getPacmanSprites(), pacManSprites.getPacManDeathAnimation());
    }

    @Test
    void playerVersusPelletTest() {
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        int playerPointsBeforeCollision = player.getScore();
        playerCollisions.playerVersusPellet(player, pellet);
        assertThat(player.isAlive()).isEqualTo(playerPointsBeforeCollision + 10);
    }

    @Test
    void playerVersusGhostTest() {
        Ghost ghost = new Ghost(pacManSprites.getGhostSprite(GhostColor.RED), 200);
        int playerPointsBeforeCollision = player.getScore();
        playerCollisions.playerVersusGhost(player, ghost);
        assertThat(player.isAlive()).isEqualTo(false);
    }

}
