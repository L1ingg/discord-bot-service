plugins {
	java
	id("org.springframework.boot") version "4.0.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.ling"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("net.dv8tion:JDA:6.4.1")
    implementation("org.springframework.boot:spring-boot-starter-kafka")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
