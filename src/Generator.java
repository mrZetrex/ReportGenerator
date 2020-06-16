import java.util.List;

public class Generator {
    private StringBuilder lineString;
    private int widthNumberColumn;
    private int widthNameColumn;
    private int widthDateColumn;
    private String aLine = "| ";
    private String bLine = " | ";
    private String cLine = " |";
    public static int countLine = 0;
    public static boolean checkGap = false;

    public Generator(StringBuilder lineString, List<Column> list) {
        this.lineString = lineString;
        widthDateColumn = Integer.parseInt(list.get(1).getWidthColumn());//7
        widthNumberColumn = Integer.parseInt(list.get(0).getWidthColumn());//8
        widthNameColumn = Integer.parseInt(list.get(2).getWidthColumn());//7
    }

    public StringBuilder getLineString(String string) {
        lineString.setLength(0);
        for (int i = 0; i < Integer.parseInt(string); i++) {
            lineString.append("-");
        }
        lineString.append("\n");
        lineString.append(checkCountLine(countLine));
        return lineString;
    }

    //строка заголовок
    public StringBuilder getTitleString(List<Column> list) {
        StringBuilder titleString = new StringBuilder();
        titleString.append(aLine + list.get(0).getTitleColumn());
        for (int i = 0; i < widthNumberColumn - list.get(0).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }
        titleString.append(bLine + list.get(1).getTitleColumn());
        for (int i = 0; i < widthDateColumn - list.get(1).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }
        titleString.append(bLine + list.get(2).getTitleColumn());
        for (int i = 0; i < widthNameColumn - list.get(2).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }
        titleString.append(" |\n");
        countLine++;
        titleString.append(checkCountLine(countLine));
        return (titleString);
    }

    public StringBuilder getTable(String id, String date, String name) {
        StringBuilder newDate = new StringBuilder(date);
        String s = "";
        //флаги если данные больше ширины столбца
        boolean dateFlag = true;
        boolean nameFlag = true;

        //отделяем год если таковой имеется
        if (date.length() > widthDateColumn) {
            newDate.delete(6, 10);
            s = date.substring(6, 10);
            dateFlag = false;
        }

        //разбиваем ФИО на фамилию, имя и отчество
        String[] mas = name.split(" |-");
        if (name.length() > widthNameColumn) {
            for (int i = 0; i < mas.length; i++) {
                if (mas[i].length() > widthNameColumn) {
                    StringBuilder newName = new StringBuilder(mas[i]);
                    newName.delete(newName.length() - (newName.length() - widthNameColumn), newName.length());
                    mas[i] = newName.toString();
                }
            }
            nameFlag = false;
        }

        StringBuilder stree = new StringBuilder();
        String standartDateLine = aLine + id + getSpace(widthNumberColumn, id) + bLine + newDate + getSpace(widthDateColumn, newDate) + bLine + mas[0] + getSpace(widthNameColumn, mas[0]) + cLine + "\n";

        if (!dateFlag) {
            if (!nameFlag) {
                stree.append(standartDateLine);
                countLine++;
                stree.append(checkCountLine(countLine));
                stree.append(aLine + getSpace(widthNumberColumn) + bLine + s + getSpace(widthDateColumn, s) + bLine + mas[1] + getSpace(widthNameColumn, mas[1]) + cLine + "\n");
                countLine++;
                stree.append(checkCountLine(countLine));
                //System.out.printf(String.valueOf(stree));
                StringBuilder stre1 = new StringBuilder();
                for (int i = 2; i < mas.length; i++) {
                    stre1.append(aLine + getSpace(widthNumberColumn) + bLine + getSpace(widthDateColumn) + bLine + mas[i] + getSpace(widthNameColumn, mas[i]) + cLine + "\n");
                    //System.out.printf(String.valueOf(stre1));
                    countLine++;
                    stree.append(checkCountLine(countLine));
                }
                stree.append(stre1);
            } else {
                stree.append(aLine + id + getSpace(widthDateColumn, id) + bLine + newDate + getSpace(widthDateColumn, newDate) + bLine + name + getSpace(widthNameColumn, name) + cLine + "\n");
                //System.out.printf(String.valueOf(stree));
                countLine++;
                stree.append(checkCountLine(countLine));
                stree.append(aLine + getSpace(widthNumberColumn) + bLine + s + getSpace(widthDateColumn, s) + bLine + getSpace(widthNameColumn) + cLine + "\n");
                countLine++;
                stree.append(checkCountLine(countLine));
            }
        } else {
            if (!nameFlag) {
                stree.append(standartDateLine);
                countLine++;
                stree.append(checkCountLine(countLine));
                stree.append(aLine + getSpace(widthNumberColumn) + bLine + s + getSpace(widthDateColumn, s) + bLine + mas[1] + getSpace(widthNameColumn, mas[1]) + cLine + "\n");
                countLine++;
                stree.append(checkCountLine(countLine));
                //System.out.printf(String.valueOf(stree));
                StringBuilder stre2 = new StringBuilder();
                for (int i = 2; i < mas.length; i++) {
                    stre2.append(aLine + getSpace(widthNumberColumn) + bLine + getSpace(widthDateColumn) + bLine + mas[i] + getSpace(widthNameColumn, mas[i]) + cLine + "\n");
                    //System.out.printf(String.valueOf(stre2));
                    countLine++;
                    stree.append(checkCountLine(countLine));
                }
                stree.append(stre2);
            } else {
                stree.append(aLine + id + getSpace(widthNumberColumn, id) + bLine + date + getSpace(widthDateColumn, date) + bLine + name + getSpace(widthNameColumn, name) + cLine + "\n");
                //System.out.printf(String.valueOf(stree));
                countLine++;
                stree.append(checkCountLine(countLine));
            }
        }
        return stree;
    }

    // высчитываем кол-во свободного пространства между конечным символом и следующим столбцом
    static StringBuilder getSpace(int x, String str) {
        StringBuilder b = new StringBuilder();
        if (x > str.length()) {
            for (int i = 0; i < x - str.length(); i++) {
                b.append(" ");
            }
        }
        return b;
    }

    static StringBuilder getSpace(int x) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < x; i++) {
            b.append(" ");
        }
        return b;
    }

    static StringBuilder getSpace(int x, StringBuilder str) {
        StringBuilder b = new StringBuilder();
        if (x > str.length()) {
            for (int i = 0; i < x - str.length(); i++) {
                b.append(" ");
            }
        }
        return b;
    }

    static int getCountLine() {
        return countLine;
    }

    static void incrCountLine() {
        countLine++;
    }

    static String checkCountLine(int count) {
        //System.out.println("Счетчик" +count);
        if (count == Integer.parseInt(TakerXmlClass.getPageHeight())) {
            countLine = 0;
            String gap = "~\n";
            checkGap = true;
            return gap ;
        }
        return ("");
    }
    static void setCheckGap(boolean off){
        checkGap = off;
    }
}
