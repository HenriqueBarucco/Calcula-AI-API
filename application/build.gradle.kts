plugins {
    kotlin("jvm")
}

group = "com.henriquebarucco"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))

    implementation("org.slf4j:slf4j-api:2.0.17")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
