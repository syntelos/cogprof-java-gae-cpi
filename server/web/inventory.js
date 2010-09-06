
function id4index(idx){
    switch(idx){
    case 0:
        return "L4";
    case 1:
        return "L3";
    case 2:
        return "L2";
    case 3:
        return "L1";
    case 4:
        return "R1";
    case 5:
        return "R2";
    case 6:
        return "R3";
    case 7:
        return "R4";
    case 8:
        return "L4";
    case 9:
        return "L3";
    case 10:
        return "L2";
    case 11:
        return "L1";
    case 12:
        return "R1";
    case 13:
        return "R2";
    case 14:
        return "R3";
    case 15:
        return "R4";
    default:
        throw new Error();
    }
}
function cl4index(idx){
    if (idx < 8)
        return "white";
    else
        return "black"
}
function sz4index(idx){
    switch(idx){
    case 0:
        return "88x57";
    case 1:
        return "70x57";
    case 2:
        return "57x57";
    case 3:
        return "57x57";
    case 4:
        return "57x57";
    case 5:
        return "57x57";
    case 6:
        return "70x57";
    case 7:
        return "88x57";
    case 8:
        return "88x57";
    case 9:
        return "70x57";
    case 10:
        return "57x57";
    case 11:
        return "57x57";
    case 12:
        return "57x57";
    case 13:
        return "57x57";
    case 14:
        return "70x57";
    case 15:
        return "88x57";
    default:
        throw new Error();
    }
}
var iimages = new Object();

function prefetch(){
    for (var cc = 0; cc < 16; cc++){
        var img = new Image();
        img.src = "http://cpi.cognitiveprofile.com/images/cpi3-"+cl4index(cc)+"-"+id4index(cc)+"-"+sz4index(cc)+".gif";
        iimages[cc] = img;
    }
}
function ix4over(id){
    switch (id){
    case 'L4':
        return 8;
    case 'L3':
        return 9;
    case 'L2':
        return 10;
    case 'L1':
        return 11;
    case 'R1':
        return 12;
    case 'R2':
        return 13;
    case 'R3':
        return 14;
    case 'R4':
        return 15;
    default:
        throw new Error();
    }
}
function ix4under(id){
    switch (id){
    case 'L4':
        return 0;
    case 'L3':
        return 1;
    case 'L2':
        return 2;
    case 'L1':
        return 3;
    case 'R1':
        return 4;
    case 'R2':
        return 5;
    case 'R3':
        return 6;
    case 'R4':
        return 7;
    default:
        throw new Error();
    }
}
function iimg(img){

    if (img.id && 2 == img.id.length){
        switch (img.id.charAt(0)){
        case 'L':
        case 'R':
            switch (img.id.charAt(1)){
            case '1':
            case '2':
            case '3':
            case '4':
                img.onmouseover = function () {this.src=iimages[ix4over(this.id)].src;}
                img.onmouseout = function () {this.src=iimages[ix4under(this.id)].src;}
                break;
            }
            break;
        }
    }
}
function init(){

    prefetch();

    var x;
	if (document.getElementById)
        x = document.getElementsByTagName('IMG');
	else if (document.all)
        x = document.all.tags('IMG');
	else 
        return;

	for (var cc = 0; cc < x.length; cc++)
	{
        iimg(x[cc]);
	}

}
