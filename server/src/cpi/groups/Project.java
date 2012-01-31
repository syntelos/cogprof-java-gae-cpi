
package cpi.groups;

import oso.data.Person;
import cpi.Redirect;
import cpi.Margins;

import gap.*;
import gap.data.*;
import gap.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.util.Date;

/**
 * Generated once (user) bean.
 * This source file will not be overwritten unless deleted,
 * so it can be edited for extensions.
 *
 * @see ProjectData
 */
public final class Project
    extends ProjectData
{
    public static List.Primitive<Key> List(Group group){
        Query query = Project.CreateQueryFor();
        {
            Filter filter = new Filter(Project.KIND);
            filter.add(Project.Field.Group,Filter.Op.eq,group.getId());
            filter.update(query);
        }
        return Project.QueryNKey(query);
    }



    public Project() {
        super();
    }
    public Project(String identifier) {
        super( identifier);
    }


    public void onread(){
    }
    public void onwrite(){
    }
    public java.io.Serializable valueOf(gap.data.Field field, boolean mayInherit){
        return (java.io.Serializable)Field.Get((Field)field,this,mayInherit);
    }
    public void define(gap.data.Field field, java.io.Serializable value){
        Field.Set((Field)field,this,value);
    }
    public void drop(){
        Delete(this);
    }
    public void clean(){
        Clean(this);
    }
    public void save(){
        Save(this);
    }
    public void store(){
        Store(this);
    }
    public Redirect getCreateRedirect(){
        Redirect redirect = this.getRedirect();
        if (null == redirect){
            Group group = this.getGroup();
            if (null == group)
                throw new IllegalStateException("Missing parent group");
            else
                redirect = group.getCreateRedirect();
        }
        return redirect;
    }
    public Margins getCreateMargins(){
        Margins margins = this.getMargins();
        if (null == margins){
            Group group = this.getGroup();
            if (null == group)
                throw new IllegalStateException("Missing parent group");
            else
                margins = group.getCreateMargins();
        }
        return margins;
    }
    public boolean setIdentifier(json.Json json){
        return false;
    }
    public boolean setName(json.Json json){
        if (json.isNull())
            return false;
        else if (json.isString())
            return this.setName(json.asString());
        else
            return false;
     }
    public boolean setGroup(json.Json json){
        return false;
    }
    public boolean setCreated(json.Json json){
        return false;
    }
    public boolean setCleaned(json.Json json){
        return false;
    }
    public boolean setCount(json.Json json){
        return false;
    }
    // public boolean setRedirect(json.Json json){
    //     return false;
    // }
    // public boolean setMargins(json.Json json){
    //     return false;
    // }

}
