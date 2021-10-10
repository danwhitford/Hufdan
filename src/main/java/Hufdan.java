import encode.HufdanEncoder;
import io.HufdanFileReader;
import io.HufdanFileWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Hufdan {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        var s = "this is a long 🤪 message";
        var e = HufdanEncoder.encode(s);
        System.out.println(s);
        System.out.println(e.getDictionary());
        System.out.println(e.getEncoded());

        var writer = new HufdanFileWriter(e);
        writer.write("test.out");

        var ee = HufdanFileReader.read("test.out");
        System.out.println(ee.getDictionary());
        System.out.println(ee.getEncoded());
    }
}
