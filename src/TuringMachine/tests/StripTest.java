package TuringMachine.Tests;
import static org.junit.Assert.assertEquals;
import org.junit.*;

import TuringMachine.Model.Strip;
import TuringMachine.Model.Symbol;

public class StripTest{
    Symbol fiveEmptySymbolsArray[] = {
        Symbol.asBlank(),
        Symbol.asBlank(),
        Symbol.asBlank(),
        Symbol.asBlank(),
        Symbol.asBlank()
    };

    @Test 
    public void testAStripWithFiveEmptySymbolsHasLengthFive () {
        Strip aStrip = new Strip(fiveEmptySymbolsArray,0);
        assertEquals(aStrip.length(),5);
    }

    @Test
    public void testAStripWithInitialPositionTwoReturnsSymbolTwo(){
        Strip aStrip = new Strip(fiveEmptySymbolsArray,2);
        assertEquals(aStrip.read(),fiveEmptySymbolsArray[2]);
    }

    @Test
    public void testAStripMovesLeftAndReturnsSymbolOne(){
        Strip aStrip = new Strip(fiveEmptySymbolsArray,2);
        aStrip.left();
        assertEquals(aStrip.read(),fiveEmptySymbolsArray[1]);
    }

    @Test
    public void testAStripMovesRightAndReturnsSymbolThree(){
        Strip aStrip = new Strip(fiveEmptySymbolsArray,2);
        aStrip.right();
        assertEquals(aStrip.read(),fiveEmptySymbolsArray[3]);
    }

    @Test
    public void testAStripWriteAtSymbolTwoAndChangeIt(){
        
        Symbol aSymbol = new Symbol("X");
        Strip aStrip = new Strip(fiveEmptySymbolsArray,2);
        aStrip.write(aSymbol);
        assertEquals(aStrip.read(),aSymbol);
    }

    @Test
    public void testAStripWithBlankSymbolsHasAStringWithThoseSymbols(){
        assertEquals(new Strip(9,0).toString(),"...B...");
    }

    @Test
    public void testAStripWithOnlyOneNotBlankSymbolsHasAStringWithThoseSymbols(){
        Strip aStrip = new Strip(5,2);
        aStrip.write(new Symbol("@"));
        assertEquals(aStrip.toString(),"...B@B...");
    }

    @Test
    public void testAStripWithOnlyOneNotBlankSymbolsHasAStringWithThoseSymbolsOnceAMoveWasMade(){
        Strip aStrip = new Strip(5,2);
        aStrip.left();
        aStrip.write(new Symbol("@"));
        assertEquals(aStrip.toString(),"...B@B...");
    }

    @Test
    public void testAStripDontExceedStripLengthWhenMakeLeftMoves(){
        Strip aStrip = new Strip(5,2);
        aStrip.left();
        aStrip.left();
        aStrip.left();
        aStrip.write(new Symbol("@"));
        assertEquals(aStrip.toString(),"...B@B...");        
    }

    @Test
    public void testAStripDontExceedStripLengthWhenMakeRightMoves(){
        Strip aStrip = new Strip(5,2);
        aStrip.right();
        aStrip.right();
        aStrip.right();
        aStrip.write(new Symbol("@"));
        assertEquals(aStrip.toString(),"...B@B...");        
    }

    @Test
    public void testAStripMadeWithAString(){
        Strip aStrip = new Strip("011XKLBB12",0);
        assertEquals(aStrip.toString(),"...B011XKLBB12B...");        
    }

}