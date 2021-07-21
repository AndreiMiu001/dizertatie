/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import common.Category;
import common.ElectionBean;
import common.ObjectToJson;
import common.UserBean;
import insert.InsertDataImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import view.ViewElectionsImpl;

/**
 *
 * @author Andrei
 */
public class UpdateElectionViewListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ViewElectionsImpl viewImpl = new ViewElectionsImpl();
        ArrayList<ElectionBean> electionsArray = viewImpl.getAllElections();
        ObjectToJson<ArrayList<ElectionBean>> jsonConverter = new ObjectToJson<>();
        String electionsArrayJson = jsonConverter.convert(electionsArray);
        request.setAttribute("electionsArrayJson", electionsArrayJson);
        request.getRequestDispatcher("/updateElectionsViewList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        UpdateElectionImpl updateImpl = new UpdateElectionImpl();
        int idElection = Integer.parseInt(request.getParameter("hiddenButton"));
        ////
        ElectionBean election = updateImpl.getElectionForUpdate(idElection);
        session.removeAttribute("electionObject");
        // get election category
        InsertDataImpl insertDataImpl = new InsertDataImpl();
        ArrayList<Category> electionCategoryArray = insertDataImpl.getElectionCategories();
        Collections.reverse(electionCategoryArray);
        request.setAttribute("listCategory", electionCategoryArray);
        request.setAttribute("categoryId", election.getCategory().getId());
        request.setAttribute("electionName", election.getElectionName());
        request.setAttribute("candidatesNumber", election.getCandidatesCount());
        request.setAttribute("electionDateStart", election.getStartingDate().toString().replace('-', '/'));
        request.setAttribute("electionDateEnd", election.getEndingDate().toString().replace('-', '/'));
        session.setAttribute("electionObject", election);
        ////
        request.getRequestDispatcher("/updateElection.jsp").forward(request, response);

    }
}
