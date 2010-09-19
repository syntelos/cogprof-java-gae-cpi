
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


function xinit( xid){

    init(); // inventory.js::init()

    if (xid){
        var img = document.getElementById(xid);
        if (img)
            xxim(img);
        else
            throw new Error("Image '"+xid+"' not found.");
    }
}
