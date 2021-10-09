import encode.HufdanEncoder;

public class Hufdan {
    public static void main(String... args) {
        var s = "this is a long 🤪 message";
        System.out.println(s);
        System.out.println(new String(HufdanEncoder.encode(s)));
    }
}
