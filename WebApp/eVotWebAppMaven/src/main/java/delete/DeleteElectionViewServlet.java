package delete;

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
import update.UpdateElectionImpl;

public class DeleteElectionViewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("response", "Called doPost from delete servlet");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        ObjectToJson<String> jsonConvereterStr = new ObjectToJson<>();
        String errStr = "";
        request.setAttribute("deleteError", jsonConvereterStr.convert(errStr));
        UpdateElectionImpl updateImpl = new UpdateElectionImpl();
        ArrayList<ElectionBean> electionsArray = updateImpl.getElectionsForModify();
        ObjectToJson<ArrayList<ElectionBean>> jsonConvereter = new ObjectToJson<>();
        String electionsArrayJson = jsonConvereter.convert(electionsArray);
        request.setAttribute("electionsArrayJson", electionsArrayJson);
        request.getRequestDispatcher("/deleteElection.jsp").forward(request, response);
    }
}
