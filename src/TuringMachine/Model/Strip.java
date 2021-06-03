package TuringMachine.Model;

public class Strip {

    private Symbol[] symbols;
    private int currentPosition;

	public Strip(Symbol[] symbolsArray, int initialPosition) {
        this.symbols = symbolsArray;
        this.currentPosition = initialPosition;
    }

    public Strip(int lengthOfStrip, int initialPosition) {
        this.symbols = new Symbol[lengthOfStrip];
        int symbolIndex = 0;

        while(symbolIndex < lengthOfStrip){
            this.symbols[symbolIndex] = Symbol.asBlank();
            symbolIndex++;
        }

        this.currentPosition = initialPosition;
    }

    public Strip(String symbols, int initialPosition) {
        this.symbols = stringToArrayOfSymbols(symbols);
        this.currentPosition = initialPosition;
    }

    private Symbol[] stringToArrayOfSymbols(String symbols) {
        Symbol[] symbolsAsArray = new Symbol[symbols.length()];

        for (int index = 0 ; index<symbols.length() ; index++)
            symbolsAsArray[index] = new Symbol(Character.toString(symbols.charAt(index)));
        return symbolsAsArray;
    }

    public Strip(String symbols) {
        this.symbols = stringToArrayOfSymbols(symbols);
        this.currentPosition = 0;
    }

    @Override
    public String toString(){
        String string = "";
        String limits = "...";
        String symbolsAsString = arrayOfSymbolsAsString();
        int firstIndex = 0;
        int lastIndex = this.length()-1;

        if (this.empty())
            string =  limits + Symbol.blankString() + limits;
        else{
            for(int i = 0 ; i<symbolsAsString.length() && Character.toString(symbolsAsString.charAt(i)).equals(Symbol.blankString()) ; i++) firstIndex++;
            for(int i = this.length()-1 ; i>=0 && Character.toString(symbolsAsString.charAt(i)).equals(Symbol.blankString()) ; i--) lastIndex--;
            string = limits + Symbol.blankString() + symbolsAsString.substring(firstIndex, lastIndex+1)  + Symbol.blankString() + limits;

        }

        return string;
    }

    private String arrayOfSymbolsAsString() {
        String string = "";

        for (Symbol symbol : symbols) {
            string = string + symbol.toString();
        }

        return string;
    }

    private boolean empty() {

        for (int index = 0 ; index<symbols.length ; index++){
            if (!symbols[index].isBlankSymbol())
                return false;
        }

        return true;
    }

    public int length(){
        return symbols.length;
    }

    public Symbol read() {
        return symbols[currentPosition];
    }

    public void left() {
        currentPosition = Math.max(currentPosition-1,0);
    }

    public void right() {
        currentPosition = Math.min(currentPosition+1,this.length()-1);
    }

    public void write(Symbol aSymbol) {
        symbols[currentPosition] = aSymbol;
    }

    public void moveTo(Movement movement) {
        movement.move(this);
    }

    public int currentPosition() {
        return this.currentPosition;
    }

}