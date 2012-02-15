
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

/**
 * Generated once.  This source file will not be overwritten
 * unless deleted, so it can be edited.
 *
 * @see Project
 */
public final class ProjectServlet
    extends gap.service.Servlet
{
    final static TemplateName ProjectDiv = new TemplateName("div.project.html");
    final static TemplateName ListProject = new TemplateName("project");
    final static TemplateName EditProject = new TemplateName("project");
    final static TemplateName PageParameters = new TemplateName("page");
    final static TemplateName PageTitle = new TemplateName("page_title");

    final static TemplateName CountReadonly = new TemplateName("count_readonly");

    final static String Readonly = "readonly";

    final static TemplateName CountTabindex = new TemplateName("count_tabindex");

    final static String Tabindex2 = "tabindex=\"2\"";


    public enum Op {
        Update, Create, List, Delete, Accounts;

        public static Op For(Request q){
            String string = q.getParameter("op");
            if (null != string)
                return Op.valueOf(string);
            else
                return null;
        }
    }


    public ProjectServlet() {
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        final Person viewer = req.getViewer();

        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){

                final String projectIdentifier = ProjectIdentifier(req);
                /*
                 * Admin Read
                 */
                req.setVariable(ProjectDiv,"div.project.admin.html");

                Project project;
                String pageTitle;

                if (null != projectIdentifier){
                    project = Project.ForLongIdentifier(projectIdentifier);

                    if (null != project){

                        final Group group = project.getGroup();

                        pageTitle = String.format("Group '%s' Project '%s'",group.getName(),project.getName());

                        req.addSection(EditProject,project);

                        Redirect redirect = project.getCreateRedirect();
                        redirect.dictionaryInto(project);

                        Margins margins = project.getCreateMargins();
                        margins.dictionaryInto(project);

                        if (project.isDirty())
                            project.save();

                        project.setVariable(CountReadonly,Readonly);
                    }
                    else {
                        pageTitle = "Group Project";

                        TemplateDataDictionary show = req.showSection(EditProject).get(0);
                        new Redirect().dictionaryInto(show);
                        new Margins().dictionaryInto(show);
                        show.setVariable(CountTabindex,Tabindex2);
                    }
                }
                else {
                    pageTitle = "Group Project";

                    TemplateDataDictionary show = req.showSection(EditProject).get(0);
                    new Redirect().dictionaryInto(show);
                    new Margins().dictionaryInto(show);
                    show.setVariable(CountTabindex,Tabindex2);
                }

                req.setVariable(PageTitle,pageTitle);

                rep.setContentTypeHtml();

                this.render(req,rep,"project.html");
                return;
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }

        case Tail.DataJson:

            final String projectIdentifier = ProjectIdentifier(req);
            if (null != projectIdentifier){

                if (null != viewer){
                    /*
                     * User Read
                     */
                    Project project = Project.ForLongIdentifier(projectIdentifier);

                    if (null != project && project.hasProjectAccess(viewer)){

                        rep.setContentTypeJson();

                        rep.println(project.toJson().toString());

                        this.ok(req,rep);

                        return;
                    }
                    /*
                     * 404
                     */
                }
                else if (req.isOAuth){

                    final Group group = Group.For(viewer);
                    if (null != group){
                        /*
                         * User create
                         */
                        final Project project = new Project(req);

                        if (project.hasCount()){

                            final int count = project.getCount();

                            if (0 < count){

                                project.setGroup(group);

                                project.save();

                                Account.Billing.NewProject(group,project,req);

                                for (int cc = 0; cc < count; cc++){

                                    new Member(project);
                                }

                                rep.setContentTypeJson();

                                rep.println(project.toJson().toString());

                                this.ok(req,rep);
                            }
                            else 
                                this.error(req,rep,400,"Bad parameter named count");
                        }
                        else 
                            this.error(req,rep,400,"Missing parameter named count");
                    }
                    else 
                        this.error(req,rep,401,"Viewer not group admin");
                }
            }
            this.error(req,rep,404,"Not found");
            return;
        case Tail.None:
            rep.sendRedirect("/projects/index.html");
            return;
        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }
    protected void doPost(Request req, Response rep)
        throws ServletException, IOException
    {
        final Person viewer = req.getViewer();

        switch(Tail.For(req.getTail())){
        case Tail.IndexHtml:
            if (req.isAdmin){
                final Op op = Op.For(req);
                if (null != op){
                    switch(op){
                    case Update:{
                        String projectIdentifier = ProjectIdentifier(req);
                        if (null != projectIdentifier){
                            /*
                             * Admin Update
                             */
                            Project project = Project.ForLongIdentifier(projectIdentifier);
                            if (null != project){
                                project.updateFrom(req);

                                req.addSection(EditProject,project);

                                Redirect redirect = project.getRedirect();
                                if (null != redirect)
                                    redirect.dictionaryInto(project);

                                Margins margins = project.getMargins();
                                if (null != margins)
                                    margins.dictionaryInto(project);

                                if (project.isDirty())
                                    project.save();

                                rep.sendRedirect("/projects/"+project.getIdentifier()+"/index.html");
                            }
                            else
                                this.error(req,rep,404,"Not found");

                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing project identifier");
                            return;
                        }
                    }
                    case Create:{
                        /*
                         * Admin Create
                         */
                        final Group group = Group.For(viewer);
                        if (null != group){

                            final Project project = new Project(req);

                            if (project.hasCount()){

                                final int count = project.getCount();

                                if (0 < count){

                                    project.setGroup(group);

                                    project.save();

                                    Account.Billing.NewProject(group,project,req);

                                    for (int cc = 0; cc < count; cc++){

                                        new Member(project);
                                    }

                                    rep.sendRedirect("/projects/"+project.getIdentifier()+"/index.html");
                                    return;
                                }
                                else 
                                    this.error(req,rep,400,"Bad parameter named count");
                            }
                            else
                                this.error(req,rep,400,"Missing parameter named count");
                        }
                        else
                            this.error(req,rep,400,"Missing group for viewer");
                    }
                    case List:{
                        /*
                         * Admin List
                         */
                        String groupIdentifier = GroupIdentifier(req);
                        if (null != groupIdentifier){
                            Group group = Group.ForLongIdentifier(groupIdentifier);
                            if (null != group && group.hasGroupAccess(viewer)){

                                String pageTitle = String.format("Group '%s' Projects",group.getName());

                                req.setVariable(ProjectDiv,"div.project.admin-list.html");
                                for (Project project : Project.ListPage(req.parameters.page)){

                                    req.addSection(ListProject,project);
                                }
                                TemplateDataDictionary page = req.addSection(PageParameters);
                                req.parameters.page.dictionaryInto(page);

                                rep.setContentTypeHtml();

                                req.setVariable(PageTitle,pageTitle);


                                this.render(req,rep,"project.html");
                            }
                            else
                                this.error(req,rep,404,"Not found");

                            return;
                        }
                        else {

                            this.error(req,rep,400,"Missing group identifier");
                            return;
                        }
                    }
                    case Delete:{

                        String projectIdentifier = ProjectIdentifier(req);
                        if (null != projectIdentifier){
                            /*
                             * Admin Update
                             */
                            Project project = Project.ForLongIdentifier(projectIdentifier);
                            if (null != project){

                                project.drop();
                            }
                            rep.sendRedirect("/groups/index.html");

                            return;
                        }
                        else {
                            this.error(req,rep,400,"Missing project identifier");
                            return;
                        }
                    }
                    case Accounts:{
                        String projectIdentifier = ProjectIdentifier(req);
                        if (null != projectIdentifier){
                            /*
                             * Admin Accounts
                             */
                            rep.sendRedirect("/accounts/index.html?project="+projectIdentifier);
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
            if (req.isOAuth){

                String projectIdentifier = ProjectIdentifier(req);
                if (null != projectIdentifier && null != req.getParameter("delete")){

                    Project project = Project.ForLongIdentifier(projectIdentifier);

                    if (null != project && project.hasProjectAccess(viewer)){
                        project.drop();

                        rep.setContentTypeJson();

                        this.ok(req,rep);
                    }
                    else
                        this.error(req,rep,404,"Not found");
                }
                else if (req.isContentTypeJson()){

                    final Json json = req.getBodyJson();
                    /*
                     * User Update
                     */
                    if (json.isNull()){

                        this.error(req,rep,400,"Missing request entity body");
                    }
                    else {
                        projectIdentifier = (String)json.getValue("identifier");
                        if (null == projectIdentifier || 1 > projectIdentifier.length())
                            this.error(req,rep,400,"Missing request entity property named identifier");
                        else {
                            Project project = Project.ForLongIdentifier(projectIdentifier);

                            if (null != project && project.hasProjectAccess(viewer)){

                                if (null != req.getParameter("delete") || json.has("delete")){

                                    project.drop();

                                    rep.setContentTypeJson();

                                    this.ok(req,rep);
                                }
                                else if (project.fromJson(json)){

                                    project.save();

                                    rep.setContentTypeJson();

                                    rep.println(project.toJson().toString());

                                    this.ok(req,rep);
                                }
                                else {
                                    rep.setContentTypeJson();

                                    rep.println(project.toJson().toString());

                                    this.ok(req,rep);
                                }
                            }
                            else
                                this.error(req,rep,404,"Not found");
                        }
                    }
                }
                else 
                    this.error(req,rep,400,"Unrecognized request entity content type");
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

    public final static String ProjectIdentifier(Request req){
        String identifier = req.getParameter("identifier");
        if (null != identifier)
            return identifier;
        else if (req.isSourceTail())
            return null;
        else
            return req.getSource();
    }
    public final static String GroupIdentifier(Request req){
        String identifier = req.getParameter("group");
        if (null != identifier)
            return identifier;
        else 
            return req.getParameter("group_identifier");
    }
}
