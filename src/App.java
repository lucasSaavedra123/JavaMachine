import TuringMachine.Model.*;

public class App {

    public static TuringMachine buildPustilnikMachine(){
        Strip strip = new Strip("BBBBBBBB",0);
        State []states = new State[7];
        Symbol symbolZero = new Symbol("0");
        Symbol symbolOne = new Symbol("1");
        Symbol blankSymbol = Symbol.asBlank();

        for( int i = 0; i<states.length;i++)
            states[i] = State.asNotAccepted("q"+String.valueOf(i));

        states[0].add(symbolZero, states[1], blankSymbol, new Right());
        states[0].add(symbolOne, states[5], blankSymbol, new Right());

        states[1].add(symbolZero, states[1], symbolZero, new Right());
        states[1].add(symbolOne, states[2], symbolOne, new Right());

        states[2].add(symbolOne, states[2], symbolOne, new Right());
        states[2].add(symbolZero, states[3], symbolOne, new Left());
        states[2].add(blankSymbol, states[4], blankSymbol, new Left());

        states[3].add(symbolOne, states[3], symbolOne, new Left());
        states[3].add(symbolZero, states[3], symbolZero, new Left());
        states[3].add(blankSymbol, states[0], blankSymbol, new Right());

        states[4].add(symbolZero, states[4], symbolZero, new Left());
        states[4].add(symbolOne, states[4], blankSymbol, new Left());
        states[4].add(blankSymbol, states[6], symbolZero, new Right());

        states[5].add(symbolZero, states[5], blankSymbol, new Right());
        states[5].add(symbolOne, states[5], blankSymbol, new Right());
        states[5].add(blankSymbol, states[6], blankSymbol, new Right());

        return new TuringMachine(states[0], strip);

    }

    public static void main(String[] args) throws Exception{
        TuringMachine pustilnikMachine = buildPustilnikMachine();

        pustilnikMachine.changeStrip(new Strip("000001000B"));
        pustilnikMachine.run();
        System.out.println("5-3: " + pustilnikMachine.result());

        pustilnikMachine.restart();
        pustilnikMachine.changeStrip(new Strip("00001000B"));
        pustilnikMachine.run();
        System.out.println("4-3: " + pustilnikMachine.result());

        pustilnikMachine.restart();
        pustilnikMachine.changeStrip(new Strip("000000000010000B"));
        pustilnikMachine.run();
        System.out.println("10-4: " + pustilnikMachine.result());

        pustilnikMachine.restart();
        pustilnikMachine.changeStrip(new Strip("010000B"));
        pustilnikMachine.run();
        System.out.println("1-4: " + pustilnikMachine.result());

        pustilnikMachine.restart();
        pustilnikMachine.changeStrip(new Strip("0001000B"));
        pustilnikMachine.run();
        System.out.println("3-3: " + pustilnikMachine.result());

    }
}