
package cpi.groups;

import oso.data.Person;

import json.Json;

import gap.*;
import gap.data.*;
import gap.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.util.Date;

/**
 * Member wraps {@link oso.data.Person Person} in a short list from
 * {@link Project}.  This wrapping is made transparent to network
 * service interfaces when possible and convenient.
 *
 * @see MemberData
 * @see Person
 */
public final class Member
    extends MemberData
{

    public Member() {
        super();
    }
    public Member(Key ancestor, Person member) {
        super(ancestor,  member);
    }
    public Member(Project project, Person member){
        this(project.getKey(),member);
    }
    public Member(Project project){
        this(project.getKey(),new Person(gap.Strings.RandomIdentifier()));

        final Date created = project.getCreated();

        final Person person = this.getPerson();
        person.setProject(project);
        person.setCreated(created);
        person.save();


        this.save();
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
        try {
            Person person = this.getPerson();
            if (null != person)
                person.drop();
        }
        finally {
            Delete(this);
        }
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
    /*
     * Unwrap -- make this class transparent to JSON model.
     */
    public Json toJson(){

        return new json.ObjectJson(this.getPerson());
    }
    public boolean fromJson(Json json){

        return false;
    }
}
