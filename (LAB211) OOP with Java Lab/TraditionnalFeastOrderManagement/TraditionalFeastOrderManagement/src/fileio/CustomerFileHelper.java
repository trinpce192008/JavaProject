/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import java.util.List;
import model.Customer;
import utils.FileUtils;

/**
 *
 * @author TRI
 */
public class CustomerFileHelper implements IFileReadWrite<Customer> {

    private final String FILE_NAME = "src\\fileio\\customers.dat";

    @Override
    public List<Customer> read() throws Exception {
        return FileUtils.<Customer>readFile(FILE_NAME);
    }

    @Override
    public boolean write(List<Customer> list) throws Exception {
        FileUtils.saveToFile(list, FILE_NAME);
        return true;
    }
}

