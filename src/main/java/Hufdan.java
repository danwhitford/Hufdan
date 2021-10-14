import decode.HufdanDecoder;
import encode.HufdanEncoder;
import io.HufdanFileReader;
import io.HufdanFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hufdan {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        var s = Files.readString(Path.of("/Users/danielwhitford/workspace/Hufdan/src/main/java/encode/HufdanEncoder.java"));
//        var s = "This is a long message";

        var e = HufdanEncoder.encode(s);
        System.out.println("Original: " + s);

        var writer = new HufdanFileWriter(e);
        writer.write("test.out");

        var ee = HufdanFileReader.read("test.out");
        String decoded = HufdanDecoder.decode(ee);
        System.out.println("Decoded: " + decoded);

        assert (s.equals(decoded));
    }
}
