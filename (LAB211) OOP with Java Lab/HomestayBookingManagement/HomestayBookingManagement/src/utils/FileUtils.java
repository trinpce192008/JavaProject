/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRI
 */
public class FileUtils {

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
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_16LE))) {

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
                new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_16LE))) {

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
