import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class VisitorPatternTest {
    private FileSystemElement sample() {
        return new Directory("root", List.of(new File("A.java", 100),
            new Directory("nested", List.of(new File("B.java", 200), new File("readme.txt", 50)))));
    }
    @Test void sizeVisitorCalculatesNestedFileSizes() {
        var visitor = new SizeCalculatorVisitor(); sample().accept(visitor);
        assertEquals(350, visitor.getTotalBytes());
    }
    @Test void searchVisitorFindsMatchesCaseInsensitively() {
        var visitor = new SearchVisitor(".JAVA"); sample().accept(visitor);
        assertEquals(List.of("A.java", "B.java"), visitor.getMatches());
    }
    @Test void countVisitorCountsFiles() {
        var visitor = new FileCountVisitor(); sample().accept(visitor);
        assertEquals(3, visitor.getFileCount());
    }
    @Test void directoryChildrenAreImmutable() {
        var directory = new Directory("empty", List.of());
        assertThrows(UnsupportedOperationException.class,
            () -> directory.getChildren().add(new File("x.txt", 1)));
    }
    @Test void rejectsNegativeFileSize() {
        assertThrows(IllegalArgumentException.class, () -> new File("bad.txt", -1));
    }
}
