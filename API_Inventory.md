# Inventory #

| Protocol | HTTP |
|:---------|:-----|
| Data     | HTML |
| Access   | Public |

## Logon ##

> The normal (not groups) process employs a site logon with requests of the following form.

```
 GET /inventory/index.html
```

> A normal inventory may begin with either of the following URLs.
```
 http://cpi.cognitiveprofile.com/inventory/index.html
 http://cpi.cognitiveprofile.com/
```

## Groups ##

> The groups process works without a site logon for requests of the following form.

```
 GET /inventory/mid/groups.html
```

> For `mid` as listed from project members.

> A group member inventory begins with a special URL in the following pattern.
```
 http://cpi.cognitiveprofile.com/inventory/mid/groups.html
```

> The conclusion of the process is defined for the group and project redirect function.

> The display of the inventory and profile pages may be adjusted with the group and project margins (CSS) information.

> Group member inventory and profile pages have no logon or sharing tools like normal inventory pages.

> Group member inventory and profile URLs become invalid when the project is deleted.

## Examples ##

> Example inventory pages are generated with URLs in the following pattern
```
 http://cpi.cognitiveprofile.com/inventory/example.html?ir=S
```
> for optional query parameter `ir` and value `S` one of the following identifiers to simulate as selected
```
 L4 L3 L2 L1 R1 R2 R3 R4
```