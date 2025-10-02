#version 330 core
out vec4 FragColor;
in vec2 UV;

void main(){
    FragColor = vec4(UV.x, UV.y, UV.x + UV.y, 1.0f);
}