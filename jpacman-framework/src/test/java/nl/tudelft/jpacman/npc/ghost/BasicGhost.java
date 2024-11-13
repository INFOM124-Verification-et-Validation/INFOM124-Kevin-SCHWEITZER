package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.sprite.Sprite;

import java.util.Map;
import java.util.Optional;

/**
 * Basic implementation of a ghost.
 */
public class BasicGhost extends Ghost {

    /**
     * Creates a new basic ghost with the specified sprites and move parameters.
     *
     * @param spriteMap         The sprites for every direction.
     * @param moveInterval      The base interval of movement.
     * @param intervalVariation The variation of the interval.
     */
    public BasicGhost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation) {
        super(spriteMap, moveInterval, intervalVariation);
    }

    @Override
    public Optional<Direction> nextAiMove() {
        // BasicGhost has no specific AI, so it doesn't suggest a move
        return Optional.empty();
    }
}
