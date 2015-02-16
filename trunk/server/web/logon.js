/*
 * Logon via HTTP/JSON interface
 */
var logon = {};
/*
 * XHR (onreadystatechange)
 */
function logon_renderer(){
    /*
     */
    if (this.DONE == this.readyState){

        if (this.response && this.response.interface){

            logon = this.response;
        }
        else if (this.responseText){
            try {
                logon = JSON.parse(this.responseText);
            }
            catch (e){
               console.error(e);
            }
        }

        if (logon.interface){
            /*
             * Generate HTML for 'index.html' (but not 'profile.html')
             */
            logon.html = '<span class="logon"><b><a href="'+logon.url+'">'+logon.text+'</a></b></span><span class="logon"><b><a href="http://www.cognitiveprofile.com/cpionline">Home</a></b></span>'+'<span class="logon"><a href="http://www.google.com/accounts">Account</a></span><span class="logon"><a href="http://www.google.com/profiles/me">Profile</a></span>';
            /*
             * Install HTML
             */
            var div = document.getElementById('logon');
            if (div){

                div.innerHTML = logon.html;
            }
        }
    }
}
/*
 * 
 */
var xhrc = new XMLHttpRequest();
xhrc.onreadystatechange = logon_renderer;
xhrc.open("GET","/logon.json");
xhrc.send();
