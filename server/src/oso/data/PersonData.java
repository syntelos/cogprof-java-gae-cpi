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
import cpi.groups.Group;

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
@Generated(value={"gap.service.OD","BeanData.java"},date="2010-09-19T10:30:58.755Z")
public abstract class PersonData
    extends gap.data.BigTable
    implements DataInheritance<Person>
{

    private final static long serialVersionUID = 6;

    public final static Kind KIND = Kind.Create("Person","oso.data","Person","/people");

    public final static String ClassName = "Person";

    public final static String DefaultSortBy = "logonId";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Person.class);
    }




    public final static Key KeyLongIdFor(String logonId){
        String id = IdFor( logonId);
        return KeyLongFor(id);
    }


    public final static String IdFor(String logonId){
        if (null != logonId){
            String logonIdString = logonId;
            return gap.data.Hash.For(logonIdString);
        }
        else
            throw new IllegalArgumentException();
    }


    public final static Person ForLongLogonId(String logonId){
        if (null != logonId){
            Key key = KeyLongIdFor( logonId);
            Person instance = (Person)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Person)gap.data.Store.Query1(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }


    public final static Person GetCreateLong(String logonId){
        Person person = ForLongLogonId( logonId);
        if (null == person){
            person = new Person( logonId);
            person = (Person)gap.data.Store.Put(person);
        }
        return person;
    }



    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Person ForLongId(String id){
        if (null != id){
            Key key = KeyLongFor(id);
            Person instance = (Person)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Person)gap.data.Store.Query1(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    public final static Person Get(Key key){
        if (null != key){
            Person instance = (Person)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Person)gap.data.Store.Query1(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = CreateQueryFor(key);
            return gap.data.Store.QueryKey1(q);
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
     * Drop the instance and any children of its key from the world,
     * memcache and store.
     */
    public final static void Delete(Person instance){
        if (null != instance){
            Key key = instance.getKey();
            gap.data.Store.DeleteCollection(KIND,new Query(key));
            gap.data.Store.Delete(key);
        }
    }
    /**
     * Drop the instance from memcache, exclusively.
     */
    public final static void Clean(Person instance){
        if (null != instance){
            Key key = instance.getKey();
            gap.data.Store.Clean(key);
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Person instance){
        if (null != instance){
            gap.data.Store.Put(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Person instance){
        if (null != instance){
            gap.data.Store.Put(instance);
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
            return (Person)gap.data.Store.Query1(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Person> QueryN(Query query, Page page){
        if (null != query && null != page)
            return gap.data.Store.QueryN(query,page);
        else
            throw new IllegalArgumentException();
    }
    public final static Key QueryKey1(Query query){
        if (null != query)
            return gap.data.Store.QueryKey1(query);
        else
            throw new IllegalArgumentException();
    }
    public final static List.Primitive<Key> QueryKeyN(Query query, Page page){
        if (null != query)
            return gap.data.Store.QueryKeyN(query,page);
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
        Inventory("inventory",Type.PrimitiveCollection),
        Nf("nf",Type.Primitive),
        Nt("nt",Type.Primitive),
        St("st",Type.Primitive),
        Sf("sf",Type.Primitive),
        Created("created",Type.Primitive),
        Completed("completed",Type.Primitive),
        Group("group",Type.BigTable);

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
            case Group:
                return instance.getGroup(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Person");
            }
        }
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
            case Group:
                return instance.setGroup(cpi.groups.Group.FromObject(value));
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


        Field(String fieldName, Type fieldType){
            if (null != fieldName && null != fieldType){
                this.fieldName = fieldName;
                this.fieldType = fieldType;
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
        public String toString(){
            return this.fieldName;
        }
    }

    private volatile transient Person inheritFrom;


    private volatile String logonId;
    private volatile Float nf;
    private volatile Float nt;
    private volatile Float st;
    private volatile Float sf;
    private volatile Date created;
    private volatile Date completed;
    private volatile Key groupKey;
    private volatile transient Group group;
    private volatile List.Primitive<Inventory> inventory;




    protected PersonData() {
        super();
    }
    protected PersonData(String logonId) {
        super();
        this.setLogonId(logonId);
        String id = IdFor( logonId);
        Key key = KeyLongFor(id);
        this.setKey(key);
    }



    public void destroy(){
        this.inheritFrom = null;
        this.datastoreEntity = null;
        this.logonId = null;
        this.nf = null;
        this.nt = null;
        this.st = null;
        this.sf = null;
        this.created = null;
        this.completed = null;
        this.group = null;
        List.Primitive<Inventory> inventory = this.inventory;
        if (null != inventory){
            this.inventory = null;
            inventory.destroy();
        }
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
            this.logonId = null;
            return true;
        }
        else
            return false;
    }
    public final String getLogonId(){
        return this.logonId;
    }
    public final String getLogonId(boolean mayInherit){
        return this.getLogonId();
    }
    public final boolean setLogonId(String logonId){
        if (IsNotEqual(this.logonId,logonId)){
            this.logonId = logonId;
            return true;
        }
        else
            return false;
    }
    public final boolean hasNf(boolean mayInherit){
        return (null != this.getNf(mayInherit));
    }
    public final boolean hasNotNf(boolean mayInherit){
        return (null == this.getNf(mayInherit));
    }
    public final boolean dropNf(){
        if (null != this.nf){
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
            this.nf = nf;
            return true;
        }
        else
            return false;
    }
    public final boolean setNf(Float nf){
        if (IsNotEqual(this.nf,nf)){
            this.nf = nf;
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
            this.nt = nt;
            return true;
        }
        else
            return false;
    }
    public final boolean setNt(Float nt){
        if (IsNotEqual(this.nt,nt)){
            this.nt = nt;
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
            this.st = st;
            return true;
        }
        else
            return false;
    }
    public final boolean setSt(Float st){
        if (IsNotEqual(this.st,st)){
            this.st = st;
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
            this.sf = sf;
            return true;
        }
        else
            return false;
    }
    public final boolean setSf(Float sf){
        if (IsNotEqual(this.sf,sf)){
            this.sf = sf;
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
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean setCreated(Date created){
        if (IsNotEqual(this.created,created)){
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean hasCompleted(boolean mayInherit){
        return (null != this.getCompleted(mayInherit));
    }
    public final boolean hasNotCompleted(boolean mayInherit){
        return (null == this.getCompleted(mayInherit));
    }
    public final boolean dropCompleted(){
        if (null != this.completed){
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
            this.completed = completed;
            return true;
        }
        else
            return false;
    }
    public final boolean setCompleted(Date completed){
        if (IsNotEqual(this.completed,completed)){
            this.completed = completed;
            return true;
        }
        else
            return false;
    }
    public final boolean hasGroup(boolean mayInherit){
        return (null != this.getGroup(mayInherit));
    }
    public final boolean hasNotGroup(boolean mayInherit){
        return (null == this.getGroup(mayInherit));
    }
    public final boolean dropGroup(){
        if (null != this.group){
            this.group = null;
            this.groupKey = null;
            return true;
        }
        else
            return false;
    }
    public final Group getGroup(){
        return this.getGroup(Notation.MayInherit);
    }
    public final Group getGroup(boolean mayInherit){
        Group group = this.group;
        if (null == group){
            Key groupKey = this.groupKey;
            if (null == groupKey && mayInherit && this.hasInheritFrom()){
                Person inheritFrom = this.getInheritFrom();
                return inheritFrom.getGroup(Notation.MayInherit);
            }
            else if (null != groupKey){
                group = Group.Get(groupKey);
                this.group = group;
            }
        }
        return group;
    }
    public final boolean setGroup(Group group, boolean withInheritance){
        if (IsNotEqual(this.group,this.getGroup(withInheritance))){
            this.group = group;
            if (null != group)
                this.groupKey = group.getKey();
            else
                this.groupKey = null;
            return true;
        }
        else
            return false;
    }
    public final boolean setGroup(Group group){
        if (IsNotEqual(this.group,group)){
            this.group = group;
            if (null != group)
                this.groupKey = group.getKey();
            else
                this.groupKey = null;
            return true;
        }
        else
            return false;
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
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;
        String nfRequest = req.getParameter("nf");
        try {
            Float nf = Strings.FloatFromString(nfRequest);
            if (this.setNf(nf)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"nf",nfRequest,exc);
        }
        String ntRequest = req.getParameter("nt");
        try {
            Float nt = Strings.FloatFromString(ntRequest);
            if (this.setNt(nt)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"nt",ntRequest,exc);
        }
        String stRequest = req.getParameter("st");
        try {
            Float st = Strings.FloatFromString(stRequest);
            if (this.setSt(st)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"st",stRequest,exc);
        }
        String sfRequest = req.getParameter("sf");
        try {
            Float sf = Strings.FloatFromString(sfRequest);
            if (this.setSf(sf)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"sf",sfRequest,exc);
        }
        String createdRequest = req.getParameter("created");
        try {
            Date created = Strings.DateFromString(createdRequest);
            if (this.setCreated(created)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"created",createdRequest,exc);
        }
        String completedRequest = req.getParameter("completed");
        try {
            Date completed = Strings.DateFromString(completedRequest);
            if (this.setCompleted(completed)){
                change = true;
            }
        }
        catch (RuntimeException exc){
            throw new ValidationError(ClassName,"completed",completedRequest,exc);
        }
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Person)proto);
    }
    public final boolean updateFrom(Person proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
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
    public final gap.service.od.ClassDescriptor getClassDescriptorFor(){
        return ClassDescriptorFor();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Field.For(name.getComponent(0));
        if (null != field){
            switch (field){
            case LogonId:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasLogonId(true);
            case Nf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasNf(true);
            case Nt:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasNt(true);
            case St:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasSt(true);
            case Sf:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasSf(true);
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasCreated(true);
            case Completed:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasCompleted(true);
            case Group:
                if (name.has(1)){
                    Group group = this.getGroup(true);
                    if (null != group)
                        return group.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else
                    return this.hasGroup(true);
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.hasVariable(name);
        }
    }
    public String getVariable(TemplateName name){
        Field field = Field.For(name.getComponent(0));
        if (null != field){
            switch (field){
            case LogonId:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getLogonId(true);
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
            case Group:
                if (name.has(1)){
                    Group group = this.getGroup(true);
                    if (null != group)
                        return group.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return null;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getVariable(name);
        }
    }
    public void setVariable(TemplateName name, String value){
        Field field = Field.For(name.getComponent(0));
        if (null != field){
            if (name.has(1)){
                switch (field){
                case LogonId:
                    throw new IllegalStateException(field.name());
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
                case Group:

                    Group group = this.getGroup(true);
                    if (null != group)
                        group.setVariable(new TemplateName(name),value);

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
        Field field = Field.For(name.getComponent(0));
        if (null != field){
            switch (field){
            case LogonId:
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
            case Group:
                Group group = this.getGroup(true);
                if (null != group)
                    return group.getSection(new TemplateName(name));
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
}
