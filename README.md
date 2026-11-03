# Assignment #6 — Visitor Pattern
**Course:** Software Design Patterns | **Language:** Java 17 | **Topic:** File System Visitor

## Overview
This project models an immutable file system and uses the Visitor behavioral design pattern to add operations without modifying `File` and `Directory`.

## Elements and visitors
- `FileSystemElement`: element interface with `accept(FileSystemVisitor)`.
- `File`, `Directory`: concrete elements.
- `FileSystemVisitor`: declares `visitFile` and `visitDirectory`.
- `SizeCalculatorVisitor`: calculates total file size.
- `SearchVisitor`: searches by extension.
- `FileCountVisitor`: third operation demonstrating extensibility.

## Double dispatch
`File.accept(visitor)` calls `visitor.visitFile(this)` and `Directory.accept(visitor)` calls `visitor.visitDirectory(this)`. The concrete element selects the matching visit method, and the concrete visitor supplies the operation.

## Concurrency and data safety
`VisitorTaskRunner` runs the size and search visitors concurrently. Each task owns a separate visitor instance. `Directory` copies its children with `List.copyOf`, so the object structure is read-only during traversal. This applies Chapter 13 ideas: isolate concurrency code, limit shared mutable state, and keep tasks independent.

## Run
Requires JDK 17 and Maven:
```bash
mvn clean test
mvn package
java -cp target/classes Main
```
Open the folder as a Maven project in IntelliJ IDEA and run `Main`.

## Expected output
```text
=== Visitor Pattern: File System ===
Total size: 6700 bytes
Java files: [Main.java, Visitor.java]
Total number of files: 5
```
UML source: `UML/visitor.puml`.

Before submission, review the code, run the tests, create your own incremental Git commits, and add your GitHub URL to the report.
