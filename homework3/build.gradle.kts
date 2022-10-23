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
        testImplementation("org.mockito:mockito-inline:4.8.0")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
        testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
        testImplementation("org.hamcrest:hamcrest-all:1.3")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
        implementation("org.jetbrains:annotations:13.0")
    }
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}