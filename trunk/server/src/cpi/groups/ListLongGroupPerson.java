/*
 * Gap Data
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
package cpi.groups;

import oso.data.Person;

import gap.data.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

/**
 * Generated long list.
 */
@Generated(value={"gap.service.OD","ListLong.java"},date="2010-09-18T03:30:21.923Z")
public final class ListLongGroupPerson
    extends gap.util.AbstractList<Person>
    implements gap.data.List.Long<Person>
{

    private final static long serialVersionUID = 1;

    public final static String ParentTypeName = "Group";

    public final static String ChildTypeName = "Person";

    public final static Class ParentTypeClass = Group.class;

    public final static Class ChildTypeClass = Person.class;


    protected transient Group parent;


    public ListLongGroupPerson(Group parent) {
        super();
        if (null != parent){
            this.parent = parent;
            this.query = Person.CreateQueryFor();
        }
        else
            throw new IllegalArgumentException();
    }
    public ListLongGroupPerson(){
        super();
    }


    public void destroy(){
        this.parent = null;
        this.clearBuffer();
    }
    public Group getParent(){
        Group parent = this.parent;
        if (null == parent){
            Key parentKey = this.ancestorKey;
            if (null != parentKey){
                parent = Group.Get(parentKey);
                if (null != parent)
                    this.parent = parent;
                else
                    throw new IllegalStateException("Missing parent, user employed wrong constructor (pre store).");
            }
            else
                throw new IllegalStateException("Missing ancestor key.");
        }
        return parent;
    }
    public String getParentTypeName(){
        return ParentTypeName;
    }
    public String getChildTypeName(){
        return ChildTypeName;
    }
    public Class getParentTypeClass(){
        return ParentTypeClass;
    }
    public Class getChildTypeClass(){
        return ChildTypeClass;
    }
    public void setValueClassAncestorKey(){
        this.ancestorKey = this.getParent().getKey();
    }
    public Person fetch(Filter filter){
        Query query = Person.CreateQueryFor(filter);
        return Person.Query1(query);
    }
}
