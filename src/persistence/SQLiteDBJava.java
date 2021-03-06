package persistence;

import model.Model;

import java.io.File;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;

public class SQLiteDBJava {
    private Model model;

    public SQLiteDBJava(Model model) {
        this.model = model;
        initDB();

        if (model == null) {
            this.model = new Model();
            this.model.setInput(read());
        }
        else {
            this.model = model;
            update(model.getInput());
        }
        observeModel();
    }

    private void observeModel() {
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Model) {
                    String data = ((Model) o).getInput();
                    SQLiteDBJava.this.update(data);
                }
            }
        });
    }

    public Model getModel() {
        return model;
    }

    private Connection connect() {
        Connection conn;
        try{
            // db parameters
            String url="jdbc:sqlite:src/persistence/db/database.db";
            // create connection
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            throw new RuntimeException("Connection to db failed.");
        }
        return conn;
    }

    private void initDB() {
        File dbFile=new File("src/persistence/db/database.db");

        if(!dbFile.exists()){
            String createTable= "CREATE TABLE IF NOT EXISTS string (" +
                    "id integer PRIMARY KEY, " +
                    "currentString text);";

            Connection conn = connect();
            Statement st;
            try {
                st = conn.createStatement();
                st.executeQuery(createTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            create("test");
        }
    }

    private void create(String string) {
        String query = "INSERT INTO string (currentString) " +
                "values ('" + string + "');";

        Connection conn = connect();
        try {
            Statement st = conn.createStatement();
            st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        String query = "SELECT * FROM string";

        Connection conn = connect();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs.getString("currentString");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public void update(String newString) {
        String query = "UPDATE string SET currentString = ?;";

        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newString);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
