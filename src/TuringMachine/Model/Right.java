package TuringMachine.Model;

public class Right extends Movement {
    public void move(Strip strip){
        strip.right();
    }
}