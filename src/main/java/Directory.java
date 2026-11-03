import java.util.List;
import java.util.Objects;
public final class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> children;
    public Directory(String name, List<FileSystemElement> children) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Directory name must not be blank.");
        this.name = name;
        this.children = List.copyOf(Objects.requireNonNull(children, "children must not be null"));
    }
    public String getName() { return name; }
    public List<FileSystemElement> getChildren() { return children; }
    @Override public void accept(FileSystemVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor must not be null").visitDirectory(this);
    }
}
