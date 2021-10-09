import encode.HufdanEncoder;

public class Hufdan {
    public static void main(String... args) {
        var s = "this is a long 🤪 message";
        var e = HufdanEncoder.encode(s);
        System.out.println(s);
        System.out.println(e.getDictionary());
        System.out.println(e.getEncoded());
    }
}
