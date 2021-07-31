/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import common.ElectionBean;
import common.UserBean;
import common.Category;
import common.Pair;

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
public class CreateElectionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ElectionBean election = new ElectionBean();
        session.removeAttribute("electionObject");
        boolean formCompletionFlag = true;
        // get election category
        String electionCategory = request.getParameter("category");
        if (electionCategory == null || electionCategory.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionCategoryNull", "Please provide an election name");
        } else {
            Category category = new Category(Integer.parseInt(electionCategory));
            // get judet and localitate names
            switch (electionCategory) {
                case "1": // Nationala
                    election.isNational = true;
                    break;
                case "3": // Locala
                    String localitate = request.getParameter("localitate");
                    if (localitate == null || localitate.isEmpty()) {
                        formCompletionFlag = false;
                        request.setAttribute("localitateNull", "Please provide an localitate name");
                    } else {
                        ArrayList<Pair<Integer, String>> cityArr = (ArrayList<Pair<Integer, String>>) session.getAttribute("cityArr");
                        for (Pair<Integer, String> city : cityArr) {
                            if (city.second.equals(localitate)) {
                                category.setCity(city);
                                break;
                            }
                        }
                        election.isLocal = true;
                    }
                case "2": // Judeteana
                    String judet = request.getParameter("judet");

                    if (judet == null || judet.isEmpty()) {
                        formCompletionFlag = false;
                        request.setAttribute("judetNull", "Please provide an judet name");
                    } else {
                        ArrayList<Pair<Integer, String>> countyArr = (ArrayList<Pair<Integer, String>>) session.getAttribute("countyArr");
                        for (Pair<Integer, String> county : countyArr) {
                            if (county.second.equals(judet)) {
                                category.setCounty(county);
                                break;
                            }
                        }
                        election.setJudet(judet);
                        if (election.isLocal = false) {
                            election.isCounty = true;

                        }
                    }
                    break;
                default:
                    formCompletionFlag = false;
                    assert (false);
            }
            election.setCategory(category);
        }

        // get election name
        String electionName = request.getParameter("electionName");
        if (electionName == null || electionName.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionNameNull", "Please provide an election name");
        } else {
            election.setElectionName(electionName);
        }
        // get election parties
        String partiesCountStr = request.getParameter("candidatesNumber");
        if (partiesCountStr == null || partiesCountStr.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("candidatesNumberNull", "Please provide the number of candidates");
        } else {
            partiesCountStr = partiesCountStr.trim();
            if (!partiesCountStr.matches("[0-9]+")) {
                formCompletionFlag = false;
                request.setAttribute("candidatesNumberNull", "Please introduce a number");
            }
            int partiesCount = Integer.parseInt(partiesCountStr);
            request.setAttribute("candidatesNumber", partiesCount);
            election.setCandidatesCount(partiesCount);
        }
        // get election starting date
        String electionDateStart = request.getParameter("electionDateStart");
        if (electionDateStart == null || electionDateStart.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionDateStartNull", "Please provide a starting date");
        } else {
            election.setStartingDate(electionDateStart);
        }
        // get election ending date
        String electionEndStart = request.getParameter("electionDateEnd");
        if (electionEndStart == null || electionEndStart.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionDateStartNull", "Please provide a electionEndStart date");
        } else {
            election.setEndingDate(electionEndStart);
        }
        // check if all the fields have been succesfully completed
        if (formCompletionFlag == false) {
            request.getRequestDispatcher("/createElection.jsp").forward(request, response);
        }
        session.setAttribute("electionObject", election);
        request.getRequestDispatcher("/insertCandidates.jsp").forward(request, response);
    }
}
