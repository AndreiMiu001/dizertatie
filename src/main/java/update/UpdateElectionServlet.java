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
import common.UserBean;
import java.io.IOException;
import java.util.ArrayList;
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
        election.setElectionName(elName);
        election.setEndingDate(elDateEnd);
        election.setStartingDate(elDateStart);
        election.setCandidatesCount(Integer.parseInt(candidateNumb));
        Category category = new Category(Integer.parseInt(elCategoryId), elCategoryName);
        election.setCategory(category);
        session.setAttribute("electionObject", election);
        ObjectToJson<ArrayList<Candidate>> jsonConverter = new ObjectToJson<>();
        String jsonString = jsonConverter.convert(candidatesArr);
        request.setAttribute("candidatesArrayJson", jsonString);
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
        int i = 0;
        for (Candidate candidate : candidates) {
            String candidateName = request.getParameter("candidateName" + i);
            String candidateDescription = request.getParameter("candidateDescription" + i);
            candidate.setCandidateName(candidateName);
            candidate.setDescription(candidateDescription);
            candidate.setIdElection(election.getIdElection());
            i++;
        }
        election.setCandidatesArray(candidates);
        UpdateElectionImpl updateElection = new UpdateElectionImpl();
        updateElection.update(election);
        session.removeAttribute("electionObject");
        request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
    }
}
