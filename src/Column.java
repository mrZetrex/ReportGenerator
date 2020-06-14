public class Column {
    private String title, width;

    public Column(String title, String width) {
        this.title = title;
        this.width = width;
    }

    public String getTitleColumn() {
        return title;
    }

    public String getWidthColumn() {
        return width;
    }
}

class DateColumn extends Column {
    public DateColumn(String title, String width) {
        super(title, width);
    }
}

class NumberColumn extends Column {
    public NumberColumn(String title, String width) {
        super(title, width);
    }
}

class NameColumn extends Column {
    public NameColumn(String title, String width) {
        super(title, width);
    }
}
