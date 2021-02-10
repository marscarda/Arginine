/*------------------------------------------------------------------------
 Form Pubs */
function openCreateObjectPubForm () {
    document.getElementById('divcreateobjpub').style.height = '100%';
    document.getElementById('divcreateobjpub').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'boxnewobjpub');
    turnon.start();
}
function closeCreateObjectPubForm() {
    document.getElementById('divcreateobjpub').style.height = '0px';
    document.getElementById('divcreateobjpub').style.width = '0px';
    document.getElementById('formcreatepub').reset();
}
/*------------------------------------------------------------------------
 Render Pubs */
let initPubs = () => {
    var div = document.getElementById('objpublist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < objectpubs.count; n++) 
        addPub(objectpubs.items[n], false);
}
function addPub (objpub, isnew) {
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.setAttribute('id', 'objpub' + objpub.objpubid);
    top.setAttribute('style', 'padding: 15px 0px; border-bottom: solid 1px #dddddd');
    if (isnew) top.style.opacity = 0;
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 13px; color: #666");
    column.innerHTML = objpub.title;
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 13px; color: #666");
    column.innerHTML = objpub.text;
    line.appendChild(column);
    /*---------------------------------------------------*/
    top.appendChild(line);
    if (isnew)
        document.getElementById('objpublist').insertBefore(top, document.getElementById('objpublist').childNodes[0]);
    else document.getElementById('objpublist').appendChild(top);
    /*---------------------------------------------------*/
}        
/*------------------------------------------------------------------------*/
