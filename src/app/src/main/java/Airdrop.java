import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import com.hollowstring.airengine.*;
import com.hollowstring.airengine.scene.*;
import com.hollowstring.airengine.object.Object;
import com.hollowstring.airengine.material.*;

public class Airdrop {
    public static void main(String[] args) {
        System.out.println(AirEngine.getVersion());
        AirEngine engine = new AirEngine(800, 600, "Airdrop");
        engine.createWindow();

        Scene scene = new Scene(64);

        Material mat = new Material();
        mat.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
        mat.setFragmentShader("/workspaces/Airdrop/others/fragment.glsl");
        Material mat2 = new Material();
        mat2.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
        mat2.setFragmentShader("/workspaces/Airdrop/others/frag2.glsl");

        Object obj = new Object(Object.squareMesh, mat);
        Object obj2 = new Object(Object.triangleMesh, mat2);

        //obj.setSize(0.5f, 0.5f, 0.5f);
        /*obj2.setSize(0.5f, 0.5f, 0.5f);
        obj.setPos(-0.5f, 0.0f, 0.0f);
        obj2.setPos(0.5f, 0.0f, 0.0f);*/

        scene.appendObject(obj);
        scene.appendObject(obj2);

        boolean run = true;
        while(run){
            scene.setClearColor(0.3f, 0.5f, 0.7f);
            scene.render();
            engine.processDefault();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }
        }
        engine.close();
    }
}
