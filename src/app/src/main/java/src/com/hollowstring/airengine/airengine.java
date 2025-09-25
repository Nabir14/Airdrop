package src.com.hollowstring.airengine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengles.*;

public class airengine {
    public static long window;

    public static void init(){
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLES32.GL_TRUE);
    }

    public static void createWindow(int width, int height, String title){
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GLES32.glViewport(0, 0, width, height);
    }

    public static void loopDefault(){
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }
    public static void close(){
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwTerminate();
    }
}
