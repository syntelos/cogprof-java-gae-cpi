/*******************************************************************************
 * Copyright (C) 2012 John Pritchard, Gap Data
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package oso.data;


import gap.Request;
import gap.Response;
import gap.data.List;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Merchant editor
 *
 * @see Merchant
 */
public final class MerchantServlet
    extends gap.service.Servlet
{

    public final static TemplateName EditMerchant = new TemplateName("merchant");

    public final static TemplateName TestLabel = new TemplateName("test_label");
    public final static TemplateName TestSelectTest = new TemplateName("test_select_test");
    public final static TemplateName TestSelectLive = new TemplateName("test_select_live");
    public final static TemplateName TestSelectDiag = new TemplateName("test_select_diag");

    final static String InTest = "TEST";
    final static String InLive = "LIVE";
    final static String InDiag = "DIAG";
    final static String TestSelect = "selected";

    public enum Op {
        Update;

        public static Op For(Request q){
            String string = q.getParameter("op");
            if (null != string)
                return Op.valueOf(string);
            else
                return null;
        }
    }
    public enum Tail {
        IndexHtml, None;

        public final static Tail For(String tail){
            if (null == tail || 2 > tail.length())
                return Tail.None;
            else {
                switch (tail.charAt(0)){
                case 'i':
                    if (tail.equals("index.html"))
                        return Tail.IndexHtml;
                    else
                        return Tail.None;

                default:
                    return Tail.None;
                }
            }
        }
    }



    public MerchantServlet() {
        super();
    }


    protected void doGet(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case IndexHtml:
            if (req.isAdmin){

                final String identifier = Identifier(req);
                /*
                 * Admin Read
                 */

                Merchant merchant;

                if (null != identifier){
                    merchant = Merchant.ForLongIdentifier(identifier);

                    if (null != merchant){
                        req.addSection(EditMerchant,merchant);

                        switch(merchant.getTest()){
                        case TEST:
                            merchant.setVariable(TestSelectTest,TestSelect);
                            break;
                        case LIVE:
                            merchant.setVariable(TestSelectLive,TestSelect);
                            break;
                        case DIAG:
                            merchant.setVariable(TestSelectDiag,TestSelect);
                            break;
                        default:
                            throw new IllegalStateException(merchant.getTest().name());
                        }

                        rep.setContentTypeHtml();

                        this.render(req,rep,"merchant.admin.html");
                        return;
                    }
                    else {
                        merchant = Merchant.Instance();
                        rep.sendRedirect("/merchants/"+merchant.getIdentifier()+"/index.html");
                        return;
                    }
                }
                else {
                    merchant = Merchant.Instance();
                    rep.sendRedirect("/merchants/"+merchant.getIdentifier()+"/index.html");
                    return;
                }
            }
            else {
                this.error(req,rep,401,"Access denied");
                return;
            }


        default:
            this.error(req,rep,404,"Not found");
            return;
        }
    }
    protected void doPost(Request req, Response rep)
        throws ServletException, IOException
    {
        switch(Tail.For(req.getTail())){
        case IndexHtml:
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
                            Merchant merchant = Merchant.ForLongIdentifier(identifier);
                            if (null != merchant){

                                merchant.updateFrom(req);

                                if (merchant.isDirty())
                                    merchant.save();

                                rep.sendRedirect("/merchants/"+merchant.getIdentifier()+"/index.html");
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
