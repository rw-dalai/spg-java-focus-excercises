plugins {
    java
    id("org.springframework.boot") version "4.0.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "at.spengergasse"
    version = "0.0.1-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
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
        "implementation"("org.springframework.boot:spring-boot-starter-data-jpa")
        "testImplementation"("org.springframework.boot:spring-boot-starter-data-jpa-test")
        "implementation"("org.springframework.boot:spring-boot-starter-validation")
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")
        "runtimeOnly"("com.h2database:h2:2.2.224")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = false
    }
    tasks.named<Jar>("jar") {
        enabled = true
    }
}
