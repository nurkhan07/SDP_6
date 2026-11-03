import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public final class SearchVisitor extends RecursiveFileSystemVisitor {
    private final String extension;
    private final List<String> matches = new ArrayList<>();
    public SearchVisitor(String extension) {
        if (extension == null || extension.isBlank()) throw new IllegalArgumentException("Extension must not be blank.");
        this.extension = (extension.startsWith(".") ? extension : "." + extension).toLowerCase(Locale.ROOT);
    }
    @Override public void visitFile(File file) {
        if (file.getName().toLowerCase(Locale.ROOT).endsWith(extension)) matches.add(file.getName());
    }
    public List<String> getMatches() { return List.copyOf(matches); }
}
