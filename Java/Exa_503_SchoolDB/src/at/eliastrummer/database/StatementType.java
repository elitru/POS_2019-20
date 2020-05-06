package at.eliastrummer.database;

public enum StatementType {
    INSERT_STUDENT(SQLStatements.INSERT_STUDENT),
    INSERT_CLASS(SQLStatements.INSERT_CLASS);
    
    private String statement;

    private StatementType(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
