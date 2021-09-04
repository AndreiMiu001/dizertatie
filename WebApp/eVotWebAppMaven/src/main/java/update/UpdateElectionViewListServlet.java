package update;

import common.Category;
import common.ElectionBean;
import common.ObjectToJson;
import common.Pair;
import common.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateElectionViewListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        UpdateElectionImpl updateImpl = new UpdateElectionImpl();
        ArrayList<ElectionBean> electionsArray = updateImpl.getElectionsForModify();
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
        ElectionBean election = updateImpl.getSingleElectionForUpdate(idElection);
        session.removeAttribute("electionObject");
        // get election category
        request.setAttribute("categoryId", election.getCategory().getId());
        request.setAttribute("electionName", election.getElectionName());
        request.setAttribute("candidatesNumber", election.getCandidatesCount());
        request.setAttribute("electionDateStart", election.getStartingDate().toString().replace('-', '/'));
        request.setAttribute("electionDateEnd", election.getEndingDate().toString().replace('-', '/'));
        session.setAttribute("electionObject", election);

        ArrayList<Pair<Integer, String>> cityArr = (ArrayList<Pair<Integer, String>>) session.getAttribute("cityArr");
        ArrayList<Pair<Integer, String>> countyArr = (ArrayList<Pair<Integer, String>>) session.getAttribute("countyArr");
        if (election.isLocal) {
            Category category = election.getCategory();
            for (Pair<Integer, String> city : cityArr) {
                if (Objects.equals(city.first, election.getCategory().getCity().first)) {
                    request.setAttribute("cityName", city.second);
                    category.setCity(city);
                }
            }
            for (Pair<Integer, String> county : countyArr) {
                if (Objects.equals(county.first, election.getCategory().getCounty().first)) {
                    request.setAttribute("countyName", county.second);
                    category.setCounty(county);
                    break;
                }
            }
        } else if (election.isCounty) {
            Category category = election.getCategory();
            for (Pair<Integer, String> county : countyArr) {
                if (Objects.equals(county.first, election.getCategory().getCounty().first)) {
                    request.setAttribute("countyName", county.second);
                    category.setCounty(county);
                    break;
                }
            }
        }
        request.getRequestDispatcher("/updateElection.jsp").forward(request, response);
    }
}
