import src.com.hollowstring.airengine.*;

public class airdrop {
    public static void main(String[] args) {
        airengine engine = new airengine(640, 320, "Airdrop");
        engine.createWindow();
        boolean run = true;
        while(run){
            engine.loopDefault();
        }
        engine.close();
    }
}
