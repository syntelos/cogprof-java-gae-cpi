# Group #

| Protocol | HTTP |
|:---------|:-----|
| Data     | JSON |

## Properties ##

| **Name** | **Value** | **Interaction** |
|:---------|:----------|:----------------|
| name     | Group name | read/write      |
| redirect | Optional project default ([Redirect](API_Redirect.md))  | read/write      |
| margins  | Optional project default ([Margins](API_Margins.md))  | read/write      |
| identifier  | Group identifier | read-only       |

## Create ##

> Groups are created by the [service administrator](mailto:cpi@cognitiveprofile.com?Subject=CPI+Groups).  Required information includes the Group Name, and email address for the Group Billing Administrator.  Billing occurs by email with Google Checkout invoices.

### Quadrant Labels ###

> Profile result image Quadrant Labels may be modified by the [service administrator](mailto:cpi@cognitiveprofile.com?Subject=CPI+Groups) with the written approval of Dr Krause.  These strings should have one to eight characters.  Test images may be produced on request.

> The following is a silly example...

> ![http://cpi.cognitiveprofile.com/profile/R7Fk7gxptlqIAXHx/groups.png](http://cpi.cognitiveprofile.com/profile/R7Fk7gxptlqIAXHx/groups.png)

### Test Status ###

> New groups are created in TEST status for development purposes.
```
 {
  "test": true
 }
```

> The service administrator must be notified when the development process has completed and real projects need to be created.  At that time the Group test status will change to LIVE.  Any existing projects will be deleted on the change from TEST to LIVE.
```
 {
  "test": false
 }
```

## Read ##

> The Group identifier is required to read or write the Group data structure.

```
 GET /groups/0jPXiVQOXlWKsJSJ/data.json
```
> for response Mime-Type `application/json`
```
 {
  "identifier": "0jPXiVQOXlWKsJSJ",
  "name": "Test",
  "billed": null,
  "paid": null,
  "test": true,
  "created": "Wed, 08 Feb 2012 22:07:44 GMT",
  "admin": {
   "project": null,
   "nt": null,
   "created": null,
   "nf": null,
   "sf": null,
   "st": null,
   "identifier": "4BCPdSbKC4b5BsgQ",
   "logonId": "admin@example.com",
   "completed": null
  },
  "owner": {
   "project": null,
   "nt": null,
   "created": null,
   "nf": null,
   "sf": null,
   "st": null,
   "identifier": "JWB4Q5Xions1Mzkz",
   "logonId": "test@example.com",
   "completed": null
  },
  "redirect": {
   "sequence": "timeout",
   "target": null,
   "href": "/profile",
   "timeout": 0
  },
  "margins": "10px"
 }
```

## Write ##

> To update the Group parameters, the HTTP POST method is employed with the HTTP content type header set for JSON.   The HTTP request entity body is a JSON object as shown in these examples -- as returned on GET request.  The update request JSON object contains fields and values to be modified.  Readonly fields (e.g. `identifier`) will be safely ignored.  The data known after the update is returned in the HTTP response body.

```
 POST /groups/0jPXiVQOXlWKsJSJ/data.json
```
> for request and response Mime-Type `application/json`.

> Any field except `identifier` is optional.  Most fields are read-only, according to the needs of the group user administrator.  The following fields may be changed by the group user administator (via OAuth /JSON API).

| `name` | Group name |
|:-------|:-----------|
| `owner` | Billing administrator |
| `admin` | User administrator |
| `redirect` | Redirect function parameters |
| `margins` | Visual display margins for inventory and profile HTML pages |