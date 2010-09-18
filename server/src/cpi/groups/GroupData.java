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
 * @see Group
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2010-09-18T07:35:13.364Z")
public abstract class GroupData
    extends gap.data.BigTable
    implements DataInheritance<Group>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Group","cpi.groups","Group","/groups");

    public final static String ClassName = "Group";

    public final static String DefaultSortBy = "name";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Group.class);
    }




    public final static Key KeyLongIdFor(String name){
        String id = IdFor( name);
        return KeyLongFor(id);
    }


    public final static String IdFor(String name){
        if (null != name){
            String nameString = name;
            return gap.data.Hash.For(nameString);
        }
        else
            throw new IllegalArgumentException();
    }


    public final static Group ForLongName(String name){
        if (null != name){
            Key key = KeyLongIdFor( name);
            Group instance = (Group)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Group)gap.data.Store.Query1(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }


    public final static Group GetCreateLong(String name){
        Group group = ForLongName( name);
        if (null == group){
            group = new Group( name);
            group = (Group)gap.data.Store.Put(group);
        }
        return group;
    }



    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Group ForLongId(String id){
        if (null != id){
            Key key = KeyLongFor(id);
            Group instance = (Group)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Group)gap.data.Store.Query1(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    public final static Group Get(Key key){
        if (null != key){
            Group instance = (Group)gap.data.Store.Get(key);
            if (null != instance)
                return instance;
            else {
                Query q = CreateQueryFor(key);
                return (Group)gap.data.Store.Query1(q);
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
    public final static Group FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Group)
            return (Group)value;
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
    public final static void Delete(Group instance){
        if (null != instance){
            Key key = instance.getKey();
            gap.data.Store.DeleteCollection(KIND,new Query(key));
            gap.data.Store.Delete(key);
        }
    }
    /**
     * Drop the instance from memcache, exclusively.
     */
    public final static void Clean(Group instance){
        if (null != instance){
            Key key = instance.getKey();
            gap.data.Store.Clean(key);
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Group instance){
        if (null != instance){
            gap.data.Store.Put(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Group instance){
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
    
    
    public final static Group Query1(Query query){
        if (null != query)
            return (Group)gap.data.Store.Query1(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Group> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Group}
     */
    public static enum Field
        implements gap.data.Field<Group.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Name("name",Type.Primitive),
        Owner("owner",Type.BigTable),
        Created("created",Type.Primitive);

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
        public static Object Get(Field field, Group instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Name:
                return instance.getName(mayInherit);
            case Owner:
                return instance.getOwner(mayInherit);
            case Created:
                return instance.getCreated(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Group");
            }
        }
        public static boolean Set(Field field, Group instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case Name:
                return instance.setName(gap.Objects.StringFromObject(value));
            case Owner:
                return instance.setOwner(oso.data.Person.FromObject(value));
            case Created:
                return instance.setCreated(gap.Objects.DateFromObject(value));
            default:
                throw new IllegalArgumentException(field.toString()+" in Group");
            }
        }

        public final static class List
            extends gap.util.ArrayList<Group.Field>
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

    private volatile transient Group inheritFrom;


    private volatile String name;
    private volatile Key ownerKey;
    private volatile transient Person owner;
    private volatile Date created;




    protected GroupData() {
        super();
    }
    protected GroupData(String name) {
        super();
        this.setName(name);
        String id = IdFor( name);
        Key key = KeyLongFor(id);
        this.setKey(key);
    }



    public void destroy(){
        this.inheritFrom = null;
        this.datastoreEntity = null;
        this.name = null;
        this.owner = null;
        this.created = null;
    }
    public final boolean hasInheritFrom(){
        return (null != this.inheritFrom || null != this.inheritFromKey);
    }
    public final boolean hasNotInheritFrom(){
        return (null == this.inheritFrom && null == this.inheritFromKey);
    }
    public final Group getInheritFrom(){
        Group inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Group.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Group ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){
            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Group ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){
            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean hasName(boolean mayInherit){
        return (null != this.getName(mayInherit));
    }
    public final boolean hasNotName(boolean mayInherit){
        return (null == this.getName(mayInherit));
    }
    public final boolean dropName(){
        if (null != this.name){
            this.name = null;
            return true;
        }
        else
            return false;
    }
    public final String getName(){
        return this.name;
    }
    public final String getName(boolean mayInherit){
        return this.getName();
    }
    public final boolean setName(String name){
        if (IsNotEqual(this.name,name)){
            this.name = name;
            return true;
        }
        else
            return false;
    }
    public final boolean hasOwner(boolean mayInherit){
        return (null != this.getOwner(mayInherit));
    }
    public final boolean hasNotOwner(boolean mayInherit){
        return (null == this.getOwner(mayInherit));
    }
    public final boolean dropOwner(){
        if (null != this.owner){
            this.owner = null;
            this.ownerKey = null;
            return true;
        }
        else
            return false;
    }
    public final Person getOwner(){
        return this.getOwner(Notation.MayInherit);
    }
    public final Person getOwner(boolean mayInherit){
        Person owner = this.owner;
        if (null == owner){
            Key ownerKey = this.ownerKey;
            if (null == ownerKey && mayInherit && this.hasInheritFrom()){
                Group inheritFrom = this.getInheritFrom();
                return inheritFrom.getOwner(Notation.MayInherit);
            }
            else if (null != ownerKey){
                owner = Person.Get(ownerKey);
                this.owner = owner;
            }
        }
        return owner;
    }
    public final boolean setOwner(Person owner, boolean withInheritance){
        if (IsNotEqual(this.owner,this.getOwner(withInheritance))){
            this.owner = owner;
            if (null != owner)
                this.ownerKey = owner.getKey();
            else
                this.ownerKey = null;
            return true;
        }
        else
            return false;
    }
    public final boolean setOwner(Person owner){
        if (IsNotEqual(this.owner,owner)){
            this.owner = owner;
            if (null != owner)
                this.ownerKey = owner.getKey();
            else
                this.ownerKey = null;
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
                Group inheritFrom = this.getInheritFrom();
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
        gap.data.List re = new Group.Field.List(Field.values());
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
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Group)proto);
    }
    public final boolean updateFrom(Group proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        Date created = proto.getCreated(mayInherit);
        if (null != created && this.setCreated(created)){
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
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasName(true);
            case Owner:
                if (name.has(1)){
                    Person owner = this.getOwner(true);
                    if (null != owner)
                        return owner.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else
                    return this.hasOwner(true);
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.hasCreated(true);
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
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getName(true);
            case Owner:
                if (name.has(1)){
                    Person owner = this.getOwner(true);
                    if (null != owner)
                        return owner.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return null;
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCreated(true));
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
                case Name:
                    throw new IllegalStateException(field.name());
                case Owner:

                    Person owner = this.getOwner(true);
                    if (null != owner)
                        owner.setVariable(new TemplateName(name),value);

                    return ;
                case Created:
                    throw new IllegalStateException(field.name());
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Group)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Field.For(name.getComponent(0));
        if (null != field){
            switch (field){
            case Name:
                return null;
            case Owner:
                Person owner = this.getOwner(true);
                if (null != owner)
                    return owner.getSection(new TemplateName(name));
                else
                    return null;
            case Created:
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
