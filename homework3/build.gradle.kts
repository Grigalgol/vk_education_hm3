plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains:annotations:13.0")
    }
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}