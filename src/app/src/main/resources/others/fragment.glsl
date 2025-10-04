#version 330 core
out vec4 FragColor;
in vec2 UV;
uniform sampler2D defaultTexture;

void main(){
    FragColor = texture(defaultTexture, UV * 32.0);
}