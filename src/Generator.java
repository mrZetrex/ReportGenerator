import java.util.List;

public class Generator {
    private StringBuilder lineString;
    private int widthNumberColumn;
    private int widthNameColumn;
    private int widthDateColumn;

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
        return lineString;
    }

    public StringBuilder getTitleString(List<Column> list) {
        StringBuilder titleString = new StringBuilder();
        titleString.append("| " + list.get(0).getTitleColumn());
        for (int i = 0; i < widthNumberColumn - list.get(0).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }
        titleString.append(" | " + list.get(1).getTitleColumn());
        for (int i = 0; i < widthDateColumn - list.get(1).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }
        titleString.append(" | " + list.get(2).getTitleColumn());
        for (int i = 0; i < widthNameColumn - list.get(2).getTitleColumn().length(); i++) {
            titleString.append(" ");
        }

        titleString.append(" |\n");
        return(titleString);
    }


}
