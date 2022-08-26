apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.pokemonDomain))
    "implementation"(Coil.coilCompose)
}
