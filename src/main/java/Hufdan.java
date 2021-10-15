import decode.HufdanDecoder;
import encode.HufdanEncoder;
import io.BitSetBuilder;
import io.HufdanFileReader;
import io.HufdanFileWriter;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hufdan {

    public static void main(String... args) throws IOException, ClassNotFoundException {
//        var s = Files.readString(Path.of("test.in"));
        var s = "Lots of otters";

        var e = HufdanEncoder.encode(s);

        var writer = new HufdanFileWriter(e);
        writer.write("test.out");

        var ee = HufdanFileReader.read("test.out");
        String decoded = HufdanDecoder.decode(ee);

        System.out.println("A: " + s);
        System.out.println("B: " + decoded);
        if (!s.equals(decoded))
            throw new RuntimeException("poo");
    }
}