plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.google.code.gson:gson:2.9.1")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("com.google.inject:guice:5.0.1")
    implementation("org.projectlombok:lombok:1.18.24")
    implementation ("org.jetbrains:annotations:13.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core:4.8.1")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}