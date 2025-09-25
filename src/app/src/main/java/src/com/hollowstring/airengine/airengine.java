package src.com.hollowstring.airengine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class airengine {
    private long window;
    private int width, height;
    private String title;

    public airengine(int w, int h, String t){
        this.width = w;
        this.height = h;
        this.title = t;
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
    }

    public static void getVersion(){
        System.out.println("LWJGL Version: " + Version.getVersion());
    }

    public void createWindow(){
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        // FIXME: Throws No Context Error
        //GL11.glViewport(0, 0, width, height);
    }

    public void loopDefault(){
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public void close(){
        GLFW.glfwWindowShouldClose(this.window);
        GLFW.glfwTerminate();
    }
}
