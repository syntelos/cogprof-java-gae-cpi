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
 */
public final class Margins
    extends Object
    implements java.lang.CharSequence,
               java.io.Serializable,
               java.lang.Comparable<Margins>
{

    public String value;


    public Margins(String value){
        super();
        this.value = value;
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
    public String toString(){
        if (null == this.value)
            return "";
        else
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
}
