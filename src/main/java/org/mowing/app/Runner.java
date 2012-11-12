package org.mowing.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.mowing.model.Direction;
import org.mowing.model.Map;
import org.mowing.model.Mower;
import org.mowing.model.Order;

/**
 * 
 * @author Paul Dijou
 * 
 * Instructions:
 * <ul>
 *  <li>Create an 'input.txt' file inside the 'src/main/resources' directory</li>
 *  <li>Write the instructions with the correct syntax in it</li>
 *  <li>Run the 'Runner' class as a Java Application</li>
 *  <li>Look at the result inside your console</li>
 * </ul>
 *
 */
public class Runner {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Map map = null;
        String line;

        // Get file with instructions
        File input = new File("src/main/resources/input.txt");
        Scanner scanner = new Scanner(input);

        int i = 0;
        int id = 0;
        
        // Read file
        while (scanner.hasNextLine()) {
            if (i == 0) {
                // First line: init the map
                line = scanner.nextLine();
                String[] mapInitParams = line.split(" ");
                map = new Map(Integer.parseInt(mapInitParams[0]), Integer.parseInt(mapInitParams[1]));
            } else if (i % 2 > 0 && scanner.hasNextLine()) {
                // Other lines: init a mower
                // Skip all even rows
                line = scanner.nextLine();
                String[] mowerInitParams = line.split(" ");
                line = scanner.nextLine();
                
                // Convert string orders to enum orders
                List<Order> orders = new ArrayList<Order>();
                for (String order : line.split("")) {
                    try {
                        orders.add(Order.valueOf(order));
                    } catch(IllegalArgumentException e) {
                        // Not a valid character, let's just ignore it
                    }
                }

                // Init a new mower and add it to the map
                ++id;
                Mower mower = new Mower(id,
                        Integer.parseInt(mowerInitParams[0]),
                        Integer.parseInt(mowerInitParams[1]),
                        Direction.valueOf(mowerInitParams[2]),
                        orders);
               
                map.addMower(mower);
            }
            
            ++i;
        }
        
        // Close the file
        scanner.close();
        
        // Let's mow!!
        map.runMowers();
    }

}
