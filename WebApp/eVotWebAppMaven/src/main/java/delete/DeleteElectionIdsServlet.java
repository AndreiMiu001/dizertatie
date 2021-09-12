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

public class DeleteElectionIdsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        String[] idsStr = request.getParameterValues("idCheckbox");
        String deleteErrStr = "";
        for (String idStr : idsStr) {
            int id = Integer.parseInt(idStr);
            DeleteElectionIdImpl deleteElection = new DeleteElectionIdImpl();
            if (!deleteElection.deleteId(id)) {
                deleteErrStr = "Could not delete election with id:" + id;
            } 
        }
        ObjectToJson<String> jsonConvereterStr = new ObjectToJson<>();
        request.setAttribute("deleteError", jsonConvereterStr.convert(deleteErrStr));
        UpdateElectionImpl updateImpl = new UpdateElectionImpl();
        ArrayList<ElectionBean> electionsArray = updateImpl.getElectionsForModify();
        ObjectToJson<ArrayList<ElectionBean>> jsonConvereter = new ObjectToJson<>();
        String electionsArrayJson = jsonConvereter.convert(electionsArray);
        request.setAttribute("electionsArrayJson", electionsArrayJson);
        request.getRequestDispatcher("/deleteElection.jsp").forward(request, response);
    }
}
