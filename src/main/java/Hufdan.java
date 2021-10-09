import encode.HufdanEncoder;

import java.util.Arrays;

public class Hufdan {
    public static void main(String... args) {
        System.out.println("woop");
        System.out.println(new String(HufdanEncoder.encode("this is a long 🤪 message")));
    }
}
