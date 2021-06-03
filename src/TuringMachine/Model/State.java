package TuringMachine.Model;

import java.util.Hashtable;

public class State {

    private Hashtable<String, Object[]> transitions = new Hashtable<String, Object[]>();
    private boolean isAccepted;
    private String representation;

    public String toString(){
        return representation;
    }

    public static State asAccepted(String representation) {
        return new State(representation,true);
    }

    public static State asNotAccepted(String representation) {
        return new State(representation,false);
    }

    private State(String representation, boolean isAccepted) {
        this.representation = representation;
        this.isAccepted = isAccepted;
    }

    public void add(Symbol aSymbol, State anotherState, Symbol anotherSymbol, Movement aMovement) {
        Object[] transition = { anotherState, anotherSymbol, aMovement };
        transitions.put(aSymbol.representation(),transition);
    }

    public Boolean hasTransitionFor(Symbol aSymbol) {
        return !(transitions.get(aSymbol.representation()) == null);
    }

    public State transitionState(Symbol aSymbol) {
        return (State)((transitions.get(aSymbol.representation()))[0]);
    }

    public Symbol transitionSymbol(Symbol aSymbol) {
        return (Symbol)((transitions.get(aSymbol.representation()))[1]);
    }

    public Movement transitionMovement(Symbol aSymbol) {
        return (Movement)((transitions.get(aSymbol.representation()))[2]);
    }

    public boolean accepted() {
        return isAccepted;
    }

}