
package cpi.groups;

import oso.data.Person;
import cpi.Redirect;
import cpi.Margins;

import json.Json;

import gap.*;
import gap.data.*;
import gap.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.util.Date;

/**
 * Project data bean
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
    public Project(Request req){
        super(gap.Strings.RandomIdentifier());

        this.updateFrom(req);

        this.setCreated(new Date());
        this.dropCleaned();
    }


    public boolean hasProjectAccess(Person viewer){

        if (this.equals(viewer.getProject()))
            return true;
        else
            return this.getGroup().hasGroupAccess(viewer);
    }
    public boolean isTest(){
        return this.getGroup().isTest();
    }
    public boolean hasCount(){
        return this.hasCount(MayInherit);
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

        if (this.hasMembers(false)){
            for (Member member: this.getMembers())
                member.drop();
        }

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
    public boolean fromJsonName(json.Json json){
        if (json.isNull())
            return false;
        else if (json.isString())
            return this.setName(json.asString());
        else
            return false;
    }
    public boolean fromJsonGroup(json.Json json){
        return false;
    }
    public boolean fromJsonCreated(json.Json json){
        return false;
    }
    public boolean fromJsonCleaned(json.Json json){
        return false;
    }
    public boolean fromJsonCount(json.Json json){
        return false;
    }
    // public boolean fromJsonRedirect(json.Json json){
    //     return false;
    // }
    // public boolean fromJsonMargins(json.Json json){
    //     return false;
    // }
    /**
     * Unwrap the list of members to a list of people.  The member
     * class is for internal address translation, and has no external
     * purpose.
     */
    public Json toJsonMembers(){
        List.Short<Member> members = this.getMembers();
        final int count = members.size();
        Person[] list = new Person[count];
        for (int cc = 0; cc < count; cc++){
            list[cc] = members.get(cc).getPerson();
        }
        return Json.Wrap(list);
    }
    public boolean fromJsonMembers(Json json){

        return false;
    }
}
