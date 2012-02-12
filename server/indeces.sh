#!/bin/bash

function update {
    for tgt in $(egrep -v '#' indeces.txt)
    do
        src=$(echo $tgt | sed 's/web/war/')
        if [ -f $src ]
        then
            if cp -p $src $tgt
            then
                ls -l $tgt
            else
                cat<<EOF>&2
Error in 'cp -p $src $tgt'
EOF
                return 1
            fi
        else
            cat<<EOF>&2
Error, file not found '${src}'.
EOF
            return 1
        fi
    done
    return 0
}

function report {
    for tgt in $(egrep -v '#' indeces.txt)
    do
        src=$(echo $tgt | sed 's/web/war/')
        if [ -f $src ]
        then
            echo diff $src $tgt
            diff $src $tgt
            echo
        else
            cat<<EOF>&2
Error, file not found '${src}'.
EOF
            return 1
        fi
    done
    return 0
}

if [ "${1}" ]
then
    case "${1}" in
        update)
            if update
            then
                1>&2 echo ok
                exit 0
            else
                1>&2 echo er
                exit 1
            fi
            ;;
        report)
            if report
            then
                1>&2 echo ok
                exit 0
            else
                1>&2 echo er
                exit 1
            fi
            ;;
        *)
            cat<<EOF>&2
Usage
    $0 [report|update]
EOF
            exit 1
            ;;
    esac
else
    if report
    then
        1>&2 echo ok
        exit 0
    else
        1>&2 echo er
        exit 1
    fi
fi
