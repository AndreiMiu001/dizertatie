package view;

import common.Candidate;
import common.ElectionBean;
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
        ElectionBean election = viewImpl.getSingleElection(idElection);
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
        request.setAttribute("electionCategory", election.getCategory().getName());
        request.setAttribute("electionCounty", election.getCategory().getCounty().second);
        request.setAttribute("electionCity", election.getCategory().getCity().second);
        request.setAttribute("startDate", election.getStartingDate().toString());
        request.setAttribute("endDate", election.getEndingDate().toString());
        request.setAttribute("electionsResultsJson", electionsResultsJson);
        request.setAttribute("votesPercentageJson", percentageJson);
        request.getRequestDispatcher("/viewResults.jsp").forward(request, response);
    }
}
