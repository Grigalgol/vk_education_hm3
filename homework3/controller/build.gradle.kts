plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":models"))
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.google.inject:guice:5.0.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}