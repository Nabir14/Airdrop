import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;

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

        obj.updateSize(1.0f, 1.0f, 1.0f);
        obj2.updateSize(1.0f, 1.0f, 1.0f);
        obj.updatePosition(0.0f, 0.0f, 0.0f);
        obj2.updatePosition(0.0f, 0.0f, 0.0f);

        scene.appendObject(obj);
        scene.appendObject(obj2);

        boolean run = true;
        while(run){
            scene.setClearColor(0.3f, 0.5f, 0.7f);
            scene.render();
            engine.processDefault();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }else if(engine.checkKey(GLFW.GLFW_KEY_1, GLFW.GLFW_PRESS)){
                obj.setHidden(!obj.isHidden);
            }else if (engine.checkKey(GLFW.GLFW_KEY_2, GLFW.GLFW_PRESS)){
                obj2.setHidden(!obj2.isHidden);
            }else if(engine.checkKey(GLFW.GLFW_KEY_W, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_LINE);
            }
        }
        engine.close();
    }
}
