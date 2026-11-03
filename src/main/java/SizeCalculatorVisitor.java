public final class SizeCalculatorVisitor extends RecursiveFileSystemVisitor {
    private long totalBytes;
    @Override public void visitFile(File file) { totalBytes += file.getSizeBytes(); }
    public long getTotalBytes() { return totalBytes; }
}
