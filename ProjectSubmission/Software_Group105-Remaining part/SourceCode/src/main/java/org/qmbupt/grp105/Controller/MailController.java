package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.backend.dblayer.MailManager;
import org.qmbupt.grp105.backend.model.Mail;
import java.util.ArrayList;
import static org.qmbupt.grp105.backend.dblayer.MailManager.getMailsById;

/**
 * The controller used for Mail Operations
 * @version 5.3
 * @author Jinghao Lyu
 */
public class MailController
{
    private static MailController mailController = new MailController();
    private MailController() {}
    public static MailController getController(){
        return mailController;
    }

    /**
     * Get Mails' information by User's ID
     * @param id User's ID
     * @return the searched result of a list of Mails
     */
    public ArrayList<org.qmbupt.grp105.Entity.Mail> getMailsById(String id)
    {
        ArrayList<Mail> mails = null;
        try {
            mails = MailManager.getMailsById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<org.qmbupt.grp105.Entity.Mail> res = new ArrayList<>();
        for(Mail i : mails)
        {
            res.add(i.converter());
        }
        return res;

    }

    /**
     * Write a mail with the given information
     * @param fromId the mail writer's ID
     * @param toId the mail receiver's ID
     * @param content the mail content
     */
    public void writeMail(String fromId, String toId, String content)
    {
        try
        {
            MailManager.writeMail(fromId, toId, content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
