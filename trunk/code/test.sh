#!/bin/bash

if jarf=$(2>/dev/null ls cpi-code-*.jar)
then

 while java -cp ${jarf} cpi.Code; do echo; done

else
 echo requires jar
 exit 1
fi
