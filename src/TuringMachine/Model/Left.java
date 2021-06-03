package TuringMachine.Model;

public class Left extends Movement{
    public void move(Strip strip){
        strip.left();
    }
}