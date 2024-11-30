import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class DuplicateRemover {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        Set<String> uniqueLines = new HashSet<>();
        long duplicateCount = 0;

        Files.lines(Paths.get(inputFilePath))
                .forEach(line -> {
                    if (!uniqueLines.add(line)) {
                        duplicateCount++;
                    }
                });

        Files.write(Paths.get(outputFilePath), uniqueLines);

        System.out.println("Количество удаленных дублирующих строк: " + duplicateCount);
    }
}



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipExtractor {
    public static void extractZip(String zipFilePath, String destinationDirectory) throws IOException {
        File destDir = new File(destinationDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    extractFile(entry, zipFile, destinationDirectory);
                }
            }
        }
    }

    private static void extractFile(ZipEntry entry, ZipFile zipFile, String destinationDirectory) throws IOException {
        String filePath = destinationDirectory + File.separator + entry.getName();
        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            try (var inputStream = zipFile.getInputStream(entry)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) >= 0) {
                    fos.write(buffer, 0, length);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String zipFilePath = "compressed.zip";
        String destinationDirectory = "extracted_files";

        extractZip(zipFilePath, destinationDirectory);

        System.out.println("Файл распакован в директорию: " + destinationDirectory);
    }
}
