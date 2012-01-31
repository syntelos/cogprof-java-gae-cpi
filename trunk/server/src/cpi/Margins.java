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

/**
 * 
 * @see http://code.google.com/p/cpi/wiki/API_Margins
 */
public final class Margins
    extends Object
    implements java.lang.CharSequence,
               java.io.Serializable,
               java.lang.Comparable<Margins>
{
    protected final static long serialVersionUID = 1;

    public final static class Names {
        public final static String Value = "margins_css";
    }
    public final static class TemplateNames {
        public final static gap.hapax.TemplateName Value = new gap.hapax.TemplateName(Margins.Names.Value);
    }
    public final static class Defaults {
        public final static String Value = "0";
    }


    public final String value;


    public Margins(Request req){
        this(req.getParameter(Margins.Names.Value));
    }
    public Margins(String value){
        super();
        if (null != value && 0 < value.length())
            this.value = value;
        else
            this.value = Margins.Defaults.Value;
    }
    public Margins(){
        this(Margins.Defaults.Value);
    }


    public int length(){
        if (null != this.value)
            return this.value.length();
        else
            return 0;
    }
    public char charAt(int idx){
        if (null != this.value)
            return this.value.charAt(idx);
        else
            throw new IndexOutOfBoundsException(String.valueOf(idx));
    }
    public CharSequence subSequence(int start, int end){
        if (null != this.value)
            return this.value.subSequence(start,end);
        else if (-1 != end && (start+1) == end)
            return "";
        else
            throw new IndexOutOfBoundsException(String.format("start %d, end %d",start,end));
    }
    public int hashCode(){
        return this.value.hashCode();
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

        return this.value;
    }
    public int compareTo(Margins that){
        if (this.value == that.value)
            return 0;
        else if (null == this.value){
            if (null == that.value)
                return 0;
            else
                return -1;
        }
        else if (null == that.value)
            return +1;
        else
            return this.value.compareTo(that.value);
    }
    public final void dictionaryInto(gap.hapax.TemplateDataDictionary dict){

        dict.setVariable(TemplateNames.Value,this.value);
    }
}
