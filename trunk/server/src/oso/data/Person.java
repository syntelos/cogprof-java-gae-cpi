
package oso.data;


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

        Person logon = Person.ForLongLogonId(logonId);
        if (null == logon){

            logon = new Person(Strings.RandomIdentifier());
            logon.setLogonId(logonId);
            logon.save();
        }
        return logon;
    }


    public Person() {
        super();
    }
    public Person(String identifier) {
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
}
