import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;
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

            //запись
            wr.write(String.valueOf(titleString));
            wr.write(String.valueOf(lineString));
            Generator.incrCountLine();

            for (int i = 0; i < parser.getListOfID().size(); i++) {
                List<String> idList = parser.getListOfID();
                List<String> dateList = parser.getListOfDate();
                List<String> nameList = parser.getListOfName();
                wr.write(String.valueOf(gen.getTable(idList.get(i), dateList.get(i), nameList.get(i))));
                wr.write(String.valueOf(lineString));
                Generator.incrCountLine();
                wr.write(Generator.checkCountLine(Generator.countLine));
                if(gen.checkGap){
                    wr.write(String.valueOf(titleString));
                    wr.write(String.valueOf(lineString));
                    Generator.incrCountLine();
                    Generator.setCheckGap(false);
                }
            }
        }
    }

}
