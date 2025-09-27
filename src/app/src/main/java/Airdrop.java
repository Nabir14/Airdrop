import org.lwjgl.glfw.*;

import com.hollowstring.airengine.*;
import com.hollowstring.airengine.scene.*;
import com.hollowstring.airengine.object.Object;
import com.hollowstring.airengine.material.*;

public class Airdrop {
    public static void main(String[] args) {
        System.out.println(AirEngine.getVersion());
        AirEngine engine = new AirEngine(640, 320, "Airdrop");
        engine.createWindow();

        Scene scene = new Scene(64);

        Material mat = new Material();
        mat.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
        mat.setFragmentShader("/workspaces/Airdrop/others/fragment.glsl");

        Object obj = new Object(Object.triangleMesh, mat);
        scene.appendObject(obj);
        scene.setCurrent();

        boolean run = true;
        while(run){
            engine.setClearColor(0.3f, 0.5f, 0.7f);
            engine.render();
            scene.render();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }
        }
        engine.close();
    }
}
