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

    private Map<StatementType, PreparedStatement> preparedStatements = new HashMap<>();

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

    public void close() {
        statementQueue.forEach(s -> {
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCachedConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        preparedStatements.forEach((k, v) -> {
            try {
                v.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBCachedConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public PreparedStatement getPreparedStatement(StatementType type) {
        if(preparedStatements.containsKey(type)){
            return preparedStatements.get(type);
        }
        
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(type.getStatement());
        } catch (SQLException ex) {
            Logger.getLogger(DBCachedConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        preparedStatements.put(type, ps);
        return ps;
    }
}
