package nl.tudelft.jpacman.board;

import nl.tudelft.jpacman.board.BasicUnit;
import nl.tudelft.jpacman.board.BasicSquare;
import nl.tudelft.jpacman.board.Direction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitTest {

    private BasicUnit unit;
    private BasicSquare base;

    @BeforeEach
    public void setUp() {
        // Create a BasicUnit and set its location to the base square (starting location for SquaresAheadOf
        unit = new BasicUnit();
        base = new BasicSquare();
        unit.occupy(base);
    }

    @Test
    public void testZeroSquaresAheadOfUnit() {
        Square squareAheadIsBase = unit.squaresAheadOf(0);
        assertThat(squareAheadIsBase).isEqualTo(base);
    }

    @Test
    public void testOneSquaresAheadOfUnit() {
        Square east1 = new BasicSquare();
        base.link(east1, Direction.EAST);
        Square square = unit.squaresAheadOf(1);
        assertThat(square).isEqualTo(east1);
    }

    @Test
    public void testTwoSquaresAheadOfUnit() {
        Square east1 = new BasicSquare();
        base.link(east1, Direction.EAST);
        Square east2 = new BasicSquare();
        east1.link(east2, Direction.EAST);
        Square square = unit.squaresAheadOf(2);
        assertThat(square).isEqualTo(east2);
    }

    @Test
    public void testNegativeSquaresAheadOfUnit() {
        Square east1 = new BasicSquare();
        base.link(east1, Direction.EAST);
        Square west1 = new BasicSquare();
        base.link(west1, Direction.WEST);
        Square square = unit.squaresAheadOf(-1);
        assertThat(square).isEqualTo(base);
    }

    @Test
    public void testNoSquaresAheadOfUnit() {
        Square square = unit.squaresAheadOf(2);
        assertThat(square).isEqualTo(base);
    }
}
