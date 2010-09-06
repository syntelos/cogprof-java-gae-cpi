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
import java.io.File;
import java.io.FileFilter;
import javax.servlet.ServletException;

/**
 * Service for /profile/* 
 */
public class ProfileServlet
    extends gap.service.Servlet
{
    private final static String TemplateFilename = "profile.html";

    private final static TemplateName JarfName = new TemplateName("viewer_jarf");

    private final static String JarfValue = cpi.Version.AppletViewer;

    private final static TemplateName InventorySf = new TemplateName("inventory_sf");
    private final static TemplateName InventorySt = new TemplateName("inventory_st");
    private final static TemplateName InventoryNf = new TemplateName("inventory_nf");
    private final static TemplateName InventoryNt = new TemplateName("inventory_nt");



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

            req.setVariable(InventorySf,dec.toStringSf());
            req.setVariable(InventorySt,dec.toStringSt());
            req.setVariable(InventoryNf,dec.toStringNf());
            req.setVariable(InventoryNt,dec.toStringNt());

            super.render(req,rep);
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
}