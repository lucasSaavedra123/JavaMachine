package TuringMachine.Model;

public class Symbol {

    private String representation;

    static String blankString(){
        return "B";
    }

    public static Symbol asBlank() {
        return new Symbol(Symbol.blankString());
    }

    public boolean equals(Object anotherSymbol){
        return this.representation.equals(((Symbol)anotherSymbol).representation());
    }

    public String representation() {
        return representation;
    }

    @Override
    public String toString(){
        return representation;
    }

    public Symbol(String representation) {
        this.representation = representation;
    }

    public boolean isBlankSymbol() {
        return representation.equals(Symbol.blankString());
    }

}