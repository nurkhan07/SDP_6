public final class FileCountVisitor extends RecursiveFileSystemVisitor {
    private int fileCount;
    @Override public void visitFile(File file) { fileCount++; }
    public int getFileCount() { return fileCount; }
}
