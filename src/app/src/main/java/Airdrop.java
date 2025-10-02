import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;

import com.hollowstring.airengine.*;
import com.hollowstring.airengine.scene.*;
import com.hollowstring.airengine.texture.*;
import com.hollowstring.airengine.object.Object;
import com.hollowstring.airengine.material.*;
import com.hollowstring.airengine.mesh.Mesh;

public class Airdrop {
    public static void main(String[] args) {
        System.out.println(AirEngine.getVersion());

        AirEngine engine = new AirEngine(800, 600, "Airdrop");
        engine.createWindow();

        Scene scene = new Scene(64);

        Texture texture = new Texture();
        texture.setTexture("/workspaces/Airdrop/others/wall_bricks_old_1024.png");

        Material mat = new Material();
        mat.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
        mat.setFragmentShader("/workspaces/Airdrop/others/fragment.glsl");
        mat.setTexture(texture);

        Object obj = new Object(Mesh.squareMesh, mat);

        scene.appendObject(obj);

        boolean run = true;
        while(run){
            scene.setClearColor(0.3f, 0.5f, 0.7f);
            scene.render();
            engine.processDefault();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }else if(engine.checkKey(GLFW.GLFW_KEY_1, GLFW.GLFW_PRESS)){
                obj.setHidden(!obj.isHidden);
            }else if(engine.checkKey(GLFW.GLFW_KEY_W, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_LINE);
            }
        }
        engine.close();
    }
}
