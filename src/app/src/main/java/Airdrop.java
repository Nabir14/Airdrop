import com.hollowstring.airengine.*;

public class Airdrop {
    public static void main(String[] args) {
        AirEngine engine = new AirEngine(640, 320, "Airdrop");
        engine.createWindow();
        boolean run = true;
        while(run){
            engine.loopDefault();
            if(engine.checkKey(GLFW_KEY_ESCAPE, GLFW_PRESS)){
                run = false;
            }
        }
        engine.close();
    }
}
