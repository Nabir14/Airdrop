#version 330 core
out vec4 FragColor;
in vec2 UV;
uniform sampler2D defaultTexture;

void main(){
    FragColor = vec4(mix(texture(defaultTexture, UV).rgb, vec3(UV.x, UV.y, UV.x + UV.y), 0.5), 1.0f);
}