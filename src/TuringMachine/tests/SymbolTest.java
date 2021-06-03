package TuringMachine.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.*;

import TuringMachine.Model.Symbol;

public class SymbolTest {
    
    @Test
    public void testAEmptySymbolIsEqualToASymbolWithTheBlankCharacter(){
        assertEquals(Symbol.asBlank(),new Symbol("B"));
    }

    @Test
    public void testASymbolIsEqualToAnotherSymbolWithSameRepresentationCharacter(){
        assertEquals(new Symbol("X"), new Symbol("X"));
    }

    @Test
    public void testASymbolIsNotEqualToAnotherSymbolWithSimilarRepresentationCharacter(){
        assertNotEquals(new Symbol("X"), new Symbol("x"));
    }

}