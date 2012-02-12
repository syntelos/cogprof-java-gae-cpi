
package oso.data;

import cpi.Code;

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
    implements cpi.Code.Data
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
            logon.setCreated(new Date());
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
    public boolean fromJsonLogonId(Json json){

        return false;
    }
    public boolean fromJsonIdentifier(Json json){

        return false;
    }
    public boolean fromJsonNf(Json json){

        return false;
    }
    public boolean fromJsonNt(Json json){

        return false;
    }
    public boolean fromJsonSt(Json json){

        return false;
    }
    public boolean fromJsonSf(Json json){

        return false;
    }
    public boolean fromJsonCreated(Json json){

        return false;
    }
    public boolean fromJsonCompleted(Json json){

        return false;
    }
    public boolean fromJsonProject(Json json){

        return false;
    }
    public Json toJsonProject(){

        return null;
    }
    public Code.Encode getCodeEncode(){

        return new Code.Encode(this);
    }
    public boolean hasCodeData(){
        return (null != this.getSf());
    }
    public boolean hasNotCodeData(){
        return (null == this.getSf());
    }
    public String toStringSf(){
        Float sf = this.getSf();
        if (null == sf)
            return null;
        else
            return Code.Format(sf);
    }
    public String toStringSt(){
        Float st = this.getSt();
        if (null == st)
            return null;
        else
            return Code.Format(st);
    }
    public String toStringNf(){
        Float nf = this.getNf();
        if (null == nf)
            return null;
        else
            return Code.Format(nf);
    }
    public String toStringNt(){
        Float nt = this.getNt();
        if (null == nt)
            return null;
        else
            return Code.Format(nt);
    }
}
