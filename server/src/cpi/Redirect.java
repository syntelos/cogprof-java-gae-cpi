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

import gap.Request;
import gap.data.ValidationError;

import hapax.Template;
import hapax.TemplateDataDictionary;

import json.Json;

import oso.data.Person;

/**
 * 
 * @see http://code.google.com/p/cpi/wiki/API_Redirect
 */
public final class Redirect
    extends hapax.TemplateDictionary
    implements java.io.Serializable,
               json.Builder
{
    protected final static long serialVersionUID = 1;

    public final static class Names {
        public final static String Href = "redirect_href";
        public final static String Target = "redirect_target";
        public final static String Sequence = "redirect_sequence";
        public final static String Timeout = "redirect_timeout";
        public final static String SequenceSelectInject = "redirect_sequence_select_inject";
        public final static String SequenceSelectTimeout = "redirect_sequence_select_timeout";
    }
    public final static class TemplateNames {
        public final static gap.hapax.TemplateName Href = new gap.hapax.TemplateName(Redirect.Names.Href);
        public final static gap.hapax.TemplateName Target = new gap.hapax.TemplateName(Redirect.Names.Target);
        public final static gap.hapax.TemplateName Sequence = new gap.hapax.TemplateName(Redirect.Names.Sequence);
        public final static gap.hapax.TemplateName Timeout = new gap.hapax.TemplateName(Redirect.Names.Timeout);

        public final static gap.hapax.TemplateName SequenceSelectInject = new gap.hapax.TemplateName(Redirect.Names.SequenceSelectInject);
        public final static gap.hapax.TemplateName SequenceSelectTimeout = new gap.hapax.TemplateName(Redirect.Names.SequenceSelectTimeout);
    }
    public final static class Defaults {
        public final static String Href = "/profile";
        public final static String Target = null;
        public final static Redirect.Sequence Sequence = Redirect.Sequence.timeout;
        public final static long Timeout = 0L;
    }
    public final static class Data {

        public final static Long Timeout(String timeout){
            if (null != timeout && 0 < timeout.length()){
                try {
                    return new Long(Integer.decode(timeout));
                }
                catch (NumberFormatException exc){
                }
            }
            return null;
        }
    }

    public enum Sequence {
        inject, timeout;

        public final static Sequence For(String value){
            if (null == value)
                return timeout;
            else {
                try {
                    return Sequence.valueOf(value.toLowerCase());
                }
                catch (RuntimeException exc){

                    return timeout;
                }
            }
        }
    }

    /*
     * HREF Templates
     */
    public static class TemplateCache
        extends hapax.TemplateCache
    {
        private final static TemplateCache Instance = new TemplateCache();


        public TemplateCache(){
            super("no-include");
        }


        public void clear(){
            synchronized(this.cache){
                this.cache.clear();
            }
        }
        public Template getTemplate(String source)
            throws hapax.TemplateException
        {
            try {
                Template template = this.cache.get(source);
                if (null == template){

                    template = new Template(source,this);

                    synchronized(this.cache){
                        this.cache.put(source,template);
                    }
                }
                return template;
            }
            catch (OutOfMemoryError err){
                /*
                 * [TODO] longterm cache management
                 */
                this.clear();

                final Template template = new Template(source,this);

                synchronized(this.cache){
                    this.cache.put(source,template);
                }
                return template;
            }
        }
    }



    /*
     * JSON field "redirect" fragment (indent column two)
     */
    public final String href, target;

    public final Sequence sequence;

    public final long timeout;

    private transient Json json;

    private transient Template template;


    public Redirect(Request req){
        this(req.getParameter(Redirect.Names.Href),req.getParameter(Redirect.Names.Target),req.getParameter(Redirect.Names.Sequence),req.getParameter(Redirect.Names.Timeout));
    }
    public Redirect(String href, String target, String sequence, String timeout){
        this(href,target,sequence,Redirect.Data.Timeout(timeout));
    }
    public Redirect(String href, String target, String sequence, Long timeout){
        this(href,target,Sequence.For(sequence),timeout);
    }
    public Redirect(String href, String target, Sequence sequence, Long timeout){
        super();

        if (null != href && 0 < href.length())
            this.href = href;
        else
            this.href= Redirect.Defaults.Href;

        if (null != target && 0 < target.length())
            this.target = target;
        else
            this.target = null;

        if (null != sequence)
            this.sequence = sequence;
        else
            this.sequence = Redirect.Defaults.Sequence;

        if (null != timeout && 0L < timeout)
            this.timeout = timeout;
        else
            this.timeout = Redirect.Defaults.Timeout;
    }
    public Redirect(){
        super();
        this.href = Redirect.Defaults.Href;
        this.target = Redirect.Defaults.Target;
        this.sequence = Redirect.Defaults.Sequence;
        this.timeout = Redirect.Defaults.Timeout;
    }
    public Redirect(Json json){
        this( (String)json.getValue("href",String.class), (String)json.getValue("target",String.class), 
              (Sequence)json.getValue("sequence",Sequence.class), (Long)json.getValue("timeout",Long.class));
    }


    /**
     * Render HREF template
     */
    public String toString(Person person)
        throws hapax.TemplateException
    {
        Template template = this.getTemplate();

        this.prepareTemplate(person);

        return template.renderToString(this);
    }
    private Template getTemplate()
        throws hapax.TemplateException
    {
        if (null == this.template){
            this.template = TemplateCache.Instance.getTemplate(this.href);
        }
        return this.template;
    }
    private void prepareTemplate(Person person)
        throws hapax.TemplateException
    {
        Float nf = person.getNf();
        Float nt = person.getNt();
        Float sf = person.getSf();
        Float st = person.getSt();
        String id = person.getId();
        String pr = null; //person.getProjectId()//[TODO odl]

        if (null != nf){
            this.setVariable("nf",nf.toString());
            this.setVariable("nt",nt.toString());
            this.setVariable("sf",sf.toString());
            this.setVariable("st",st.toString());
        }
        this.setVariable("identifier",id);
        this.setVariable("project",pr);
    }
    public int hashCode(){
        return this.toJson().hashCode();
    }
    public boolean equals(Object that){
        if (this == that)
            return true;
        else if (that instanceof Redirect)
            return this.toString().equals(that.toString());
        else
            return false;
    }
    public String toString(){

        return this.toJson().toString();
    }
    public Json toJson(){
        Json json = this.json;
        if (null == json){
            json = new json.ObjectJson();
            json.set("href",this.href);
            json.set("target",this.target);
            json.set("sequence",this.sequence);
            json.set("timeout",this.timeout);
            this.json = json;
        }
        return json;
    }
    public boolean fromJson(Json json){

        throw new Immutable();
    }
    public final void dictionaryInto(gap.hapax.TemplateDataDictionary dict){

        dict.setVariable(TemplateNames.Href,this.href);
        dict.setVariable(TemplateNames.Target,this.target);
        dict.setVariable(TemplateNames.Sequence,this.sequence.toString());
        dict.setVariable(TemplateNames.Timeout,String.valueOf(this.timeout));

        switch (this.sequence){
        case inject:
            dict.setVariable(TemplateNames.SequenceSelectInject,"selected");
            break;
        case timeout:
            dict.setVariable(TemplateNames.SequenceSelectTimeout,"selected");
            break;
        default:
            throw new IllegalArgumentException();
        }
        
    }
}
