# Assignment #6 - Visitor Pattern
## File System Visitor

**Course:** Software Design Patterns  
**Language:** Java 17  
**Student:** [Your full name]  
**GitHub repository:** [Insert your repository URL]

## 1. Introduction

This project demonstrates the Visitor behavioral design pattern using a small file system model. The object structure contains File and Directory elements. A directory can contain files and nested directories.

The purpose of the pattern is to add new operations to an existing object structure without placing all operation logic inside the element classes.

The project includes three operations:

- SizeCalculatorVisitor calculates the total size of all files.
- SearchVisitor finds files by their file extension.
- FileCountVisitor counts the files in the structure.

## 2. Project Structure

The project uses the standard Maven source folders. Java source files are placed directly inside src/main/java, and the test file is placed inside src/test/java. The classes do not use an edu.aitu package.

```text
Assignment6_Visitor_Pattern/
|-- src/
|   |-- main/
|   |   |-- java/
|   |       |-- FileSystemElement.java
|   |       |-- File.java
|   |       |-- Directory.java
|   |       |-- FileSystemVisitor.java
|   |       |-- RecursiveFileSystemVisitor.java
|   |       |-- SizeCalculatorVisitor.java
|   |       |-- SearchVisitor.java
|   |       |-- FileCountVisitor.java
|   |       |-- VisitorTaskRunner.java
|   |       |-- Main.java
|   |-- test/
|       |-- java/
|           |-- VisitorPatternTest.java
|-- UML/
|   |-- visitor.puml
|-- README.md
|-- pom.xml
```

## 3. Design and UML

FileSystemElement is the Element interface and declares the accept method. File and Directory are Concrete Elements. FileSystemVisitor is the Visitor interface and declares visitFile and visitDirectory operations.

SizeCalculatorVisitor, SearchVisitor, and FileCountVisitor are Concrete Visitors. Each visitor implements a different operation on the file system elements.

RecursiveFileSystemVisitor contains the common directory traversal logic. Concrete visitors inherit this behavior and implement their own file-specific operation. This avoids repeating the same directory traversal code.

The UML source is available in UML/visitor.puml.

### Double Dispatch

The Visitor pattern uses double dispatch to select the correct operation:

1. The program calls accept on an element.
2. The concrete element determines which visit method to call.
3. The concrete visitor executes its implementation of that method.

For example, File.accept calls visitor.visitFile(this), while Directory.accept calls visitor.visitDirectory(this). This allows the operation to depend on both the concrete element type and the concrete visitor type.

## 4. Clean Code Requirements - Chapters 12 and 13

This section applies the Clean Code principles requested for Chapters 12 and 13.

### 4.1 Runs All the Tests - Chapter 12

The project includes automated tests for size calculation, file searching, file counting, immutable directory contents, and invalid file sizes. Tests help verify that the program behaves as expected and make it safer to modify the code.

The test file is located at src/test/java/VisitorPatternTest.java.

### 4.2 No Duplication - Chapter 12

The directory traversal algorithm is implemented once in RecursiveFileSystemVisitor. SizeCalculatorVisitor, SearchVisitor, and FileCountVisitor reuse this implementation instead of repeating the same loop through directory children.

### 4.3 Expressive Code - Chapter 12

The class and method names describe their responsibilities. Names such as SizeCalculatorVisitor, SearchVisitor, visitFile, visitDirectory, and getTotalBytes make the purpose of the code easier to understand.

### 4.4 Minimal Classes and Methods - Chapter 12

Each class has a clear responsibility. The project uses a shared base visitor for common traversal logic and keeps each concrete visitor focused on one operation. The design avoids unnecessary helper classes and methods.

### 4.5 Independent Threads and Limited Shared Data - Chapter 13

VisitorTaskRunner runs the size calculation and file search as separate tasks. Each task receives its own visitor instance, so mutable visitor state is not shared between the two tasks.

The file system structure is read-only during traversal. File fields are final, and Directory stores its children using List.copyOf. The concurrency code is also separated from the model and visitor implementations.

This is a small demonstration of Chapter 13 principles. It is not intended to represent a complete production-level concurrent file system.

## 5. Sample Output

The sample file system contains five files:

- Main.java - 1,200 bytes
- Visitor.java - 900 bytes
- notes.txt - 300 bytes
- report.pdf - 2,500 bytes
- diagram.png - 1,800 bytes

Expected program output:

```text
=== Visitor Pattern: File System ===
Total size: 6700 bytes
Java files: [Main.java, Visitor.java]
Total number of files: 5
```

The size calculation and search operations run as independent tasks. The file count visitor is executed separately on the same file system structure.

## 6. Advantages and Limitations

The Visitor pattern makes it possible to add new operations without changing the existing File and Directory classes. It also separates operations from the objects on which they work.

A limitation is that adding a new element type requires updating the visitor interface and the concrete visitors. Therefore, Visitor is most useful when the element structure is relatively stable and new operations are expected.

## 7. Conclusion

The project implements the Visitor pattern with two concrete element types and three concrete visitors. The accept and visit methods demonstrate double dispatch. The implementation also applies Clean Code principles from Chapters 12 and 13 through automated tests, shared traversal logic, expressive names, focused classes, immutable data, and independent visitor tasks.

