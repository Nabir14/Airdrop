import org.lwjgl.glfw.*;

import com.hollowstring.airengine.*;

public class Airdrop {
    public static void main(String[] args) {
        AirEngine engine = new AirEngine(640, 320, "Airdrop");
        engine.createWindow();
        boolean run = true;
        while(run){
            engine.setClearColor(0.3f, 0.5f, 0.7f);
            engine.render();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }
        }
        engine.close();
    }
}
