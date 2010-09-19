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

import gap.Request;
import gap.Response;
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

    private final static TemplateName JarfName = new TemplateName("viewer_jarf");

    private final static String JarfValue = cpi.Version.AppletViewer;

    private final static TemplateName InventorySf   = new TemplateName("inventory_sf");
    private final static TemplateName InventorySt   = new TemplateName("inventory_st");
    private final static TemplateName InventoryNf   = new TemplateName("inventory_nf");
    private final static TemplateName InventoryNt   = new TemplateName("inventory_nt");
    private final static TemplateName InventoryCode = new TemplateName("inventory_code");

    private final static TemplateName LogonEtc = new TemplateName("logonEtc");
    private final static TemplateName ThisPageUrlEncoded = new TemplateName("thisPageUrlEncoded");

    protected static void Sharing(Request req, String url)
        throws java.io.UnsupportedEncodingException
    {

        req.setVariable(LogonEtc,"div.logon.sharing.html");

        String urlEnc = java.net.URLEncoder.encode(url,"US-ASCII");

        req.addSection(LogonEtc).setVariable(ThisPageUrlEncoded,urlEnc);
    }
    /**
     * 
     */
    public static abstract class Tail 
        extends Object
    {
        public final static int None = -1;
        public final static int ImagePng = 1;
        public final static int DataJson = 2;


        public final static int For(String tail){
            if (null == tail || 0 == tail.length())
                return Tail.None;
            else {
                switch (tail.charAt(0)){
                case 'i':
                    if (tail.equals("image.png"))
                        return Tail.ImagePng;
                    else
                        return Tail.None;
                case 'd':
                    if (tail.equals("data.json"))
                        return Tail.DataJson;
                    else
                        return Tail.None;
                default:
                    return Tail.None;
                }
            }
        }
    }


    public ProfileServlet(){
        super();
    }


    private Code.Decode getCode(Request req){

        String code = req.getPath(0);
        if (null != code){
            try {
                return new Code.Decode(code);
            }
            catch (Exception any){
            }
        }
        return null;
    }
    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        Code.Decode dec = this.getCode(req);

        if (null != dec){
            switch(Tail.For(req.getPath(1))){
            case Tail.ImagePng:{

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
                return;
            }
            case Tail.DataJson:{
                java.io.PrintWriter out = rep.getWriter();

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
                return;
            }
            default:
                if (dec.old){

                    rep.sendRedirect("/profile/"+dec.code); /* (show new code in web user interface)
                                                             */
                }
                else {
                    req.setVariable(InventorySf,dec.toStringSf());
                    req.setVariable(InventorySt,dec.toStringSt());
                    req.setVariable(InventoryNf,dec.toStringNf());
                    req.setVariable(InventoryNt,dec.toStringNt());
                    req.setVariable(InventoryCode,dec.code);

                    Sharing(req,req.getRequestURL().toString());

                    super.render(req,rep);
                }
                return;
            }
        }
        else if (req.hasViewer()){

            Person viewer = req.getViewer();

            if (Inventory.IsComplete(viewer)){

                Inventory.Complete(viewer);

                Code.Encode enc = Inventory.Encode(viewer);

                rep.sendRedirect("/profile/"+enc.code);
            }
            else
                rep.sendRedirect("/inventory");
        }
        else
            rep.sendRedirect("/");
    }
    protected TemplateRenderer getTemplate(Request req)
        throws TemplateException 
    {
        req.setVariable(JarfName,JarfValue);

        return Templates.GetTemplate(TemplateFilename);
    }

    protected long getLastModified(HttpServletRequest req) {

        String tail = ((Request)req).getPath(1);
        if (null != tail && tail.equals("image.png"))
            return 1L;
        else
            return -1L;
    }

}
