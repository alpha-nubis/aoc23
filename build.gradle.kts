plugins {
    kotlin("jvm") version "1.9.21" // Kotlin version to use
    application // Application plugin. Also see 1️⃣ below the code
}

group = "org.anubis" // A company name, for example, `org.jetbrains`
version = "1.0-SNAPSHOT" // Version to assign to the built artifact

repositories { // Sources of dependencies. See 2️⃣
    mavenCentral() // Maven Central Repository. See 3️⃣
}

dependencies { // All the libraries you want to use. See 4️⃣
    // Copy dependencies' names after you find them in a repository
    testImplementation(kotlin("test")) // The Kotlin test library
    implementation("io.arrow-kt:arrow-core:1.2.0")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0")
}

tasks.test { // See 5️⃣
    useJUnitPlatform() // JUnitPlatform for tests. See 6️⃣
}

kotlin { // Extension for easy setup
    jvmToolchain(21) // Target version of generated JVM bytecode. See 7️⃣
}

application {
    mainClass.set("MainKt") // The main class of the application
}