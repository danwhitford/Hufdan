import encode.HufdanEncoder;
import io.HufdanFileWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Hufdan {
    public static void main(String... args) throws IOException {
        var s = "this is a long 🤪 message";
        var e = HufdanEncoder.encode(s);
        System.out.println(s);
        System.out.println(e.getDictionary());
        System.out.println(e.getEncoded());

        var writer = new HufdanFileWriter(e);
        writer.write("test.out");
    }
}
