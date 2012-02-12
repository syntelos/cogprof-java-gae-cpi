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

import json.Json;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.BlobKey;

import java.util.Date;

import javax.annotation.Generated;

/**
 * Generated bean data binding.
 *
 * @see Note
 */
@Generated(value={"gap.service.OD","BeanData.java"},date="2012-02-11T18:01:34.262Z")
public abstract class NoteData
    extends gap.data.BigTable
    implements DataInheritance<Note>
{

    private final static long serialVersionUID = 1;

    public final static Kind KIND = Kind.Create("Note","cpi.groups","Note","/notes");

    public final static String ClassName = "Note";

    public final static String DefaultSortBy = "identifier";


    public final static gap.service.od.ClassDescriptor ClassDescriptorFor(){
        return ClassDescriptorFor(Note.class);
    }
    public final static gap.service.od.ClassDescriptor ClassDescriptorForParent(){
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
     * Short instance key from parent key
     */
    public final static Key KeyShortIdFor(Key ancestor, String identifier){
        String id = Note.IdFor( identifier);
        return Note.KeyShortFor(ancestor,id);
    }
    /**
     * Used by gap.data.Kind
     *
     * Calls {@link #KeyShortIdFor}
     */
    public final static Key KeyIdFor(Object... args){
        return Note.KeyShortIdFor( (Key)args[0], (String)args[1]);
    }
    /**
     * Used by setId
     *
     * Calls {@link #KeyShortFor}
     */
    public final static Key KeyFor(Object... args){
        return Note.KeyShortFor( (Key)args[0], (String)args[1]);
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
     * Instance lookup from parent key
     */
    public final static Note ForShortIdentifier(Key ancestor, String identifier){
        if (null != identifier){
            Key key = Note.KeyShortIdFor(ancestor, identifier);
            Note instance = (Note)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Note.CreateQueryFor(key);
                return (Note)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Instance lookup or create from parent key
     */
    public final static Note GetCreateShort(Key ancestor, String identifier){
        Note note = Note.ForShortIdentifier(ancestor, identifier);
        if (null == note){
            note = new Note(ancestor, identifier);
            note = (Note)gap.data.Store.PutClass(note);
        }
        return note;
    }


    public final static Key KeyShortFor(Key ancestor, String id){
        return KeyFactory.createKey(ancestor,KIND.getName(),id);
    }


    public final static Note ForShortId(Key ancestor, String id){
        if (null != ancestor && ancestor.isComplete() && null != id){
            Key key = Note.KeyShortFor(ancestor,id);
            Note instance = (Note)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Note.CreateQueryFor(key);
                return (Note)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Used by gap.data.Kind
     */
    public final static Note Get(Key key){
        if (null != key){
            Note instance = (Note)gap.data.Store.GetClass(key);
            if (null != instance)
                return instance;
            else {
                Query q = Note.CreateQueryFor(key);
                return (Note)gap.data.Store.Query1Class(q);
            }
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Key GetKey(Key key){
        if (null != key){
            Query q = Note.CreateQueryFor(key);
            return gap.data.Store.Query1Key(q);
        }
        else
            throw new IllegalArgumentException();
    }
    public final static Note FromObject(Object value){
        if (null == value)
            return null;
        else if (value instanceof Note)
            return (Note)value;
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
     * @see Note#IdFor
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
    public final static void Delete(Account parent){
        if (null != parent){

            DeleteChildrenOf(parent.getKey());
        }
    }
    /**
     * Drop all children of the parent
     */
    public final static void DeleteChildrenOf(Key parentKey){
        if (null != parentKey){

             gap.data.Store.DeleteCollection(KIND,Note.CreateQueryFor(parentKey));
        }
    }
    /**
     * Drop the instance from memcache and datastore.
     */
    public final static void Delete(Note instance){
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
    public final static void Clean(Note instance){
        if (null != instance){

            gap.data.Store.Clean(instance.getKey());
        }
    }
    /**
     * Store the instance.
     */
    public final static void Save(Note instance){
        if (null != instance){

            gap.data.Store.PutClass(instance);
        }
    }
    /**
     * Write the instance to store.
     */
    public final static void Store(Note instance){
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
    public final static Note Query1(Query query){
        if (null != query)
            return (Note)gap.data.Store.Query1Class(query);
        else
            throw new IllegalArgumentException();
    }
    public final static BigTableIterator<Note> ListPage(Key ancestor, Page page){

        return Note.QueryN(Note.CreateQueryFor(ancestor),page);
    }
    public final static BigTableIterator<Note> QueryN(Query query, Page page){
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
     * Persistent fields' binding for {@link Note}
     */
    public static enum Field
        implements gap.data.Field<Note.Field>
    {
        InheritFromKey("inheritFromKey",Type.Primitive),
        ParentKey("parentKey",Type.Primitive),
        Key("key",Type.Primitive),
        Id("id",Type.Primitive),
        Identifier("identifier",Type.Primitive),
        Writer("writer",Type.BigTable),
        Created("created",Type.Primitive),
        Updated("updated",Type.Primitive),
        Text("text",Type.Primitive);

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
            extends gap.data.Field.Statistics<Note.Field>
        {
            public Statistics(){
                super(Note.Field.class);
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static Object Get(Field field, Note instance, boolean mayInherit){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case ParentKey:
                return instance.getParentKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(mayInherit);
            case Writer:
                return instance.getWriterId();
            case Created:
                return instance.getCreated(mayInherit);
            case Updated:
                return instance.getUpdated(mayInherit);
            case Text:
                return instance.getText(mayInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Note");
            }
        }
        /**
         * Dynamic binding operator for field data type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static boolean Set(Field field, Note instance, Object value){
            switch(field){
            case InheritFromKey:
                return instance.setInheritFromKey(gap.Objects.KeyFromObject(value));
            case ParentKey:
                return instance.setParentKey(gap.Objects.KeyFromObject(value));
            case Key:
                return instance.setKey(gap.Objects.KeyFromObject(value));
            case Id:
                return instance.setId(gap.Objects.StringFromObject(value));
            case Identifier:
                return instance.setIdentifier(gap.Objects.StringFromObject(value));
            case Writer:
                return instance.setWriterId(gap.Objects.StringFromObject(value));
            case Created:
                return instance.setCreated(gap.Objects.DateFromObject(value));
            case Updated:
                return instance.setUpdated(gap.Objects.DateFromObject(value));
            case Text:
                return instance.setText(gap.Objects.TextFromObject(value));
            default:
                throw new IllegalArgumentException(field.toString()+" in Note");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static java.io.Serializable Storage(Field field, Note instance){
            switch(field){
            case InheritFromKey:
                return instance.getInheritFromKey();
            case ParentKey:
                return instance.getParentKey();
            case Key:
                return instance.getKey();
            case Id:
                return instance.getId();
            case Identifier:
                return instance.getIdentifier(MayNotInherit);
            case Writer:
                return instance.getWriterId();
            case Created:
                return instance.getCreated(MayNotInherit);
            case Updated:
                return instance.getUpdated(MayNotInherit);
            case Text:
                return instance.getText(MayNotInherit);
            default:
                throw new IllegalArgumentException(field.toString()+" in Note");
            }
        }
        /**
         * Dynamic binding operator for field storage type
         *
         * Persistent BigTable fields are represented by the string ID.
         */
        public static void Storage(Field field, Note instance, java.io.Serializable value){
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
            case Identifier:
                instance.setIdentifier( (String)value);
                return;
            case Writer:
                instance.setWriterId( (String)value);
                return;
            case Created:
                instance.setCreated( (Date)value);
                return;
            case Updated:
                instance.setUpdated( (Date)value);
                return;
            case Text:
                instance.setText( (Text)value);
                return;
            default:
                throw new IllegalArgumentException(field.toString()+" in Note");
            }
        }


        public final static class List
            extends gap.util.ArrayList<Note.Field>
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

    private transient Note.Field.Statistics fieldStatistics = new Note.Field.Statistics();

    private transient Note inheritFrom;


    private String identifier;
    private String writerId;
    private transient Key writerKey;
    private transient Person writer;
    private Date created;
    private Date updated;
    private Text text;


    private Key parentKey;
    private transient Account parent;


    protected NoteData() {
        super();
    }
    protected NoteData(Key ancestor, String identifier) {
        super();
        this.setIdentifier(identifier);
        this.parentKey = ancestor;
        {
            final String id = Note.IdFor(identifier);
            final Key key = Note.KeyShortFor(ancestor,id);
            this.setKey(key);
        }
    }


    private Note.Field.Statistics fieldStatistics(){
        Note.Field.Statistics fieldStatistics = this.fieldStatistics;
        if (null == fieldStatistics){
            fieldStatistics = new Note.Field.Statistics();
            this.fieldStatistics = fieldStatistics;
        }
        return fieldStatistics;
    }
    public void destroy(){
        this.inheritFrom = null;
        this.identifier = null;
        this.writer = null;
        this.created = null;
        this.updated = null;
        this.text = null;
        this.parent = null;
    }
    public final String getId(){

        String id = Note.IdFor(KIND.name, this.key);
        if (null != id)
            return id;
        else
            return Note.IdFor(this.identifier);
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
            this.key = Note.KeyShortFor(this.getParentKey(),id);
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
    public final Note getInheritFrom(){
        Note inheritFrom = this.inheritFrom;
        if (null == inheritFrom){
            Key inheritFromKey = this.inheritFromKey;
            if (null != inheritFromKey){
                inheritFrom = Note.Get(inheritFromKey);
                this.inheritFrom = inheritFrom;
            }
        }
        return inheritFrom;
    }
    public final boolean setInheritFrom(Note ancestor){
        if (IsNotEqual(this.inheritFrom,ancestor)){

            this.inheritFrom = ancestor;
            if (null != ancestor)
                this.inheritFromKey = ancestor.getKey();
            return true;
        }
        else
            return false;
    }
    public final boolean inheritFrom(Note ancestor){
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
    public final Account getParent(){
        Account parent = this.parent;
        if (null == parent){
            Key parentKey = this.parentKey;
            if (null != parentKey){
                parent = Account.Get(parentKey);
                this.parent = parent;
            }
        }
        return parent;
    }
    public final boolean setParent(Account ancestor){
        if (IsNotEqual(this.parent,ancestor)){

            this.parent = ancestor;
            if (null != ancestor)
                this.parentKey = ancestor.getKey();
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
            this.fieldStatistics().markDirty(Note.Field.Identifier);
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
            this.fieldStatistics().markDirty(Note.Field.Identifier);
            this.identifier = identifier;
            return true;
        }
        else
            return false;
    }
    public final boolean hasWriter(boolean mayInherit){
        return (null != this.getWriter(mayInherit));
    }
    public final boolean hasNotWriter(boolean mayInherit){
        return (null == this.getWriter(mayInherit));
    }
    public final boolean dropWriter(){
        if (null != this.writer){
            this.fieldStatistics().markDirty(Note.Field.Writer);
            this.writer = null;
            this.writerId = null;
            this.writerKey = null;
            return true;
        }
        else
            return false;
    }
    public final String getWriterId(){
        return this.writerId;
    }
    public final boolean setWriterId(String writerId){
        if (IsNotEqual(this.writerId,writerId)){
            this.fieldStatistics().markDirty(Note.Field.Writer);
            this.writerId = writerId;
            this.writerKey = null;
            this.writer = null;
            return true;
        }
        else
            return false;
    }
    public final Key getWriterKey(){
        if (null == this.writerKey){
            /*
             * BigTable dereferencing
             */
            if (null != this.writerId){
                this.writerKey = Person.KeyLongFor(this.writerId);
            }
        }
        return this.writerKey;
    }
    public final String getWriterId(boolean mayInherit){
        if (null != this.writerId)
            return this.writerId;
        else if (mayInherit && this.hasInheritFrom()){
            Note inheritFrom = this.getInheritFrom();
            return inheritFrom.getWriterId(mayInherit);
        }
        else
            return null;
    }
    public final Key getWriterKey(boolean mayInherit){
        Key writerKey = this.getWriterKey();
        if (null != writerKey)
            return writerKey;
        else if (mayInherit && this.hasInheritFrom()){
            Note inheritFrom = this.getInheritFrom();
            return inheritFrom.getWriterKey(mayInherit);
        }
        else
            return null;
    }
    public final Person getWriter(){
        return this.getWriter(Notation.MayInherit);
    }
    public final Person getWriter(boolean mayInherit){
        Person writer = this.writer;
        if (null == writer){
            /*
             * BigTable dereference
             */
            Key writerKey = this.getWriterKey(mayInherit);

            if (null != writerKey){
                writer = Person.Get(writerKey);
                this.writer = writer;
            }
        }

        if (null == writer && mayInherit && this.hasInheritFrom()){
            Note inheritFrom = this.getInheritFrom();
            return inheritFrom.getWriter(Notation.MayInherit);
        }
        else
            return writer;
    }
    public final boolean setWriter(Person writer, boolean withInheritance){
        if (IsNotEqual(this.writer,this.getWriter(withInheritance))){
            this.fieldStatistics().markDirty(Note.Field.Writer);
            this.writer = writer;
            if (null != writer){
                this.writerId = writer.getId();
                this.writerKey = writer.getKey();
            }
            else {
                this.writerId = null;
                this.writerKey = null;
            }
            return true;
        }
        else
            return false;
    }
    public final boolean setWriter(Person writer){
        if (IsNotEqual(this.writer,writer)){
            this.fieldStatistics().markDirty(Note.Field.Writer);
            this.writer = writer;
            if (null != writer){
                this.writerId = writer.getId();
                this.writerKey = writer.getKey();
            }
            else {
                this.writerId = null;
                this.writerKey = null;
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
            this.fieldStatistics().markDirty(Note.Field.Created);
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
                Note inheritFrom = this.getInheritFrom();
                return inheritFrom.getCreated(Notation.MayInherit);
            }
            return created;
        }
        else
            return this.created;
    }
    public final boolean setCreated(Date created, boolean withInheritance){
        if (IsNotEqual(this.created,this.getCreated(withInheritance))){
            this.fieldStatistics().markDirty(Note.Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean setCreated(Date created){
        if (IsNotEqual(this.created,created)){
            this.fieldStatistics().markDirty(Note.Field.Created);
            this.created = created;
            return true;
        }
        else
            return false;
    }
    public final boolean hasUpdated(boolean mayInherit){
        return (null != this.getUpdated(mayInherit));
    }
    public final boolean hasNotUpdated(boolean mayInherit){
        return (null == this.getUpdated(mayInherit));
    }
    public final boolean dropUpdated(){
        if (null != this.updated){
            this.fieldStatistics().markDirty(Note.Field.Updated);
            this.updated = null;
            return true;
        }
        else
            return false;
    }
    public final Date getUpdated(){
        return this.getUpdated(Notation.MayInherit);
    }
    public final Date getUpdated(boolean mayInherit){
        if (mayInherit){
            Date updated = this.updated;
            if (null == updated && this.hasInheritFrom()){
                Note inheritFrom = this.getInheritFrom();
                return inheritFrom.getUpdated(Notation.MayInherit);
            }
            return updated;
        }
        else
            return this.updated;
    }
    public final boolean setUpdated(Date updated, boolean withInheritance){
        if (IsNotEqual(this.updated,this.getUpdated(withInheritance))){
            this.fieldStatistics().markDirty(Note.Field.Updated);
            this.updated = updated;
            return true;
        }
        else
            return false;
    }
    public final boolean setUpdated(Date updated){
        if (IsNotEqual(this.updated,updated)){
            this.fieldStatistics().markDirty(Note.Field.Updated);
            this.updated = updated;
            return true;
        }
        else
            return false;
    }
    public final boolean hasText(boolean mayInherit){
        return (null != this.getText(mayInherit));
    }
    public final boolean hasNotText(boolean mayInherit){
        return (null == this.getText(mayInherit));
    }
    public final boolean dropText(){
        if (null != this.text){
            this.fieldStatistics().markDirty(Note.Field.Text);
            this.text = null;
            return true;
        }
        else
            return false;
    }
    public final Text getText(){
        return this.getText(Notation.MayInherit);
    }
    public final Text getText(boolean mayInherit){
        if (mayInherit){
            Text text = this.text;
            if (null == text && this.hasInheritFrom()){
                Note inheritFrom = this.getInheritFrom();
                return inheritFrom.getText(Notation.MayInherit);
            }
            return text;
        }
        else
            return this.text;
    }
    public final boolean setText(Text text, boolean withInheritance){
        if (IsNotEqual(this.text,this.getText(withInheritance))){
            this.fieldStatistics().markDirty(Note.Field.Text);
            this.text = text;
            return true;
        }
        else
            return false;
    }
    public final boolean setText(Text text){
        if (IsNotEqual(this.text,text)){
            this.fieldStatistics().markDirty(Note.Field.Text);
            this.text = text;
            return true;
        }
        else
            return false;
    }
    public Json toJsonIdentifier(){
        String identifier = this.getIdentifier();
        return Json.Wrap( identifier);
    }
    public boolean fromJsonIdentifier(Json json){
        if (null == json)
            return false;
        else
            return this.setIdentifier((String)json.getValue(String.class));
    }
    public Json toJsonWriter(){
        Person writer = this.getWriter();
        return Json.Wrap( writer);
    }
    public boolean fromJsonWriter(Json json){
        if (null == json)
            return false;
        else
            return this.setWriter((Person)json.getValue(Person.class));
    }
    public Json toJsonCreated(){
        Date created = this.getCreated();
        return Json.Wrap( created);
    }
    public boolean fromJsonCreated(Json json){
        if (null == json)
            return false;
        else
            return this.setCreated((Date)json.getValue(Date.class));
    }
    public Json toJsonUpdated(){
        Date updated = this.getUpdated();
        return Json.Wrap( updated);
    }
    public boolean fromJsonUpdated(Json json){
        if (null == json)
            return false;
        else
            return this.setUpdated((Date)json.getValue(Date.class));
    }
    public Json toJsonText(){
        Text text = this.getText();
        return Json.Wrap( text);
    }
    public boolean fromJsonText(Json json){
        if (null == json)
            return false;
        else
            return this.setText((Text)json.getValue(Text.class));
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
        gap.data.List re = new Note.Field.List(Field.values());
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
        Json identifier = this.toJsonIdentifier();
        if (null != identifier)
            json.set("identifier",identifier);
        Json writer = this.toJsonWriter();
        if (null != writer)
            json.set("writer",writer);
        Json created = this.toJsonCreated();
        if (null != created)
            json.set("created",created);
        Json updated = this.toJsonUpdated();
        if (null != updated)
            json.set("updated",updated);
        Json text = this.toJsonText();
        if (null != text)
            json.set("text",text);
        return json;
    }
    public boolean fromJson(Json json){
        boolean modified = false;
        modified = (this.fromJsonWriter(json.at("writer")) || modified);
        modified = (this.fromJsonCreated(json.at("created")) || modified);
        modified = (this.fromJsonUpdated(json.at("updated")) || modified);
        modified = (this.fromJsonText(json.at("text")) || modified);
        return modified;
    }
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;
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
        String updatedRequest = req.getParameter("updated");
        if (null != updatedRequest && 0 < updatedRequest.length()){
            try {
                Date updated = gap.Strings.DateFromString(updatedRequest);
                if (this.setUpdated(updated)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"updated",updatedRequest,exc);
            }
        }
        String textRequest = req.getParameter("text");
        if (null != textRequest && 0 < textRequest.length()){
            try {
                Text text = gap.Strings.TextFromString(textRequest);
                if (this.setText(text)){
                    change = true;
                }
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"text",textRequest,exc);
            }
        }
        return change;
    }
    public final boolean updateFrom(BigTable proto){
        return this.updateFrom( (Note)proto);
    }
    public final boolean updateFrom(Note proto){
        boolean mayInherit = (!this.hasInheritFromKey());
        boolean change = false;
        Date created = proto.getCreated(mayInherit);
        if (null != created && this.setCreated(created)){
            change = true;
        }
        Date updated = proto.getUpdated(mayInherit);
        if (null != updated && this.setUpdated(updated)){
            change = true;
        }
        Text text = proto.getText(mayInherit);
        if (null != text && this.setText(text)){
            change = true;
        }
        return change;
    }
    public java.io.Serializable valueStorage(gap.data.Field field){

        return Field.Storage( (Field)field, (Note)this);
    }
    public void defineStorage(gap.data.Field field, java.io.Serializable value){

        Field.Storage( (Field)field, (Note)this, value);
    }
    public final Note markClean(){

        this.fieldStatistics().markClean();
        return (Note)this;
    }
    public final Note markDirty(){

        this.fieldStatistics().markDirty();
        return (Note)this;
    }
    public final Note markDirty(gap.data.Field field){

        this.fieldStatistics().markDirty(field);
        return (Note)this;
    }
    public final Note markDirty(java.io.Serializable instance){
        if (instance == this.identifier){
            gap.data.Field field = Note.Field.Identifier;
            return this.markDirty(field);
        }
        else if (instance == this.writer){
            gap.data.Field field = Note.Field.Writer;
            return this.markDirty(field);
        }
        else if (instance == this.created){
            gap.data.Field field = Note.Field.Created;
            return this.markDirty(field);
        }
        else if (instance == this.updated){
            gap.data.Field field = Note.Field.Updated;
            return this.markDirty(field);
        }
        else if (instance == this.text){
            gap.data.Field field = Note.Field.Text;
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
        return Note.ClassDescriptorFor();
    }
    public final gap.service.od.ClassDescriptor getClassDescriptorForParent(){
        return Note.ClassDescriptorForParent();
    }
    /*
     * Template Data Dictionary
     */
    public boolean hasVariable(TemplateName name){
        Field field = Note.Field.For(name.getTerm());
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
            case Writer:
                if (name.has(1)){
                    Person writer = this.getWriter(true);
                    if (null != writer)
                        return writer.hasVariable(new TemplateName(name));
                    else
                        return false;
                }
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasWriter(true);
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
            case Updated:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasUpdated(true);
                }
            case Text:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else {
                    /*
                     * Synthesize section for Field (EXISTS)
                     */
                    return this.hasText(true);
                }
            default:
                break;
            }
        }
        return super.hasVariable(name);
    }
    public String getVariable(TemplateName name){
        Field field = Note.Field.For(name.getTerm());
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
            case Writer:
                if (name.has(1)){
                    Person writer = this.getWriter(Notation.MayInherit);
                    if (null != writer)
                        return writer.getVariable(new TemplateName(name));
                    else
                        return null;
                }
                else
                    return this.getWriterId(Notation.MayInherit);
            case Created:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getCreated(true));
            case Updated:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.DateToString(this.getUpdated(true));
            case Text:
                if (name.has(1))
                    throw new IllegalStateException(field.name());
                else
                    return gap.Strings.TextToString(this.getText(true));
            default:
                break;
            }
        }
        return super.getVariable(name);
    }
    public void setVariable(TemplateName name, String value){
        Field field = Note.Field.For(name.getTerm());
        if (null != field){
            if (name.has(1)){
                switch (field){
                case Id:
                    throw new UnsupportedOperationException(field.name());
                case Identifier:
                    throw new IllegalStateException(field.name());
                case Writer:

                    Person writer = this.getWriter(true);
                    if (null != writer)
                        writer.setVariable(new TemplateName(name),value);

                    return ;
                case Created:
                    throw new IllegalStateException(field.name());
                case Updated:
                    throw new IllegalStateException(field.name());
                case Text:
                    throw new IllegalStateException(field.name());
                default:
                    throw new IllegalStateException(field.name());
                }
            }
            else
                Field.Set(field,((Note)this),value);
        }
        else {
            super.setVariable(name,value);
        }
    }
    public List.Short<TemplateDataDictionary> getSection(TemplateName name){
        Field field = Note.Field.For(name.getTerm());
        if (null != field){
            switch (field){
            case Identifier:
                return null;
            case Writer:
                Person writer = this.getWriter(true);
                if (null != writer){
                    if (name.has(1))
                        return writer.getSection(new TemplateName(name));
                    else
                        return new gap.util.ShortList( this, writer);
                }
                else
                    return null;
            case Created:
                return null;
            case Updated:
                return null;
            case Text:
                return null;
            default:
                throw new IllegalStateException(field.name());
            }
        }
        else {
            return super.getSection(name);
        }
    }
    public Note clone(){
        return (Note)super.clone();
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
