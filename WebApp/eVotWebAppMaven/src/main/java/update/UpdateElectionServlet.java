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
        boolean formCompletionFlag = true;
        // Check Category
        String electionCategoryId = request.getParameter("category");
        // get election category
        if (electionCategoryId == null || electionCategoryId.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionCategoryNull", "Please provide an election name");
        } else {
            Category category = new Category(Integer.parseInt(electionCategoryId));
            // get judet and localitate names
            election.resetCategoryState();
            switch (electionCategoryId) {
                case "1": // Nationala
                    election.isNational = true;
                    category.setCity(new Pair<>(1, "N/A"));
                    category.setCounty(new Pair<>(1, "N/A"));
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
                                request.setAttribute("cityName", city.second);
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
                                request.setAttribute("countyName", county.second);
                                category.setCounty(county);
                                break;
                            }
                        }
                        election.setJudet(judet);
                        if (election.isLocal == false) {
                            category.setCity(new Pair<>(1, ""));
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

        String electionName = request.getParameter("electionName");
        if (electionName == null || electionName.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("electionNameNull", "Please provide an election name");
            election.setElectionName(electionName);
        } else {
            election.setElectionName(electionName);
        }

        String partiesCountStr = request.getParameter("candidatesNumber");
        if (partiesCountStr == null || partiesCountStr.isEmpty()) {
            formCompletionFlag = false;
            request.setAttribute("candidatesNumberNull", "Please provide the number of candidates");
            election.setCandidatesCount(-1);
        } else {
            partiesCountStr = partiesCountStr.trim();
            if (!partiesCountStr.matches("[0-9]+")) {
                formCompletionFlag = false;
                request.setAttribute("candidatesNumberNull", "Please introduce a number");
                election.setCandidatesCount(-1);
            }
            int partiesCount = Integer.parseInt(partiesCountStr);
            request.setAttribute("candidatesNumber", partiesCount);
            election.setOldCandidatesCount(election.getCandidatesCount());
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
            request.setAttribute("electionDateEndNull", "Please provide a electionEndStart date");
        } else {
            election.setEndingDate(electionEndStart);
        }

        while (election.getCandidatesCount() > candidatesArr.size()) {
            Candidate newCand = new Candidate();
            election.addCandidate(newCand);
        }
        ArrayList<String> nameErrList = new ArrayList<>();
        for (int i = 0; i < candidatesArr.size(); i++) {
            nameErrList.add("");
        }
        ObjectToJson<ArrayList<String>> jsonConverterString = new ObjectToJson<>();
        String errListJson = jsonConverterString.convert(nameErrList);
        request.setAttribute("nameErrorList", errListJson);
        session.setAttribute("electionObject", election);
        ObjectToJson<ArrayList<Candidate>> jsonConverter = new ObjectToJson<>();
        String jsonString = jsonConverter.convert(candidatesArr);
        request.setAttribute("candidatesArrayJson", jsonString);
        request.setAttribute("candidatesNumber", election.getCandidatesCount());
        if (formCompletionFlag) { 
            request.getRequestDispatcher("/updateCandidates.jsp").forward(request, response);
        } else {
            request.setAttribute("categoryId", election.getCategory().getId());
            request.setAttribute("electionName", election.getElectionName());
            request.setAttribute("candidatesNumber", election.getCandidatesCount());
            request.setAttribute("electionDateStart", election.getStartingDate().toString().replace('-', '/'));
            request.setAttribute("electionDateEnd", election.getEndingDate().toString().replace('-', '/'));
            session.setAttribute("electionObject", election);
            request.getRequestDispatcher("/updateElection.jsp").forward(request, response);
        }
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
        boolean globalFormCompletionFlag = true;
        ArrayList<String> nameErrList = new ArrayList<>();
        for (int i = 0; i < election.getCandidatesCount(); i++) {
            String candidateName = request.getParameter("candidateName" + i);
            String candidateDescription = request.getParameter("candidateDescription" + i);
            Candidate tempCand = new Candidate();
            if (candidateName == null || candidateName.isEmpty()) {
                globalFormCompletionFlag = false;
                nameErrList.add("Please provide a name for this candidate.");
            } else {
                tempCand.setCandidateName(candidateName);
                nameErrList.add("");
            }
            if (candidateDescription == null || candidateDescription.isEmpty()) {
                candidateDescription = "No description available";
            } 
            tempCand.setDescription(candidateDescription);
            tempCand.setIdElection(election.getIdElection());
            tempCand.setIdCandidate(candidates.get(i).getIdCandidate());
            candidatesUpdated.add(tempCand);
        }
        if (!globalFormCompletionFlag) {
            session.setAttribute("electionObject", election);
            ObjectToJson<ArrayList<Candidate>> jsonConverter = new ObjectToJson<>();
            ObjectToJson<ArrayList<String>> jsonConverterString = new ObjectToJson<>();
            String errListJson = jsonConverterString.convert(nameErrList);
            request.setAttribute("nameErrorList", errListJson);
            String jsonString = jsonConverter.convert(candidatesUpdated);
            request.setAttribute("candidatesArrayJson", jsonString);
            request.setAttribute("candidatesNumber", election.getCandidatesCount());
            request.getRequestDispatcher("/updateCandidates.jsp").forward(request, response);
            return;
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
