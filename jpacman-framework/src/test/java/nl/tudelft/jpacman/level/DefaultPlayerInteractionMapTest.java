package nl.tudelft.jpacman.level;

class DefaultPlayerInteractionMapTest extends CollisionTest {
    @Override
    CollisionMap createCollisionMap() {
        return new DefaultPlayerInteractionMap();
    }
}
