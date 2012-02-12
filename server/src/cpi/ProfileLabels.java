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

import json.Json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.StringTokenizer;

/**
 * 
 * @see http://code.google.com/p/cpi/wiki/API_Group
 */
public final class ProfileLabels
    extends Object
    implements java.lang.CharSequence,
               java.io.Serializable,
               java.lang.Comparable<ProfileLabels>
{
    protected final static long serialVersionUID = 1;

    public final static class Names {
        public final static String St = "labels_st";
        public final static String Sf = "labels_sf";
        public final static String Nt = "labels_nt";
        public final static String Nf = "labels_nf";
    }
    public final static class TemplateNames {
        public final static gap.hapax.TemplateName St = new gap.hapax.TemplateName(ProfileLabels.Names.St);
        public final static gap.hapax.TemplateName Sf = new gap.hapax.TemplateName(ProfileLabels.Names.Sf);
        public final static gap.hapax.TemplateName Nt = new gap.hapax.TemplateName(ProfileLabels.Names.Nt);
        public final static gap.hapax.TemplateName Nf = new gap.hapax.TemplateName(ProfileLabels.Names.Nf);
    }
    public final static class Defaults {
        public final static String Value = "ST SF NT NF";
        public final static String St = "ST";
        public final static String Sf = "SF";
        public final static String Nt = "NT";
        public final static String Nf = "NF";
    }

    public final static ProfileLabels Default = new ProfileLabels();


    private String st, sf, nt, nf, string;


    public ProfileLabels(Request req){
        this(req.getParameter(ProfileLabels.Names.St),req.getParameter(ProfileLabels.Names.Sf),
             req.getParameter(ProfileLabels.Names.Nt),req.getParameter(ProfileLabels.Names.Nf));
    }
    public ProfileLabels(String value){
        this(FromString(value));
    }
    private ProfileLabels(String[] values){
        this(values[0],values[1],values[2],values[3]);
    }
    public ProfileLabels(String st, String sf, String nt, String nf){
        super();
        if (null != st && 0 < st.length())
            this.st = st;
        else
            this.st = ProfileLabels.Defaults.St;

        if (null != sf && 0 < sf.length())
            this.sf = sf;
        else
            this.sf = ProfileLabels.Defaults.Sf;

        if (null != nt && 0 < nt.length())
            this.nt = nt;
        else
            this.nt = ProfileLabels.Defaults.Nt;

        if (null != nf && 0 < nf.length())
            this.nf = nf;
        else
            this.nf = ProfileLabels.Defaults.Nf;

        this.string = ProfileLabels.ToString(st,sf,nt,nf);
    }
    public ProfileLabels(){
        this(ProfileLabels.Defaults.Value);
    }


    public String getSt(){
        return this.st;
    }
    public String getSf(){
        return this.sf;
    }
    public String getNt(){
        return this.nt;
    }
    public String getNf(){
        return this.nf;
    }
    public int wSt(int two){
        return this.w(two,this.st.length());
    }
    public int wSf(int two){
        return this.w(two,this.sf.length());
    }
    public int wNt(int two){
        return this.w(two,this.nt.length());
    }
    public int wNf(int two){
        return this.w(two,this.nf.length());
    }
    private int w(int two, int strlen){
        if (2 == strlen)
            return two;
        else if (6 >= strlen){
            float p = ((float)strlen)/2.1f;
            return (int)Math.ceil( ((float)two) * p);
        }
        else {
            float p = ((float)strlen)/2.2f;
            return (int)Math.ceil( ((float)two) * p);
        }
    }
    public int length(){
        if (null != this.string)
            return this.string.length();
        else
            return 0;
    }
    public char charAt(int idx){
        if (null != this.string)
            return this.string.charAt(idx);
        else
            throw new IndexOutOfBoundsException(String.valueOf(idx));
    }
    public CharSequence subSequence(int start, int end){
        if (null != this.string)
            return this.string.subSequence(start,end);
        else if (-1 != end && (start+1) == end)
            return "";
        else
            throw new IndexOutOfBoundsException(String.format("start %d, end %d",start,end));
    }
    public int hashCode(){
        return this.string.hashCode();
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

        return this.string;
    }
    public int compareTo(ProfileLabels that){
        if (this.string == that.string)
            return 0;
        else if (null == this.string){
            if (null == that.string)
                return 0;
            else
                return -1;
        }
        else if (null == that.string)
            return +1;
        else
            return this.string.compareTo(that.string);
    }
    public final void dictionaryInto(gap.hapax.TemplateDataDictionary dict){

        dict.setVariable(TemplateNames.St,this.st);
        dict.setVariable(TemplateNames.Sf,this.sf);
        dict.setVariable(TemplateNames.Nt,this.nt);
        dict.setVariable(TemplateNames.Nf,this.nf);
    }

    private final static String Encoding = "UTF-8";

    private final static String[] FromString(String string){
        if (null == string)
            throw new IllegalArgumentException();
        else {
            final StringTokenizer strtok = new StringTokenizer(string," ");

            if (4 != strtok.countTokens())
                throw new IllegalArgumentException(string);
            else {
                try {
                    String[] set = new String[4];
                    for (int cc = 0; cc < 4; cc++){
                    
                        set[cc] = URLDecoder.decode(strtok.nextToken(),ProfileLabels.Encoding);
                    }
                    return set;
                }
                catch (UnsupportedEncodingException err){
                    throw new InternalError(ProfileLabels.Encoding);
                }
            }
        }
    }
    private final static String ToString(String st, String sf, String nt, String nf){
        StringBuilder string = new StringBuilder();
        try {
            string.append(URLEncoder.encode(st,ProfileLabels.Encoding));
            string.append(' ');
            string.append(URLEncoder.encode(sf,ProfileLabels.Encoding));
            string.append(' ');
            string.append(URLEncoder.encode(nt,ProfileLabels.Encoding));
            string.append(' ');
            string.append(URLEncoder.encode(nf,ProfileLabels.Encoding));

            return string.toString();
        }
        catch (UnsupportedEncodingException err){
            throw new InternalError(ProfileLabels.Encoding);
        }
    }
}
