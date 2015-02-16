
function xxim(xim){

    if (xim.id && 2 == xim.id.length){
        switch (xim.id.charAt(0)){
        case 'L':
        case 'R':
            switch (xim.id.charAt(1)){
            case '1':
            case '2':
            case '3':
            case '4':
                xim.src = iimages[ix4over(xim.id)].src;

                xim.onmouseover = function () {this.src=iimages[ix4under(this.id)].src;}
                xim.onmouseout = function () {this.src=iimages[ix4over(this.id)].src;}
                break;
            default:
                throw new Error("Image '"+xim.id+"' not recognized.");
                break;
            }
            break;
        default:
            throw new Error("Image '"+xim.id+"' not recognized.");
            break;
        }
    }
    else
        throw new Error("Example img bad ID");
}
function xtarget(){
    var href = document.location.toString().split(/[\?]/);
    if (href && href.length && 1 < href.length){
        var qs = href[1].split(/[\&]/);
        var qc = qs.length;
        for (qx = 0; qx < qc; qx++){
            var q = qs[qx];
            var nv = q.split(/\=/);
            if (nv && 2 == nv.length){
                var n = nv[0];
                var v = nv[1];
                /*
                 * target defined as the value of query parameter
                 * named "target" or "ir"
                 */
                if ('target' == n || 'ir' == n){

                    return v;
                }
            }
        }
    }
    return null;
}

function xinit(){

    init(); // inventory.js::init()

    var xid = xtarget();
    if (xid){

        var img = document.getElementById(xid);
        if (img){

            xxim(img);
        }
        else
            throw new Error("Image '"+xid+"' not found.");
    }
}
