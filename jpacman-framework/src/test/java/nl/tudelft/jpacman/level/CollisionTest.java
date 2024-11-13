package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.npc.ghost.BasicGhost;
import nl.tudelft.jpacman.npc.ghost.GhostColor;
import nl.tudelft.jpacman.board.BasicSquare;
import nl.tudelft.jpacman.board.BasicUnit;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class CollisionTest {
    protected CollisionMap collisionMap;
    protected Player player;
    protected final PacManSprites pacManSprites = new PacManSprites();

    abstract CollisionMap createCollisionMap();

    @BeforeEach
    void setUp() {
        collisionMap = createCollisionMap();
        player = new Player(pacManSprites.getPacmanSprites(), pacManSprites.getPacManDeathAnimation());
        BasicSquare playerSquare = new BasicSquare();
        player.occupy(playerSquare);
    }
    @Test
    void playerVersusPelletTest() {
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        pellet.occupy(player.getSquare());
        assertThat(pellet.hasSquare()).isTrue();
        int playerPointsBeforeCollision = player.getScore();
        collisionMap.collide(player, pellet);
        assertThat(player.getScore()).isEqualTo(playerPointsBeforeCollision + 10);
        assertThat(pellet.hasSquare()).isFalse();
    }

    @Test
    void playerVersusGhostTest() {
        BasicGhost ghost = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.ORANGE), 100, 50);
        collisionMap.collide(player, ghost);
        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void ghostVersusPlayerTest() {
        BasicGhost ghost = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.ORANGE), 100, 50);
        collisionMap.collide(ghost, player);
        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void ghostVersusPelletTest() {
        BasicGhost ghost = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.ORANGE), 100, 50);
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        pellet.occupy(player.getSquare());
        assertThat(pellet.hasSquare()).isTrue();
        int playerPointsBeforeCollision = player.getScore();
        collisionMap.collide(ghost, pellet);
        assertThat(player.getScore()).isEqualTo(playerPointsBeforeCollision);
        assertThat(pellet.hasSquare()).isTrue();
    }

    @Test
    void ghostVersusGhost(){
        BasicGhost ghost1 = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.ORANGE), 100, 50);
        BasicGhost ghost2 = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.PINK), 100, 50);
        BasicSquare square1 = new BasicSquare();
        BasicSquare square2 = new BasicSquare();
        ghost1.occupy(square1);
        ghost2.occupy(square2);
        assertThat(ghost1.getSquare()).isNotNull();
        collisionMap.collide(ghost1, ghost2);
        // No action is taken when two ghosts collide
        // Verify that the ghosts are still on their squares
        assertThat(ghost1.getSquare()).isNotNull();
        assertThat(ghost2.getSquare()).isNotNull();

    }

    @Test
    void pelletVersusPlayerTest() {
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        pellet.occupy(player.getSquare());
        assertThat(pellet.hasSquare()).isTrue();
        int playerPointsBeforeCollision = player.getScore();
        collisionMap.collide(pellet, player);
        assertThat(player.getScore()).isEqualTo(playerPointsBeforeCollision + 10);
        assertThat(pellet.hasSquare()).isFalse();
    }

    @Test
    void pelletVersusGhostTest() {
        BasicGhost ghost = new BasicGhost(pacManSprites.getGhostSprite(GhostColor.ORANGE), 100, 50);
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        pellet.occupy(player.getSquare());
        assertThat(pellet.hasSquare()).isTrue();
        int playerPointsBeforeCollision = player.getScore();
        collisionMap.collide(pellet, ghost);
        assertThat(player.getScore()).isEqualTo(playerPointsBeforeCollision);
        assertThat(pellet.hasSquare()).isTrue();
    }

    @Test
    void notRecognizedMoverTest() {
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        BasicUnit basicUnit = new BasicUnit();
        BasicSquare unitSquare = new BasicSquare();
        basicUnit.occupy(unitSquare);
        pellet.occupy(player.getSquare());

        collisionMap.collide(basicUnit, pellet);

        // No action should be taken so pellet should remain on its square and basicUnit should remain on its square
        assertThat(basicUnit.getSquare()).isEqualTo(unitSquare);
        assertThat(pellet.hasSquare()).isTrue();
    }

    @Test
    void notRecognizedColliderTest() {
        Pellet pellet = new Pellet(10, pacManSprites.getPelletSprite());
        BasicUnit basicUnit = new BasicUnit();
        BasicSquare unitSquare = new BasicSquare();
        basicUnit.occupy(unitSquare);
        pellet.occupy(player.getSquare());

        collisionMap.collide(pellet, basicUnit);

        // No action should be taken so pellet should remain on its square and basicUnit should remain on its square
        assertThat(basicUnit.getSquare()).isEqualTo(unitSquare);
        assertThat(pellet.hasSquare()).isTrue();
    }
}
