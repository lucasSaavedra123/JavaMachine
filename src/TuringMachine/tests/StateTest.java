package TuringMachine.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import TuringMachine.Model.*;

public class StateTest {

    private State aState = State.asNotAccepted("aState");
    private State anotherState = State.asNotAccepted("anotherState");
    private Symbol aSymbol = new Symbol("A");
    private Symbol anotherSymbol = new Symbol("B");
    private Movement aMove = new Right();

    @Before
    public void before() throws Exception{
        aState.add(aSymbol,  anotherState, anotherSymbol, aMove);
    }

    @Test
    public void testANewStateHasNoTransition(){
        assertFalse((State.asNotAccepted("q")).hasTransitionFor((Symbol.asBlank())));
    }

    @Test
    public void testAStateHasATransitionForAnSpecificSymbol(){
        assertTrue(aState.hasTransitionFor(aSymbol));
    }

    @Test
    public void testAStateHasATransitionState(){
        assertEquals(aState.transitionState(aSymbol),anotherState);
    }

    @Test
    public void testAStateHasASymbolToPassToTheStripForASpecificSymbol(){
        assertEquals(aState.transitionSymbol(aSymbol),anotherSymbol);
    }

    @Test
    public void testAStateHasAMoveForTheStripForASpecificSymbol(){
        assertEquals(aState.transitionMovement(aSymbol),aMove);
    }

}