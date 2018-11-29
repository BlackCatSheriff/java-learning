import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author skyUnv
 * created on 2018/10/21 15:17
 */
public class Test {

    public static void main(String[] args) {
        String testStr = "zhaishuyan";

        Map<Integer, String> map = new HashMap<>();

        map.put(100, "i");
        map.put(101, "l");
        map.put(102, "o");
        map.put(103, "v");
        map.put(104, "e");


        System.out.println(map.values().getClass().getSimpleName());
    }
}
