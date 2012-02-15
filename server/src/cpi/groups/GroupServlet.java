
package cpi.groups;

import cpi.Margins;
import cpi.ProfileLabels;
import cpi.Redirect;
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

/**
 * Generated once.  This source file will not be overwritten
 * unless deleted, so it can be edited.
 *
 * @see Group
 */
public final class GroupServlet
    extends gap.service.Servlet
{
    public final static TemplateName GroupDiv = new TemplateName("div.group.html");
    public final static TemplateName ListGroup = new TemplateName("group");
    public final static TemplateName EditGroup = new TemplateName("group");
    public final static TemplateName PageParameters = new TemplateName("page");

    public final static TemplateName TestLabel = new TemplateName("test_label");
    public final static TemplateName TestSelectTrue = new TemplateName("test_select_true");
    public final static TemplateName TestSelectFalse = new TemplateName("test_select_false");

    final static String IsTest = "TEST";
    final static String IsNotTest = "LIVE";
    final static String TestSelect = "selected";

    public enum Op {
        Update, Create, List, Delete, Accounts, Projects;

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
                /*
                 * Admin Read
                 */
                req.setVariable(GroupDiv,"div.group.admin.html");

                Group group;

                if (null != identifier){
                    group = Group.ForLongIdentifier(identifier);

                    if (null != group){
                        req.addSection(EditGroup,group);

                        Redirect redirect = group.getCreateRedirect();
                        redirect.dictionaryInto(group);

                        Margins margins = group.getCreateMargins();
                        margins.dictionaryInto(group);

                        ProfileLabels profileLabels = group.getCreateProfileLabels();
                        profileLabels.dictionaryInto(group);

                        if (group.isTest()){
                            group.setVariable(TestSelectTrue,TestSelect);
                        }
                        else {
                            group.setVariable(TestSelectFalse,TestSelect);
                        }

                        if (group.isDirty())
                            group.save();
                    }
                    else {
                        TemplateDataDictionary show = req.showSection(EditGroup).get(0);
                        new Redirect().dictionaryInto(show);
                        new Margins().dictionaryInto(show);
                        new ProfileLabels().dictionaryInto(show);
                    }
                }
                else {
                    TemplateDataDictionary show = req.showSection(EditGroup).get(0);
                    new Redirect().dictionaryInto(show);
                    new Margins().dictionaryInto(show);
                    new ProfileLabels().dictionaryInto(show);
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
            if (req.isMember){

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
                         */
                        if (null != group && group.hasGroupAccess(viewer)){

                            rep.setContentTypeJson();

                            rep.println(group.toJson().toString());

                            return;
                        }
                    }
                }
            }
            this.error(req,rep,404,"Not found");
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
                    case Update:{
                        String identifier = Identifier(req);
                        if (null != identifier){
                            /*
                             * Admin Update
                             */
                            Group group = Group.ForLongIdentifier(identifier);
                            if (null != group){

                                Boolean intest = group.getTest();

                                group.updateFrom(req);

                                Boolean cktest = group.getTest();

                                if (IsNotEqual(intest,cktest) && IsNotTrue(cktest))
                                    group.dropProjects();

                                if (group.isDirty())
                                    group.save();

                                rep.sendRedirect("/groups/"+group.getIdentifier()+"/index.html");
                            }
                            else
                                this.error(req,rep,404,"Not found");

                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing identifier");
                            return;
                        }
                    }
                    case Create:{
                        /*
                         * Admin Create
                         */
                        Group group = new Group(req);

                        group.save();

                        Account.Billing.NewGroup(group,req);

                        rep.sendRedirect("/groups/"+group.getIdentifier()+"/index.html");
                        return;
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

                            ProfileLabels profileLabels = group.getCreateProfileLabels();
                            profileLabels.dictionaryInto(group);

                            if (group.isTest()){
                                group.setVariable(TestLabel,IsTest);
                            }
                            else {
                                group.setVariable(TestLabel,IsNotTest);
                            }

                            if (group.isDirty())
                                group.save();
                        }
                        TemplateDataDictionary page = req.addSection(PageParameters);
                        req.parameters.page.dictionaryInto(page);

                        rep.setContentTypeHtml();

                        this.render(req,rep,"group.html");
                        return;
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
                    case Projects:{
                        String identifier = Identifier(req);
                        if (null != identifier){
                            /*
                             * Admin Projects
                             */
                            rep.sendRedirect("/projects/index.html?group="+identifier);
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
            
        case Tail.DataJson:

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
            else {

                this.error(req,rep,401,"Access denied");
            }
            return;
        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }

    public final static String Identifier(Request req){
        String identifier = req.getParameter("identifier");
        if (null != identifier)
            return identifier;
        else if (req.isSourceTail())
            return null;
        else
            return req.getSource();
    }
}
