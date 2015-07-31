# Project #

| Protocol | HTTP |
|:---------|:-----|
| Data     | JSON |

## Properties ##

| **Name** | **Value** | **Interaction** |
|:---------|:----------|:----------------|
| name     | Project name | read/write      |
| count    | Number of members  | write-once      |
| redirect | Optional group override ([Redirect](API_Redirect.md))  | read/write      |
| margins  | Optional group override ([Margins](API_Margins.md))  | read/write      |
| identifier  | Project identifier | read-only       |
| members  | Project membership | read-only       |

## Create ##

> Create a new Project with
```
 GET /projects/data.json?name=Example&count=10&group=0jPXiVQOXlWKsJSJ
```
> or
```
 POST /projects/data.json?group=0jPXiVQOXlWKsJSJ
```
> for request and response Mime-Type `application/json`.
```
   {
     "name": "Example",
     "count": "10"
   }
```

> Field `count` is required.  Field `name` is highly recommended.

> Fields `margins` or `redirect` are included only as overrides for the values in your [Group](API_Group.md).

> The request line query parameter `group` is required.


## Read ##

> Example project with identifier `rxFKBGfIBfBjH3iP`, name `Test Project`, and count `2`.

```
 GET /projects/rxFKBGfIBfBjH3iP/data.json
```
> for response Mime-Type `application/json`.

```
 {
  "count": 2,
  "created": "Sat, 11 Feb 2012 10:47:50 GMT",
  "name": "Test Project",
  "cleaned": null,
  "redirect": null,
  "identifier": "rxFKBGfIBfBjH3iP",
  "members": [
   {
    "nt": null,
    "created": "Sat, 11 Feb 2012 10:47:50 GMT",
    "inventory": [
     "L4",
     "L3",
     "L2",
     "L1",
     "R1",
     "R2",
     "R3",
     "R4"
    ],
    "nf": null,
    "sf": null,
    "st": null,
    "identifier": "ajyqexIIn8jvMdSl",
    "logonId": null,
    "completed": null
   },
   {
    "nt": 0.6304348,
    "created": "Sat, 11 Feb 2012 10:47:50 GMT",
    "inventory": [
    ],
    "nf": 0.5652174,
    "sf": 1.0,
    "st": 0.84782606,
    "identifier": "c252LllNBVpp8cp3",
    "logonId": null,
    "completed": "Sat, 11 Feb 2012 10:49:57 GMT"
   }
  ],
  "margins": null
 }
```

> This example shows two members in a projects created with count "2".  The first member has identifier `ajyqexIIn8jvMdSl` and shows an incomplete inventory session.  The second member has identifier `c252LllNBVpp8cp3` and shows a completed inventory session.

## Write ##

```
 POST /projects/rxFKBGfIBfBjH3iP/data.json
```
> for request and response Mime-Type `application/json`.

> Any field except `identifier` is optional.  The following fields may be modified.

| `name` | Project name |
|:-------|:-------------|
| `redirect` | Group override |
| `margins` | Group override |


## Delete ##

> Deleting a project deletes the project and members, and all member data.

> Requires a parameter named `delete` and the `group` identifier.

> In testing, please delete all projects created.  In production, delete projects as desired.

```
 POST /projects/rxFKBGfIBfBjH3iP/data.json?delete=please?group=0jPXiVQOXlWKsJSJ
```
> for empty request and response entity bodies (HTTP message body) responses with HTTP response code 200 for success and 404 for not found.  The delete parameter value is any valid URL encoded value.
