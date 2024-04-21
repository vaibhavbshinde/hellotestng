package testngkit.runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestCode {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./src/test/resources/testpayload/addbook_details.txt");
        try ( FileReader fr = new FileReader(file)){
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
