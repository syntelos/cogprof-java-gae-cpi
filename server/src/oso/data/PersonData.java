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
package oso.data;

import cpi.Inventory;
import cpi.groups.Project;

import gap.*;
import gap.data.*;
import gap.hapax.TemplateDataDictionary;
import gap.hapax.TemplateName;
import gap.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.Date;

import javax.annotation.Generated;

/**
 * Generated bean data binding.
 *
 * @see Person
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-02-01T20:10:09.740Z")
public abstract class PersonData
    extends gap.data.BigTable
    implements DataInheritance<Person>
{

    private final static long serialVersionUID = 8;

    public final static Kind KIND = Kind.Create("Person","oso.data","Person","/people");

    public final static String ClassName = "Person";

    public final static String DefaultSortBy = "identifier";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Person.class);
    }
    /**
     * @see gap.data.Kind#pathto()
     */
    public final static String PathTo(){
        return KIND.pathto();
    }
    public final static String PathTo(String subpath){
        return KIND.pathto(subpath);
    }

    /**
     * Long instance key without parent key
     */
    public final static Key KeyLongIdFor(String identifier){
        String id = Person.IdFor( identifier);
        return KeyLongFor(id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyLongIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Person.KeyLongIdFor((String)args[0]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyLongFor}
     */
    public final static Key KeyFor(Object... args){
        return Person.KeyLongFor( (String)args[0]);
    }
    /**
     * Identifier for unique fields
     */
    public final static String IdFor(String identifier){
        if (null != identifier){
            String identifierString = identifier;
            return gap.data.Hash.For(identifierString);
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup
     */
    public final static Person ForLongIdentifier(String identifier){
        if (null != identifier){
            Key key = Person.KeyLongIdFor( identifier);
            Person instance = (Person)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Person.CreateQueryFor(key);
                return (Person)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create
     */
    public static Person GetCreateLong(String identifier){
        return GetCreateLongIdentifier( identifier);
    }
    /**
     * Instance lookup or create
     */
    public final static Person GetCreateLongIdentifier(String identifier){
        Person person = Person.ForLongIdentifier( identifier);
        if (null == person){
            person = new Person( identifier);
            person = (Person)gap.data.Store.PutClass(person);
        }
        return person;
    }


    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Person ForLongId(String id){
        if (null != id){
            Key key = Person.KeyLongFor(id);
            Person instance = (Person)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Person.CreateQueryFor(key);
                return (Person)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Person Get(Key key){
        if (null != key){
            Person instance = (Person)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Person.CreateQueryFor(key);
                return (Person)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Person.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Person FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Person)
            return (Person)value;
        else if (value instanceof Key)
            return Get( (Key)value);
        else if (value instanceof String){
            /*
             * TODO: ilarg: not key.enc; Key For ID.
             */
            Key key = gap.Strings.KeyFromString( (String)value);
            return Get(key);
        }
        else
            throw new IllegalArgumentException(value.getClass().getName());
    }


    /**
     * Anonymous random key cannot be mapped to network identifier
     * @see Person#IdFor
     *
     * Test for uniqueness and iterate under collisions.
     */
    public final static Key NewRandomKeyLong(){
        /*
         * Source matter for data local uniqueness
         */
        long matter = (gap.data.Hash.Djb64(ClassName) ^ (serialVersionUID<<3) ^ serialVersionUID);
        /*
         * Random matter for network global uniqueness
         */
        java.util.Random random = new java.util.Random();
        do {
            matter ^= random.nextLong();
            String idString = gap.data.Hash.Hex(matter);
            Key key = KeyFactory.createKey(KIND.getName(),idString);
            if (null == GetKey(key))
                return key;
        }
        while (true);
    }
    /**
     * Drop the instance from memcache and datastore.
     */
    public final static void Delete(Person instance){
        if (null != instance){

            Delete(instance.getKey());
        }
    }
    /**
     * Drop the instance from memcache and datastore.
     */
    public final static void Delete(Key instanceKey){
        if (null != instanceKey){

            gap.data.Store.Delete(instanceKey);
        }
    }
    /**
     * Drop the instance from memcache, exclusively.
     */
    public final static void Clean(Person instance){
        if (null != instance){

            gap.data.Store.Clean(instance.getKey());
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Person instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Person instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Default sort
     */
    public final static Query CreateQueryFor(){
        return new Query(KIND.getName()).addSort(DefaultSortBy);
    }
    /**
     * Default sort
     */
    public final static Query CreateQueryFor(Key key){
        return new Query(KIND.getName(),key).addSort(DefaultSortBy);
    }
    /**
     * Filter ops
     */
    public final static Query CreateQueryFor(Filter filter){
        Query query = new Query(KIND.getName());
        return filter.update(query);
    }
    
    public final static Person Query1(Query query){
        if (null != query)
            return (Person)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Person> ListPage(Page page){

        return Person.QueryN(Person.CreateQueryFor(),page);
    }
    public final static BigTableIterator<Person> QueryN(Query query, Page page){
        if (null != query && null != page)
            return gap.data.Store.QueryNClass(query,page);
        else
            throw new IllegalArgumentException();
    }
    public final static Key QueryKey1(Query query){
        if (null != query)
            return gap.data.Store.Query1Key(query);
        else
            throw new IllegalArgumentException();
    }
    public final static List.Primitive<Key> QueryNKey(Query query, Page page){
        if (null != query)
            return gap.data.Store.QueryNKey(query,page);
        else
            throw new IllegalArgumentException();
    }
    public final static List.Primitive<Key> QueryNKey(Query query){
        if (null != query)
            return gap.data.Store.QueryNKey(query);
        else
            throw new IllegalArgumentException();
    }

    /**
     * Persistent fields' binding for {@link Person}
     */
    public static enum Field
        implements gap.data.Field<Person.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        LogonId("logonId",Type.Primitive),
        Identifier("identifier",Type.Primitive),
        Inventory("inventory",Type.PrimitiveCollection),
        Nf("nf",Type.Primitive),
        Nt("nt",Type.Primitive),
        St("st",Type.Primitive),
        Sf("sf",Type.Primitive),
        Created("created",Type.Primitive),
        Completed("completed",Type.Primitive),
        Project("project",Type.BigTable);

        private final static lxl.Map<String,Field> FieldName = new lxl.Map<String,Field>();
        public static final String[] AllNames;
        static {
            Field[] allFields = Field.values();
            int count = allFields.length;
            String[] names = new String[count];
            for (int cc = 0; cc < count; cc++) {
                Field field = allFields[cc];
                String fieldName = field.getFieldName();
                names[cc] = fieldName;
                FieldName.put(fieldName,field);
            }
            AllNames = names;
        }
        public static Field getField(String name) {
            return FieldName.get(name);
        }
        public static Field For(String name) {
            Field field = FieldName.get(name);
            if (null == field)
                try {
                    return Field.valueOf(name);
                }
                catch (IllegalArgumentException notFound){
                    return null;
                }
            else
                return field;
        }
        /**
         * Field statistics are maintained for persistent fields exclusively
         */
        public final static class Statistics
            extends gap.data.Field.Statistics<Person.Field>
        {
            public Statistics(){
                super(Person.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static Object Get(Field field, Person instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case LogonId:
                return instance.getLogonId(mayInherit);
            case Identifier:
                return instance.getIdentifier(mayInherit);
            case Inventory:
                return instance.getInventory(mayInherit);
            case Nf:
                return instance.getNf(mayInherit);
            case Nt:
                return instance.getNt(mayInherit);
            case St:
                return instance.getSt(mayInherit);
            case Sf:
                return instance.getSf(mayInherit);
            case Created:
                return instance.getCreated(mayInherit);
            case Completed:
                return instance.getCompleted(mayInherit);
            case Project:
                return instance.getProjectId();
            default:
                throw new IllegalArgumentException(field.toString()+" in Person");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static boolean Set(Field field, Person instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case LogonId:
                return instance.setLogonId(gap.Objects.StringFromObject(value));
            case Identifier:
                return instance.setIdentifier(gap.Objects.StringFromObject(value));
            case Inventory:
                return instance.setInventory((List.Primitive<Inventory>)value);
            case Nf:
                return instance.setNf(gap.Objects.FloatFromObject(value));
            case Nt:
                return instance.setNt(gap.Objects.FloatFromObject(value));
            case St:
                return instance.setSt(gap.Objects.FloatFromObject(value));
            case Sf:
                return instance.setSf(gap.Objects.FloatFromObject(value));
            case Created:
                return instance.setCreated(gap.Objects.DateFromObject(value));
            case Completed:
                return instance.setCompleted(gap.Objects.DateFromObject(value));
            case Project:
                return instance.setProjectId(gap.Objects.StringFromObject(value));
            default:
                throw new IllegalArgumentException(field.toString()+" in Person");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Person instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case LogonId:
                return instance.getLogonId(MayNotInherit);
            case Identifier:
                return instance.getIdentifier(MayNotInherit);
            case Inventory:{
                List.Primitive<Inventory> inventory = instance.getInventory(MayNotInherit);
                if (null != inventory)
                    return Serialize.To(field,inventory);
                else
                    return null;
            }
            case Nf:{
                return instance.getNf(MayNotInherit);
            }
            case Nt:{
                return instance.getNt(MayNotInherit);
            }
            case St:{
                return instance.getSt(MayNotInherit);
            }
            case Sf:{
                return instance.getSf(MayNotInherit);
            }
            case Created:
                return instance.getCreated(MayNotInherit);
            case Completed:
                return instance.getCompleted(MayNotInherit);
            case Project:
                return instance.getProjectId();
            default:
                throw new IllegalArgumentException(field.toString()+" in Person");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Person instance, java.io.Serializable value){
            switch(field){
            case InheritFromKey:
                instance.setInheritFromKey( (Key)value);
                return;
            case Key:
                instance.setKey( (Key)value);
                return;
            case Id:
                instance.setId( (String)value);
                return;
            case LogonId:
                instance.setLogonId( (String)value);
                return;
            case Identifier:
                instance.setIdentifier( (String)value);
                return;
            case Inventory:{
                List.Primitive<Inventory> _inventory = null;
                if (null != value){
                    _inventory = (List.Primitive<Inventory>)Serialize.From(field,value);
                }
                instance.setInventory( _inventory);
                return;
            }
            case Nf:{

                instance.setNf( (Number)value);
                return;
            }
            case Nt:{

                instance.setNt( (Number)value);
                return;
            }
            case St:{

                instance.setSt( (Number)value);
                return;
            }
            case Sf:{

                instance.setSf( (Number)value);
                return;
            }
            case Created:
                instance.setCreated( (Date)value);
                return;
            case Completed:
                instance.setCompleted( (Date)value);
                return;
            case Project:
                instance.setProjectId( (String)value);
                return;
            default:
                throw new IllegalArgumentException(field.toString()+" in Person");
            }
        }


        public final static class List
            extends gap.util.ArrayList<Person.Field>
        {
            public List(){
                super();
            }
            public List(Field[] fields){
                super(fields);
            }
            public List(Iterable<Field> fields){
                super();
                for (Field field : fields)
                    this.add(field);
            }
        }


        private final String fieldName;

        private final Type fieldType;

        private final boolean fieldTypePrimitive, fieldTypeBigTable, fieldTypeCollection;

        private final boolean fieldNameKeyOrId;


        Field(String fieldName, Type fieldType){
            if (null != fieldName && null != fieldType){
                this.fieldName = fieldName;
                this.fieldType = fieldType;
                this.fieldNameKeyOrId = BigTable.IsKeyOrId(fieldName);
                /*
                 * Using a switch here causes a null pointer
                 * initializing the switch map.
                 */
                if (Type.Primitive == fieldType){
                    this.fieldTypePrimitive = true;
                    this.fieldTypeBigTable = false;
                    this.fieldTypeCollection = false;
                }
                else if (Type.BigTable == fieldType){
                    this.fieldTypePrimitive = false;
                    this.fieldTypeBigTable = true;
                    this.fieldTypeCollection = false;
                }
                else if (Type.Collection == fieldType){
                    this.fieldTypePrimitive = false;
                    this.fieldTypeBigTable = false;
                    this.fieldTypeCollection = true;
                }
                else if (Type.PrimitiveCollection == fieldType){
                    this.fieldTypePrimitive = true;
                    this.fieldTypeBigTable = false;
                    this.fieldTypeCollection = true;
                }
                else
                    throw new IllegalStateException("Unimplemented field type "+fieldType);
            }
            else
                throw new IllegalStateException();
        }


        public String getFieldName(){
            return this.fieldName;
        }
        public Type getFieldType(){
            return this.fieldType;
        }
        public boolean isFieldTypePrimitive(){
            return this.fieldTypePrimitive;
        }
        public boolean isNotFieldTypePrimitive(){
            return (!this.fieldTypePrimitive);
        }
        public boolean isFieldTypeBigTable(){
            return this.fieldTypeBigTable;
        }
        public boolean isNotFieldTypeBigTable(){
            return (!this.fieldTypeBigTable);
        }
        public boolean isFieldTypeCollection(){
            return this.fieldTypeCollection;
        }
        public boolean isNotFieldTypeCollection(){
            return (!this.fieldTypeCollection);
        }
        public boolean isFieldNameKeyOrId(){
            return this.fieldNameKeyOrId;
        }
        public boolean isNotFieldNameKeyOrId(){
            return (!this.fieldNameKeyOrId);
        }
        public String toString(){
            return this.fieldName;
        }
    }

    private transient final Person.Field.Statistics fieldStatistics = new Person.Field.Statistics();

    private transient Person inheritFrom;


    private String logonId;
    private String identifier;
    private Float nf;
    private Float nt;
    private Float st;
    private Float sf;
    private Date created;
    private Date completed;
    private String projectId;
    private transient Key projectKey;
    private transient Project project;
    private List.Primitive<Inventory> inventory;




    protected PersonData() {
        super();
    }
    protected PersonData(String identifier) {
        super();
        this.setIdentifier(identifier);
        {
            final String id = Person.IdFor(identifier);
            final Key key = Person.KeyLongFor(id);
            this.setKey(key);
        }
    }


    public void destroy(){
        this.inheritFrom = null;
        this.logonId = null;
        this.identifier = null;
        this.nf = null;
        this.nt = null;
        this.st = null;
        this.sf = null;
        this.created = null;
        this.completed = null;
        this.project = null;
        List.Primitive<Inventory> inventory = this.inventory;
        if (null != inventory){
            this.inventory = null;
            inventory.destroy();
        }
    }
    public final String getId(){

        String id = Person.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Person.IdFor(this.identifier);
    }
    public final boolean setId(String id){
        if (null == id){
            if (null != this.key){
                this.key = null;
                return true;
            }
            else
                return false;
        }
        else if (null == this.key){
            this.key = Person.KeyLongFor(id);
            return true;
        }
        else
            return false;
    }
    public final boolean hasInheritFrom(){
        return (null != this.inheritFrom || null != this.inheritFromKey);
    }
    public final boolean hasNotInheritFrom(){
        return (null == this.inheritFrom && null == this.inheritFromKey);
    }
    public final Person getInheritFrom(){
        Person inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Person.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Person ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Person ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean hasLogonId(boolean mayInherit){
        return (null != this.getLogonId(mayInherit));
    }
    public final boolean hasNotLogonId(boolean mayInherit){
        return (null == this.getLogonId(mayInherit));
    }
    public final boolean dropLogonId(){
        if (null != this.logonId){
            this.fieldStatistics.markDirty(Person.Field.LogonId);
            this.logonId = null;
            return true;
        }
        else
            return false;
    }
    public final String getLogonId(){
        return this.getLogonId(Notation.MayInherit);
    }
    public final String getLogonId(boolean mayInherit){
        if (mayInherit){
            String logonId = this.logonId;
            if (null == logonId && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getLogonId(Notation.MayInherit);
            }
            return logonId;
        }
        else
            return this.logonId;
    }
    public final boolean setLogonId(String logonId, boolean withInheritance){
        if (IsNotEqual(this.logonId,this.getLogonId(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.LogonId);
            this.logonId = logonId;
            return true;
        }
        else
            return false;
    }
    public final boolean setLogonId(String logonId){
        if (IsNotEqual(this.logonId,logonId)){
            this.fieldStatistics.markDirty(Person.Field.LogonId);
            this.logonId = logonId;
            return true;
        }
        else
            return false;
    }
    public boolean setLogonId(json.Json json){
        if (null == json)
            return false;
        else
            return this.setLogonId((String)json.getValue(String.class));
    }
    public final boolean hasIdentifier(boolean mayInherit){
        return (null != this.getIdentifier(mayInherit));
    }
    public final boolean hasNotIdentifier(boolean mayInherit){
        return (null == this.getIdentifier(mayInherit));
    }
    public final boolean dropIdentifier(){
        if (null != this.identifier){
            this.fieldStatistics.markDirty(Person.Field.Identifier);
            this.identifier = null;
            return true;
        }
        else
            return false;
    }
    public final String getIdentifier(){
        return this.identifier;
    }
    public final String getIdentifier(boolean mayInherit){
        return this.getIdentifier();
    }
    public final boolean setIdentifier(String identifier){
        if (IsNotEqual(this.identifier,identifier)){
            this.fieldStatistics.markDirty(Person.Field.Identifier);
            this.identifier = identifier;
            return true;
        }
        else
            return false;
    }
    public boolean setIdentifier(json.Json json){
        if (null == json)
            return false;
        else
            return this.setIdentifier((String)json.getValue(String.class));
    }
    public final boolean hasNf(boolean mayInherit){
        return (null != this.getNf(mayInherit));
    }
    public final boolean hasNotNf(boolean mayInherit){
        return (null == this.getNf(mayInherit));
    }
    public final boolean dropNf(){
        if (null != this.nf){
            this.fieldStatistics.markDirty(Person.Field.Nf);
            this.nf = null;
            return true;
        }
        else
            return false;
    }
    public final Float getNf(){
        return this.getNf(Notation.MayInherit);
    }
    public final Float getNf(boolean mayInherit){
        if (mayInherit){
            Float nf = this.nf;
            if (null == nf && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getNf(Notation.MayInherit);
            }
            return nf;
        }
        else
            return this.nf;
    }
    public final boolean setNf(Float nf, boolean withInheritance){
        if (IsNotEqual(this.nf,this.getNf(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Nf);
            this.nf = nf;
            return true;
        }
        else
            return false;
    }
    public final boolean setNf(Float nf){
        if (IsNotEqual(this.nf,nf)){
            this.fieldStatistics.markDirty(Person.Field.Nf);
            this.nf = nf;
            return true;
        }
        else
            return false;
    }
    public boolean setNf(json.Json json){
        if (null == json)
            return false;
        else
            return this.setNf((Float)json.getValue(Float.class));
    }
    public final boolean setNf(Number nf){
        if (IsNotEqual(this.nf,nf)){
            this.fieldStatistics.markDirty(Person.Field.Nf);
            if (nf instanceof Float)
                this.nf = (Float)nf;
            else
                this.nf = new Float( nf.floatValue());
            return true;
        }
        else
            return false;
    }
    public final boolean hasNt(boolean mayInherit){
        return (null != this.getNt(mayInherit));
    }
    public final boolean hasNotNt(boolean mayInherit){
        return (null == this.getNt(mayInherit));
    }
    public final boolean dropNt(){
        if (null != this.nt){
            this.fieldStatistics.markDirty(Person.Field.Nt);
            this.nt = null;
            return true;
        }
        else
            return false;
    }
    public final Float getNt(){
        return this.getNt(Notation.MayInherit);
    }
    public final Float getNt(boolean mayInherit){
        if (mayInherit){
            Float nt = this.nt;
            if (null == nt && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getNt(Notation.MayInherit);
            }
            return nt;
        }
        else
            return this.nt;
    }
    public final boolean setNt(Float nt, boolean withInheritance){
        if (IsNotEqual(this.nt,this.getNt(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Nt);
            this.nt = nt;
            return true;
        }
        else
            return false;
    }
    public final boolean setNt(Float nt){
        if (IsNotEqual(this.nt,nt)){
            this.fieldStatistics.markDirty(Person.Field.Nt);
            this.nt = nt;
            return true;
        }
        else
            return false;
    }
    public boolean setNt(json.Json json){
        if (null == json)
            return false;
        else
            return this.setNt((Float)json.getValue(Float.class));
    }
    public final boolean setNt(Number nt){
        if (IsNotEqual(this.nt,nt)){
            this.fieldStatistics.markDirty(Person.Field.Nt);
            if (nt instanceof Float)
                this.nt = (Float)nt;
            else
                this.nt = new Float( nt.floatValue());
            return true;
        }
        else
            return false;
    }
    public final boolean hasSt(boolean mayInherit){
        return (null != this.getSt(mayInherit));
    }
    public final boolean hasNotSt(boolean mayInherit){
        return (null == this.getSt(mayInherit));
    }
    public final boolean dropSt(){
        if (null != this.st){
            this.fieldStatistics.markDirty(Person.Field.St);
            this.st = null;
            return true;
        }
        else
            return false;
    }
    public final Float getSt(){
        return this.getSt(Notation.MayInherit);
    }
    public final Float getSt(boolean mayInherit){
        if (mayInherit){
            Float st = this.st;
            if (null == st && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getSt(Notation.MayInherit);
            }
            return st;
        }
        else
            return this.st;
    }
    public final boolean setSt(Float st, boolean withInheritance){
        if (IsNotEqual(this.st,this.getSt(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.St);
            this.st = st;
            return true;
        }
        else
            return false;
    }
    public final boolean setSt(Float st){
        if (IsNotEqual(this.st,st)){
            this.fieldStatistics.markDirty(Person.Field.St);
            this.st = st;
            return true;
        }
        else
            return false;
    }
    public boolean setSt(json.Json json){
        if (null == json)
            return false;
        else
            return this.setSt((Float)json.getValue(Float.class));
    }
    public final boolean setSt(Number st){
        if (IsNotEqual(this.st,st)){
            this.fieldStatistics.markDirty(Person.Field.St);
            if (st instanceof Float)
                this.st = (Float)st;
            else
                this.st = new Float( st.floatValue());
            return true;
        }
        else
            return false;
    }
    public final boolean hasSf(boolean mayInherit){
        return (null != this.getSf(mayInherit));
    }
    public final boolean hasNotSf(boolean mayInherit){
        return (null == this.getSf(mayInherit));
    }
    public final boolean dropSf(){
        if (null != this.sf){
            this.fieldStatistics.markDirty(Person.Field.Sf);
            this.sf = null;
            return true;
        }
        else
            return false;
    }
    public final Float getSf(){
        return this.getSf(Notation.MayInherit);
    }
    public final Float getSf(boolean mayInherit){
        if (mayInherit){
            Float sf = this.sf;
            if (null == sf && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getSf(Notation.MayInherit);
            }
            return sf;
        }
        else
            return this.sf;
    }
    public final boolean setSf(Float sf, boolean withInheritance){
        if (IsNotEqual(this.sf,this.getSf(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Sf);
            this.sf = sf;
            return true;
        }
        else
            return false;
    }
    public final boolean setSf(Float sf){
        if (IsNotEqual(this.sf,sf)){
            this.fieldStatistics.markDirty(Person.Field.Sf);
            this.sf = sf;
            return true;
        }
        else
            return false;
    }
    public boolean setSf(json.Json json){
        if (null == json)
            return false;
        else
            return this.setSf((Float)json.getValue(Float.class));
    }
    public final boolean setSf(Number sf){
        if (IsNotEqual(this.sf,sf)){
            this.fieldStatistics.markDirty(Person.Field.Sf);
            if (sf instanceof Float)
                this.sf = (Float)sf;
            else
                this.sf = new Float( sf.floatValue());
            return true;
        }
        else
            return false;
    }
    public final boolean hasCreated(boolean mayInherit){
        return (null != this.getCreated(mayInherit));
    }
    public final boolean hasNotCreated(boolean mayInherit){
        return (null == this.getCreated(mayInherit));
    }
    public final boolean dropCreated(){
        if (null != this.created){
            this.fieldStatistics.markDirty(Person.Field.Created);
            this.created = null;
            return true;
        }
        else
            return false;
    }
    public final Date getCreated(){
        return this.getCreated(Notation.MayInherit);
    }
    public final Date getCreated(boolean mayInherit){
        if (mayInherit){
            Date created = this.created;
            if (null == created && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getCreated(Notation.MayInherit);
            }
            return created;
        }
        else
            return this.created;
    }
    public final boolean setCreated(Date created, boolean withInheritance){
        if (IsNotEqual(this.created,this.getCreated(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean setCreated(Date created){
        if (IsNotEqual(this.created,created)){
            this.fieldStatistics.markDirty(Person.Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public boolean setCreated(json.Json json){
        if (null == json)
            return false;
        else
            return this.setCreated((Date)json.getValue(Date.class));
    }
    public final boolean hasCompleted(boolean mayInherit){
        return (null != this.getCompleted(mayInherit));
    }
    public final boolean hasNotCompleted(boolean mayInherit){
        return (null == this.getCompleted(mayInherit));
    }
    public final boolean dropCompleted(){
        if (null != this.completed){
            this.fieldStatistics.markDirty(Person.Field.Completed);
            this.completed = null;
            return true;
        }
        else
            return false;
    }
    public final Date getCompleted(){
        return this.getCompleted(Notation.MayInherit);
    }
    public final Date getCompleted(boolean mayInherit){
        if (mayInherit){
            Date completed = this.completed;
            if (null == completed && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getCompleted(Notation.MayInherit);
            }
            return completed;
        }
        else
            return this.completed;
    }
    public final boolean setCompleted(Date completed, boolean withInheritance){
        if (IsNotEqual(this.completed,this.getCompleted(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Completed);
            this.completed = completed;
            return true;
        }
        else
            return false;
    }
    public final boolean setCompleted(Date completed){
        if (IsNotEqual(this.completed,completed)){
            this.fieldStatistics.markDirty(Person.Field.Completed);
            this.completed = completed;
            return true;
        }
        else
            return false;
    }
    public boolean setCompleted(json.Json json){
        if (null == json)
            return false;
        else
            return this.setCompleted((Date)json.getValue(Date.class));
    }
    public final boolean hasProject(boolean mayInherit){
        return (null != this.getProject(mayInherit));
    }
    public final boolean hasNotProject(boolean mayInherit){
        return (null == this.getProject(mayInherit));
    }
    public final boolean dropProject(){
        if (null != this.project){
            this.fieldStatistics.markDirty(Person.Field.Project);
            this.project = null;
            this.projectId = null;
            this.projectKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getProjectId(){
        return this.projectId;
    }
    public final boolean setProjectId(String projectId){
        if (IsNotEqual(this.projectId,projectId)){
            this.fieldStatistics.markDirty(Person.Field.Project);
            this.projectId = projectId;
            this.projectKey = null;
            this.project = null;
            return true;
        }
        else
            return false;
    }
    public final Key getProjectKey(){
        if (null == this.projectKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.projectId){
                this.projectKey = Project.KeyLongFor(this.projectId);
            }
        }
        return this.projectKey;
    }
    public final String getProjectId(boolean mayInherit){
        if (null != this.projectId)
            return this.projectId;
        else if (mayInherit && this.hasInheritFrom()){
            Person inheritFrom = this.getInheritFrom();
            return inheritFrom.getProjectId(mayInherit);
        }
        else
            return null;
    }
    public final Key getProjectKey(boolean mayInherit){
        Key projectKey = this.getProjectKey();
        if (null != projectKey)
            return projectKey;
        else if (mayInherit && this.hasInheritFrom()){
            Person inheritFrom = this.getInheritFrom();
            return inheritFrom.getProjectKey(mayInherit);
        }
        else
            return null;
    }
    public final Project getProject(){
        return this.getProject(Notation.MayInherit);
    }
    public final Project getProject(boolean mayInherit){
        Project project = this.project;
        if (null == project){
            /*
             * BigTable dereference
             */
            Key projectKey = this.getProjectKey(mayInherit);

            if (null != projectKey){
                project = Project.Get(projectKey);
                this.project = project;
            }
        }

        if (null == project && mayInherit && this.hasInheritFrom()){
            Person inheritFrom = this.getInheritFrom();
            return inheritFrom.getProject(Notation.MayInherit);
        }
        else
            return project;
    }
    public final boolean setProject(Project project, boolean withInheritance){
        if (IsNotEqual(this.project,this.getProject(withInheritance))){
            this.fieldStatistics.markDirty(Person.Field.Project);
            this.project = project;
            if (null != project){
                this.projectId = project.getId();
                this.projectKey = project.getKey();
            }
            else {
                this.projectId = null;
                this.projectKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean setProject(Project project){
        if (IsNotEqual(this.project,project)){
            this.fieldStatistics.markDirty(Person.Field.Project);
            this.project = project;
            if (null != project){
                this.projectId = project.getId();
                this.projectKey = project.getKey();
            }
            else {
                this.projectId = null;
                this.projectKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public boolean setProject(json.Json json){
        if (null == json)
            return false;
        else
            return this.setProject((Project)json.getValue(Project.class));
    }
    public final boolean hasInventory(boolean mayInherit){
        return (this.getInventory(mayInherit).isNotEmpty());
    }
    public final boolean hasNotInventory(boolean mayInherit){
        return (this.getInventory(mayInherit).isEmpty());
    }
    public final boolean dropInventory(){
        List.Primitive<Inventory> inventory = this.inventory;
        if (null != inventory){
            this.inventory = null;
            inventory.destroy();
            return true;
        }
        else
            return false;
    }
    public final List.Primitive<Inventory> getInventory(){
        return this.getInventory(Notation.MayInherit);
    }
    public final List.Primitive<Inventory> getInventory(boolean mayInherit){
        List.Primitive<Inventory> inventory = this.inventory;
        if (null == inventory){
            if (mayInherit && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                if (null != inheritFrom){
                    inventory = inheritFrom.getInventory(Notation.MayInherit);
                    if (null != inventory)
                        return inventory;
                }
            }
            /*
             * Collection type coersion
             */
            {
                Object tmp = new ListPrimitiveEnum((Person)this);
                inventory = (List.Primitive<Inventory>)tmp;
            }
            this.inventory = inventory;
            inventory.init();
        }
        return inventory;
    }
    public final boolean setInventory(List.Primitive<Inventory> inventory){
        if (IsNotEqual(this.inventory,inventory)){

            this.inventory = inventory;
            return true;
        }
        else
            return false;
    }
    public final boolean isEmptyInventory(){
        List.Primitive<Inventory> collection = this.inventory;
        if (null != collection)
            return collection.isEmpty();
        else
            return true;
    }
    public final boolean isNotEmptyInventory(){
        List.Primitive<Inventory> collection = this.inventory;
        if (null != collection)
            return (!collection.isEmpty());
        else
            return false;
    }
    public final Inventory getInventory(gap.data.ListFilter<Inventory> filter){
        if (null != filter){
            List.Primitive<Inventory> list = this.getInventory(Notation.MayInherit);
            for (Inventory item : list){
                if (filter.accept(item))
                    return item;
            }
            return null;
        }
        else
            throw new IllegalArgumentException();
    }
    /*
     * Data binding supports
     */
    public final Kind getClassKind(){
        return KIND;
    }
    public final String getClassName(){
        return ClassName;
    }
    public final gap.data.List<gap.data.Field> getClassFields(){
        gap.data.List re = new Person.Field.List(Field.values());
        /*
         * Compiler has a type astigmatism (parameterized interface gap.data.Field)
         */
        return (gap.data.List<gap.data.Field>)re;
    }
    public final gap.data.Field getClassFieldByName(String name){
        return Field.getField(name);
    }
    public json.Json toJson(){
        json.Json json = new json.ObjectJson();
        String logonId = this.getLogonId();
        json.set("logonId",logonId);
        String identifier = this.getIdentifier();
        json.set("identifier",identifier);
        Float nf = this.getNf();
        json.set("nf",nf);
        Float nt = this.getNt();
        json.set("nt",nt);
        Float st = this.getSt();
        json.set("st",st);
        Float sf = this.getSf();
        json.set("sf",sf);
        Date created = this.getCreated();
        json.set("created",created);
        Date completed = this.getCompleted();
        json.set("completed",completed);
        Project project = this.getProject();
        json.set("project",project);
        return json;
    }
    public boolean fromJson(json.Json json){
        boolean modified = false;
        modified = (this.setLogonId(json.at("logonId")) || modified);
        modified = (this.setNf(json.at("nf")) || modified);
        modified = (this.setNt(json.at("nt")) || modified);
        modified = (this.setSt(json.at("st")) || modified);
        modified = (this.setSf(json.at("sf")) || modified);
        modified = (this.setCreated(json.at("created")) || modified);
        modified = (this.setCompleted(json.at("completed")) || modified);
        modified = (this.setProject(json.at("project")) || modified);
        return modified;
    }
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;
        String logonIdRequest = req.getParameter("logonId");
        if (null != logonIdRequest && 0 < logonIdRequest.length()){
            try {
                String logonId = logonIdRequest;
                if (this.setLogonId(logonId)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"logonId",logonIdRequest,exc);
            }
        }
        String nfRequest = req.getParameter("nf");
        if (null != nfRequest && 0 < nfRequest.length()){
            try {
                Float nf = gap.Strings.FloatFromString(nfRequest);
                if (this.setNf(nf)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"nf",nfRequest,exc);
            }
        }
        String ntRequest = req.getParameter("nt");
        if (null != ntRequest && 0 < ntRequest.length()){
            try {
                Float nt = gap.Strings.FloatFromString(ntRequest);
                if (this.setNt(nt)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"nt",ntRequest,exc);
            }
        }
        String stRequest = req.getParameter("st");
        if (null != stRequest && 0 < stRequest.length()){
            try {
                Float st = gap.Strings.FloatFromString(stRequest);
                if (this.setSt(st)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"st",stRequest,exc);
            }
        }
        String sfRequest = req.getParameter("sf");
        if (null != sfRequest && 0 < sfRequest.length()){
            try {
                Float sf = gap.Strings.FloatFromString(sfRequest);
                if (this.setSf(sf)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"sf",sfRequest,exc);
            }
        }
        String createdRequest = req.getParameter("created");
        if (null != createdRequest && 0 < createdRequest.length()){
            try {
                Date created = gap.Strings.DateFromString(createdRequest);
                if (this.setCreated(created)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"created",createdRequest,exc);
            }
        }
        String completedRequest = req.getParameter("completed");
        if (null != completedRequest && 0 < completedRequest.length()){
            try {
                Date completed = gap.Strings.DateFromString(completedRequest);
                if (this.setCompleted(completed)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"completed",completedRequest,exc);
            }
        }
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Person)proto);
    }
    public final boolean updateFrom(Person proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        String logonId = proto.getLogonId(mayInherit);
        if (null != logonId && this.setLogonId(logonId)){
            change = true;
        }
        Float nf = proto.getNf(mayInherit);
        if (null != nf && this.setNf(nf)){
            change = true;
        }
        Float nt = proto.getNt(mayInherit);
        if (null != nt && this.setNt(nt)){
            change = true;
        }
        Float st = proto.getSt(mayInherit);
        if (null != st && this.setSt(st)){
            change = true;
        }
        Float sf = proto.getSf(mayInherit);
        if (null != sf && this.setSf(sf)){
            change = true;
        }
        Date created = proto.getCreated(mayInherit);
        if (null != created && this.setCreated(created)){
            change = true;
        }
        Date completed = proto.getCompleted(mayInherit);
        if (null != completed && this.setCompleted(completed)){
            change = true;
        }
        return change;
    }
    public java.io.Serializable valueStorage(gap.data.Field field){

        return Field.Storage( (Field)field, (Person)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Person)this, value);
    }
    public final Person markClean(){

        this.fieldStatistics.markClean();
        return (Person)this;
    }
    public final Person markDirty(){

        this.fieldStatistics.markDirty();
        return (Person)this;
    }
    public final Person markDirty(gap.data.Field field){

        this.fieldStatistics.markDirty(field);
        return (Person)this;
    }
    public final Person markDirty(java.io.Serializable instance){
        if (instance == this.logonId){
            gap.data.Field field = Person.Field.LogonId;
            return this.markDirty(field);
        }
        else if (instance == this.identifier){
            gap.data.Field field = Person.Field.Identifier;
            return this.markDirty(field);
        }
        else if (instance == this.inventory){
            gap.data.Field field = Person.Field.Inventory;
            return this.markDirty(field);
        }
        else if (instance == this.nf){
            gap.data.Field field = Person.Field.Nf;
            return this.markDirty(field);
        }
        else if (instance == this.nt){
            gap.data.Field field = Person.Field.Nt;
            return this.markDirty(field);
        }
        else if (instance == this.st){
            gap.data.Field field = Person.Field.St;
            return this.markDirty(field);
        }
        else if (instance == this.sf){
            gap.data.Field field = Person.Field.Sf;
            return this.markDirty(field);
        }
        else if (instance == this.created){
            gap.data.Field field = Person.Field.Created;
            return this.markDirty(field);
        }
        else if (instance == this.completed){
            gap.data.Field field = Person.Field.Completed;
            return this.markDirty(field);
        }
        else if (instance == this.project){
            gap.data.Field field = Person.Field.Project;
            return this.markDirty(field);
        }
        else
            return (Person)this;
    }
    public final Iterable<gap.data.Field> listClean(){

        return this.fieldStatistics.listClean();
    }
    public final Iterable<gap.data.Field> listDirty(){

        return this.fieldStatistics.listDirty();
    }
    public final boolean isClean(){

        return this.fieldStatistics.isClean();
    }
    public final boolean isDirty(){

        return this.fieldStatistics.isDirty();
    }
    public final gap.service.od.ClassDescriptor getClassDescriptorFor(){
        return Person.ClassDescriptorFor();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Person.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0)){
                    String id = this.getId();
                    return (null != id);
                }
                else
                    return false;
            case LogonId:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasLogonId(true);
                }
            case Identifier:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasIdentifier(true);
                }
            case Inventory:
            case Nf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasNf(true);
                }
            case Nt:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasNt(true);
                }
            case St:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasSt(true);
                }
            case Sf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasSf(true);
                }
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasCreated(true);
                }
            case Completed:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasCompleted(true);
                }
            case Project:
                if (name.has(1)){
                    Project project = this.getProject(true);
                    if (null != project)
                        return project.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasProject(true);
                }
            default:
                break;
            }
        }
        return super.hasVariable(name);
    }
    public String getVariable(TemplateName name){
        Field field = Person.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0))
                    return this.getId();
                else
                    return null;
            case LogonId:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getLogonId(true);
            case Identifier:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getIdentifier(true);
            case Inventory:
            case Nf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.FloatToString(this.getNf(true));
            case Nt:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.FloatToString(this.getNt(true));
            case St:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.FloatToString(this.getSt(true));
            case Sf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.FloatToString(this.getSf(true));
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCreated(true));
            case Completed:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCompleted(true));
            case Project:
                if (name.has(1)){
                    Project project = this.getProject(Notation.MayInherit);
                    if (null != project)
                        return project.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getProjectId(Notation.MayInherit);
            default:
                break;
            }
        }
        return super.getVariable(name);
    }
    public void setVariable(TemplateName name, String value){
        Field field = Person.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case LogonId:
                    throw new IllegalStateException(field.name());
                case Identifier:
                    throw new IllegalStateException(field.name());
                case Inventory:
                case Nf:
                    throw new IllegalStateException(field.name());
                case Nt:
                    throw new IllegalStateException(field.name());
                case St:
                    throw new IllegalStateException(field.name());
                case Sf:
                    throw new IllegalStateException(field.name());
                case Created:
                    throw new IllegalStateException(field.name());
                case Completed:
                    throw new IllegalStateException(field.name());
                case Project:

                    Project project = this.getProject(true);
                    if (null != project)
                        project.setVariable(new TemplateName(name),value);

                    return ;
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Person)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Person.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case LogonId:
                return null;
            case Identifier:
                return null;
            case Inventory:
                return null;
            case Nf:
                return null;
            case Nt:
                return null;
            case St:
                return null;
            case Sf:
                return null;
            case Created:
                return null;
            case Completed:
                return null;
            case Project:
                Project project = this.getProject(true);
                if (null != project){
                    if (name.has(1))
                        return project.getSection(new TemplateName(name));
                    else
                        return new gap.util.ShortList( this, project);
                }
                else
                    return null;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getSection(name);
        }
    }
    public Person clone(){
        return (Person)super.clone();
    }
    public String pathto(){
        return PathTo(this.getId());
    }
    public String pathto(String subpath){
        StringBuilder string = new StringBuilder();
        string.append(this.getId());
        if (null != subpath){
            if (0 == subpath.length() || '/' != subpath.charAt(0))
                string.append('/');
            string.append(subpath);
        }
        return PathTo(string.toString());
    }
}
