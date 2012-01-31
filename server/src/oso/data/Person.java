
package oso.data;


import gap.*;
import gap.data.*;
import gap.util.*;

import json.Json;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.util.Date;

/**
 * Generated once (user) bean.
 * This source file will not be overwritten unless deleted,
 * so it can be edited for extensions.
 *
 * @see PersonData
 */
public final class Person
    extends PersonData
{
    public final static Person ForLongLogonId(String logonId){

        final Filter filter = new Filter(Person.KIND).add(Person.Field.LogonId,Filter.Op.eq,logonId);

        return Person.Query1(Person.CreateQueryFor(filter));

    }
    public final static Person GetCreateLong(String logonId){

        return Person.GetCreateLongLogonId(logonId);
    }
    public final static Person GetCreateLongLogonId(String logonId){

        Person logon = Person.ForLongLogonId(logonId);
        if (null == logon){

            logon = new Person(Strings.RandomIdentifier());
            logon.setLogonId(logonId);
            logon.save();
        }
        else {
            /*
             * Old data conversion update
             */
            logon.getCreateIdentifier();
        }
        return logon;
    }


    public Person() {
        super();
    }
    public Person(String identifier) {
        super( identifier);
    }
    public Person(Json json){
        super();
        String identifier = (String)json.getValue("identifier",String.class);
        if (null != identifier){
            Key key = Person.KeyLongIdFor(identifier);
            this.setIdentifier(identifier);
            this.setKey(key);
            this.fillFrom(gap.data.Store.Get(key));
            this.markClean();
        }
        else {
            identifier = gap.Strings.RandomIdentifier();
            this.setIdentifier(identifier);
            this.setKey(Person.KeyLongIdFor(identifier));
        }
        this.fromJson(json);
        if (this.isDirty())
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
    public boolean equals(Person that){
        if (null == that)
            return false;
        else {
            Key thisKey = this.getKey();
            Key thatKey = that.getKey();
            if (null == thisKey || null == thatKey)
                return false;
            else
                return ToString(thisKey).equals(ToString(thatKey));
        }
    }
    public String getCreateIdentifier(){
        String identifier = this.getIdentifier();
        if (null == identifier){
            identifier = gap.Strings.RandomIdentifier();
            this.setIdentifier(identifier);
            this.save();
        }
        return identifier;
    }
}
