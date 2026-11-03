import java.util.List;
import java.util.concurrent.Future;
public final class Main {
    private Main() { }
    public static void main(String[] args) throws Exception {
        FileSystemElement root = createSampleFileSystem();
        try (VisitorTaskRunner runner = new VisitorTaskRunner()) {
            Future<SizeCalculatorVisitor> sizeTask = runner.run(root, new SizeCalculatorVisitor());
            Future<SearchVisitor> searchTask = runner.run(root, new SearchVisitor(".java"));
            System.out.println("=== Visitor Pattern: File System ===");
            System.out.println("Total size: " + sizeTask.get().getTotalBytes() + " bytes");
            System.out.println("Java files: " + searchTask.get().getMatches());
        }
        FileCountVisitor countVisitor = new FileCountVisitor();
        root.accept(countVisitor);
        System.out.println("Total number of files: " + countVisitor.getFileCount());
    }
    private static FileSystemElement createSampleFileSystem() {
        Directory source = new Directory("src", List.of(
            new File("Main.java", 1200), new File("Visitor.java", 900), new File("notes.txt", 300)));
        Directory documents = new Directory("documents", List.of(
            new File("report.pdf", 2500), new File("diagram.png", 1800)));
        return new Directory("root", List.of(source, documents));
    }
}
