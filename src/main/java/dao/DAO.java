/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.Candidate;
import common.Category;
import common.ElectionBean;
import common.ElectionResultsBean;
import common.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class DAO {

    private Connection mConnection = null;
    private final String mConnectionDatabaseName = "jdbc:mysql://127.0.0.1:3306/evot2";
    private final String mConnectionUserName = "root";
    private final String mConnectionPassword = "admin";

    public void connect() throws ClassNotFoundException, SQLException {
        if (mConnection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mConnection = DriverManager.getConnection(mConnectionDatabaseName,
                    mConnectionUserName, mConnectionPassword);
            if (mConnection != null) {
                mConnection.setAutoCommit(false);
            }
        }
    }

    public void disconnect() throws SQLException {
        if (mConnection != null) {
            mConnection.commit();
            mConnection.close();
        }
    }

    public void disconnect(boolean errState) throws SQLException {
        if (mConnection != null && errState == true) {
            mConnection.commit();
            mConnection.close();
        } else if (errState == false) {
            mConnection.rollback();
            mConnection.close();
        }
    }

    public int deleteId(int id) {
        int state = -1;
        try {
            String query = "DELETE FROM `elections` WHERE `idElections`=?";
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, id);
            state = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }

    public String getFieldFromUsers(String fieldName, String username) {
        String query = "SELECT `" + fieldName + "` FROM `users` WHERE `username`=?";
        String salt = "";
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                salt = rs.getString(fieldName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salt;
    }

    public boolean readFromUsers(UserBean user) {
        String query = "SELECT * FROM `users` WHERE `username`=? AND `password`=?";
        boolean userExists = false;
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                user.setPrivilege(rs.getShort("privilege"));
                user.setIdUser(rs.getInt("idUsers"));
                userExists = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userExists;
    }

    public ArrayList<ElectionBean> getElections() {
        String query = "SELECT * FROM `elections`";
        ArrayList<ElectionBean> electionArray = new ArrayList<>();
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ElectionBean elTemp = new ElectionBean();
                elTemp.setElectionName(rs.getString("nameElections"));
                elTemp.setIdElection(rs.getInt("idElections"));
                elTemp.setStartingDate(rs.getDate("startDate").toString());
                elTemp.setEndingDate(rs.getDate("endDate").toString());
                electionArray.add(elTemp);
            }
        } catch (SQLException ex) {
        }
        return electionArray;
    }

    public ElectionBean getSingleElection(int id) {
        String query = "SELECT * FROM `elections` WHERE `idElections`=?";
        ElectionBean election = new ElectionBean();
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                election.setElectionName(rs.getString("nameElections"));
                election.setIdElection(rs.getInt("idElections"));
                election.setStartingDate(rs.getDate("startDate").toString());
                election.setEndingDate(rs.getDate("endDate").toString());
                election.setCategory(getSingleElectionCategory(rs.getInt("idElectionType")));
                election.setCandidatesCount(getCandidatesNum(id));
            }
        } catch (SQLException ex) {
        }
        return election;
    }

    public int getCandidatesNum(int id) {
        int candidatesNum = 0;
        String query = "SELECT COUNT(*) AS candidatesNum FROM `candidates` WHERE `idElections`=?";
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                candidatesNum = rs.getInt("candidatesNum");
            }
        } catch (SQLException ex) {
        }
        return candidatesNum;
    }

    public ArrayList<Candidate> getCandidates(int id) {
        ArrayList<Candidate> candidatesArr = new ArrayList<>();
        String query = "SELECT * FROM `candidates` WHERE `idElections`=?";
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidate tempCandidate = new Candidate();
                tempCandidate.setIdCandidate(rs.getInt("idCandidates"));
                tempCandidate.setCandidateName(rs.getString("nameCandidates"));
                tempCandidate.setDescription(rs.getString("description"));
                candidatesArr.add(tempCandidate);
            }
        } catch (SQLException ex) {
        }
        return candidatesArr;
    }

    public boolean insertElection(ElectionBean election) {
        String queryInsertElection = "INSERT INTO `elections` (`nameElections`, `startDate`, `endDate`, `idElectionType`) VALUES (?, ?, ?, ?);";
        String queryGetId = "SELECT `idElections` FROM `elections` WHERE `nameElections`=? AND `startDate`=? AND `endDate`=?";
        try {
            PreparedStatement ps = mConnection.prepareStatement(queryInsertElection);
            ps.setString(1, election.getElectionName());
            ps.setDate(2, Date.valueOf(election.getStartingDate()));
            ps.setDate(3, Date.valueOf(election.getEndingDate()));
            ps.setInt(4, election.getCategory().getId());
            int state = ps.executeUpdate();
            if (state != 0) {
                PreparedStatement ps2 = mConnection.prepareStatement(queryGetId);
                ps2.setString(1, election.getElectionName());
                ps2.setDate(2, Date.valueOf(election.getStartingDate()));
                ps2.setDate(3, Date.valueOf(election.getEndingDate()));
                ResultSet rs = ps2.executeQuery();
                while (rs.next()) {
                    int index = 0;
                    election.setIdElection(rs.getInt("idElections"));
                    index++;
                    if (index != 1) {
                        mConnection.rollback();
                        return false;
                    } else {
                        return insertCandidates(election.getCandidates(), election.getIdElection());
                    }
                }
            } else {
                mConnection.rollback();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return true;
    }

    public ElectionResultsBean getElectionResults(int idElection) {
        ElectionResultsBean election = new ElectionResultsBean();
        try {
            String query = "SELECT c.idCandidates, c.idElections, c.nameCandidates, COUNT(idVotes) as vote"
                    + " FROM votes as v NATURAL RIGHT JOIN candidates as c WHERE c.idElections=? GROUP BY idCandidates";
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, idElection);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nameCandidates");
                int votes = rs.getInt("vote");
                Candidate candidateTemp = new Candidate(name, votes);
                election.addCandidate(candidateTemp);
            }
            election.setElectionName(fetchElectionName(idElection));
        } catch (SQLException ex) {
        }
        return election;
    }

    private String fetchElectionName(int idElection) {
        String query = "SELECT `nameElections` FROM `elections` WHERE `idElections`=?";
        String electionName = "";
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, idElection);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                electionName = rs.getString("nameElections");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return electionName;
    }

    public boolean insertCandidates(ArrayList<Candidate> candidateArray, int idElection) {
        String query = "INSERT INTO `candidates` (`idElections`, `nameCandidates`, `description`) VALUES (?, ?, ?);";
        String queryIdCandidate =  "INSERT INTO `candidates` (`idElections`, `nameCandidates`, `description`, `idCandidates`) VALUES (?, ?, ?, ?);";
        try {
            for (Candidate candidate : candidateArray) {
                PreparedStatement ps;
                if (candidate.getIdCandidate() == 0 ) {
                    ps = mConnection.prepareStatement(query);
                } else {
                    ps = mConnection.prepareStatement(queryIdCandidate);
                    ps.setInt(4, candidate.getIdCandidate());
                }
                ps.setInt(1, idElection);
                ps.setString(2, candidate.getCandidateName());
                ps.setString(3, candidate.getDescription());
                int state = ps.executeUpdate();
                if (state == 0) {
                    mConnection.rollback();
                    return false;
                }
            }
        } catch (SQLException ex) {
        }
        return true;
    }

    public ArrayList<Category> getElectionCategories() {
        String query = "SELECT `idElectionType`, `nameElectionType` FROM `election_types`";
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idElectionType");
                String name = rs.getString("nameElectionType");
                Category cat = new Category(id, name);
                categories.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public Category getSingleElectionCategory(int idElectionType) {
        String query = "SELECT `idElectionType`, `nameElectionType` FROM `election_types` WHERE `idElectionType`=?";
        Category category = new Category();
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, idElectionType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idReturned = rs.getInt("idElectionType");
                String name = rs.getString("nameElectionType");
                category = new Category(idReturned, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    public boolean updateElection(ElectionBean election) {
        String query = "UPDATE `elections` AS e SET "
                + " e.`nameElections`=?, e.idElectionType=?, e.startDate=?, e.endDate=?"
                + " WHERE e.`idElections`=?;";

        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setString(1, election.getElectionName());
            ps.setInt(2, election.getCategory().getId());
            ps.setDate(3, Date.valueOf(election.getStartingDate()));
            ps.setDate(4, Date.valueOf(election.getEndingDate()));
            ps.setInt(5, election.getIdElection());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String queryCandidates = "UPDATE `candidates` AS c SET ";
        for (Candidate candidate : election.getCandidates()) {
            String queryUpdateCandidates = queryCandidates
                    + "c.nameCandidates=?, c.description=? WHERE c.idCandidates=? and c.idElections=?";
            try {
                PreparedStatement ps = mConnection.prepareStatement(queryUpdateCandidates);
                ps.setString(1, candidate.getCandidateName());
                ps.setString(2, candidate.getDescription());
                ps.setInt(3, candidate.getIdCandidate());
                ps.setInt(4, candidate.getIdElection());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public int deleteCandidates(int idElection) {
        String query = "DELETE FROM `candidates` WHERE `idElections`=?";
        int state = -1;
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setInt(1, idElection);
            state = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }
}
