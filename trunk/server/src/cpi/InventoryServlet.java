/*
 * Cognitive Profile Inventory Online
 * Copyright (C) 2009 John Pritchard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package cpi;

import cpi.Code;
import cpi.groups.GroupServlet;
import cpi.groups.Project;

import gap.Request;
import gap.Response;
import gap.data.List;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;
import oso.data.Person;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Service for <code>/inventory/*</code>
 * 
 * Includes <code>/inventory/example.html</code>
 */
public class InventoryServlet
    extends gap.service.Servlet
{
    private final static String TemplateFilename = "inventory.html";

    private final static TemplateName DivInventory = new TemplateName("div.inventory.html");
    private final static TemplateName DivLogon = new TemplateName("div.logon.html");
    private final static TemplateName DivCopyright = new TemplateName("div.copyright.html");
    private final static TemplateName InventoryCurrent = new TemplateName("inventory_current");
    private final static TemplateName InventoryLhs = new TemplateName("inventory_lhs");
    private final static TemplateName InventoryRhs = new TemplateName("inventory_rhs");

    private final static TemplateName WithoutRedirect = new TemplateName("without_redirect");


    public final static TemplateName ExampleTarget = new TemplateName("example_target");

    final static class Pair {

        public final String lhs, rhs;

        Pair(String line){
            super();
            if (null != line && 0 < line.length() && '#' != line.charAt(0)){
                int idx = line.indexOf(':');
                if (0 < idx){
                    this.lhs = line.substring(0,idx);
                    this.rhs = line.substring(idx+1);
                    return;
                }
            }
            throw new IllegalArgumentException();
        }
    }
    final static class Set 
        extends java.util.ArrayList<Pair>
    {

        Set(){
            this("inventory.txt","US-ASCII");
        }
        protected Set(String name, String charset){
            super(60);
            try {
                java.io.InputStream in = this.getClass().getResourceAsStream(name);
                if (null != in){
                    try {
                        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(in,charset));
                        String line;
                        while (null != (line = reader.readLine())){
                            try {
                                Pair p = new Pair(line);
                                this.add(p);
                            }
                            catch (IllegalArgumentException skip){
                            }
                        }
                    }
                    finally {
                        in.close();
                    }
                }
                else
                    throw new Error("Resource not found");
            }
            catch (IOException exc){
                throw new Error("Resource error",exc);
            }
        }
    }

    private final static Set EN_US = new Set();
    static {
        if (Inventory.Size != EN_US.size())
            throw new Error("Inventory Incomplete");
    }

    protected final static Set Examples = new Set("examples.txt","US-ASCII");


    public InventoryServlet(){
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        if (req.isSourceTail()){

            final String source = req.getSource();
            switch (Tail.For(source)){
            case Tail.IndexHtml:
                if (req.isMember){
                    /*
                     * Request to /inventory/index.html with login
                     */
                    this.doGet(req,rep,req.getViewer());
                }
                else {
                    /*
                     * Request to /inventory/index.html without login
                     */
                    rep.sendRedirect(req.getLogonUrl());
                }
                return;
            case Tail.ExampleHtml:{

                String target = req.getParameter("target");
                if (null != target){
                    req.setVariable(InventoryServlet.ExampleTarget,target);
                }
                else {
                    target = req.getParameter("ir");
                    if (null != target){
                        req.setVariable(InventoryServlet.ExampleTarget,target);
                    }
                }
                {
                    InventoryServlet.Pair pair = InventoryServlet.Examples.get(0);

                    InventoryServlet.DefineInventory(req,0,pair,null);
                }
                this.render(req,rep,"example.html");

                return;
            }
            case Tail.None:{

                Person person = Person.ForLongIdentifier(source);
                if (null != person){
                    /*
                     * Person identifier in /inventory/identifier
                     * Redirect to /inventory/identifier/index.html
                     */
                    rep.sendRedirect("/inventory/"+person.getIdentifier()+"/groups.html");
                }
                else
                    rep.sendRedirect("/inventory/index.html");

                return;
            }
            default:
                rep.sendRedirect("/inventory/index.html");
                return;
            }
        }
        else if (req.isGroupTail()){

            final String source = req.getSource();

            switch(Tail.For(req.getGroup())){

            case Tail.GroupsHtml:{

                Person person = Person.ForLongIdentifier(source);
                if (null != person){
                    /*
                     * Person identifier in /inventory/identifier/index.html
                     */
                    this.doGet(req,rep,person);
                }
                else
                    this.error(req,rep,404,"Not found");

                return;
            }
            case Tail.IndexHtml:
                /*
                 * Login at /inventory/index.html
                 */
                if (req.isMember){

                    Person viewer = req.getViewer();

                    this.doGet(req,rep,viewer);
                }
                else {
                    rep.sendRedirect(req.getLogonUrl());
                }
                return;
            default:{
                Person person = Person.ForLongIdentifier(source);
                if (null != person){
                    rep.sendRedirect("/inventory/"+person.getIdentifier()+"/groups.html");
                }
                else {
                    rep.sendRedirect("/inventory/index.html");
                }
                return;
            }
            }
        }
        else 
            rep.sendRedirect("/inventory/index.html");
    }
    protected TemplateRenderer getTemplate(Request req)
        throws TemplateException 
    {


        return Templates.GetTemplate(TemplateFilename);
    }

    protected void doGet(Request req, Response rep, Person viewer)
        throws ServletException, IOException
    {

        if (Inventory.IsComplete(viewer)){

            Inventory.Complete(viewer);

            if (viewer.hasProject(true)){

                final Project project = viewer.getProject(true);

                final Redirect redirect = project.getCreateRedirect();

                if (redirect.isSequenceInject()){
                    try {
                        rep.sendRedirect(redirect.toString(viewer));
                    }
                    catch (hapax.TemplateException exc){

                        this.error(req,rep,500,String.format("Redirect template '%s'",redirect.href),exc);
                    }
                }
                else {

                    rep.sendRedirect("/profile/"+viewer.getIdentifier()+"/groups.html");
                }
            }
            else {

                final Code.Encode enc = Inventory.Encode(viewer);

                rep.sendRedirect("/profile/"+enc.code+"/index.html");
            }
        }
        else {
            String id_s = req.getParameter("id");
            String ir_s = req.getParameter("ir");

            if (null != id_s && null != ir_s){
                try {
                    int id = Integer.parseInt(id_s);
                    Inventory ir = Inventory.valueOf(ir_s);
                    {
                        List.Primitive<Inventory> inventory = viewer.getInventory();
                        if (id < inventory.size())
                            inventory.set(id,ir);
                        else if (id == inventory.size())
                            inventory.add(ir);
                        else {
                            this.error(req,rep,500,"Input error (bad ID "+id+" != "+inventory.size()+")");
                        }
                    }
                    int next = (id+1);
                    
                    if (next >= Inventory.Size){

                        Inventory.Complete(viewer);

                        if (viewer.hasProject(true)){

                            rep.sendRedirect("/profile/"+viewer.getIdentifier()+"/groups.html");
                        }
                        else {
                            Code.Encode enc = Inventory.Encode(viewer);

                            rep.sendRedirect("/profile/"+enc.code+"/index.html");
                        }
                    }
                    else {
                        viewer.store();

                        Pair pair = EN_US.get(next);
                        if (null != pair){

                            DefineInventory(req,next,pair,viewer);

                            super.render(req,rep);
                        }
                        else if (-1 < id && id < Inventory.Size)
                            this.error(req,rep,500,"Configuration error in inventory set");
                        else
                            this.error(req,rep,500,"Input error (bad ID "+id+')');
                    }
                }
                catch (Exception any){
                    this.error(req,rep,500,"Input error",any);
                }
            }
            else if (null != id_s){
                try {
                    int id = Integer.parseInt(id_s);
                    
                    Pair pair = EN_US.get(id);
                    if (null != pair){

                        DefineInventory(req,id,pair,viewer);

                        super.render(req,rep);
                    }
                    else if (-1 < id && id < Inventory.Size)
                        this.error(req,rep,500,"Configuration error in inventory set");
                    else
                        this.error(req,rep,500,"Input error (bad ID "+id+')');
                }
                catch (Exception any){
                    this.error(req,rep,500,"Input error",any);
                }
            }
            else if (viewer.isNotEmptyInventory()){

                int next = viewer.getInventory().size();

                if (next >= Inventory.Size){
                    Inventory.Complete(viewer);

                    if (viewer.hasProject(true)){

                        rep.sendRedirect("/profile/"+viewer.getIdentifier()+"/groups.html");
                    }
                    else {
                        Code.Encode enc = Inventory.Encode(viewer);

                        rep.sendRedirect("/profile/"+enc.code+"/index.html");
                    }
                }
                else {
                    Pair pair = EN_US.get(next);
                    if (null != pair){

                        DefineInventory(req,next,pair,viewer);

                        super.render(req,rep);
                    }
                    else
                        this.error(req,rep,500,"Server error (ID[n] "+next+')');
                }
            }
            else {
                
                Pair pair = EN_US.get(0);
                if (null != pair){

                    DefineInventory(req,0,pair,viewer);

                    super.render(req,rep);
                }
                else
                    this.error(req,rep,500,"Configuration error in inventory set");
            }
        }
    }

    protected static TemplateDataDictionary DefineInventory(Request req, int id, Pair pair, Person viewer){

        TemplateDataDictionary div = req.addSection(DivInventory);
        {
            div.setVariable(InventoryCurrent,String.valueOf(id));
            div.setVariable(InventoryLhs,pair.lhs);
            div.setVariable(InventoryRhs,pair.rhs);
        }
        /*
         * viewer is null from (SiteServlet) "example.html"
         */
        if (null != viewer && viewer.hasProject(true)){

            req.setVariable(DivLogon,"div.logon.group.html"); /* collapse div.logon */
            req.setVariable(DivCopyright,"div.copyright.group.html"); /* collapse div.copyright */

            Project project = viewer.getProject(true);

            Margins margins = project.getCreateMargins();
            /*
             * {{#group}}
             *  css/body/margin :  {{=margins_css}}
             * {{/group}}
             */
            margins.dictionaryInto(req.addSection(GroupServlet.EditGroup));
            /*
             * {{#without_redirect}}
             * {{/without_redirect}}
             */
            req.showSection(InventoryServlet.WithoutRedirect);
        }
        return div;
    }
}
