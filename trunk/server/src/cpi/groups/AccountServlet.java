
package cpi.groups;

import cpi.Redirect;
import cpi.Margins;
import cpi.Tail;

import gap.Request;
import gap.Response;
import gap.data.List;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;
import gap.util.Page;

import json.Json;

import oso.data.Person;

import java.io.IOException;

import javax.servlet.ServletException;

import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.oauth.OAuthService;
import com.google.appengine.api.oauth.OAuthServiceFactory;
import com.google.appengine.api.oauth.OAuthServiceFailureException;

/**
 * Generated once.  This source file will not be overwritten
 * unless deleted, so it can be edited.
 *
 * @see Account
 */
public final class AccountServlet
    extends gap.service.Servlet
{
    final static TemplateName AccountDiv = new TemplateName("div.account.html");
    final static TemplateName ListAccount = new TemplateName("account");
    final static TemplateName EditAccount = new TemplateName("account");
    final static TemplateName PageParameters = new TemplateName("page");
    final static TemplateName PageTitle = new TemplateName("page_title");
    final static TemplateName OpenLabel = new TemplateName("open_label");

    public enum Op {
        Save, List, Group, Project;

        public static Op For(Request q){
            String string = q.getParameter("op");
            if (null != string)
                return Op.valueOf(string);
            else
                return null;
        }
    }


    public AccountServlet() {
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){
                String pageTitle;

                final String identifier = AccountIdentifier(req);
                if (null != identifier){
                    /*
                     * Admin Read
                     */
                    req.setVariable(AccountDiv,"div.account.admin.html");

                    Account account = Account.ForLongIdentifier(identifier);

                    if (null != account){

                        Group group = account.getGroup();
                        Project project = account.getProject();

                        if (null != project)
                            pageTitle = String.format("Group '%s' Project '%s' Account",group.getName(),project.getName());
                        else
                            pageTitle = String.format("Group '%s' Account",group.getName());


                        req.addSection(EditAccount,account);
                    }
                    else {
                        pageTitle = "Account";

                        req.showSection(EditAccount);
                    }
                }
                else {
                    final String groupIdentifier = GroupIdentifier(req);
                    if (null != groupIdentifier){
                        /*
                         * Admin List Group Accounts
                         */
                        Group group = Group.ForLongIdentifier(groupIdentifier);

                        req.addSection(GroupServlet.ListGroup,group);

                        pageTitle = String.format("Group '%s' Account",group.getName());

                        req.setVariable(AccountDiv,"div.account.admin-list.html");
                        for (Account account : Account.ListPage(group,req.parameters.page)){

                            req.addSection(ListAccount,account);

                            Boolean closed = account.getClosed();
                            if (null != closed && closed)
                                account.setVariable(OpenLabel,"CLOSED");
                            else
                                account.setVariable(OpenLabel,"OPEN");
                        }
                        TemplateDataDictionary page = req.addSection(PageParameters);
                        req.parameters.page.dictionaryInto(page);
                    }
                    else {
                        final String projectIdentifier = ProjectIdentifier(req);
                        if (null != projectIdentifier){
                            /*
                             * Admin List Project Accounts
                             */
                            Project project = Project.ForLongIdentifier(projectIdentifier);

                            Group group = project.getGroup();

                            req.addSection(GroupServlet.ListGroup,group);

                            pageTitle = String.format("Group '%s' Project '%s' Account",group.getName(),project.getName());

                            req.setVariable(AccountDiv,"div.account.admin-list.html");
                            for (Account account : Account.ListPage(group,req.parameters.page)){

                                req.addSection(ListAccount,account);

                                Boolean closed = account.getClosed();
                                if (null != closed && closed)
                                    account.setVariable(OpenLabel,"CLOSED");
                                else
                                    account.setVariable(OpenLabel,"OPEN");
                            }
                            TemplateDataDictionary page = req.addSection(PageParameters);
                            req.parameters.page.dictionaryInto(page);
                        }
                        else {
                            pageTitle = "Accounts";
                            req.showSection(ListAccount);
                            req.showSection(GroupServlet.ListGroup);
                        }
                    }
                }
                req.setVariable(PageTitle,pageTitle);
                /*
                 */
                rep.setContentTypeHtml();

                this.render(req,rep,"account.html");
                return;
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }

        case Tail.DataJson:
            try {
                final OAuthService OAuth = OAuthServiceFactory.getOAuthService();

                final String consumer = OAuth.getOAuthConsumerKey();

                req.setVariable(AccountDiv,"div.account.user.html");

                final String identifier = AccountIdentifier(req);
                if (null != identifier){

                    Person viewer = req.getViewer();

                    if (null != viewer){
                        /*
                         * User Read
                         */
                        Account account = Account.ForLongIdentifier(identifier);

                        if (null != account && account.hasAccountAccess(viewer)){

                            req.addSection(EditAccount,account);

                            rep.setContentTypeJson();

                            rep.println(account.toJson().toString());
                        }
                        else
                            this.error(req,rep,404,"Not found");
                    }
                    else
                        this.error(req,rep,500,"Missing viewer");
                }
                else
                    this.error(req,rep,400,"Missing identifier");
            }
            catch (OAuthRequestException request){

                this.error(req,rep,401,"Access denied");
            }
            catch (OAuthServiceFailureException service){

                this.error(req,rep,500,"Access error");
            }
            return;
        case Tail.None:
            rep.sendRedirect("/accounts/index.html");
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
                        String identifier = AccountIdentifier(req);
                        if (null != identifier){
                            /*
                             * Admin Update
                             */
                            Account account = Account.ForLongIdentifier(identifier);
                            if (null != account){
                                account.updateFrom(req);

                                req.addSection(EditAccount,account);

                                if (account.isDirty())
                                    account.save();
                            }
                            req.setVariable(AccountDiv,"div.account.admin.html");
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                        break;
                    }
                    case List:{
                        /*
                         * Admin List
                         */
                        req.setVariable(AccountDiv,"div.account.admin-list.html");
                        for (Account account : Account.ListPage(req.parameters.page)){

                            req.addSection(ListAccount,account);

                        }
                        TemplateDataDictionary page = req.addSection(PageParameters);
                        req.parameters.page.dictionaryInto(page);

                        break;
                    }
                    case Group:{
                        String groupIdentifier = GroupIdentifier(req);
                        if (null != groupIdentifier){
                            /*
                             * Admin Group
                             */
                            rep.sendRedirect("/groups/"+groupIdentifier+"/index.html");
                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }
                    case Project:{
                        String projectIdentifier = ProjectIdentifier(req);
                        if (null != projectIdentifier){
                            /*
                             * Admin Project
                             */
                            rep.sendRedirect("/projects/"+projectIdentifier+"/index.html");
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

                rep.setContentTypeHtml();

                this.render(req,rep,"account.html");
                return;
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }
            
        case Tail.DataJson:
            try {
                final OAuthService OAuth = OAuthServiceFactory.getOAuthService();

                final String consumer = OAuth.getOAuthConsumerKey();

                if (req.isContentTypeJson()){

                    final Json json = req.getBodyJson();
                    /*
                     * User Update
                     */
                    if (json.isNull()){

                        this.error(req,rep,400,"Missing request entity body");
                    }
                    else {
                        String identifier = (String)json.getValue("identifier");
                        if (null == identifier || 1 > identifier.length())
                            this.error(req,rep,400,"Missing request entity property named identifier");
                        else {
                            Account account = Account.ForLongIdentifier(identifier);
                            if (null != account){
                                if (account.fromJson(json)){

                                    account.save();
                                }

                                rep.setContentTypeJson();

                                rep.println(account.toJson().toString());

                                this.ok(req,rep);
                            }
                            else
                                this.error(req,rep,404,"Not found");
                        }
                    }
                }
                else 
                    this.error(req,rep,400,"Unrecognized request entity content type");
            }
            catch (OAuthRequestException request){

                this.error(req,rep,401,"Access denied");
            }
            catch (OAuthServiceFailureException service){

                this.error(req,rep,500,"Access error");
            }
            return;
        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }

    public final static String AccountIdentifier(Request req){
        String identifier = req.getParameter("identifier");
        if (null == identifier)
            return req.getSource();
        else
            return identifier;
    }
    public final static String GroupIdentifier(Request req){
        String identifier = req.getParameter("group_identifier");
        if (null == identifier)
            return req.getParameter("group");
        else
            return identifier;
    }
    public final static String ProjectIdentifier(Request req){
        String identifier = req.getParameter("project_identifier");
        if (null == identifier)
            return req.getParameter("project");
        else
            return identifier;
    }
}
