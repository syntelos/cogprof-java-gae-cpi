# Result #

| Protocol | HTTP |
|:---------|:-----|
| Data     | JSON, PNG |
| Access   | Public |

## Logon ##

> The normal (not groups) process employs a site logon with requests of the following form.

```
 GET /profile/index.html
```
> Mime-Type `text/html`

> The HTML result page for a normal logon will include an image, data, and associated tools.

> This reference will redirect a logon to a permanent coded location, as in the following.
```
 GET /profile/b390a019eff70566/index.html
```

### Data ###

> The CPI Online includes [JSON](http://www.json.org/) data access via a Result URL as
```
 GET /profile/b390a019eff70566/data.json
```

> Example
> > [/profile/b390a019eff70566/data.json](http://cpi.cognitiveprofile.com/profile/b390a019eff70566/data.json)


### Image ###


> The CPI Online includes result display image access via a Result URL as
```
 GET /profile/b390a019eff70566/image.png
```

> Example
> > [/profile/b390a019eff70566/image.png](http://cpi.cognitiveprofile.com/profile/b390a019eff70566/image.png)

## Groups ##


> The groups process works similarly, using `groups.html` in place of `index.html`, and employing a group member identifier in place of a result code.
```
 GET /profile/mid/groups.html
```
> In this pattern - example, `mid` refers to the member identifier available from the [Project API](API_Project.md).

> The [inventory](API_Inventory.md) begins with a URL in the following pattern.
```
 http://cpi.cognitiveprofile.com/inventory/mid/groups.html
```
> This reference is the source of an HTML IFRAME when the CPI is embedded into your external application.

### Redirect ###

> For groups, the [Redirect](API_Redirect.md) controls whether the profile result page is shown, or shown momentarily with a timeout, or shown with zero timeout.

### Image ###

> The image is available through a similar pattern
```
 GET /profile/mid/groups.png
```
> in PNG format.

### Data ###

> Result data is available through the [Project API](API_Project.md).