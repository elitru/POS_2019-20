package at.eliastrummer.bl;


public abstract class TableDisplayObject {
    abstract public String getValueForColumn(int columnIndex);
    abstract public boolean setValueForColumn(int columnIndex, Object value);
}
