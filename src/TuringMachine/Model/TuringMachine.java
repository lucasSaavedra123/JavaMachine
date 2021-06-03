package TuringMachine.Model;

public class TuringMachine {

    private State currentState;
    private Strip strip;
    private State initialState;

    public TuringMachine(State initialState, Strip aStrip) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.strip = aStrip;
    }

    public void run() {
        while(currentState.hasTransitionFor(strip.read())){
            this.step();
        }
    }

    private void step() {
        Symbol currentSymbol = strip.read();
        strip.write(currentState.transitionSymbol(currentSymbol));
        strip.moveTo(currentState.transitionMovement(currentSymbol));
        currentState = currentState.transitionState(currentSymbol);
    }

    public String result(){
        return strip.toString();
    }

    public boolean accepted() {
        return currentState.accepted();
    }

    public void restart() {
        this.currentState = this.initialState;
    }

    public void changeStrip(Strip newStrip) {
        this.strip = newStrip;
    }

}
