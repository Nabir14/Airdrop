#version 330 core
out vec4 FragColor;
in vec2 UV;
in vec3 Normal;
in vec3 FragPos;
uniform sampler2D defaultTexture;
uniform vec3 ambientLightColor;
uniform vec3 pointLightColor;
uniform vec3 pointLightPos;
uniform vec3 camPos;
float specularStrength = 1.0;
int shinyness = 64;

void main(){
    vec3 pointLightDir = normalize(pointLightPos - FragPos);
    vec3 diffuse = max(dot(normalize(Normal), pointLightDir), 0.0) * pointLightColor;
    vec3 camDir = normalize(camPos - FragPos);
    vec3 reflectDir = reflect(-pointLightDir, normalize(Normal));
    float shine = pow(max(dot(camDir, reflectDir), 0.0), shinyness);
    vec3 specular = specularStrength * shine * pointLightColor;
    FragColor = vec4((ambientLightColor+diffuse+specular) * texture(defaultTexture, UV).rgb, 1.0);
}