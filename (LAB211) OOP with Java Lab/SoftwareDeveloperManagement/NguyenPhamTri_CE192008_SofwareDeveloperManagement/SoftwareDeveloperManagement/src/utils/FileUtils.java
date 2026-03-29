package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {
    
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /* ========================
       READ FILE
    ========================= */
    public static List<String> readAllLines(String fileName) throws IOException {

        List<String> lines = new ArrayList<>();
        File f = new File(fileName);

        if (!f.exists()) {
            return lines;
        }

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), DEFAULT_CHARSET))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }

        return lines;
    }

    /* ========================
       WRITE FILE
    ========================= */
    public static void writeAllLines(String fileName, List<String> lines) throws IOException {

        try ( BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName), DEFAULT_CHARSET))) {

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
