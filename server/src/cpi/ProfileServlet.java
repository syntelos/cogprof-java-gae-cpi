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
import cpi.groups.Group;
import cpi.groups.GroupServlet;
import cpi.groups.Project;

import gap.Request;
import gap.Response;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;

import oso.data.Person;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Service for /profile/* 
 */
public class ProfileServlet
    extends gap.service.Servlet
{
    private final static String TemplateFilename = "profile.html";

    private final static TemplateName DivLogon = new TemplateName("div.logon.html");
    private final static TemplateName DivCopyright = new TemplateName("div.copyright.html");

    private final static TemplateName InventorySf   = new TemplateName("inventory_sf");
    private final static TemplateName InventorySt   = new TemplateName("inventory_st");
    private final static TemplateName InventoryNf   = new TemplateName("inventory_nf");
    private final static TemplateName InventoryNt   = new TemplateName("inventory_nt");
    private final static TemplateName InventoryCode = new TemplateName("inventory_code");
    private final static TemplateName InventoryPng  = new TemplateName("inventory_png");

    private final static TemplateName LogonEtc = new TemplateName("logonEtc");
    private final static TemplateName ThisPageUrlEncoded = new TemplateName("thisPageUrlEncoded");

    private final static TemplateName WithRedirect = new TemplateName("with_redirect");


    public ProfileServlet(){
        super();
    }


    @Override
    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        if (req.isGroupTail()){

            switch(Tail.For(req.getGroup())){
            case Tail.ImagePng:{

                Code.Decode dec = this.getCode(req);
                if (null != dec){
                    ProfileImage image = new ProfileImage(dec);

                    final byte[] png = image.toPNG();
                    final int len = png.length;

                    if (null != req.getParameter("download"))
                        rep.setHeader("Content-Type","application/octet-stream");
                    else
                        rep.setHeader("Content-Type","image/png");

                    rep.setHeader("Content-Length",String.valueOf(len));
                    rep.setDateHeader("Last-Modified",System.currentTimeMillis());
                    OutputStream out = rep.getOutputStream();
                    out.write(png,0,len);
                    out.flush();
                }
                else
                    this.error(req,rep,404,"Not found");
                return;
            }
            case Tail.DataJson:{

                Code.Decode dec = this.getCode(req);
                if (null != dec){
                    final java.io.PrintWriter out = rep.getWriter();

                    if (null != req.getParameter("download"))
                        rep.setHeader("Content-Type","application/octet-stream");
                    else
                        rep.setHeader("Content-Type","application/json");

                    out.println("{");
                    out.printf("  \"nt\": %s,%n",dec.toStringNt());
                    out.printf("  \"nf\": %s,%n",dec.toStringNf());
                    out.printf("  \"st\": %s,%n",dec.toStringSt());
                    out.printf("  \"sf\": %s%n",dec.toStringSf());
                    out.println("}");
                }
                else
                    this.error(req,rep,404,"Not found");
                return;
            }
            case Tail.IndexHtml:{

                Code.Decode dec = this.getCode(req);
                if (null == dec)
                    this.error(req,rep,404,"Not found");

                else if (dec.old){

                    rep.sendRedirect("/profile/"+dec.code+"/index.html"); /* (show new code in web user interface)
                                                                           */
                }
                else {
                    req.setVariable(InventorySf,dec.toStringSf());
                    req.setVariable(InventorySt,dec.toStringSt());
                    req.setVariable(InventoryNf,dec.toStringNf());
                    req.setVariable(InventoryNt,dec.toStringNt());
                    req.setVariable(InventoryCode,dec.getCode());
                    req.setVariable(InventoryPng,"image.png");

                    Sharing(req,req.getRequestURL().toString());

                    super.render(req,rep);
                }
                return;
            }
            case Tail.GroupsPng:{

                final Person viewer = Person.ForLongIdentifier(req.getSource());
                if (null != viewer){

                    if (viewer.hasCodeData()){

                        final Project project = viewer.getProject();

                        final Group group = project.getGroup();

                        final ProfileLabels labels = group.getLabels();

                        final Code.Encode enc = viewer.getCodeEncode();

                        ProfileImage image = new ProfileImage(enc,labels);

                        final byte[] png = image.toPNG();
                        final int len = png.length;

                        if (null != req.getParameter("download"))
                            rep.setHeader("Content-Type","application/octet-stream");
                        else
                            rep.setHeader("Content-Type","image/png");

                        rep.setHeader("Content-Length",String.valueOf(len));
                        rep.setDateHeader("Last-Modified",System.currentTimeMillis());
                        OutputStream out = rep.getOutputStream();
                        out.write(png,0,len);
                        out.flush();
                    }
                    else
                        this.error(req,rep,404,"Unavailable");
                }
                else
                    this.error(req,rep,404,"Not found");
                return;
            }
            case Tail.GroupsHtml:{
                final Person viewer = Person.ForLongIdentifier(req.getSource());
                if (null != viewer){

                    if (viewer.hasCodeData()){
                        final Project project = viewer.getProject(true);

                        final Redirect redirect = project.getCreateRedirect();
                        final Margins margins = project.getCreateMargins();

                        Code.Encode enc = viewer.getCodeEncode();

                        req.setVariable(InventorySf,enc.toStringSf());
                        req.setVariable(InventorySt,enc.toStringSt());
                        req.setVariable(InventoryNf,enc.toStringNf());
                        req.setVariable(InventoryNt,enc.toStringNt());
                        req.setVariable(InventoryCode,viewer.getIdentifier());
                        req.setVariable(InventoryPng,"groups.png");

                        req.setVariable(DivLogon,"div.logon.group.html"); /* collapse div.logon */
                        req.setVariable(DivCopyright,"div.copyright.group.html"); /* collapse div.copyright */
                        /*
                         * {{#group}}
                         *      margin: {{=margins_css}};
                         * {{/group}}
                         */
                        TemplateDataDictionary group = req.addSection(GroupServlet.EditGroup);
                        margins.dictionaryInto(group);
                        /*
                         * {{#with_redirect}}
                         *      var timeout = {{=redirect_timeout}};
                         * {{/with_redirect}}
                         */
                        TemplateDataDictionary with_redirect = req.addSection(ProfileServlet.WithRedirect);
                        try {
                            redirect.dictionaryInto(viewer,with_redirect);

                            //this.info(req,redirect.toString());
                        }
                        catch (hapax.TemplateException exc){

                            this.error(req,rep,500,String.format("Error in redirect template '%s'",redirect.href),exc);
                        }

                        super.render(req,rep);
                    }
                    else 
                        rep.sendRedirect("/inventory/"+viewer.getIdentifier()+"/groups.html");
                }
                else
                    this.error(req,rep,404,"Not found");
                return;
            }
            default:
                this.error(req,rep,404,"Not found");
            }
        }
        else if (req.hasSource()){

            final String source = req.getSource();

            if (Tail.None == Tail.For(source)){
                final Person viewer = Person.ForLongIdentifier(source);
                if (null != viewer)
                    rep.sendRedirect("/profile/"+viewer.getIdentifier()+"/groups.html");
                else
                    rep.sendRedirect("/profile/"+source+"/index.html");

                return;
            }
            /*
             */
        }
        /*
         */
        if (req.isMember){

            Person viewer = req.getViewer();

            if (Inventory.IsComplete(viewer)){

                Inventory.Complete(viewer);

                Code.Encode enc = Inventory.Encode(viewer);

                rep.sendRedirect("/profile/"+enc.code+"/index.html");
            }
            else
                rep.sendRedirect("/inventory/index.html");
        }
        else
            rep.sendRedirect("/");
    }
    /**
     * Render template
     */
    @Override
    protected TemplateRenderer getTemplate(Request req)
        throws TemplateException 
    {


        return Templates.GetTemplate(TemplateFilename);
    }
    /**
     * Image conditional get logic operator
     */
    @Override
    protected long getLastModified(HttpServletRequest req) {

        String tail = ((Request)req).getPath(1);
        if (null != tail && tail.equals("image.png"))
            return 1L;
        else
            return -1L;
    }
    /**
     * Safe code parser
     */
    private Code.Decode getCode(Request req){

        String code = req.getSource();
        if (null != code){
            try {
                return new Code.Decode(code);
            }
            catch (RuntimeException any){
            }
        }
        return null;
    }


    protected static void Sharing(Request req, String url)
        throws java.io.UnsupportedEncodingException
    {

        req.setVariable(LogonEtc,"div.logon.sharing.html");

        String urlEnc = java.net.URLEncoder.encode(url,"US-ASCII");

        req.addSection(LogonEtc).setVariable(ThisPageUrlEncoded,urlEnc);
    }
}
