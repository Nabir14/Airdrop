#version 330 core
out vec4 FragColor;
in vec2 UV;

uniform sampler2D defaultTexture;
uniform sampler2D secondTexture;

void main(){
    FragColor = mix(texture(defaultTexture, UV), texture(secondTexture, UV), 0.75);
}