import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class DataParserTsvClass {
    private List<String> listOfID;
    private List<String> listOfDate;
    private List<String> listOfName;

    public void getData(File file) {
        ArrayList<String> listOfData = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_16);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                listOfData.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createDataLists();
        sortListOfData(listOfData);
    }

    public void sortListOfData(ArrayList<String> listOfData) {

        for (int i = 0; i < listOfData.size(); i++) {
            String[] strings = listOfData.get(i).split("\t");
            listOfID.add(strings[0]);
            listOfDate.add(strings[1]);
            listOfName.add(strings[2]);
        }
    }

    public void createDataLists() {
        listOfDate = new ArrayList<>();
        listOfID = new ArrayList<>();
        listOfName = new ArrayList<>();

    }

    public List<String> getListOfID() {
        return listOfID;
    }

    public List<String> getListOfDate() {
        return listOfDate;
    }

    public List<String> getListOfName() {
        return listOfName;
    }


}

