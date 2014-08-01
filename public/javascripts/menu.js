function setActiveClass() {
    var url = window.location.href;
    var arr = url.split("/");
    var result = url.split(arr[2]);
    var l = document.getElementsByTagName("a");
    for(i = 0; i < l.length; ++i)
    {
        if(result[1] == l[i].getAttribute("href"))
            l[i].parentNode.className = "active";
        else
            l[i].parentNode.className = "";
    }
}