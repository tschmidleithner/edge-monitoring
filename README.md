# Readme
This project is intended to be used as an architecture and simulation tool for VANETs using edge computing. 

# Overview

# Modules
The project is built with Gradle and Spring Boot 1.4 and separated in various submodules.
- Trace-parser: This submodule is responsible for parsing vehicle traces and using this traces to simulate vehicles communicating
with Cloudlets in an edge computing environment.
- Shared: Reusable classes that are shared over different modules are placed in the shared module. The classes from this submodule 
can simply be included by specifing ```compile project(':shared')``` as Gradle dependency in the build.gradle file.
