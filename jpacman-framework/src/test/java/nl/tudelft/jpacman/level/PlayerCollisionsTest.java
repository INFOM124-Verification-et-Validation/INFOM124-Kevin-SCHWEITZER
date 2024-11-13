package nl.tudelft.jpacman.level;

class PlayerCollisionsTest extends CollisionTest {
    @Override
    CollisionMap createCollisionMap() {
        return new PlayerCollisions();
    }
}
