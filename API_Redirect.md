

# Redirect #

| Protocol | HTTP |
|:---------|:-----|
| Data     | JSON |

Both [Project](API_Project.md) and [Group](API_Group.md) have a redirect function.

This JSON fragment is a field in Project or Group.

```
  "redirect": {
    "href": "http://host.net/url",
    "target": "frame_name",
    "sequence": ( "inject" | "timeout" ) , 
    "timeout": "millis"
  }
```

When not found in Project, the field in Group will be used.

## Properties ##

### HREF ###

The redirect link url accepts the following template variables

| **Name** | **Value** |
|:---------|:----------|
| st       | Result (0.0 - 1.0) |
| nt       | Result    |
| sf       | Result    |
| nf       | Result    |
| identifier  | Profile identifier |

These names may be employed with Hapax syntax in the redirect HREF string.

Example

```
 http://redirect.net/for?st={{=st}}&sf={{=sf}}&nf={{=nf}}&nt={{=nt}}&y={{=identifier}}
```

### Target Frame Name ###

A frame name will cause the redirect to target a browser window frame, or a new browser window (for any unknown name).

### Sequence ###

> #### inject ####
> Cause the inventory process to redirect before displaying a result page (with graph image).


> #### timeout ####
> Cause the inventory process to proceed to the result page, and then timeout and redirect.

### Timeout ###

For sequence timeout, wait for this number of milliseconds before redirecting.

This value is employed as the timeout argument to [window setTimeout](http://www.w3.org/TR/Window/#window-timers), for which the value zero (0) means "no timeout".