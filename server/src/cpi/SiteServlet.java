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

/**
 * Service for /* 
 * 
 * Includes 
 * <pre>
 *   /example.html?target={L4,L3,L2,L1,R1,R2,R3,R4} (one of)
 * </pre>
 */
public class SiteServlet
    extends gap.service.Servlet
{



    public SiteServlet(){
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        String path = req.getPath(0);

        switch(Tail.For(req.getSource())){

        case Tail.LogonJson:
            {
                rep.setContentTypeJson();

                final java.io.PrintWriter out = rep.getWriter();

                out.println("{");
                out.printf("  \"interface\": \"logon\",%n");
                out.printf("  \"url\": \"%s\",%n",Replace(req.logonUrl,"%2Flogon.json","%2Finventory%2Findex.html"));
                out.printf("  \"text\": \"%s\"%n",req.logonText);
                out.println("}");
            }
            break;

        default:
            {
                if (req.isMember){
                    /*
                     * Page template "/index.html" logon redirect
                     */
                    Person viewer = req.getViewer();

                    if (Inventory.IsComplete(viewer)){

                        Inventory.Complete(viewer);

                        Code.Encode enc = Inventory.Encode(viewer);

                        rep.sendRedirect("/profile/"+enc.code+"/index.html");
                    }
                    else {

                        rep.sendRedirect("/inventory/index.html");
                    }
                }
                else {
                    /*
                     * Redirect "/" to "/index.html" and render
                     */
                    super.doGet(req,rep);
                }
            }
            break;
        }
    }

    private final static String Replace(String string, String from, String to){

        final int from_x = string.indexOf(from);

        if (-1 < from_x){

            StringBuilder re = new StringBuilder();
            {
                if (0 < from_x){
                    re.append(string.substring(0,from_x));
                }
                re.append(to);

                final int to_x = (from_x + from.length());

                if (to_x < string.length()){
                    re.append(string.substring(to_x));
                }
            }
            return re.toString();
        }
        else {
            return string;
        }
    }
}
