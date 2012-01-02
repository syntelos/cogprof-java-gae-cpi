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

    /*
     * JSON field "margins" fragment (indent column two)
     */
    public final String json;

    public final String value;


    public Margins(String value){
        super();
        if (null != value){
            this.value = value;

            final StringBuilder json = new StringBuilder();
            /*
             * Fragment
             */
            json.append(" \"margins\": \"");
            json.append(this.value);
            json.append("\"");

            this.json = json.toString();
        }
        else
            throw new IllegalArgumentException();
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
    public int compareTo(Margins that){
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
