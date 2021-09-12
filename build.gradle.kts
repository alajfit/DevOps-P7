plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.cucumber:cucumber-junit:6.10.3")
    testImplementation("io.cucumber:cucumber-java:6.10.3")
}
