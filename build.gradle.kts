plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.liquibase:liquibase-core")
	implementation ("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.data:spring-data-redis")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("net.javacrumbs.shedlock:shedlock-spring:5.13.0")
	implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:5.13.0")
	implementation("io.lettuce:lettuce-core:6.3.2.RELEASE")
	implementation("io.jsonwebtoken:jjwt:0.12.5")
	implementation("org.springframework.security:spring-security-oauth2-jose:6.3.0")
	implementation("org.keycloak:keycloak-spring-boot-starter:24.0.4")
	implementation("org.springframework.security:spring-security-oauth2-client:6.3.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.1.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("commons-validator:commons-validator:1.8.0")
	implementation("org.springframework.security:spring-security-crypto:6.2.4")
	compileOnly("org.projectlombok:lombok")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
