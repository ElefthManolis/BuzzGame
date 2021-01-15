import java.awt.*;
import java.io.IOException;

/**
 * From here starts all the Buzz Game
 * @author Nikolaos Tzavidas 3442
 * @author Emmanouil Eleftheriadis 3415
 * @version 2.0 15/1/2020
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Dimension dim = new Dimension(650, 500);
        new GUI(dim);
    }
}