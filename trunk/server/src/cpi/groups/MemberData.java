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

import json.Json;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.Date;

import javax.annotation.Generated;

/**
 * Generated bean data binding.
 *
 * @see Member
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-02-28T15:49:23.128Z")
public abstract class MemberData
    extends gap.data.BigTable
    implements DataInheritance<Member>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Member","cpi.groups","Member","/members");

    public final static String ClassName = "Member";

    public final static String DefaultSortBy = "person";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Member.class);
    }
    public final static gap.service.od.ClassDescriptor ClassDescriptorForParent(){
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
     * Short instance key from parent key
     */
    public final static Key KeyShortIdFor(Key ancestor, Person person){
        String id = Member.IdFor( person);
        return Member.KeyShortFor(ancestor,id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyShortIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Member.KeyShortIdFor( (Key)args[0], (Person)args[1]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyShortFor}
     */
    public final static Key KeyFor(Object... args){
        return Member.KeyShortFor( (Key)args[0], (String)args[1]);
    }
    /**
     * Identifier for unique fields
     */
    public final static String IdFor(Person person){
        if (null != person){
            String personString = gap.Strings.KeyToString(person);
            return gap.data.Hash.For(personString);
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup from parent key
     */
    public final static Member ForShortPerson(Key ancestor, Person person){
        if (null != person){
            Key key = Member.KeyShortIdFor(ancestor, person);
            Member instance = (Member)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Member.CreateQueryFor(key);
                return (Member)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create from parent key
     */
    public final static Member GetCreateShort(Key ancestor, Person person){
        Member member = Member.ForShortPerson(ancestor, person);
        if (null == member){
            member = new Member(ancestor, person);
            member = (Member)gap.data.Store.PutClass(member);
        }
        return member;
    }


    public final static Key KeyShortFor(Key ancestor, String id){
        return KeyFactory.createKey(ancestor,KIND.getName(),id);
    }


    public final static Member ForShortId(Key ancestor, String id){
        if (null != ancestor && ancestor.isComplete() && null != id){
            Key key = Member.KeyShortFor(ancestor,id);
            Member instance = (Member)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Member.CreateQueryFor(key);
                return (Member)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Member Get(Key key){
        if (null != key){
            Member instance = (Member)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Member.CreateQueryFor(key);
                return (Member)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Member.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Member FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Member)
            return (Member)value;
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
     * @see Member#IdFor
     *
     * Test for uniqueness and iterate under collisions.
     */
    public final static Key NewRandomKeyShort(Key ancestor){
        if (null != ancestor){
            /*
             * Source matter for data local uniqueness
             */
            long matter = gap.data.Hash.Djb64(gap.data.BigTable.ToString(ancestor));
            /*
             * Random matter for network global uniqueness
             */
            java.util.Random random = new java.util.Random();
            do {
                matter ^= random.nextLong();
                String idString = gap.data.Hash.Hex(matter);
                Key key = KeyFactory.createKey(ancestor,KIND.getName(),idString);
                if (null == GetKey(key))
                    return key;
            }
            while (true);
        }
        else
            throw new IllegalArgumentException();
    }
    /**
     * Drop all children of the parent
     */
    public final static void Delete(Project parent){
        if (null != parent){

            DeleteChildrenOf(parent.getKey());
        }
    }
    /**
     * Drop all children of the parent
     */
    public final static void DeleteChildrenOf(Key parentKey){
        if (null != parentKey){

             gap.data.Store.DeleteCollection(KIND,Member.CreateQueryFor(parentKey));
        }
    }
    /**
     * Drop the instance from memcache and datastore.
     */
    public final static void Delete(Member instance){
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
    public final static void Clean(Member instance){
        if (null != instance){

            gap.data.Store.Clean(instance.getKey());
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Member instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Member instance){
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
    public final static Query CreateQueryFor(Key ancestor, Filter filter){
        Query query = new Query(KIND.getName(),ancestor);
        return filter.update(query);
    }
    public final static Member Query1(Query query){
        if (null != query)
            return (Member)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Member> ListPage(Key ancestor, Page page){

        return Member.QueryN(Member.CreateQueryFor(ancestor),page);
    }
    public final static BigTableIterator<Member> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Member}
     */
    public static enum Field
        implements gap.data.Field<Member.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        ParentKey("parentKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Person("person",Type.BigTable);

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
            extends gap.data.Field.Statistics<Member.Field>
        {
            public Statistics(){
                super(Member.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static Object Get(Field field, Member instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case ParentKey:
                return instance.getParentKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Person:
                return instance.getPersonId();
            default:
                throw new IllegalArgumentException(field.toString()+" in Member");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static boolean Set(Field field, Member instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case ParentKey:
                return instance.setParentKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case Person:
                return instance.setPersonId(gap.Objects.StringFromObject(value));
            default:
                throw new IllegalArgumentException(field.toString()+" in Member");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Member instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case ParentKey:
                return instance.getParentKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Person:
                return instance.getPersonId();
            default:
                throw new IllegalArgumentException(field.toString()+" in Member");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Member instance, java.io.Serializable value){
            switch(field){
            case InheritFromKey:
                instance.setInheritFromKey( (Key)value);
                return;
            case ParentKey:
                instance.setParentKey( (Key)value);
                return;
            case Key:
                instance.setKey( (Key)value);
                return;
            case Id:
                instance.setId( (String)value);
                return;
            case Person:
                instance.setPersonId( (String)value);
                return;
            default:
                throw new IllegalArgumentException(field.toString()+" in Member");
            }
        }


        public final static class List
            extends gap.util.ArrayList<Member.Field>
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

    private transient Member.Field.Statistics fieldStatistics = new Member.Field.Statistics();

    private transient Member inheritFrom;


    private String personId;
    private transient Key personKey;
    private transient Person person;


    private Key parentKey;
    private transient Project parent;


    protected MemberData() {
        super();
    }
    protected MemberData(Key ancestor, Person person) {
        super();
        this.setPerson(person);
        this.parentKey = ancestor;
        {
            final String id = Member.IdFor(person);
            final Key key = Member.KeyShortFor(ancestor,id);
            this.setKey(key);
        }
    }


    private Member.Field.Statistics fieldStatistics(){
        Member.Field.Statistics fieldStatistics = this.fieldStatistics;
        if (null == fieldStatistics){
            fieldStatistics = new Member.Field.Statistics();
            this.fieldStatistics = fieldStatistics;
        }
        return fieldStatistics;
    }
    public void destroy(){
        this.inheritFrom = null;
        this.person = null;
        this.parent = null;
    }
    public final String getId(){

        String id = Member.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Member.IdFor(this.person);
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
            this.key = Member.KeyShortFor(this.getParentKey(),id);
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
    public final Member getInheritFrom(){
        Member inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Member.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Member ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Member ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean hasParentKey(){
        return (null != this.parentKey);
    }
    public final boolean hasNotParentKey(){
        return (null == this.parentKey);
    }
    public final Key getParentKey(){
        return this.parentKey;
    }
    public final boolean setParentKey(Key ancestor){
        if (IsNotEqual(this.parentKey,ancestor)){

            this.parentKey = ancestor;
            return true;
        }
        else
            return false;
    }
    public final boolean hasParent(){
        return (null != this.parent || null != this.parentKey);
    }
    public final boolean hasNotParent(){
        return (null == this.parent && null == this.parentKey);
    }
    public final Project getParent(){
        Project parent = this.parent;
        if (null == parent){
            Key parentKey = this.parentKey;
            if (null != parentKey){
                parent = Project.Get(parentKey);
                this.parent = parent;
            }
        }
        return parent;
    }
    public final boolean setParent(Project ancestor){
        if (IsNotEqual(this.parent,ancestor)){

            this.parent = ancestor;
            if (null != ancestor)
                this.parentKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }

    public final boolean hasPerson(boolean mayInherit){
        return (null != this.getPerson(mayInherit));
    }
    public final boolean hasNotPerson(boolean mayInherit){
        return (null == this.getPerson(mayInherit));
    }
    public final boolean dropPerson(){
        if (null != this.person){
            this.fieldStatistics().markDirty(Member.Field.Person);
            this.person = null;
            this.personId = null;
            this.personKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getPersonId(){
        return this.personId;
    }
    public final boolean setPersonId(String personId){
        if (IsNotEqual(this.personId,personId)){
            this.fieldStatistics().markDirty(Member.Field.Person);
            this.personId = personId;
            this.personKey = null;
            this.person = null;
            return true;
        }
        else
            return false;
    }
    public final Key getPersonKey(){
        if (null == this.personKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.personId){
                this.personKey = Person.KeyLongFor(this.personId);
            }
        }
        return this.personKey;
    }
    public final Person getPerson(){
        Person person = this.person;
        if (null == person){
            /*
             * BigTable dereference
             */
            Key personKey = this.getPersonKey();

            if (null != personKey){
                person = Person.Get(personKey);
                this.person = person;
            }
        }
        return person;
    }
    public final Person getPerson(boolean mayInherit){
        return this.getPerson();
    }
    public final boolean setPerson(Person person){
        if (IsNotEqual(this.person,person)){
            this.fieldStatistics().markDirty(Member.Field.Person);
            this.person = person;
            if (null != person){
                this.personId = person.getId();
                this.personKey = person.getKey();
            }
            else {
                this.personId = null;
                this.personKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public Json toJsonPerson(){
        Person person = this.getPerson();
        return Json.Wrap( person);
    }
    public boolean fromJsonPerson(Json json){
        if (null == json)
            return false;
        else
            return this.setPerson((Person)json.getValue(Person.class));
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
        gap.data.List re = new Member.Field.List(Field.values());
        /*
         * Compiler has a type astigmatism (parameterized interface gap.data.Field)
         */
        return (gap.data.List<gap.data.Field>)re;
    }
    public final gap.data.Field getClassFieldByName(String name){
        return Field.getField(name);
    }
    public Json toJson(){
        Json json = new json.ObjectJson();
        Json person = this.toJsonPerson();
        if (null != person)
            json.set("person",person);
        return json;
    }
    public boolean fromJson(Json json){
        boolean modified = false;
        return modified;
    }
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Member)proto);
    }
    public final boolean updateFrom(Member proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        return change;
    }
    public java.io.Serializable valueStorage(gap.data.Field field){

        return Field.Storage( (Field)field, (Member)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Member)this, value);
    }
    public final Member markClean(){

        this.fieldStatistics().markClean();
        return (Member)this;
    }
    public final Member markDirty(){

        this.fieldStatistics().markDirty();
        return (Member)this;
    }
    public final Member markDirty(gap.data.Field field){

        this.fieldStatistics().markDirty(field);
        return (Member)this;
    }
    public final Member markDirty(java.io.Serializable instance){
        if (instance == this.person){
            gap.data.Field field = Member.Field.Person;
            return this.markDirty(field);
        }
        else if (null != instance)
            throw new IllegalArgumentException(instance.getClass().getName());
        else
            throw new IllegalArgumentException();
    }
    public final Iterable<gap.data.Field> listClean(){

        return this.fieldStatistics().listClean();
    }
    public final Iterable<gap.data.Field> listDirty(){

        return this.fieldStatistics().listDirty();
    }
    public final boolean isClean(){

        return this.fieldStatistics().isClean();
    }
    public final boolean isDirty(){

        return this.fieldStatistics().isDirty();
    }
    public final gap.service.od.ClassDescriptor getClassDescriptorFor(){
        return Member.ClassDescriptorFor();
    }
    public final gap.service.od.ClassDescriptor getClassDescriptorForParent(){
        return Member.ClassDescriptorForParent();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Member.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0)){
                    String id = this.getId();
                    return (null != id);
                }
                else
                    return false;
            case Person:
                if (name.has(1)){
                    Person person = this.getPerson(true);
                    if (null != person)
                        return person.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasPerson(true);
                }
            default:
                break;
            }
        }
        return super.hasVariable(name);
    }
    public String getVariable(TemplateName name){
        Field field = Member.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Id:
                if (name.is(0))
                    return this.getId();
                else
                    return null;
            case Person:
                if (name.has(1)){
                    Person person = this.getPerson(Notation.MayInherit);
                    if (null != person)
                        return person.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getPersonId();
            default:
                break;
            }
        }
        return super.getVariable(name);
    }
    public void setVariable(TemplateName name, String value){
        Field field = Member.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case Person:

                    Person person = this.getPerson(true);
                    if (null != person)
                        person.setVariable(new TemplateName(name),value);

                    return ;
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Member)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Member.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Person:
                Person person = this.getPerson(true);
                if (null != person){
                    if (name.has(1))
                        return person.getSection(new TemplateName(name));
                    else
                        return new gap.util.ShortList( this, person);
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
    public Member clone(){
        return (Member)super.clone();
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
