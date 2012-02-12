
package cpi.groups;

import cpi.Redirect;
import cpi.Margins;
import cpi.Tail;

import gap.Request;
import gap.Response;
import gap.Strings;
import gap.data.List;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;
import gap.util.Page;

import json.Json;

import oso.data.Person;

import com.google.appengine.api.datastore.Key;

import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Date;


/**
 * Bound to "/notes/"
 *
 * @see Note
 */
public final class NoteServlet
    extends gap.service.Servlet
{
    final static TemplateName NoteDiv = new TemplateName("div.note.html");
    final static TemplateName ListNote = new TemplateName("note");
    final static TemplateName EditNote = new TemplateName("note");
    final static TemplateName NoteWriter = new TemplateName("writer");
    final static TemplateName NoteCreated = new TemplateName("created");
    final static TemplateName PageParameters = new TemplateName("page");
    final static TemplateName PageTitle = new TemplateName("page_title");
    final static TemplateName TextShort = new TemplateName("text_short");

    public enum Op {
        Save, List, Account;

        public static Op For(Request q){
            String string = q.getParameter("op");
            if (null != string)
                return Op.valueOf(string);
            else
                return null;
        }
    }


    public NoteServlet() {
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){
                String pageTitle;
                final String accountIdentifier = AccountIdentifier(req);
                final String noteIdentifier = NoteIdentifier(req);
                if (null != accountIdentifier && null != noteIdentifier){

                    final Key accountKey = Account.KeyLongIdFor(accountIdentifier);
                    /*
                     * Admin Read
                     */
                    req.setVariable(NoteDiv,"div.note.admin.html");

                    Note note = Note.ForShortIdentifier(accountKey,noteIdentifier);

                    if (null != note){

                        Account account = note.getParent();
                        Project project = account.getProject();

                        if (null != project)
                            pageTitle = String.format("Account '%.2f' Project '%s' Note",account.getAmount(),project.getName());
                        else
                            pageTitle = String.format("Account '%.2f' Note",account.getAmount());


                        req.addSection(EditNote,note);
                        req.addSection(AccountServlet.EditAccount,account);

                        note.setVariable(TextShort,note.getTextShort());
                    }
                    else {
                        pageTitle = "Note";

                        req.showSection(EditNote);
                    }
                }
                else if (null != accountIdentifier){
                    /*
                     * Admin List Account Notes
                     */
                    final Account account = Account.ForLongIdentifier(accountIdentifier);

                    if (null != account){
                        req.addSection(AccountServlet.ListAccount,account);

                        pageTitle = String.format("Account '%s' Notes",account.getAmount());

                        req.setVariable(NoteDiv,"div.note.admin-list.html");
                        for (Note note : Note.ListPage(account,req.parameters.page)){

                            req.addSection(ListNote,note);
                            note.setVariable(TextShort,note.getTextShort());
                        }
                    }
                    else {
                        req.setVariable(NoteDiv,"div.note.admin-list.html");

                        pageTitle = "Notes";
                        req.showSection(ListNote);
                        req.showSection(AccountServlet.ListAccount);
                    }
                }
                else {
                    req.setVariable(NoteDiv,"div.note.admin-list.html");

                    pageTitle = "Notes";
                    req.showSection(ListNote);
                    req.showSection(AccountServlet.ListAccount);
                }

                TemplateDataDictionary page = req.addSection(PageParameters);
                req.parameters.page.dictionaryInto(page);

                req.setVariable(PageTitle,pageTitle);
                /*
                 */
                rep.setContentTypeHtml();

                this.render(req,rep,"note.html");
                return;
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }

        case Tail.None:
            rep.sendRedirect("/notes/index.html");
            return;
        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }
    protected void doPost(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){
                final Op op = Op.For(req);
                if (null != op){
                    switch(op){
                    case Save:{
                        /*
                         * Admin Update
                         */
                        String accountIdentifier = AccountIdentifier(req);
                        String noteIdentifier = NoteIdentifier(req);
                        if (null != accountIdentifier && null != noteIdentifier){

                            Key accountKey = Account.KeyLongIdFor(accountIdentifier);

                            Note note = Note.ForShortIdentifier(accountKey,noteIdentifier);
                            if (null != note && note.mayUpdate(req)){

                                note.updateFrom(req);

                                if (note.isDirty()){

                                    note.setUpdated(new Date());

                                    note.save();
                                }

                                rep.sendRedirect("/notes/"+note.getIdentifier()+"/index.html?account="+accountIdentifier);
                            }
                            else
                                this.error(req,rep,404,"Not found");

                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifiers");
                            return;
                        }
                    }
                    case List:{
                        String pageTitle;

                        String accountIdentifier = AccountIdentifier(req);
                        if (null != accountIdentifier){
                            final Account account = Account.ForLongIdentifier(accountIdentifier);

                            if (null != account){
                                req.addSection(AccountServlet.ListAccount,account);

                                pageTitle = String.format("Account '%s' Notes",account.getAmount());
                                /*
                                 * Admin List
                                 */
                                for (Note note : Note.ListPage(account,req.parameters.page)){

                                    req.addSection(ListNote,note);

                                    note.setVariable(TextShort,note.getTextShort());
                                }
                            }
                            else {
                                pageTitle = "Notes";
                                req.showSection(ListNote);
                                req.showSection(AccountServlet.ListAccount);
                            }

                            req.setVariable(NoteDiv,"div.note.admin-list.html");
                            /*
                             */
                            TemplateDataDictionary page = req.addSection(PageParameters);
                            req.parameters.page.dictionaryInto(page);

                            req.setVariable(PageTitle,pageTitle);
                            /*
                             */
                            rep.setContentTypeHtml();

                            this.render(req,rep,"note.html");
                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }
                    case Account:{
                        String accountIdentifier = AccountIdentifier(req);
                        if (null != accountIdentifier){
                            /*
                             * Admin Account
                             */
                            rep.sendRedirect("/accounts/"+accountIdentifier+"/index.html");
                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }

                    default:
                        this.error(req,rep,500,String.format("Unknown op '%s'",op.name()));
                        return;
                    }
                }
                else {
                    this.error(req,rep,400,"Missing op");
                    return;
                }
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }
            
        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }

    public final static String NoteIdentifier(Request req){
        String identifier = req.getParameter("identifier");
        if (null != identifier)
            return identifier;
        else if (req.isSourceTail())
            return null;
        else
            return req.getSource();
    }
    public final static String AccountIdentifier(Request req){
        String identifier = req.getParameter("account_identifier");
        if (null == identifier)
            return req.getParameter("account");
        else
            return identifier;
    }
}
