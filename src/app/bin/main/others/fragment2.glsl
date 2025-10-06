#version 330 core
out vec4 FragColor;
in vec2 UV;

uniform sampler2D defaultTexture;
uniform vec3 ambientLightColor;

void main(){
    FragColor = vec4(ambientLightColor * texture(defaultTexture, UV).rgb, 1.0);
}