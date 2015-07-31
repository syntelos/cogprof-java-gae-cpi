# Margins #

| Protocol | HTTP |
|:---------|:-----|
| Data     | JSON |

Both [Project](API_Project.md) and [Group](API_Group.md) may have a margins property.

This JSON fragment is a field in Project or Group.

```
  "margins": "20px"
```

This value is the [CSS property "margin"](http://www.w3.org/TR/CSS2/box.html#margin-properties) for the embedded HTML page.

When not found in Project, the field in Group will be used.
