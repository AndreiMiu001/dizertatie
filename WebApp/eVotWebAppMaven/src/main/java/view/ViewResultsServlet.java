/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.ElectionResultsBean;
import common.ObjectToJson;
import common.UserBean;
import java.io.IOException;
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
        ObjectToJson<ElectionResultsBean> jsonConverter = new ObjectToJson<>();
        String electionsResultsJson = jsonConverter.convert(electionResults);
        System.out.println(electionsResultsJson);
        request.setAttribute("electionsResultsJson", electionsResultsJson);
        request.getRequestDispatcher("/viewResults.jsp").forward(request, response);
    }

}
