/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import common.Candidate;
import common.Category;
import common.ElectionBean;
import common.ObjectToJson;
import common.Pair;
import common.UserBean;
import insert.InsertDataImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrei
 */
public class UpdateElectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ElectionBean election = (ElectionBean) session.getAttribute("electionObject");
        UpdateElectionImpl updateElection = new UpdateElectionImpl();
        ArrayList<Candidate> candidatesArr = updateElection.getCandidates(election.getIdElection());
        election.setCandidatesArray(candidatesArr);
        String elCategoryName = request.getParameter("listCategory");
        String elCategoryId = request.getParameter("category");
        String elName = request.getParameter("electionName");
        String candidateNumb = request.getParameter("candidatesNumber");
        String elDateStart = request.getParameter("electionDateStart");
        String elDateEnd = request.getParameter("electionDateEnd");
        int cNumb = Integer.parseInt(candidateNumb);
        election.setElectionName(elName);
        election.setEndingDate(elDateEnd);
        election.setStartingDate(elDateStart);
        election.setOldCandidatesCount(election.getCandidatesCount());
        election.setCandidatesCount(cNumb);
        Category category = new Category(Integer.parseInt(elCategoryId), elCategoryName);
        election.setCategory(category);
        while (cNumb > candidatesArr.size()) {
            Candidate newCand = new Candidate();
            election.addCandidate(newCand);
        }
        session.setAttribute("electionObject", election);
        ObjectToJson<ArrayList<Candidate>> jsonConverter = new ObjectToJson<>();
        String jsonString = jsonConverter.convert(candidatesArr);
        request.setAttribute("candidatesArrayJson", jsonString);
        request.setAttribute("candidatesNumber", cNumb);
        
        InsertDataImpl insertDataImpl = new InsertDataImpl();
        ArrayList<Pair<Integer, String>> cityArr = insertDataImpl.getCities();
        ArrayList<Pair<Integer, String>> countyArr = insertDataImpl.getCounties();
        ObjectToJson<ArrayList<Pair<Integer, String>>> converter = new ObjectToJson<>();
        String countyJson = converter.convert(countyArr);
        String cityJson = converter.convert(cityArr);
        request.setAttribute("countyJson", countyJson);
        request.setAttribute("cityJson", cityJson);

        request.getRequestDispatcher("/updateCandidates.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ElectionBean election = (ElectionBean) session.getAttribute("electionObject");
        ArrayList<Candidate> candidates = election.getCandidates();
        ArrayList<Candidate> candidatesUpdated = new ArrayList<>();
        for (int i = 0; i < election.getCandidatesCount(); i++) {
            String candidateName = request.getParameter("candidateName" + i);
            String candidateDescription = request.getParameter("candidateDescription" + i);
            Candidate tempCand = new Candidate();
            tempCand.setCandidateName(candidateName);
            tempCand.setDescription(candidateDescription);
            tempCand.setIdElection(election.getIdElection());
            tempCand.setIdCandidate(candidates.get(i).getIdCandidate());
            candidatesUpdated.add(tempCand);
        }
        UpdateElectionImpl updateElection = new UpdateElectionImpl();
        election.setCandidatesArray(candidatesUpdated);
        int sizeDif = election.getOldCandidatesCount() - election.getCandidatesCount();
        if (sizeDif == 0) {
            updateElection.update(election);
        } else {
            updateElection.updateWithInsertOrDelete(election);
        }
        session.removeAttribute("electionObject");
        request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
    }
}
