#version 330 core
out vec4 FragColor;
in vec2 UV;
in vec3 Normal;
in vec3 FragPos;
uniform sampler2D defaultTexture;
uniform vec3 ambientLightColor;
uniform vec3 pointLightColor;
uniform vec3 pointLightPos;

void main(){
    vec3 pointLightDir = normalize(pointLightPos - FragPos);
    vec3 diffuse = max(dot(normalize(Normal), pointLightDir), 0.0) * pointLightColor;
    FragColor = vec4((ambientLightColor+diffuse) * texture(defaultTexture, UV * 32.0).rgb, 1.0);
}