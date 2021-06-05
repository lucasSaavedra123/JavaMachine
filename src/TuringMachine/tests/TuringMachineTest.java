package TuringMachine.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;
import TuringMachine.Model.*;

public class TuringMachineTest {

    private TuringMachine turingMachineCleaner;
    private TuringMachine turingMachineSubStringDetector;

    @Before
    public void before() throws Exception{
        turingMachineCleanerSetUp();
        turingMachineSubStringDetectorSetUp();
    }

    private void turingMachineCleanerSetUp() {
        State stateZero;
        State stateOne;
        Strip aStrip;
        Symbol blankSymbol = Symbol.asBlank();
        Symbol symbolOne = new Symbol("0");
        Symbol symbolZero = new Symbol("1");
        Symbol []inputSymbols = {
            blankSymbol,
            symbolZero,
            symbolOne,
            symbolOne,
            symbolZero,
            symbolZero,
            symbolZero,
            symbolOne,
            symbolOne,
            blankSymbol
        };

        aStrip = new Strip(inputSymbols, 3);

        stateZero = State.asNotAccepted("q0");
        stateOne = State.asNotAccepted("q1");

        stateZero.add(symbolZero, stateZero, symbolZero, new Left());
        stateZero.add(symbolOne, stateZero, symbolOne, new Left());
        stateZero.add(blankSymbol, stateOne, blankSymbol, new Right());

        stateOne.add(symbolZero, stateOne, blankSymbol, new Right());
        stateOne.add(symbolOne, stateOne, blankSymbol, new Right());

        turingMachineCleaner = new TuringMachine(stateZero,aStrip);
    }

    private void turingMachineSubStringDetectorSetUp() {

        Symbol symbolOne = new Symbol("1");
        Symbol symbolZero = new Symbol("0");
        State stateZero = State.asNotAccepted("q0");
        State stateOne = State.asNotAccepted("q1");
        State stateTwo = State.asNotAccepted("q2");
        State stateThree = State.asAccepted("q3");
        Movement rightMove = new Right();
        Symbol []arrayOfSymbols = {
            symbolOne,
            symbolZero,
            symbolZero,
            symbolZero,
            symbolZero,
            symbolOne,
            symbolOne,
            symbolOne,
            symbolZero,
            symbolOne,
            symbolZero
        };
        Strip aStrip = new Strip(arrayOfSymbols, 0);

        stateZero.add(symbolZero, stateZero, symbolZero, rightMove);
        stateZero.add(symbolOne,stateOne,symbolOne,rightMove);

        stateOne.add(symbolOne, stateOne, symbolOne, rightMove);
        stateOne.add(symbolZero, stateTwo, symbolZero, rightMove);
        
        stateTwo.add(symbolZero, stateZero, symbolZero, rightMove);
        stateTwo.add(symbolOne, stateThree, symbolOne, rightMove);

        turingMachineSubStringDetector = new TuringMachine(stateZero, aStrip);
    }

    @Test
    public void testATuringMachineThatOnlyCleanAStrip(){
        String result;

        turingMachineCleaner.run();
        result = turingMachineCleaner.result();
        assertEquals(result,"...B...");
    }

    @Test
    public void testATuringMachineThatOnlyCleanAStripHasNoAcceptedState(){
        turingMachineCleaner.run();
        assertFalse(turingMachineCleaner.accepted());
    }    
    
    @Test
    public void testATuringMachineThatDetectsTheSubstring101(){
        turingMachineSubStringDetector.run();
        assert(turingMachineSubStringDetector.accepted());
    }

    @Test
    public void testATuringMachineThatDetectsTheSubstring101CanRestartAndChangeStrip(){
        Symbol symbolOne = new Symbol("1");
        Symbol symbolZero = new Symbol("0");
        Symbol blankSymbol = new Symbol("B");

        Symbol []arrayOfSymbols = {
            blankSymbol,
            symbolOne,
            symbolZero,
            symbolZero,
            symbolZero,
            symbolZero,
            symbolOne,
            symbolOne,
            symbolOne,
            symbolZero,
            symbolZero,
            symbolZero,
            blankSymbol
        };

        turingMachineSubStringDetector.restart();
        turingMachineSubStringDetector.changeStrip(new Strip(arrayOfSymbols,1));
        turingMachineSubStringDetector.run();

        assertFalse(turingMachineSubStringDetector.accepted());
    }

    @Test
    public void testATuringMachineThatDetectsTheSubstring101DoesntChangeStripContent(){
        assertEquals(turingMachineSubStringDetector.result(),"...B10000111010B...");
    }
}