/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import java.util.List;
import model.Order;
import utils.FileUtils;

/**
 *
 * @author TRI
 */
public class OrderFileHelper implements IFileReadWrite<Order> {

    private final String FILE_NAME = "src\\fileio\\feast_order_service.dat";

    @Override
    public List<Order> read() throws Exception {
        return FileUtils.<Order>readFile(FILE_NAME);
    }

    @Override
    public boolean write(List<Order> list) throws Exception {
        FileUtils.saveToFile(list, FILE_NAME);
        return true;
    }
}

