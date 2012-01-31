
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
 * @see Group
 */
public final class GroupServlet
    extends gap.service.Servlet
{
    private final static TemplateName GroupDiv = new TemplateName("div.group.html");
    private final static TemplateName ListGroup = new TemplateName("group");
    private final static TemplateName EditGroup = new TemplateName("group");
    private final static TemplateName PageParameters = new TemplateName("page");

    public enum Op {
        Save, Create, List, Delete, Accounts;

        public static Op For(Request q){
            String string = q.getParameter("op");
            if (null != string)
                return Op.valueOf(string);
            else
                return null;
        }
    }


    public GroupServlet() {
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){

                final String identifier = Identifier(req);
                if (null != identifier){
                    /*
                     * Admin Read
                     */
                    req.setVariable(GroupDiv,"div.group.admin.html");

                    Group group = Group.ForLongIdentifier(identifier);

                    if (null != group){
                        req.addSection(EditGroup,group);

                        Redirect redirect = group.getCreateRedirect();
                        redirect.dictionaryInto(group);

                        Margins margins = group.getCreateMargins();
                        margins.dictionaryInto(group);

                        if (group.isDirty())
                            group.save();
                    }
                    else {
                        TemplateDataDictionary show = req.showSection(EditGroup).get(0);
                        new Redirect().dictionaryInto(show);
                        new Margins().dictionaryInto(show);
                    }
                }
                else {
                    /*
                     * Admin List
                     */
                    req.setVariable(GroupDiv,"div.group.admin-list.html");
                    for (Group group : Group.ListPage(req.parameters.page)){

                        req.addSection(ListGroup,group);
                        Redirect redirect = group.getCreateRedirect();
                        redirect.dictionaryInto(group);

                        Margins margins = group.getCreateMargins();
                        margins.dictionaryInto(group);

                        if (group.isDirty())
                            group.save();
                    }
                    TemplateDataDictionary page = req.addSection(PageParameters);
                    req.parameters.page.dictionaryInto(page);
                }
                /*
                 */
                rep.setContentTypeHtml();

                this.render(req,rep,"group.html");
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

                req.setVariable(GroupDiv,"div.group.user.html");

                final String identifier = Identifier(req);
                if (null != identifier){

                    Person viewer = req.getViewer();

                    if (null != viewer){
                        Group group;

                        if (Tail.DataJson == Tail.For(identifier)){
                            /*
                             * User Query
                             */
                            group = Group.For(viewer);
                        }
                        else {
                            /*
                             * User Read
                             */
                            group = Group.ForLongIdentifier(identifier);
                        }
                        /*
                         * JSON
                         */
                        if (null != group && group.hasGroupAccess(viewer)){

                            req.addSection(EditGroup,group);

                            rep.setContentTypeJson();

                            rep.println(group.toJson().toString());
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
            rep.sendRedirect("/groups/index.html");
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
                        String identifier = Identifier(req);
                        if (null != identifier){
                            /*
                             * Admin Update
                             */
                            Group group = Group.ForLongIdentifier(identifier);
                            if (null != group){
                                group.updateFrom(req);

                                req.addSection(EditGroup,group);

                                Redirect redirect = group.getCreateRedirect();
                                redirect.dictionaryInto(group);

                                Margins margins = group.getCreateMargins();
                                margins.dictionaryInto(group);

                                if (group.isDirty())
                                    group.save();
                            }
                            req.setVariable(GroupDiv,"div.group.admin.html");
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                        break;
                    }
                    case Create:{
                        /*
                         * Admin Create
                         */
                        Group group = new Group(req);

                        group.save();

                        req.addSection(EditGroup,group);

                        Redirect redirect = group.getCreateRedirect();
                        redirect.dictionaryInto(group);

                        Margins margins = group.getCreateMargins();
                        margins.dictionaryInto(group);

                        req.setVariable(GroupDiv,"div.group.admin.html");

                        Account.Billing.NewGroup(group,req);

                        break;
                    }
                    case List:{
                        /*
                         * Admin List
                         */
                        req.setVariable(GroupDiv,"div.group.admin-list.html");
                        for (Group group : Group.ListPage(req.parameters.page)){

                            req.addSection(ListGroup,group);
                            Redirect redirect = group.getCreateRedirect();
                            redirect.dictionaryInto(group);

                            Margins margins = group.getCreateMargins();
                            margins.dictionaryInto(group);

                            if (group.isDirty())
                                group.save();
                        }
                        TemplateDataDictionary page = req.addSection(PageParameters);
                        req.parameters.page.dictionaryInto(page);

                        break;
                    }
                    case Delete:{

                        String identifier = Identifier(req);
                        if (null != identifier){
                            /*
                             * Admin Update
                             */
                            Group group = Group.ForLongIdentifier(identifier);
                            if (null != group){

                                for (Key k: group.listProjects())
                                    gap.data.Store.Delete(k);

                                for (Key k: group.listAccounts())
                                    gap.data.Store.Delete(k);

                                group.drop();
                            }
                            rep.sendRedirect("/groups/index.html");

                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }
                    case Accounts:{
                        String identifier = Identifier(req);
                        if (null != identifier){
                            /*
                             * Admin Accounts
                             */
                            rep.sendRedirect("/accounts/index.html?group="+identifier);
                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }
                    default:
                        throw new IllegalStateException(op.name());
                    }
                }
                else {
                    /*
                     * Admin List
                     */
                    req.setVariable(GroupDiv,"div.group.admin-list.html");
                    for (Group group : Group.ListPage(req.parameters.page)){

                        req.addSection(ListGroup,group);
                    }
                }

                rep.setContentTypeHtml();

                this.render(req,rep,"group.html");
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
                            Group group = Group.ForLongIdentifier(identifier);
                            if (null != group){
                                if (group.fromJson(json)){

                                    group.save();
                                }

                                rep.setContentTypeJson();

                                rep.println(group.toJson().toString());

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

    public final static String Identifier(Request req){
        String identifier = req.getParameter("identifier");
        if (null == identifier)
            return req.getSource();
        else
            return identifier;
    }
}
