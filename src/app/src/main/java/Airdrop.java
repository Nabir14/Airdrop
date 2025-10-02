import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;

import com.hollowstring.airengine.*;
import com.hollowstring.airengine.camera.Camera;
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
        Camera camera = new Camera(70, 800, 600, 0.1f, 1000.0f);

        scene.setActiveCamera(camera);

        camera.position.z = -3.0f;

        Texture texture = new Texture();
        texture.setTexture("/workspaces/Airdrop/others/wall_bricks_old_1024.png");

        Material mat = new Material();
        mat.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
        mat.setFragmentShader("/workspaces/Airdrop/others/fragment.glsl");
        mat.setTexture(texture);

        Object obj = new Object(Mesh.cubeMesh, mat);

        obj.rotation.x = -45.0f;

        scene.appendObject(obj);
        scene.process();

        boolean run = true;
        while(run){
            obj.rotation.x -= 0.1f;
            obj.rotation.y -= 0.1f;
            scene.setClearColor(0.3f, 0.5f, 0.7f);
            scene.render();
            engine.processDefault();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }else if(engine.checkKey(GLFW.GLFW_KEY_1, GLFW.GLFW_PRESS)){
                obj.setHidden(!obj.isHidden);
            }else if(engine.checkKey(GLFW.GLFW_KEY_W, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_LINE);
            }else if(engine.checkKey(GLFW.GLFW_KEY_F, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_FILL);
            }else if(engine.checkKey(GLFW.GLFW_KEY_UP, GLFW.GLFW_PRESS)){
                camera.position.z += 0.1f;
            }else if(engine.checkKey(GLFW.GLFW_KEY_DOWN, GLFW.GLFW_PRESS)){
                camera.position.z -= 0.1f;
            }else if(engine.checkKey(GLFW.GLFW_KEY_LEFT, GLFW.GLFW_PRESS)){
                camera.rotation.y += 0.1f;
            }else if(engine.checkKey(GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_PRESS)){
                camera.rotation.y -= 0.1f;
            }
        }
        engine.close();
    }
}
