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
 * @see Project
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-02-01T20:10:13.786Z")
public abstract class ProjectData
    extends gap.data.BigTable
    implements DataInheritance<Project>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Project","cpi.groups","Project","/projects");

    public final static String ClassName = "Project";

    public final static String DefaultSortBy = "identifier";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Project.class);
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
        String id = Project.IdFor( identifier);
        return KeyLongFor(id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyLongIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Project.KeyLongIdFor((String)args[0]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyLongFor}
     */
    public final static Key KeyFor(Object... args){
        return Project.KeyLongFor( (String)args[0]);
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
    public final static Project ForLongIdentifier(String identifier){
        if (null != identifier){
            Key key = Project.KeyLongIdFor( identifier);
            Project instance = (Project)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Project.CreateQueryFor(key);
                return (Project)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create
     */
    public static Project GetCreateLong(String identifier){
        return GetCreateLongIdentifier( identifier);
    }
    /**
     * Instance lookup or create
     */
    public final static Project GetCreateLongIdentifier(String identifier){
        Project project = Project.ForLongIdentifier( identifier);
        if (null == project){
            project = new Project( identifier);
            project = (Project)gap.data.Store.PutClass(project);
        }
        return project;
    }


    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Project ForLongId(String id){
        if (null != id){
            Key key = Project.KeyLongFor(id);
            Project instance = (Project)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Project.CreateQueryFor(key);
                return (Project)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Project Get(Key key){
        if (null != key){
            Project instance = (Project)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Project.CreateQueryFor(key);
                return (Project)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Project.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Project FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Project)
            return (Project)value;
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
     * @see Project#IdFor
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
    public final static void Delete(Project instance){
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
    public final static void Clean(Project instance){
        if (null != instance){

            gap.data.Store.Clean(instance.getKey());
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Project instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Project instance){
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
    
    public final static Project Query1(Query query){
        if (null != query)
            return (Project)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Project> ListPage(Page page){

        return Project.QueryN(Project.CreateQueryFor(),page);
    }
    public final static BigTableIterator<Project> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Project}
     */
    public static enum Field
        implements gap.data.Field<Project.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Identifier("identifier",Type.Primitive),
        Name("name",Type.Primitive),
        Group("group",Type.BigTable),
        Created("created",Type.Primitive),
        Cleaned("cleaned",Type.Primitive),
        Count("count",Type.Primitive),
        Redirect("redirect",Type.Primitive),
        Margins("margins",Type.Primitive),
        Members("members",Type.Collection);

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
            extends gap.data.Field.Statistics<Project.Field>
        {
            public Statistics(){
                super(Project.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static Object Get(Field field, Project instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(mayInherit);
            case Name:
                return instance.getName(mayInherit);
            case Group:
                return instance.getGroupId();
            case Created:
                return instance.getCreated(mayInherit);
            case Cleaned:
                return instance.getCleaned(mayInherit);
            case Count:
                return instance.getCount(mayInherit);
            case Redirect:
                return instance.getRedirect(mayInherit);
            case Margins:
                return instance.getMargins(mayInherit);
            case Members:
                return instance.getMembers(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Project");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static boolean Set(Field field, Project instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case Identifier:
                return instance.setIdentifier(gap.Objects.StringFromObject(value));
            case Name:
                return instance.setName(gap.Objects.StringFromObject(value));
            case Group:
                return instance.setGroupId(gap.Objects.StringFromObject(value));
            case Created:
                return instance.setCreated(gap.Objects.DateFromObject(value));
            case Cleaned:
                return instance.setCleaned(gap.Objects.DateFromObject(value));
            case Count:
                return instance.setCount(gap.Objects.IntegerFromObject(value));
            case Redirect:
                return instance.setRedirect((Redirect)gap.Objects.SerializableFromObject(value));
            case Margins:
                return instance.setMargins((Margins)gap.Objects.SerializableFromObject(value));
            case Members:
                return instance.setMembers((List.Short<Member>)value);
            default:
                throw new IllegalArgumentException(field.toString()+" in Project");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Project instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(MayNotInherit);
            case Name:
                return instance.getName(MayNotInherit);
            case Group:
                return instance.getGroupId();
            case Created:
                return instance.getCreated(MayNotInherit);
            case Cleaned:
                return instance.getCleaned(MayNotInherit);
            case Count:{
                return instance.getCount(MayNotInherit);
            }
            case Redirect:{
                Redirect _redirect = instance.getRedirect(MayNotInherit);
                return Serialize.To(field,_redirect);
            }
            case Margins:{
                Margins _margins = instance.getMargins(MayNotInherit);
                return Serialize.To(field,_margins);
            }
            case Members:{
                return null;
            }
            default:
                throw new IllegalArgumentException(field.toString()+" in Project");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Project instance, java.io.Serializable value){
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
            case Identifier:
                instance.setIdentifier( (String)value);
                return;
            case Name:
                instance.setName( (String)value);
                return;
            case Group:
                instance.setGroupId( (String)value);
                return;
            case Created:
                instance.setCreated( (Date)value);
                return;
            case Cleaned:
                instance.setCleaned( (Date)value);
                return;
            case Count:{

                instance.setCount( (Number)value);
                return;
            }
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
            case Members:{
                return;
            }
            default:
                throw new IllegalArgumentException(field.toString()+" in Project");
            }
        }


        public final static class List
            extends gap.util.ArrayList<Project.Field>
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

    private transient final Project.Field.Statistics fieldStatistics = new Project.Field.Statistics();

    private transient Project inheritFrom;


    private String identifier;
    private String name;
    private String groupId;
    private transient Key groupKey;
    private transient Group group;
    private Date created;
    private Date cleaned;
    private Integer count;
    private Redirect redirect;
    private Margins margins;
    private transient List.Short<Member> members;




    protected ProjectData() {
        super();
    }
    protected ProjectData(String identifier) {
        super();
        this.setIdentifier(identifier);
        {
            final String id = Project.IdFor(identifier);
            final Key key = Project.KeyLongFor(id);
            this.setKey(key);
        }
    }


    public void destroy(){
        this.inheritFrom = null;
        this.identifier = null;
        this.name = null;
        this.group = null;
        this.created = null;
        this.cleaned = null;
        this.count = null;
        this.redirect = null;
        this.margins = null;
        List.Short<Member> members = this.members;
        if (null != members){
            this.members = null;
            members.destroy();
        }
    }
    public final String getId(){

        String id = Project.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Project.IdFor(this.identifier);
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
            this.key = Project.KeyLongFor(id);
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
    public final Project getInheritFrom(){
        Project inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Project.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Project ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Project ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
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
            this.fieldStatistics.markDirty(Project.Field.Identifier);
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
            this.fieldStatistics.markDirty(Project.Field.Identifier);
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
    public final boolean hasName(boolean mayInherit){
        return (null != this.getName(mayInherit));
    }
    public final boolean hasNotName(boolean mayInherit){
        return (null == this.getName(mayInherit));
    }
    public final boolean dropName(){
        if (null != this.name){
            this.fieldStatistics.markDirty(Project.Field.Name);
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
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getName(Notation.MayInherit);
            }
            return name;
        }
        else
            return this.name;
    }
    public final boolean setName(String name, boolean withInheritance){
        if (IsNotEqual(this.name,this.getName(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Name);
            this.name = name;
            return true;
        }
        else
            return false;
    }
    public final boolean setName(String name){
        if (IsNotEqual(this.name,name)){
            this.fieldStatistics.markDirty(Project.Field.Name);
            this.name = name;
            return true;
        }
        else
            return false;
    }
    public boolean setName(json.Json json){
        if (null == json)
            return false;
        else
            return this.setName((String)json.getValue(String.class));
    }
    public final boolean hasGroup(boolean mayInherit){
        return (null != this.getGroup(mayInherit));
    }
    public final boolean hasNotGroup(boolean mayInherit){
        return (null == this.getGroup(mayInherit));
    }
    public final boolean dropGroup(){
        if (null != this.group){
            this.fieldStatistics.markDirty(Project.Field.Group);
            this.group = null;
            this.groupId = null;
            this.groupKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getGroupId(){
        return this.groupId;
    }
    public final boolean setGroupId(String groupId){
        if (IsNotEqual(this.groupId,groupId)){
            this.fieldStatistics.markDirty(Project.Field.Group);
            this.groupId = groupId;
            this.groupKey = null;
            this.group = null;
            return true;
        }
        else
            return false;
    }
    public final Key getGroupKey(){
        if (null == this.groupKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.groupId){
                this.groupKey = Group.KeyLongFor(this.groupId);
            }
        }
        return this.groupKey;
    }
    public final String getGroupId(boolean mayInherit){
        if (null != this.groupId)
            return this.groupId;
        else if (mayInherit && this.hasInheritFrom()){
            Project inheritFrom = this.getInheritFrom();
            return inheritFrom.getGroupId(mayInherit);
        }
        else
            return null;
    }
    public final Key getGroupKey(boolean mayInherit){
        Key groupKey = this.getGroupKey();
        if (null != groupKey)
            return groupKey;
        else if (mayInherit && this.hasInheritFrom()){
            Project inheritFrom = this.getInheritFrom();
            return inheritFrom.getGroupKey(mayInherit);
        }
        else
            return null;
    }
    public final Group getGroup(){
        return this.getGroup(Notation.MayInherit);
    }
    public final Group getGroup(boolean mayInherit){
        Group group = this.group;
        if (null == group){
            /*
             * BigTable dereference
             */
            Key groupKey = this.getGroupKey(mayInherit);

            if (null != groupKey){
                group = Group.Get(groupKey);
                this.group = group;
            }
        }

        if (null == group && mayInherit && this.hasInheritFrom()){
            Project inheritFrom = this.getInheritFrom();
            return inheritFrom.getGroup(Notation.MayInherit);
        }
        else
            return group;
    }
    public final boolean setGroup(Group group, boolean withInheritance){
        if (IsNotEqual(this.group,this.getGroup(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Group);
            this.group = group;
            if (null != group){
                this.groupId = group.getId();
                this.groupKey = group.getKey();
            }
            else {
                this.groupId = null;
                this.groupKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean setGroup(Group group){
        if (IsNotEqual(this.group,group)){
            this.fieldStatistics.markDirty(Project.Field.Group);
            this.group = group;
            if (null != group){
                this.groupId = group.getId();
                this.groupKey = group.getKey();
            }
            else {
                this.groupId = null;
                this.groupKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public boolean setGroup(json.Json json){
        if (null == json)
            return false;
        else
            return this.setGroup((Group)json.getValue(Group.class));
    }
    public final boolean hasCreated(boolean mayInherit){
        return (null != this.getCreated(mayInherit));
    }
    public final boolean hasNotCreated(boolean mayInherit){
        return (null == this.getCreated(mayInherit));
    }
    public final boolean dropCreated(){
        if (null != this.created){
            this.fieldStatistics.markDirty(Project.Field.Created);
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
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getCreated(Notation.MayInherit);
            }
            return created;
        }
        else
            return this.created;
    }
    public final boolean setCreated(Date created, boolean withInheritance){
        if (IsNotEqual(this.created,this.getCreated(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean setCreated(Date created){
        if (IsNotEqual(this.created,created)){
            this.fieldStatistics.markDirty(Project.Field.Created);
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
    public final boolean hasCleaned(boolean mayInherit){
        return (null != this.getCleaned(mayInherit));
    }
    public final boolean hasNotCleaned(boolean mayInherit){
        return (null == this.getCleaned(mayInherit));
    }
    public final boolean dropCleaned(){
        if (null != this.cleaned){
            this.fieldStatistics.markDirty(Project.Field.Cleaned);
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
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getCleaned(Notation.MayInherit);
            }
            return cleaned;
        }
        else
            return this.cleaned;
    }
    public final boolean setCleaned(Date cleaned, boolean withInheritance){
        if (IsNotEqual(this.cleaned,this.getCleaned(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Cleaned);
            this.cleaned = cleaned;
            return true;
        }
        else
            return false;
    }
    public final boolean setCleaned(Date cleaned){
        if (IsNotEqual(this.cleaned,cleaned)){
            this.fieldStatistics.markDirty(Project.Field.Cleaned);
            this.cleaned = cleaned;
            return true;
        }
        else
            return false;
    }
    public boolean setCleaned(json.Json json){
        if (null == json)
            return false;
        else
            return this.setCleaned((Date)json.getValue(Date.class));
    }
    public final boolean hasCount(boolean mayInherit){
        return (null != this.getCount(mayInherit));
    }
    public final boolean hasNotCount(boolean mayInherit){
        return (null == this.getCount(mayInherit));
    }
    public final boolean dropCount(){
        if (null != this.count){
            this.fieldStatistics.markDirty(Project.Field.Count);
            this.count = null;
            return true;
        }
        else
            return false;
    }
    public final Integer getCount(){
        return this.getCount(Notation.MayInherit);
    }
    public final Integer getCount(boolean mayInherit){
        if (mayInherit){
            Integer count = this.count;
            if (null == count && this.hasInheritFrom()){
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getCount(Notation.MayInherit);
            }
            return count;
        }
        else
            return this.count;
    }
    public final boolean setCount(Integer count, boolean withInheritance){
        if (IsNotEqual(this.count,this.getCount(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Count);
            this.count = count;
            return true;
        }
        else
            return false;
    }
    public final boolean setCount(Integer count){
        if (IsNotEqual(this.count,count)){
            this.fieldStatistics.markDirty(Project.Field.Count);
            this.count = count;
            return true;
        }
        else
            return false;
    }
    public boolean setCount(json.Json json){
        if (null == json)
            return false;
        else
            return this.setCount((Integer)json.getValue(Integer.class));
    }
    public final boolean setCount(Number count){
        if (IsNotEqual(this.count,count)){
            this.fieldStatistics.markDirty(Project.Field.Count);
            if (count instanceof Integer)
                this.count = (Integer)count;
            else
                this.count = new Integer( count.intValue());
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
            this.fieldStatistics.markDirty(Project.Field.Redirect);
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
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getRedirect(Notation.MayInherit);
            }
            return redirect;
        }
        else
            return this.redirect;
    }
    public final boolean setRedirect(Redirect redirect, boolean withInheritance){
        if (IsNotEqual(this.redirect,this.getRedirect(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Redirect);
            this.redirect = redirect;
            return true;
        }
        else
            return false;
    }
    public final boolean setRedirect(Redirect redirect){
        if (IsNotEqual(this.redirect,redirect)){
            this.fieldStatistics.markDirty(Project.Field.Redirect);
            this.redirect = redirect;
            return true;
        }
        else
            return false;
    }
    public boolean setRedirect(json.Json json){
        if (null == json)
            return false;
        else
            return this.setRedirect((Redirect)json.getValue(Redirect.class));
    }
    public final boolean hasMargins(boolean mayInherit){
        return (null != this.getMargins(mayInherit));
    }
    public final boolean hasNotMargins(boolean mayInherit){
        return (null == this.getMargins(mayInherit));
    }
    public final boolean dropMargins(){
        if (null != this.margins){
            this.fieldStatistics.markDirty(Project.Field.Margins);
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
                Project inheritFrom = this.getInheritFrom();
                return inheritFrom.getMargins(Notation.MayInherit);
            }
            return margins;
        }
        else
            return this.margins;
    }
    public final boolean setMargins(Margins margins, boolean withInheritance){
        if (IsNotEqual(this.margins,this.getMargins(withInheritance))){
            this.fieldStatistics.markDirty(Project.Field.Margins);
            this.margins = margins;
            return true;
        }
        else
            return false;
    }
    public final boolean setMargins(Margins margins){
        if (IsNotEqual(this.margins,margins)){
            this.fieldStatistics.markDirty(Project.Field.Margins);
            this.margins = margins;
            return true;
        }
        else
            return false;
    }
    public boolean setMargins(json.Json json){
        if (null == json)
            return false;
        else
            return this.setMargins((Margins)json.getValue(Margins.class));
    }
    public final boolean hasMembers(boolean mayInherit){
        return (this.getMembers(mayInherit).isNotEmpty());
    }
    public final boolean hasNotMembers(boolean mayInherit){
        return (this.getMembers(mayInherit).isEmpty());
    }
    public final boolean dropMembers(){
        List.Short<Member> members = this.members;
        if (null != members){
            this.members = null;
            members.destroy();
            return true;
        }
        else
            return false;
    }
    public final List.Short<Member> getMembers(){
        return this.getMembers(Notation.MayInherit);
    }
    public final List.Short<Member> getMembers(boolean mayInherit){
        List.Short<Member> members = this.members;
        if (null == members){
            if (mayInherit && this.hasInheritFrom()){
                Project inheritFrom = this.getInheritFrom();
                if (null != inheritFrom){
                    members = inheritFrom.getMembers(Notation.MayInherit);
                    if (null != members)
                        return members;
                }
            }
            /*
             * Collection type coersion
             */
            {
                Object tmp = new ListProjectMember((Project)this);
                members = (List.Short<Member>)tmp;
            }
            this.members = members;
            members.init();
        }
        return members;
    }
    public final boolean setMembers(List.Short<Member> members){
        if (IsNotEqual(this.members,members)){

            this.members = members;
            return true;
        }
        else
            return false;
    }
    public final boolean isEmptyMembers(){
        List.Short<Member> collection = this.members;
        if (null != collection)
            return collection.isEmpty();
        else
            return true;
    }
    public final boolean isNotEmptyMembers(){
        List.Short<Member> collection = this.members;
        if (null != collection)
            return (!collection.isEmpty());
        else
            return false;
    }
    public final Member fetchMembers(Filter filter){
        if (null != filter && KIND == filter.kind){
            List.Short<Member> collection = this.getMembers(Notation.MayInherit);
            return collection.fetch(filter);
        }
        else
            throw new IllegalArgumentException();
    }
    public final Member getMembers(gap.data.ListFilter<Member> filter){
        if (null != filter){
            List.Short<Member> list = this.getMembers(Notation.MayInherit);
            for (Member item : list){
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
        gap.data.List re = new Project.Field.List(Field.values());
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
        String identifier = this.getIdentifier();
        json.set("identifier",identifier);
        String name = this.getName();
        json.set("name",name);
        Group group = this.getGroup();
        json.set("group",group);
        Date created = this.getCreated();
        json.set("created",created);
        Date cleaned = this.getCleaned();
        json.set("cleaned",cleaned);
        Integer count = this.getCount();
        json.set("count",count);
        Redirect redirect = this.getRedirect();
        json.set("redirect",redirect);
        Margins margins = this.getMargins();
        json.set("margins",margins);
        return json;
    }
    public boolean fromJson(json.Json json){
        boolean modified = false;
        modified = (this.setName(json.at("name")) || modified);
        modified = (this.setGroup(json.at("group")) || modified);
        modified = (this.setCreated(json.at("created")) || modified);
        modified = (this.setCleaned(json.at("cleaned")) || modified);
        modified = (this.setCount(json.at("count")) || modified);
        modified = (this.setRedirect(json.at("redirect")) || modified);
        modified = (this.setMargins(json.at("margins")) || modified);
        return modified;
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
        String countRequest = req.getParameter("count");
        if (null != countRequest && 0 < countRequest.length()){
            try {
                Integer count = gap.Strings.IntegerFromString(countRequest);
                if (this.setCount(count)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"count",countRequest,exc);
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
        return this.updateFrom( (Project)proto);
    }
    public final boolean updateFrom(Project proto){
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
        Integer count = proto.getCount(mayInherit);
        if (null != count && this.setCount(count)){
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

        return Field.Storage( (Field)field, (Project)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Project)this, value);
    }
    public final Project markClean(){

        this.fieldStatistics.markClean();
        return (Project)this;
    }
    public final Project markDirty(){

        this.fieldStatistics.markDirty();
        return (Project)this;
    }
    public final Project markDirty(gap.data.Field field){

        this.fieldStatistics.markDirty(field);
        return (Project)this;
    }
    public final Project markDirty(java.io.Serializable instance){
        if (instance == this.identifier){
            gap.data.Field field = Project.Field.Identifier;
            return this.markDirty(field);
        }
        else if (instance == this.name){
            gap.data.Field field = Project.Field.Name;
            return this.markDirty(field);
        }
        else if (instance == this.group){
            gap.data.Field field = Project.Field.Group;
            return this.markDirty(field);
        }
        else if (instance == this.created){
            gap.data.Field field = Project.Field.Created;
            return this.markDirty(field);
        }
        else if (instance == this.cleaned){
            gap.data.Field field = Project.Field.Cleaned;
            return this.markDirty(field);
        }
        else if (instance == this.count){
            gap.data.Field field = Project.Field.Count;
            return this.markDirty(field);
        }
        else if (instance == this.redirect){
            gap.data.Field field = Project.Field.Redirect;
            return this.markDirty(field);
        }
        else if (instance == this.margins){
            gap.data.Field field = Project.Field.Margins;
            return this.markDirty(field);
        }
        else if (instance == this.members){
            gap.data.Field field = Project.Field.Members;
            return this.markDirty(field);
        }
        else
            return (Project)this;
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
        return Project.ClassDescriptorFor();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Project.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0)){
                    String id = this.getId();
                    return (null != id);
                }
                else
                    return false;
            case Identifier:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasIdentifier(true);
                }
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasName(true);
                }
            case Group:
                if (name.has(1)){
                    Group group = this.getGroup(true);
                    if (null != group)
                        return group.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasGroup(true);
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
            case Count:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasCount(true);
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
            case Members:
            default:
                break;
            }
        }
        return super.hasVariable(name);
    }
    public String getVariable(TemplateName name){
        Field field = Project.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0))
                    return this.getId();
                else
                    return null;
            case Identifier:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getIdentifier(true);
            case Name:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getName(true);
            case Group:
                if (name.has(1)){
                    Group group = this.getGroup(Notation.MayInherit);
                    if (null != group)
                        return group.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getGroupId(Notation.MayInherit);
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
            case Count:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.IntegerToString(this.getCount(true));
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
            case Members:
            default:
                break;
            }
        }
        return super.getVariable(name);
    }
    public void setVariable(TemplateName name, String value){
        Field field = Project.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case Identifier:
                    throw new IllegalStateException(field.name());
                case Name:
                    throw new IllegalStateException(field.name());
                case Group:

                    Group group = this.getGroup(true);
                    if (null != group)
                        group.setVariable(new TemplateName(name),value);

                    return ;
                case Created:
                    throw new IllegalStateException(field.name());
                case Cleaned:
                    throw new IllegalStateException(field.name());
                case Count:
                    throw new IllegalStateException(field.name());
                case Redirect:
                    throw new IllegalStateException(field.name());
                case Margins:
                    throw new IllegalStateException(field.name());
                case Members:
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Project)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Project.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Identifier:
                return null;
            case Name:
                return null;
            case Group:
                Group group = this.getGroup(true);
                if (null != group){
                    if (name.has(1))
                        return group.getSection(new TemplateName(name));
                    else
                        return new gap.util.ShortList( this, group);
                }
                else
                    return null;
            case Created:
                return null;
            case Cleaned:
                return null;
            case Count:
                return null;
            case Redirect:
                return null;
            case Margins:
                return null;
            case Members:
                /*
                 * compiler type coersion
                 */
                Object members = this.getMembers(true);
                return (List.Short<TemplateDataDictionary>)members;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getSection(name);
        }
    }
    public Project clone(){
        return (Project)super.clone();
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
