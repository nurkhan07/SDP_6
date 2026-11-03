import java.util.Objects;
public final class File implements FileSystemElement {
    private final String name;
    private final long sizeBytes;
    public File(String name, long sizeBytes) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("File name must not be blank.");
        if (sizeBytes < 0) throw new IllegalArgumentException("File size cannot be negative.");
        this.name = name; this.sizeBytes = sizeBytes;
    }
    public String getName() { return name; }
    public long getSizeBytes() { return sizeBytes; }
    @Override public void accept(FileSystemVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor must not be null").visitFile(this);
    }
}
