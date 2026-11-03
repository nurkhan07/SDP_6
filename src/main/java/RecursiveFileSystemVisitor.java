/** Shared depth-first traversal; subclasses implement only file-specific behavior. */
public abstract class RecursiveFileSystemVisitor implements FileSystemVisitor {
    @Override
    public final void visitDirectory(Directory directory) {
        for (var child : directory.getChildren()) {
            child.accept(this);
        }
    }
}
