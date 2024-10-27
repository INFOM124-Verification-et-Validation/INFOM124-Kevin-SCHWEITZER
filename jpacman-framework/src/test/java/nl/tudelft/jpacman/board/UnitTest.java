package nl.tudelft.jpacman.board;

import nl.tudelft.jpacman.board.BasicUnit;
import nl.tudelft.jpacman.board.BasicSquare;
import nl.tudelft.jpacman.board.Direction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitTest {

    @Test
    public void testZeroSquaresAheadOfUnit() {
        // Create a BasicUnit and set its location to the base square (starting location for SquaresAheadOf(0))
        BasicUnit unit = new BasicUnit();
        BasicSquare base = new BasicSquare();
        unit.occupy(base);
        Square squareAheadIsBase = unit.squaresAheadOf(0);
        assertThat(squareAheadIsBase).isEqualTo(base);
    }

    @Test
    public void testOneSquaresAheadOfUnit() {
        BasicUnit unit = new BasicUnit();
        BasicSquare base = new BasicSquare();
        Square east1 = new BasicSquare();
        unit.occupy(base);
        base.link(east1, Direction.EAST);
        Square square = unit.squaresAheadOf(1);
        assertThat(square).isEqualTo(east1);
    }

    @Test
    public void testTwoSquaresAheadOfUnit() {
        BasicUnit unit = new BasicUnit();
        BasicSquare base = new BasicSquare();
        base.put(unit);
        Square east1 = new BasicSquare();
        base.link(east1, Direction.EAST);
        Square east2 = new BasicSquare();
        east1.link(east2, Direction.EAST);
        Square square = unit.squaresAheadOf(2);
        assertThat(square).isEqualTo(east2);
    }

    @Test
    public void testNegativeSquaresAheadOfUnit() {
        BasicUnit unit = new BasicUnit();
        BasicSquare base = new BasicSquare();
        unit.occupy(base);
        Square east1 = new BasicSquare();
        base.link(east1, Direction.EAST);
        Square west1 = new BasicSquare();
        base.link(west1, Direction.WEST);
        Square square = unit.squaresAheadOf(-1);
        assertThat(square).isEqualTo(base);
    }

    @Test
    public void testNoSquaresAheadOfUnit() {
        BasicUnit unit = new BasicUnit();
        BasicSquare base = new BasicSquare();
        unit.occupy(base);
        Square square = unit.squaresAheadOf(2);
        assertThat(square).isEqualTo(base);
    }
}
