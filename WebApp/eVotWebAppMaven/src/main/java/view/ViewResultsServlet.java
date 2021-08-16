/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Candidate;
import common.ElectionResultsBean;
import common.ObjectToJson;
import common.UserBean;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrei
 */
public class ViewResultsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ViewResultsImpl viewImpl = new ViewResultsImpl();
        int idElection = Integer.parseInt(request.getParameter("hiddenButton"));
        ElectionResultsBean electionResults = viewImpl.getVoteResults(idElection);
        Collections.sort(electionResults.getCandidates(), Collections.reverseOrder());
        DecimalFormat decFormat = new DecimalFormat("#%");
        ArrayList<String> perc = new ArrayList<>();
        ArrayList<Candidate> candidates = electionResults.getCandidates();
        int totalVotes = 0;
        for (Candidate candidate : candidates) {
            totalVotes += candidate.getVotesCount();
        }
        for (Candidate candidate : candidates) {
            perc.add(decFormat.format(((float) candidate.getVotesCount() / totalVotes)));
        }
        ObjectToJson<ElectionResultsBean> jsonConverter = new ObjectToJson<>();
        ObjectToJson<ArrayList<String>> jsonConverterString = new ObjectToJson<>();
        String percentageJson = jsonConverterString.convert(perc);
        String electionsResultsJson = jsonConverter.convert(electionResults);
        System.out.println(electionsResultsJson);
        request.setAttribute("electionsResultsJson", electionsResultsJson);
        request.setAttribute("votesPercentageJson", percentageJson);
        request.getRequestDispatcher("/viewResults.jsp").forward(request, response);
    }

}
