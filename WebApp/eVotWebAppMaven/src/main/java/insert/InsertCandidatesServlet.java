package insert;

import common.Candidate;
import common.ElectionBean;
import common.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InsertCandidatesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ElectionBean election = (ElectionBean) session.getAttribute("electionObject");
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null || election == null) {
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        request.setAttribute("candidatesNumber", election.getCandidatesCount());

        boolean globalFormCompletionFlag = true;
        for (int i = 1; i <= election.getCandidatesCount(); i++) {
            boolean currentFormCompletionFlag = true;
            Candidate candidate = new Candidate();
            String candidateName = request.getParameter("candidate" + i);
            if (candidateName == null || candidateName.isEmpty()) {
                currentFormCompletionFlag = false;
                globalFormCompletionFlag = false;
                request.setAttribute("candidateNameNull" + i, "\"Please provide a name for candidate number " + i + "\"");
            } else {
                request.setAttribute("candidatesNameValue" + i, "\"" + candidateName + "\"");
            }

            String candidateDescription = request.getParameter("description" + i);
            if (candidateDescription == null || candidateDescription.isEmpty()) {
                candidateDescription = "No description available";
            } else {
                request.setAttribute("candidatesDescriptionValue" + i, "\"" + candidateDescription + "\"");
            }
            if (currentFormCompletionFlag) {
                candidate.setCandidateName(candidateName);
                candidate.setDescription(candidateDescription);
                election.addCandidate(candidate);
            }
        }
        if (globalFormCompletionFlag) {
            InsertCandidatesImpl insertImpl = new InsertCandidatesImpl();
            insertImpl.insertElection(election);
            request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
        } else {
            election.dropCandidates();
            session.setAttribute("electionObject", election);
            request.getRequestDispatcher("/insertCandidates.jsp").forward(request, response);
        }
    }
}
