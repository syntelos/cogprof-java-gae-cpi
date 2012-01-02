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

import hapax.Template;
import hapax.TemplateDataDictionary;

import oso.data.Person;

/**
 * 
 * @see http://code.google.com/p/cpi/wiki/API_Redirect
 */
public final class Redirect
    extends hapax.TemplateDictionary
    implements java.lang.CharSequence,
               java.io.Serializable,
               java.lang.Comparable<Redirect>
{
    protected final static long serialVersionUID = 1;


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
    public final String json;

    public final String href, target;

    public final Sequence sequence;

    public final long timeout;

    private transient Template template;


    public Redirect(String href, String target, String sequence, String timeout){
        this(href,target,sequence,Integer.decode(timeout));
    }
    public Redirect(String href, String target, String sequence, long timeout){
        this(href,target,Sequence.For(sequence),timeout);
    }
    public Redirect(String href, String target, Sequence sequence, long timeout){
        super();

        if (null != href)
            this.href = href;
        else
            this.href= "/profile";
        this.target = target;

        if (null != sequence)
            this.sequence = sequence;
        else
            this.sequence = Sequence.timeout;

        if (0L < timeout)
            this.timeout = timeout;
        else
            this.timeout = 0L;

        final StringBuilder json = new StringBuilder();
        /*
         * Fragment Head
         */
        json.append(" \"redirect\": {\n");
        json.append("  \"href\": \"");
        json.append(this.href);
        json.append("\",\n");

        if (null != target){
            json.append("  \"target\": ");
            json.append(target);
            json.append("\",\n");
        }
        /*
         * Fragment Tail
         */
        if (0 < this.timeout){
            json.append("  \"sequence\": \"");
            json.append(sequence.name());
            json.append("\",\n");

            json.append("  \"timeout\": \"");
            json.append(this.timeout);
            json.append("\"\n");
        }
        else {
            json.append("  \"sequence\": \"");
            json.append(sequence.name());
            json.append("\"\n");
        }
        json.append(" }");

        this.json = json.toString();
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
    public int length(){
        if (null != this.json)
            return this.json.length();
        else
            return 0;
    }
    public char charAt(int idx){
        if (null != this.json)
            return this.json.charAt(idx);
        else
            throw new IndexOutOfBoundsException(String.valueOf(idx));
    }
    public CharSequence subSequence(int start, int end){
        if (null != this.json)
            return this.json.subSequence(start,end);
        else if (-1 != end && (start+1) == end)
            return "";
        else
            throw new IndexOutOfBoundsException(String.format("start %d, end %d",start,end));
    }
    public int hashCode(){
        return this.json.hashCode();
    }
    public boolean equals(Object that){
        if (this == that)
            return true;
        else if (that instanceof Redirect)
            return this.json.equals(that.toString());
        else
            return false;
    }
    public String toString(){

        return this.json;
    }
    public int compareTo(Redirect that){
        if (this.json == that.json)
            return 0;
        else if (null == this.json){
            if (null == that.json)
                return 0;
            else
                return -1;
        }
        else if (null == that.json)
            return +1;
        else
            return this.json.compareTo(that.json);
    }
}
