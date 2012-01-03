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
import cpi.Redirect;
import cpi.Margins;

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
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-01-03T19:10:43.103Z")
public abstract class GroupData
    extends gap.data.BigTable
    implements DataInheritance<Group>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Group","cpi.groups","Group","/groups");

    public final static String ClassName = "Group";

    public final static String DefaultSortBy = "identifier";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Group.class);
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
        String id = Group.IdFor( identifier);
        return KeyLongFor(id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyLongIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Group.KeyLongIdFor((String)args[0]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyLongFor}
     */
    public final static Key KeyFor(Object... args){
        return Group.KeyLongFor( (String)args[0]);
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
    public final static Group ForLongIdentifier(String identifier){
        if (null != identifier){
            Key key = Group.KeyLongIdFor( identifier);
            Group instance = (Group)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Group.CreateQueryFor(key);
                return (Group)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create
     */
    public static Group GetCreateLong(String identifier){
        return GetCreateLongIdentifier( identifier);
    }
    /**
     * Instance lookup or create
     */
    public final static Group GetCreateLongIdentifier(String identifier){
        Group group = Group.ForLongIdentifier( identifier);
        if (null == group){
            group = new Group( identifier);
            group = (Group)gap.data.Store.PutClass(group);
        }
        return group;
    }


    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Group ForLongId(String id){
        if (null != id){
            Key key = Group.KeyLongFor(id);
            Group instance = (Group)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Group.CreateQueryFor(key);
                return (Group)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Group Get(Key key){
        if (null != key){
            Group instance = (Group)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Group.CreateQueryFor(key);
                return (Group)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Group.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
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
     * Anonymous random key cannot be mapped to network identifier
     * @see Group#IdFor
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
    public final static void Delete(Group instance){
        if (null != instance){

            Delete(instance.getKey());
        }
    }
    /**
     * Drop the instance from memcache and datastore.
     */
    public final static void Delete(Key instanceKey){
        if (null != instanceKey){

            gap.data.Store.DeleteKey(instanceKey);
        }
    }
    /**
     * Drop the instance from memcache, exclusively.
     */
    public final static void Clean(Group instance){
        if (null != instance){
            Key key = instance.getKey();
            gap.data.Store.CleanKey(key);
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Group instance){
        if (null != instance){
            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Group instance){
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
    
    public final static Group Query1(Query query){
        if (null != query)
            return (Group)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Group> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Group}
     */
    public static enum Field
        implements gap.data.Field<Group.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Name("name",Type.Primitive),
        Identifier("identifier",Type.Primitive),
        Owner("owner",Type.BigTable),
        Admin("admin",Type.BigTable),
        Created("created",Type.Primitive),
        Cleaned("cleaned",Type.Primitive),
        Redirect("redirect",Type.Primitive),
        Margins("margins",Type.Primitive);

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
            extends gap.data.Field.Statistics<Group.Field>
        {
            public Statistics(){
                super(Group.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
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
            case Identifier:
                return instance.getIdentifier(mayInherit);
            case Owner:
                return instance.getOwnerId();
            case Admin:
                return instance.getAdminId();
            case Created:
                return instance.getCreated(mayInherit);
            case Cleaned:
                return instance.getCleaned(mayInherit);
            case Redirect:
                return instance.getRedirect(mayInherit);
            case Margins:
                return instance.getMargins(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Group");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
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
            case Identifier:
                return instance.setIdentifier(gap.Objects.StringFromObject(value));
            case Owner:
                return instance.setOwnerId(gap.Objects.StringFromObject(value));
            case Admin:
                return instance.setAdminId(gap.Objects.StringFromObject(value));
            case Created:
                return instance.setCreated(gap.Objects.DateFromObject(value));
            case Cleaned:
                return instance.setCleaned(gap.Objects.DateFromObject(value));
            case Redirect:
                return instance.setRedirect((Redirect)gap.Objects.SerializableFromObject(value));
            case Margins:
                return instance.setMargins((Margins)gap.Objects.SerializableFromObject(value));
            default:
                throw new IllegalArgumentException(field.toString()+" in Group");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Group instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Name:
                return instance.getName(MayNotInherit);
            case Identifier:
                return instance.getIdentifier(MayNotInherit);
            case Owner:
                return instance.getOwnerId();
            case Admin:
                return instance.getAdminId();
            case Created:
                return instance.getCreated(MayNotInherit);
            case Cleaned:
                return instance.getCleaned(MayNotInherit);
            case Redirect:{
                Redirect _redirect = instance.getRedirect(MayNotInherit);
                return Serialize.To(field,_redirect);
            }
            case Margins:{
                Margins _margins = instance.getMargins(MayNotInherit);
                return Serialize.To(field,_margins);
            }
            default:
                throw new IllegalArgumentException(field.toString()+" in Group");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Group instance, java.io.Serializable value){
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
            case Name:
                instance.setName( (String)value);
                return;
            case Identifier:
                instance.setIdentifier( (String)value);
                return;
            case Owner:
                instance.setOwnerId( (String)value);
                return;
            case Admin:
                instance.setAdminId( (String)value);
                return;
            case Created:
                instance.setCreated( (Date)value);
                return;
            case Cleaned:
                instance.setCleaned( (Date)value);
                return;
            case Redirect:{
                Redirect _redirect = (Redirect)Serialize.From(field,value);
                instance.setRedirect( _redirect);
                return;
            }
            case Margins:{
                Margins _margins = (Margins)Serialize.From(field,value);
                instance.setMargins( _margins);
                return;
            }
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

    private final Group.Field.Statistics fieldStatistics = new Group.Field.Statistics();

    private transient Group inheritFrom;


    private String name;
    private String identifier;
    private String ownerId;
    private transient Key ownerKey;
    private transient Person owner;
    private String adminId;
    private transient Key adminKey;
    private transient Person admin;
    private Date created;
    private Date cleaned;
    private Redirect redirect;
    private Margins margins;




    protected GroupData() {
        super();
    }
    protected GroupData(String identifier) {
        super();
        this.setIdentifier(identifier);
        {
            final String id = Group.IdFor(identifier);
            final Key key = Group.KeyLongFor(id);
            this.setKey(key);
        }
    }


    public void destroy(){
        this.inheritFrom = null;
        this.name = null;
        this.identifier = null;
        this.owner = null;
        this.admin = null;
        this.created = null;
        this.cleaned = null;
        this.redirect = null;
        this.margins = null;
    }
    public final String getId(){

        String id = Group.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Group.IdFor(this.identifier);
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
            this.key = Group.KeyLongFor(id);
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
            this.fieldStatistics.markDirty(Field.Name);
            this.name = null;
            return true;
        }
        else
            return false;
    }
    public final String getName(){
        return this.getName(Notation.MayInherit);
    }
    public final String getName(boolean mayInherit){
        if (mayInherit){
            String name = this.name;
            if (null == name && this.hasInheritFrom()){
                Group inheritFrom = this.getInheritFrom();
                return inheritFrom.getName(Notation.MayInherit);
            }
            return name;
        }
        else
            return this.name;
    }
    public final boolean setName(String name, boolean withInheritance){
        if (IsNotEqual(this.name,this.getName(withInheritance))){
            this.fieldStatistics.markDirty(Field.Name);
            this.name = name;
            return true;
        }
        else
            return false;
    }
    public final boolean setName(String name){
        if (IsNotEqual(this.name,name)){
            this.fieldStatistics.markDirty(Field.Name);
            this.name = name;
            return true;
        }
        else
            return false;
    }
    public final boolean hasIdentifier(boolean mayInherit){
        return (null != this.getIdentifier(mayInherit));
    }
    public final boolean hasNotIdentifier(boolean mayInherit){
        return (null == this.getIdentifier(mayInherit));
    }
    public final boolean dropIdentifier(){
        if (null != this.identifier){
            this.fieldStatistics.markDirty(Field.Identifier);
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
            this.fieldStatistics.markDirty(Field.Identifier);
            this.identifier = identifier;
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
            this.fieldStatistics.markDirty(Field.Owner);
            this.owner = null;
            this.ownerId = null;
            this.ownerKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getOwnerId(){
        return this.ownerId;
    }
    public final boolean setOwnerId(String ownerId){
        if (IsNotEqual(this.ownerId,ownerId)){
            this.fieldStatistics.markDirty(Field.Owner);
            this.ownerId = ownerId;
            this.ownerKey = null;
            this.owner = null;
            return true;
        }
        else
            return false;
    }
    public final Key getOwnerKey(){
        if (null == this.ownerKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.ownerId){
                this.ownerKey = Person.KeyLongFor(this.ownerId);
            }
        }
        return this.ownerKey;
    }
    public final String getOwnerId(boolean mayInherit){
        if (null != this.ownerId)
            return this.ownerId;
        else if (mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getOwnerId(mayInherit);
        }
        else
            return null;
    }
    public final Key getOwnerKey(boolean mayInherit){
        Key ownerKey = this.getOwnerKey();
        if (null != ownerKey)
            return ownerKey;
        else if (mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getOwnerKey(mayInherit);
        }
        else
            return null;
    }
    public final Person getOwner(){
        return this.getOwner(Notation.MayInherit);
    }
    public final Person getOwner(boolean mayInherit){
        Person owner = this.owner;
        if (null == owner){
            /*
             * BigTable dereference
             */
            Key ownerKey = this.getOwnerKey(mayInherit);

            if (null != ownerKey){
                owner = Person.Get(ownerKey);
                this.owner = owner;
            }
        }

        if (null == owner && mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getOwner(Notation.MayInherit);
        }
        else
            return owner;
    }
    public final boolean setOwner(Person owner, boolean withInheritance){
        if (IsNotEqual(this.owner,this.getOwner(withInheritance))){
            this.fieldStatistics.markDirty(Field.Owner);
            this.owner = owner;
            if (null != owner){
                this.ownerId = owner.getId();
                this.ownerKey = owner.getKey();
            }
            else {
                this.ownerId = null;
                this.ownerKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean setOwner(Person owner){
        if (IsNotEqual(this.owner,owner)){
            this.fieldStatistics.markDirty(Field.Owner);
            this.owner = owner;
            if (null != owner){
                this.ownerId = owner.getId();
                this.ownerKey = owner.getKey();
            }
            else {
                this.ownerId = null;
                this.ownerKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean hasAdmin(boolean mayInherit){
        return (null != this.getAdmin(mayInherit));
    }
    public final boolean hasNotAdmin(boolean mayInherit){
        return (null == this.getAdmin(mayInherit));
    }
    public final boolean dropAdmin(){
        if (null != this.admin){
            this.fieldStatistics.markDirty(Field.Admin);
            this.admin = null;
            this.adminId = null;
            this.adminKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getAdminId(){
        return this.adminId;
    }
    public final boolean setAdminId(String adminId){
        if (IsNotEqual(this.adminId,adminId)){
            this.fieldStatistics.markDirty(Field.Admin);
            this.adminId = adminId;
            this.adminKey = null;
            this.admin = null;
            return true;
        }
        else
            return false;
    }
    public final Key getAdminKey(){
        if (null == this.adminKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.adminId){
                this.adminKey = Person.KeyLongFor(this.adminId);
            }
        }
        return this.adminKey;
    }
    public final String getAdminId(boolean mayInherit){
        if (null != this.adminId)
            return this.adminId;
        else if (mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getAdminId(mayInherit);
        }
        else
            return null;
    }
    public final Key getAdminKey(boolean mayInherit){
        Key adminKey = this.getAdminKey();
        if (null != adminKey)
            return adminKey;
        else if (mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getAdminKey(mayInherit);
        }
        else
            return null;
    }
    public final Person getAdmin(){
        return this.getAdmin(Notation.MayInherit);
    }
    public final Person getAdmin(boolean mayInherit){
        Person admin = this.admin;
        if (null == admin){
            /*
             * BigTable dereference
             */
            Key adminKey = this.getAdminKey(mayInherit);

            if (null != adminKey){
                admin = Person.Get(adminKey);
                this.admin = admin;
            }
        }

        if (null == admin && mayInherit && this.hasInheritFrom()){
            Group inheritFrom = this.getInheritFrom();
            return inheritFrom.getAdmin(Notation.MayInherit);
        }
        else
            return admin;
    }
    public final boolean setAdmin(Person admin, boolean withInheritance){
        if (IsNotEqual(this.admin,this.getAdmin(withInheritance))){
            this.fieldStatistics.markDirty(Field.Admin);
            this.admin = admin;
            if (null != admin){
                this.adminId = admin.getId();
                this.adminKey = admin.getKey();
            }
            else {
                this.adminId = null;
                this.adminKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean setAdmin(Person admin){
        if (IsNotEqual(this.admin,admin)){
            this.fieldStatistics.markDirty(Field.Admin);
            this.admin = admin;
            if (null != admin){
                this.adminId = admin.getId();
                this.adminKey = admin.getKey();
            }
            else {
                this.adminId = null;
                this.adminKey = null;
            }
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
            this.fieldStatistics.markDirty(Field.Created);
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
            this.fieldStatistics.markDirty(Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean setCreated(Date created){
        if (IsNotEqual(this.created,created)){
            this.fieldStatistics.markDirty(Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean hasCleaned(boolean mayInherit){
        return (null != this.getCleaned(mayInherit));
    }
    public final boolean hasNotCleaned(boolean mayInherit){
        return (null == this.getCleaned(mayInherit));
    }
    public final boolean dropCleaned(){
        if (null != this.cleaned){
            this.fieldStatistics.markDirty(Field.Cleaned);
            this.cleaned = null;
            return true;
        }
        else
            return false;
    }
    public final Date getCleaned(){
        return this.getCleaned(Notation.MayInherit);
    }
    public final Date getCleaned(boolean mayInherit){
        if (mayInherit){
            Date cleaned = this.cleaned;
            if (null == cleaned && this.hasInheritFrom()){
                Group inheritFrom = this.getInheritFrom();
                return inheritFrom.getCleaned(Notation.MayInherit);
            }
            return cleaned;
        }
        else
            return this.cleaned;
    }
    public final boolean setCleaned(Date cleaned, boolean withInheritance){
        if (IsNotEqual(this.cleaned,this.getCleaned(withInheritance))){
            this.fieldStatistics.markDirty(Field.Cleaned);
            this.cleaned = cleaned;
            return true;
        }
        else
            return false;
    }
    public final boolean setCleaned(Date cleaned){
        if (IsNotEqual(this.cleaned,cleaned)){
            this.fieldStatistics.markDirty(Field.Cleaned);
            this.cleaned = cleaned;
            return true;
        }
        else
            return false;
    }
    public final boolean hasRedirect(boolean mayInherit){
        return (null != this.getRedirect(mayInherit));
    }
    public final boolean hasNotRedirect(boolean mayInherit){
        return (null == this.getRedirect(mayInherit));
    }
    public final boolean dropRedirect(){
        if (null != this.redirect){
            this.fieldStatistics.markDirty(Field.Redirect);
            this.redirect = null;
            return true;
        }
        else
            return false;
    }
    public final Redirect getRedirect(){
        return this.getRedirect(Notation.MayInherit);
    }
    public final Redirect getRedirect(boolean mayInherit){
        if (mayInherit){
            Redirect redirect = this.redirect;
            if (null == redirect && this.hasInheritFrom()){
                Group inheritFrom = this.getInheritFrom();
                return inheritFrom.getRedirect(Notation.MayInherit);
            }
            return redirect;
        }
        else
            return this.redirect;
    }
    public final boolean setRedirect(Redirect redirect, boolean withInheritance){
        if (IsNotEqual(this.redirect,this.getRedirect(withInheritance))){
            this.fieldStatistics.markDirty(Field.Redirect);
            this.redirect = redirect;
            return true;
        }
        else
            return false;
    }
    public final boolean setRedirect(Redirect redirect){
        if (IsNotEqual(this.redirect,redirect)){
            this.fieldStatistics.markDirty(Field.Redirect);
            this.redirect = redirect;
            return true;
        }
        else
            return false;
    }
    public final boolean hasMargins(boolean mayInherit){
        return (null != this.getMargins(mayInherit));
    }
    public final boolean hasNotMargins(boolean mayInherit){
        return (null == this.getMargins(mayInherit));
    }
    public final boolean dropMargins(){
        if (null != this.margins){
            this.fieldStatistics.markDirty(Field.Margins);
            this.margins = null;
            return true;
        }
        else
            return false;
    }
    public final Margins getMargins(){
        return this.getMargins(Notation.MayInherit);
    }
    public final Margins getMargins(boolean mayInherit){
        if (mayInherit){
            Margins margins = this.margins;
            if (null == margins && this.hasInheritFrom()){
                Group inheritFrom = this.getInheritFrom();
                return inheritFrom.getMargins(Notation.MayInherit);
            }
            return margins;
        }
        else
            return this.margins;
    }
    public final boolean setMargins(Margins margins, boolean withInheritance){
        if (IsNotEqual(this.margins,this.getMargins(withInheritance))){
            this.fieldStatistics.markDirty(Field.Margins);
            this.margins = margins;
            return true;
        }
        else
            return false;
    }
    public final boolean setMargins(Margins margins){
        if (IsNotEqual(this.margins,margins)){
            this.fieldStatistics.markDirty(Field.Margins);
            this.margins = margins;
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
        String nameRequest = req.getParameter("name");
        if (null != nameRequest && 0 < nameRequest.length()){
            try {
                String name = nameRequest;
                if (this.setName(name)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"name",nameRequest,exc);
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
        String cleanedRequest = req.getParameter("cleaned");
        if (null != cleanedRequest && 0 < cleanedRequest.length()){
            try {
                Date cleaned = gap.Strings.DateFromString(cleanedRequest);
                if (this.setCleaned(cleaned)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"cleaned",cleanedRequest,exc);
            }
        }
        String redirectRequest = req.getParameter("redirect");
        if (null != redirectRequest && 0 < redirectRequest.length()){
            try {
                Redirect redirect = (Redirect)gap.Strings.SerializableFromString(redirectRequest);
                if (this.setRedirect(redirect)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"redirect",redirectRequest,exc);
            }
        }
        String marginsRequest = req.getParameter("margins");
        if (null != marginsRequest && 0 < marginsRequest.length()){
            try {
                Margins margins = (Margins)gap.Strings.SerializableFromString(marginsRequest);
                if (this.setMargins(margins)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"margins",marginsRequest,exc);
            }
        }
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Group)proto);
    }
    public final boolean updateFrom(Group proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        String name = proto.getName(mayInherit);
        if (null != name && this.setName(name)){
            change = true;
        }
        Date created = proto.getCreated(mayInherit);
        if (null != created && this.setCreated(created)){
            change = true;
        }
        Date cleaned = proto.getCleaned(mayInherit);
        if (null != cleaned && this.setCleaned(cleaned)){
            change = true;
        }
        Redirect redirect = proto.getRedirect(mayInherit);
        if (null != redirect && this.setRedirect(redirect)){
            change = true;
        }
        Margins margins = proto.getMargins(mayInherit);
        if (null != margins && this.setMargins(margins)){
            change = true;
        }
        return change;
    }
    public java.io.Serializable valueStorage(gap.data.Field field){

        return Field.Storage( (Field)field, (Group)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Group)this, value);
    }
    public final Group markClean(){

        this.fieldStatistics.markClean();
        return (Group)this;
    }
    public final Group markDirty(){

        this.fieldStatistics.markDirty();
        return (Group)this;
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
        return Group.ClassDescriptorFor();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Group.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0)){
                    String id = this.getId();
                    return (null != id);
                }
                else
                    return false;
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasName(true);
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
            case Owner:
                if (name.has(1)){
                    Person owner = this.getOwner(true);
                    if (null != owner)
                        return owner.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasOwner(true);
                }
            case Admin:
                if (name.has(1)){
                    Person admin = this.getAdmin(true);
                    if (null != admin)
                        return admin.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasAdmin(true);
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
            case Cleaned:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasCleaned(true);
                }
            case Redirect:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasRedirect(true);
                }
            case Margins:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasMargins(true);
                }
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.hasVariable(name);
        }
    }
    public String getVariable(TemplateName name){
        Field field = Group.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0))
                    return this.getId();
                else
                    return null;
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getName(true);
            case Identifier:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getIdentifier(true);
            case Owner:
                if (name.has(1)){
                    Person owner = this.getOwner(Notation.MayInherit);
                    if (null != owner)
                        return owner.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getOwnerId(Notation.MayInherit);
            case Admin:
                if (name.has(1)){
                    Person admin = this.getAdmin(Notation.MayInherit);
                    if (null != admin)
                        return admin.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getAdminId(Notation.MayInherit);
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCreated(true));
            case Cleaned:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCleaned(true));
            case Redirect:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.SerializableToString(this.getRedirect(true));
            case Margins:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.SerializableToString(this.getMargins(true));
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getVariable(name);
        }
    }
    public void setVariable(TemplateName name, String value){
        Field field = Group.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case Name:
                    throw new IllegalStateException(field.name());
                case Identifier:
                    throw new IllegalStateException(field.name());
                case Owner:

                    Person owner = this.getOwner(true);
                    if (null != owner)
                        owner.setVariable(new TemplateName(name),value);

                    return ;
                case Admin:

                    Person admin = this.getAdmin(true);
                    if (null != admin)
                        admin.setVariable(new TemplateName(name),value);

                    return ;
                case Created:
                    throw new IllegalStateException(field.name());
                case Cleaned:
                    throw new IllegalStateException(field.name());
                case Redirect:
                    throw new IllegalStateException(field.name());
                case Margins:
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
        Field field = Group.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Name:
                return null;
            case Identifier:
                return null;
            case Owner:
                Person owner = this.getOwner(true);
                if (null != owner)
                    return owner.getSection(new TemplateName(name));
                else
                    return null;
            case Admin:
                Person admin = this.getAdmin(true);
                if (null != admin)
                    return admin.getSection(new TemplateName(name));
                else
                    return null;
            case Created:
                return null;
            case Cleaned:
                return null;
            case Redirect:
                return null;
            case Margins:
                return null;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getSection(name);
        }
    }
    public Group clone(){
        return (Group)super.clone();
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
