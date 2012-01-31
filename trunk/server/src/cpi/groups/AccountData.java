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
 * @see Account
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-01-30T18:48:58.942Z")
public abstract class AccountData
    extends gap.data.BigTable
    implements DataInheritance<Account>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Account","cpi.groups","Account","/accounts");

    public final static String ClassName = "Account";

    public final static String DefaultSortBy = "identifier";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Account.class);
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
        String id = Account.IdFor( identifier);
        return KeyLongFor(id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyLongIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Account.KeyLongIdFor((String)args[0]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyLongFor}
     */
    public final static Key KeyFor(Object... args){
        return Account.KeyLongFor( (String)args[0]);
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
    public final static Account ForLongIdentifier(String identifier){
        if (null != identifier){
            Key key = Account.KeyLongIdFor( identifier);
            Account instance = (Account)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Account.CreateQueryFor(key);
                return (Account)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create
     */
    public static Account GetCreateLong(String identifier){
        return GetCreateLongIdentifier( identifier);
    }
    /**
     * Instance lookup or create
     */
    public final static Account GetCreateLongIdentifier(String identifier){
        Account account = Account.ForLongIdentifier( identifier);
        if (null == account){
            account = new Account( identifier);
            account = (Account)gap.data.Store.PutClass(account);
        }
        return account;
    }


    public final static Key KeyLongFor(String id){
        return KeyFactory.createKey(KIND.getName(),id);
    }


    public final static Account ForLongId(String id){
        if (null != id){
            Key key = Account.KeyLongFor(id);
            Account instance = (Account)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Account.CreateQueryFor(key);
                return (Account)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Account Get(Key key){
        if (null != key){
            Account instance = (Account)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Account.CreateQueryFor(key);
                return (Account)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Account.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Account FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Account)
            return (Account)value;
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
     * @see Account#IdFor
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
    public final static void Delete(Account instance){
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
    public final static void Clean(Account instance){
        if (null != instance){

            gap.data.Store.Clean(instance.getKey());
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Account instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Account instance){
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
    
    public final static Account Query1(Query query){
        if (null != query)
            return (Account)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Account> ListPage(Page page){

        return Account.QueryN(Account.CreateQueryFor(),page);
    }
    public final static BigTableIterator<Account> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Account}
     */
    public static enum Field
        implements gap.data.Field<Account.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Identifier("identifier",Type.Primitive),
        Group("group",Type.BigTable),
        Project("project",Type.BigTable),
        Paid("paid",Type.Primitive),
        Amount("amount",Type.Primitive),
        Currency("currency",Type.Primitive),
        Notes("notes",Type.Collection);

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
            extends gap.data.Field.Statistics<Account.Field>
        {
            public Statistics(){
                super(Account.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static Object Get(Field field, Account instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(mayInherit);
            case Group:
                return instance.getGroupId();
            case Project:
                return instance.getProjectId();
            case Paid:
                return instance.getPaid(mayInherit);
            case Amount:
                return instance.getAmount(mayInherit);
            case Currency:
                return instance.getCurrency(mayInherit);
            case Notes:
                return instance.getNotes(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Account");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static boolean Set(Field field, Account instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case Identifier:
                return instance.setIdentifier(gap.Objects.StringFromObject(value));
            case Group:
                return instance.setGroupId(gap.Objects.StringFromObject(value));
            case Project:
                return instance.setProjectId(gap.Objects.StringFromObject(value));
            case Paid:
                return instance.setPaid(gap.Objects.BooleanFromObject(value));
            case Amount:
                return instance.setAmount(gap.Objects.FloatFromObject(value));
            case Currency:
                return instance.setCurrency(gap.Objects.StringFromObject(value));
            case Notes:
                return instance.setNotes((List.Short<Note>)value);
            default:
                throw new IllegalArgumentException(field.toString()+" in Account");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Account instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(MayNotInherit);
            case Group:
                return instance.getGroupId();
            case Project:
                return instance.getProjectId();
            case Paid:
                return instance.getPaid(MayNotInherit);
            case Amount:{
                return instance.getAmount(MayNotInherit);
            }
            case Currency:
                return instance.getCurrency(MayNotInherit);
            case Notes:{
                return null;
            }
            default:
                throw new IllegalArgumentException(field.toString()+" in Account");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Account instance, java.io.Serializable value){
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
            case Group:
                instance.setGroupId( (String)value);
                return;
            case Project:
                instance.setProjectId( (String)value);
                return;
            case Paid:
                instance.setPaid( (Boolean)value);
                return;
            case Amount:{

                instance.setAmount( (Number)value);
                return;
            }
            case Currency:
                instance.setCurrency( (String)value);
                return;
            case Notes:{
                return;
            }
            default:
                throw new IllegalArgumentException(field.toString()+" in Account");
            }
        }


        public final static class List
            extends gap.util.ArrayList<Account.Field>
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

    private transient final Account.Field.Statistics fieldStatistics = new Account.Field.Statistics();

    private transient Account inheritFrom;


    private String identifier;
    private String groupId;
    private transient Key groupKey;
    private transient Group group;
    private String projectId;
    private transient Key projectKey;
    private transient Project project;
    private Boolean paid;
    private Float amount;
    private String currency;
    private transient List.Short<Note> notes;




    protected AccountData() {
        super();
    }
    protected AccountData(String identifier) {
        super();
        this.setIdentifier(identifier);
        {
            final String id = Account.IdFor(identifier);
            final Key key = Account.KeyLongFor(id);
            this.setKey(key);
        }
    }


    public void destroy(){
        this.inheritFrom = null;
        this.identifier = null;
        this.group = null;
        this.project = null;
        this.paid = null;
        this.amount = null;
        this.currency = null;
        List.Short<Note> notes = this.notes;
        if (null != notes){
            this.notes = null;
            notes.destroy();
        }
    }
    public final String getId(){

        String id = Account.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Account.IdFor(this.identifier);
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
            this.key = Account.KeyLongFor(id);
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
    public final Account getInheritFrom(){
        Account inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Account.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Account ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Account ancestor){
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
            this.fieldStatistics.markDirty(Account.Field.Identifier);
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
            this.fieldStatistics.markDirty(Account.Field.Identifier);
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
    public final boolean hasGroup(boolean mayInherit){
        return (null != this.getGroup(mayInherit));
    }
    public final boolean hasNotGroup(boolean mayInherit){
        return (null == this.getGroup(mayInherit));
    }
    public final boolean dropGroup(){
        if (null != this.group){
            this.fieldStatistics.markDirty(Account.Field.Group);
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
            this.fieldStatistics.markDirty(Account.Field.Group);
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
            Account inheritFrom = this.getInheritFrom();
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
            Account inheritFrom = this.getInheritFrom();
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
            Account inheritFrom = this.getInheritFrom();
            return inheritFrom.getGroup(Notation.MayInherit);
        }
        else
            return group;
    }
    public final boolean setGroup(Group group, boolean withInheritance){
        if (IsNotEqual(this.group,this.getGroup(withInheritance))){
            this.fieldStatistics.markDirty(Account.Field.Group);
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
            this.fieldStatistics.markDirty(Account.Field.Group);
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
    public final boolean hasProject(boolean mayInherit){
        return (null != this.getProject(mayInherit));
    }
    public final boolean hasNotProject(boolean mayInherit){
        return (null == this.getProject(mayInherit));
    }
    public final boolean dropProject(){
        if (null != this.project){
            this.fieldStatistics.markDirty(Account.Field.Project);
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
            this.fieldStatistics.markDirty(Account.Field.Project);
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
            Account inheritFrom = this.getInheritFrom();
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
            Account inheritFrom = this.getInheritFrom();
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
            Account inheritFrom = this.getInheritFrom();
            return inheritFrom.getProject(Notation.MayInherit);
        }
        else
            return project;
    }
    public final boolean setProject(Project project, boolean withInheritance){
        if (IsNotEqual(this.project,this.getProject(withInheritance))){
            this.fieldStatistics.markDirty(Account.Field.Project);
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
            this.fieldStatistics.markDirty(Account.Field.Project);
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
    public final boolean hasPaid(boolean mayInherit){
        return (null != this.getPaid(mayInherit));
    }
    public final boolean hasNotPaid(boolean mayInherit){
        return (null == this.getPaid(mayInherit));
    }
    public final boolean dropPaid(){
        if (null != this.paid){
            this.fieldStatistics.markDirty(Account.Field.Paid);
            this.paid = null;
            return true;
        }
        else
            return false;
    }
    public final Boolean getPaid(){
        return this.getPaid(Notation.MayInherit);
    }
    public final Boolean getPaid(boolean mayInherit){
        if (mayInherit){
            Boolean paid = this.paid;
            if (null == paid && this.hasInheritFrom()){
                Account inheritFrom = this.getInheritFrom();
                return inheritFrom.getPaid(Notation.MayInherit);
            }
            return paid;
        }
        else
            return this.paid;
    }
    public final boolean setPaid(Boolean paid, boolean withInheritance){
        if (IsNotEqual(this.paid,this.getPaid(withInheritance))){
            this.fieldStatistics.markDirty(Account.Field.Paid);
            this.paid = paid;
            return true;
        }
        else
            return false;
    }
    public final boolean setPaid(Boolean paid){
        if (IsNotEqual(this.paid,paid)){
            this.fieldStatistics.markDirty(Account.Field.Paid);
            this.paid = paid;
            return true;
        }
        else
            return false;
    }
    public boolean setPaid(json.Json json){
        if (null == json)
            return false;
        else
            return this.setPaid((Boolean)json.getValue(Boolean.class));
    }
    public final boolean hasAmount(boolean mayInherit){
        return (null != this.getAmount(mayInherit));
    }
    public final boolean hasNotAmount(boolean mayInherit){
        return (null == this.getAmount(mayInherit));
    }
    public final boolean dropAmount(){
        if (null != this.amount){
            this.fieldStatistics.markDirty(Account.Field.Amount);
            this.amount = null;
            return true;
        }
        else
            return false;
    }
    public final Float getAmount(){
        return this.getAmount(Notation.MayInherit);
    }
    public final Float getAmount(boolean mayInherit){
        if (mayInherit){
            Float amount = this.amount;
            if (null == amount && this.hasInheritFrom()){
                Account inheritFrom = this.getInheritFrom();
                return inheritFrom.getAmount(Notation.MayInherit);
            }
            return amount;
        }
        else
            return this.amount;
    }
    public final boolean setAmount(Float amount, boolean withInheritance){
        if (IsNotEqual(this.amount,this.getAmount(withInheritance))){
            this.fieldStatistics.markDirty(Account.Field.Amount);
            this.amount = amount;
            return true;
        }
        else
            return false;
    }
    public final boolean setAmount(Float amount){
        if (IsNotEqual(this.amount,amount)){
            this.fieldStatistics.markDirty(Account.Field.Amount);
            this.amount = amount;
            return true;
        }
        else
            return false;
    }
    public boolean setAmount(json.Json json){
        if (null == json)
            return false;
        else
            return this.setAmount((Float)json.getValue(Float.class));
    }
    public final boolean setAmount(Number amount){
        if (IsNotEqual(this.amount,amount)){
            this.fieldStatistics.markDirty(Account.Field.Amount);
            if (amount instanceof Float)
                this.amount = (Float)amount;
            else
                this.amount = new Float( amount.floatValue());
            return true;
        }
        else
            return false;
    }
    public final boolean hasCurrency(boolean mayInherit){
        return (null != this.getCurrency(mayInherit));
    }
    public final boolean hasNotCurrency(boolean mayInherit){
        return (null == this.getCurrency(mayInherit));
    }
    public final boolean dropCurrency(){
        if (null != this.currency){
            this.fieldStatistics.markDirty(Account.Field.Currency);
            this.currency = null;
            return true;
        }
        else
            return false;
    }
    public final String getCurrency(){
        return this.getCurrency(Notation.MayInherit);
    }
    public final String getCurrency(boolean mayInherit){
        if (mayInherit){
            String currency = this.currency;
            if (null == currency && this.hasInheritFrom()){
                Account inheritFrom = this.getInheritFrom();
                return inheritFrom.getCurrency(Notation.MayInherit);
            }
            return currency;
        }
        else
            return this.currency;
    }
    public final boolean setCurrency(String currency, boolean withInheritance){
        if (IsNotEqual(this.currency,this.getCurrency(withInheritance))){
            this.fieldStatistics.markDirty(Account.Field.Currency);
            this.currency = currency;
            return true;
        }
        else
            return false;
    }
    public final boolean setCurrency(String currency){
        if (IsNotEqual(this.currency,currency)){
            this.fieldStatistics.markDirty(Account.Field.Currency);
            this.currency = currency;
            return true;
        }
        else
            return false;
    }
    public boolean setCurrency(json.Json json){
        if (null == json)
            return false;
        else
            return this.setCurrency((String)json.getValue(String.class));
    }
    public final boolean hasNotes(boolean mayInherit){
        return (this.getNotes(mayInherit).isNotEmpty());
    }
    public final boolean hasNotNotes(boolean mayInherit){
        return (this.getNotes(mayInherit).isEmpty());
    }
    public final boolean dropNotes(){
        List.Short<Note> notes = this.notes;
        if (null != notes){
            this.notes = null;
            notes.destroy();
            return true;
        }
        else
            return false;
    }
    public final List.Short<Note> getNotes(){
        return this.getNotes(Notation.MayInherit);
    }
    public final List.Short<Note> getNotes(boolean mayInherit){
        List.Short<Note> notes = this.notes;
        if (null == notes){
            if (mayInherit && this.hasInheritFrom()){
                Account inheritFrom = this.getInheritFrom();
                if (null != inheritFrom){
                    notes = inheritFrom.getNotes(Notation.MayInherit);
                    if (null != notes)
                        return notes;
                }
            }
            /*
             * Collection type coersion
             */
            {
                Object tmp = new ListAccountNote((Account)this);
                notes = (List.Short<Note>)tmp;
            }
            this.notes = notes;
            notes.init();
        }
        return notes;
    }
    public final boolean setNotes(List.Short<Note> notes){
        if (IsNotEqual(this.notes,notes)){

            this.notes = notes;
            return true;
        }
        else
            return false;
    }
    public final boolean isEmptyNotes(){
        List.Short<Note> collection = this.notes;
        if (null != collection)
            return collection.isEmpty();
        else
            return true;
    }
    public final boolean isNotEmptyNotes(){
        List.Short<Note> collection = this.notes;
        if (null != collection)
            return (!collection.isEmpty());
        else
            return false;
    }
    public final Note fetchNotes(Filter filter){
        if (null != filter && KIND == filter.kind){
            List.Short<Note> collection = this.getNotes(Notation.MayInherit);
            return collection.fetch(filter);
        }
        else
            throw new IllegalArgumentException();
    }
    public final Note getNotes(gap.data.ListFilter<Note> filter){
        if (null != filter){
            List.Short<Note> list = this.getNotes(Notation.MayInherit);
            for (Note item : list){
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
        gap.data.List re = new Account.Field.List(Field.values());
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
        Group group = this.getGroup();
        json.set("group",group);
        Project project = this.getProject();
        json.set("project",project);
        Boolean paid = this.getPaid();
        json.set("paid",paid);
        Float amount = this.getAmount();
        json.set("amount",amount);
        String currency = this.getCurrency();
        json.set("currency",currency);
        return json;
    }
    public boolean fromJson(json.Json json){
        boolean modified = false;
        modified = (this.setGroup(json.at("group")) || modified);
        modified = (this.setProject(json.at("project")) || modified);
        modified = (this.setPaid(json.at("paid")) || modified);
        modified = (this.setAmount(json.at("amount")) || modified);
        modified = (this.setCurrency(json.at("currency")) || modified);
        return modified;
    }
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;
        String paidRequest = req.getParameter("paid");
        if (null != paidRequest && 0 < paidRequest.length()){
            try {
                Boolean paid = gap.Strings.BooleanFromString(paidRequest);
                if (this.setPaid(paid)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"paid",paidRequest,exc);
            }
        }
        String amountRequest = req.getParameter("amount");
        if (null != amountRequest && 0 < amountRequest.length()){
            try {
                Float amount = gap.Strings.FloatFromString(amountRequest);
                if (this.setAmount(amount)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"amount",amountRequest,exc);
            }
        }
        String currencyRequest = req.getParameter("currency");
        if (null != currencyRequest && 0 < currencyRequest.length()){
            try {
                String currency = currencyRequest;
                if (this.setCurrency(currency)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"currency",currencyRequest,exc);
            }
        }
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Account)proto);
    }
    public final boolean updateFrom(Account proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        Boolean paid = proto.getPaid(mayInherit);
        if (null != paid && this.setPaid(paid)){
            change = true;
        }
        Float amount = proto.getAmount(mayInherit);
        if (null != amount && this.setAmount(amount)){
            change = true;
        }
        String currency = proto.getCurrency(mayInherit);
        if (null != currency && this.setCurrency(currency)){
            change = true;
        }
        return change;
    }
    public java.io.Serializable valueStorage(gap.data.Field field){

        return Field.Storage( (Field)field, (Account)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Account)this, value);
    }
    public final Account markClean(){

        this.fieldStatistics.markClean();
        return (Account)this;
    }
    public final Account markDirty(){

        this.fieldStatistics.markDirty();
        return (Account)this;
    }
    public final Account markDirty(gap.data.Field field){

        this.fieldStatistics.markDirty(field);
        return (Account)this;
    }
    public final Account markDirty(java.io.Serializable instance){
        if (instance == this.identifier){
            gap.data.Field field = Account.Field.Identifier;
            return this.markDirty(field);
        }
        else if (instance == this.group){
            gap.data.Field field = Account.Field.Group;
            return this.markDirty(field);
        }
        else if (instance == this.project){
            gap.data.Field field = Account.Field.Project;
            return this.markDirty(field);
        }
        else if (instance == this.paid){
            gap.data.Field field = Account.Field.Paid;
            return this.markDirty(field);
        }
        else if (instance == this.amount){
            gap.data.Field field = Account.Field.Amount;
            return this.markDirty(field);
        }
        else if (instance == this.currency){
            gap.data.Field field = Account.Field.Currency;
            return this.markDirty(field);
        }
        else if (instance == this.notes){
            gap.data.Field field = Account.Field.Notes;
            return this.markDirty(field);
        }
        else
            return (Account)this;
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
        return Account.ClassDescriptorFor();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Account.Field.For(name.getTerm());
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
            case Paid:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Boolean (EXISTS && TRUE)
                     */
                    return (this.hasPaid(true) && this.getPaid(true));
                }
            case Amount:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasAmount(true);
                }
            case Currency:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasCurrency(true);
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
        Field field = Account.Field.For(name.getTerm());
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
            case Paid:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.BooleanToString(this.getPaid(true));
            case Amount:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.FloatToString(this.getAmount(true));
            case Currency:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return this.getCurrency(true);
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getVariable(name);
        }
    }
    public void setVariable(TemplateName name, String value){
        Field field = Account.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case Identifier:
                    throw new IllegalStateException(field.name());
                case Group:

                    Group group = this.getGroup(true);
                    if (null != group)
                        group.setVariable(new TemplateName(name),value);

                    return ;
                case Project:

                    Project project = this.getProject(true);
                    if (null != project)
                        project.setVariable(new TemplateName(name),value);

                    return ;
                case Paid:
                    throw new IllegalStateException(field.name());
                case Amount:
                    throw new IllegalStateException(field.name());
                case Currency:
                    throw new IllegalStateException(field.name());
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Account)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Account.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Identifier:
                return null;
            case Group:
                Group group = this.getGroup(true);
                if (null != group)
                    return group.getSection(new TemplateName(name));
                else
                    return null;
            case Project:
                Project project = this.getProject(true);
                if (null != project)
                    return project.getSection(new TemplateName(name));
                else
                    return null;
            case Paid:
                return null;
            case Amount:
                return null;
            case Currency:
                return null;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getSection(name);
        }
    }
    public Account clone(){
        return (Account)super.clone();
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
