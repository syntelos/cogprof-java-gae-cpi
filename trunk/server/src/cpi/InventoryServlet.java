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
import gap.data.List;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;
import oso.data.Person;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Service for /inventory/*
 */
public class InventoryServlet
    extends gap.service.Servlet
{
    private final static String TemplateFilename = "inventory.html";

    private final static TemplateName InventoryCurrent = new TemplateName("inventory_current");
    private final static TemplateName InventoryLhs = new TemplateName("inventory_lhs");
    private final static TemplateName InventoryRhs = new TemplateName("inventory_rhs");

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
                        if (Inventory.Size != this.size())
                            throw new IllegalArgumentException("Incomplete");
                    }
                    finally {
                        in.close();
                    }
                }
                else
                    throw new IllegalArgumentException("Resource not found");
            }
            catch (IOException exc){
                throw new IllegalArgumentException("Resource error",exc);
            }
        }
    }

    private final static Set EN_US = new Set();


    public InventoryServlet(){
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        if (req.hasViewer()){

            Person viewer = req.getViewer();

            if (Inventory.IsComplete(viewer)){

                Inventory.Complete(viewer);

                Code.Encode enc = Inventory.Encode(viewer);

                rep.sendRedirect("/profile/"+enc.code);
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
                                return;
                            }
                            viewer.store();
                        }
                        int next = (id+1);
                    
                        if (next >= Inventory.Size)
                            rep.sendRedirect("/profile");
                        else {
                            Pair pair = EN_US.get(next);
                            if (null != pair){
                                req.setVariable(InventoryCurrent,String.valueOf(next));
                                req.setVariable(InventoryLhs,pair.lhs);
                                req.setVariable(InventoryRhs,pair.rhs);

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
                else if (viewer.isNotEmptyInventory()){

                    int next = viewer.getInventory().size();

                    if (next >= Inventory.Size)
                        rep.sendRedirect("/profile");
                    else {
                        Pair pair = EN_US.get(next);
                        if (null != pair){
                            req.setVariable(InventoryCurrent,String.valueOf(next));
                            req.setVariable(InventoryLhs,pair.lhs);
                            req.setVariable(InventoryRhs,pair.rhs);

                            super.render(req,rep);
                        }
                        else
                            this.error(req,rep,500,"Server error (ID[n] "+next+')');
                    }
                }
                else {
                
                    Pair pair = EN_US.get(0);
                    if (null != pair){
                        req.setVariable(InventoryCurrent,"0");
                        req.setVariable(InventoryLhs,pair.lhs);
                        req.setVariable(InventoryRhs,pair.rhs);

                        super.render(req,rep);
                    }
                    else
                        this.error(req,rep,500,"Configuration error in inventory set");
                }
            }
        }
        else
            rep.sendRedirect(req.getLogonUrl());
    }
    protected TemplateRenderer getTemplate(Request req)
        throws TemplateException 
    {


        return Templates.GetTemplate(TemplateFilename);
    }
}
