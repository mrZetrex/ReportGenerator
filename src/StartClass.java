import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;


public class StartClass {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DataParserTsvClass parser = new DataParserTsvClass();
        TakerXmlClass taker = new TakerXmlClass();
        File xmlFile = new File(args[0]);
        File dataFile = new File(args[1]);
        //File xmlFile = new File("settings.xml");
        //File dataFile = new File("source-data.tsv");

        try (BufferedWriter wr = Files.newBufferedWriter(Paths.get(args[2]), StandardCharsets.UTF_16)) {
            parser.getData(dataFile);
            taker.getSettings(xmlFile);
            List<Column> list = taker.getListOfColumns();

            //Создаем разделитель
            StringBuilder lineString = new StringBuilder();
            Generator gen = new Generator(lineString, list);

            //Создаем титульный заголовок и разрыв
            StringBuilder titleString = gen.getTitleString(list);
            lineString = gen.getLineString(taker.getPageWidth());
            String gap = "~\n";

            //запись
            //  System.out.printf(String.valueOf(titleString));
            //  System.out.printf(String.valueOf(lineString));
            wr.write(String.valueOf(titleString));
            wr.write(String.valueOf(lineString));
            int count = 2;

            for (int i = 0; i < parser.getListOfID().size(); i++) {
                Formatter fr = new Formatter();
                fr.format("| %-" + list.get(0).getWidthColumn() + "s | %-" + list.get(1).getWidthColumn() + "." + list.get(1).getWidthColumn() + "s | %-" + list.get(2).getWidthColumn() + "." + list.get(2).getWidthColumn() + "s |\n", parser.getListOfID().get(i), parser.getListOfDate().get(i), parser.getListOfName().get(i));
                // System.out.printf(String.valueOf(fr));
                //System.out.printf(String.valueOf(lineString));
                wr.write(String.valueOf(fr));
                wr.write(String.valueOf(lineString));
                fr.close();
                if (count == Integer.parseInt(taker.getPageHeight()) / 2) {
                    // System.out.printf(gap);
                    // System.out.printf(String.valueOf(titleString));
                    // System.out.printf(String.valueOf(lineString));
                    wr.write(gap);
                    wr.write(String.valueOf(titleString));
                    wr.write(String.valueOf(lineString));
                    count = 0;
                } else count++;
            }
        }
    }
}