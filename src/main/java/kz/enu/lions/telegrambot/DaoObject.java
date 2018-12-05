package kz.enu.lions.telegrambot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoObject {

    public void init() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lions?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
    }

    public List<Question> getAllQuestions() {
        List<Question> resultList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getNewConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lions.quiz");


            while (rs.next()) {
                Question question = new Question();
                question.id = rs.getLong("id");
                question.status = rs.getString("status");
                question.language = rs.getString("language");
                question.subject = rs.getString("subject");
                question.name = rs.getString("question");

                Answer answer = new Answer();
                answer.name = rs.getString("a1");
                answer.right = true;
                question.answers.add(answer);

                answer = new Answer();
                answer.name = rs.getString("a2");
                answer.right = false;
                question.answers.add(answer);

                answer = new Answer();
                answer.name = rs.getString("a3");
                answer.right = false;
                question.answers.add(answer);

                answer = new Answer();
                answer.name = rs.getString("a4");
                answer.right = false;
                question.answers.add(answer);

                answer = new Answer();
                answer.name = rs.getString("a5");
                answer.right = false;
                question.answers.add(answer);
                resultList.add(question);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    public List<User> getAllUsers() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<User> resultList = new ArrayList<>();

        try {
            con = getNewConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lions.users");
            while (rs.next()) {
                User user = new User();
                user.status = rs.getString("status");
                user.language = rs.getString("language");
                user.username = rs.getString("username");
                user.location = Location.MAIN;
                resultList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    public void addNewUser(User user) {
        Statement stmt = null;
        Connection con = null;
        try {
            con = getNewConnection();
            stmt = con.createStatement();

            String sql = "INSERT INTO lions.users ("+"username,language,status"+") VALUES (" + user.username + ',' + user.language + ',' + user.status + ')';
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
