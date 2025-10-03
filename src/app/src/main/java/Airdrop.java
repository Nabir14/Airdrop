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

        camera.setPosition(-3.0f, 3.0f, 0.0f);

        Texture texture = new Texture();
        texture.setTexture("/workspaces/Airdrop/others/wall_bricks_old_1024.png");

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                Material mat = new Material();
                mat.setVertexShader("/workspaces/Airdrop/others/vertex.glsl");
                mat.setFragmentShader("/workspaces/Airdrop/others/fragment.glsl");
                mat.setTexture(texture);
                Object obj = new Object(Mesh.cubeMesh, mat);
                obj.setPosition((float)i, 0.0f, (float)j);
                scene.appendObject(obj);
            }
        }

        scene.processObjects();

        boolean run = true;
        while(run){
            scene.setClearColor(0.3f, 0.5f, 0.7f);
            scene.render();
            engine.processDefault();
            if(engine.checkKey(GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_PRESS) || GLFW.glfwWindowShouldClose(engine.getWindow())){
                run = false;
            }else if(engine.checkKey(GLFW.GLFW_KEY_W, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_LINE);
            }else if(engine.checkKey(GLFW.GLFW_KEY_F, GLFW.GLFW_PRESS)){
                engine.setDrawMode(GL11.GL_FILL);
            }else if(engine.checkKey(GLFW.GLFW_KEY_UP, GLFW.GLFW_PRESS)){
                camera.position.add(camera.direction.normalize().mul(0.1f));
            }else if(engine.checkKey(GLFW.GLFW_KEY_DOWN, GLFW.GLFW_PRESS)){
                camera.position.add(camera.direction.normalize().mul(0.1f));
            }else if(engine.checkKey(GLFW.GLFW_KEY_LEFT, GLFW.GLFW_PRESS)){
                camera.rotation.y -= 1.0f;
            }else if(engine.checkKey(GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_PRESS)){
                camera.rotation.y += 1.0f;
            }
        }
        engine.close();
    }
}
