> The CPI Online [server](http://code.google.com/p/cpi/source/browse/trunk/server) uses the [Gap Data](http://gap-data.googlecode.com/) software framework.

# Data Schema #

> A "data programming bean" defines the storage and caching and application I/O for a record in a relational database table.  It communicates with the Datastore and Memcache, and implements HTTP and XMPP applications with GAE.

> The Gap Data compiler generates java programming language source code for Google App Engine data beans.  The Gap Data Bean is defined in ODL.

## Person ##

> This application redefines [Person](http://code.google.com/p/cpi/source/browse/trunk/server/odl/oso/data/Person.odl) from [gap-data](http://code.google.com/p/gap-data/source/browse/trunk/gae/odl/oso/data/Person.odl).
```
 package oso.data;
 path /people;
 import cpi.Inventory;
 import cpi.groups.Project;

 /**
  * Accept Accounts or OAuth Logon Id for viewer identity
  * 
  * Application access by random identifier
  * 
  * Logon Id is optional with Project
  * 
  * Identifier is required
  */
 class Person
     version 8
 {
     String logonId;
     *unique String identifier;
     List.Primitive<Inventory> inventory;
     Float nf;
     Float nt;
     Float st;
     Float sf;
     Date created;
     Date completed;
     Project project;
 }
```

> Person is used by Gap Data for any logged- in user

> A Person with a Project may not have a logonId

> See also [API Result](API_Result.md)

## Group ##

> A [Group](http://code.google.com/p/cpi/source/browse/trunk/server/odl/cpi/groups/Group.odl) has many [Projects](http://code.google.com/p/cpi/source/browse/trunk/server/odl/cpi/groups/Project.odl)
```
package cpi.groups;
path /groups;
import oso.data.Person;

import cpi.Margins;
import cpi.Redirect;
import cpi.ProfileLabels;

/**
 * A {@link Group} has many {@link Project Projects}
 */
class Group
    version 1
{
    *unique String identifier;
    /*
     * Required label
     */
    String name;
    /*
     * Billing admin
     */
    Person owner;
    /*
     * Project admin
     */
    Person admin;
    /*
     * Group created
     */
    Date created;
    /*
     * Usage billed
     */
    Date billed;
    /*
     * Bill paid
     */
    Date paid;
    /*
     * If not Project, use Group
     */
    Redirect redirect;
    /*
     * If not Project, use Group
     */
    Margins margins;
    /*
     * While group is in test, accounts and notes are not created.
     * Null value is equivalent to false value, not in test.
     */
    Boolean test;
    /*
     * Specialized labels for profile image quadrants
     */
    ProfileLabels labels;
}
```

> See also [API Group](API_Group.md)


## Project ##

> A [Group](http://code.google.com/p/cpi/source/browse/trunk/server/odl/cpi/groups/Group.odl) has many [Projects](http://code.google.com/p/cpi/source/browse/trunk/server/odl/cpi/groups/Project.odl)
```
package cpi.groups;
path /projects;
import oso.data.Person;
import cpi.Redirect;
import cpi.Margins;

/**
 * A {@link Group} has many {@link Project Projects}
 * 
 * @note Could add (Person admin) for delegation
 */
parent Project
    version 1
    child Member
{
    *unique String identifier;
    /*
     * Optional label
     */
    String name;
    /*
     * Parent group
     */
    Group group;
    /* 
     * Project created
     */
    Date created;
    /* 
     * Membership destroyed
     */
    Date cleaned;
    /*
     * Quota on query Person.project = Project.identifier
     */
    Integer count;
    /*
     * If not Project, use Group
     */
    Redirect redirect;
    /*
     * If not Project, use Group
     */
    Margins margins;
    /*
     * List of person identifiers
     */
    List.Short<Member> members;
}
```

> See also [API Project](API_Project.md)

