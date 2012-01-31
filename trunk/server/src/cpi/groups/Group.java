
package cpi.groups;

import cpi.Redirect;
import cpi.Margins;

import oso.data.Person;

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
 * @see GroupData
 */
public final class Group
    extends GroupData
{
    public final static Group ForOwner(Person owner){
        Query query = Group.CreateQueryFor();
        {
            Filter filter = new Filter(Group.KIND).add(Field.Owner,Filter.Op.eq,owner.getId());
            filter.update(query);
        }
        return Group.Query1(query);
    }
    public final static Group ForAdmin(Person admin){
        Query query = Group.CreateQueryFor();
        {
            Filter filter = new Filter(Group.KIND).add(Field.Admin,Filter.Op.eq,admin.getId());
            filter.update(query);
        }
        return Group.Query1(query);
    }
    public final static Group For(Person user){
        Group group = Group.ForAdmin(user);
        if (null == group){
            group = Group.ForOwner(user);
        }
        return group;
    }


    public Group() {
        super();
    }
    public Group(String identifier) {
        super( identifier);
    }
    public Group(Request req){
        super(gap.Strings.RandomIdentifier());

        this.updateFrom(req);

        this.setCreated(new Date());
        this.dropBilled();
        this.dropPaid();
    }


    @Override
    public boolean updateFrom(Request req) throws ValidationError {
        boolean modified = super.updateFrom(req);

        modified = (this.setRedirect(new Redirect(req)) || modified);
        modified = (this.setMargins(new Margins(req)) || modified);

        final String owner_logonId = req.getParameter("owner_logonId");
        if (null != owner_logonId){
            modified = (this.setOwner(Person.GetCreateLongLogonId(owner_logonId)) || modified);
        }

        final String admin_logonId = req.getParameter("admin_logonId");
        if (null != admin_logonId){
            modified = (this.setAdmin(Person.GetCreateLongLogonId(admin_logonId)) || modified);
        }
        return modified;
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
    /**
     * @param person Viewer
     * @return Person is admin or owner
     */
    public boolean hasGroupAccess(Person person){
        if (null == person)
            return false;
        else
            return (person.equals(this.getAdmin()) || person.equals(this.getOwner()));
    }
    public Redirect getCreateRedirect(){
        Redirect redirect = this.getRedirect();
        if (null == redirect){
            redirect = new Redirect();
            this.setRedirect(redirect);
        }
        return redirect;
    }
    public Margins getCreateMargins(){
        Margins margins = this.getMargins();
        if (null == margins){
            margins = new Margins();
            this.setMargins(margins);
        }
        return margins;
    }
    public boolean setIdentifier(json.Json json){
        return false;
    }
    // public boolean setName(json.Json json){
    // }
    public boolean setOwner(json.Json json){
        return false;
    }
    public boolean setAdmin(json.Json json){
        return false;
    }
    public boolean setCreated(json.Json json){
        return false;
    }
    public boolean setBilled(json.Json json){
        return false;
    }
    public boolean setPaid(json.Json json){
        return false;
    }
    // public boolean setRedirect(json.Json json){
    // }
    // public boolean setMargins(json.Json json){
    // }
    public List.Primitive<Key> listProjects(){
        return Project.List(this);
    }
    public List.Primitive<Key> listAccounts(){
        return Account.List(this);
    }
}
