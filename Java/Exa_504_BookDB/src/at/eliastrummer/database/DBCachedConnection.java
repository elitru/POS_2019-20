package at.eliastrummer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCachedConnection {

    private Queue<Statement> statementQueue = new LinkedList<>();
    private Connection connection;

    public DBCachedConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        if (!statementQueue.isEmpty()) {
            return statementQueue.poll();
        }
        return connection.createStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        statementQueue.offer(statement);
    }

    private Exception RuntimeException(String notConnected) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
