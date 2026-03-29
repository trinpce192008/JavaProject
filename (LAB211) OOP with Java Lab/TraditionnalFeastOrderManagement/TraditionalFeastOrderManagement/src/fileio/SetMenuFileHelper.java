/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.SetMenu;

/**
 *
 * @author TRI
 */
public class SetMenuFileHelper implements IFileReadWrite<SetMenu> {

    private final String FILE_NAME = "src\\fileio\\FeastMenu.csv";

    @Override
    public List<SetMenu> read() throws Exception {
        List<SetMenu> menus = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists()) return menus;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {

            br.readLine(); // header: Code,Name,Price,Ingredients
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // split comma but ignore comma inside quotes
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String code = parts[0].replace("\uFEFF", "").trim();
                String name = parts[1].trim();
                long price = Long.parseLong(parts[2].trim());
                String ing = parts[3].trim();

                if (ing.startsWith("\"") && ing.endsWith("\"")) {
                    ing = ing.substring(1, ing.length() - 1);
                }
                ing = ing.replace("#+", "\n+");

                menus.add(new SetMenu(code, name, price, ing));
            }
        }
        return menus;
    }

    @Override
    public boolean write(List<SetMenu> list) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
