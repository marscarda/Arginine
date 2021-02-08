let initMetrics = () => {
    var div = document.getElementById('metriclist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < reactionmetrics.count; n++) 
        addMetric(reactionmetrics.items[n], false);
}
/*------------------------------------------------------------------------
 Render Metrics */
function addMetric (reactionmet) {
    var top;
    var line;
    top = document.createElement("div");
    top.setAttribute("id", "action" + reactionmet.actionid);    
    switch (reactionmet.type) {
        case 1:
            line = renderPubViewMetric(reactionmet); break;
        default: return;
    }
    top.appendChild(line);
    document.getElementById('metriclist').appendChild(top);
}
function renderPubViewMetric (reactionmet) {
    var line;
    var column;
    var piece;
    var img;
    var fullbar;
    var partbar;
    /*--------------------------------------------------*/
    line = document.createElement("div");
    line.setAttribute("style", "display: flex; flex-direction: row; padding: 25px 0px; border-bottom: solid 1px #aaaaaa");
    /*--------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 50px");
    img = document.createElement('img');
    img.setAttribute('src', iconpubviewurl);
    img.setAttribute('alt', 'icon');
    img.setAttribute('style', 'width: 25px; height: 25px');
    column.appendChild(img);
    line.appendChild(column);
    /*--------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 200px");
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 10px; font-weight: 600; color: #999; margin-bottom: 5px");
    piece.innerHTML = "Public view evaluation";
    column.appendChild(piece);
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 15px; font-weight: 500; color: #555");
    piece.innerHTML = reactionmet.label;
    column.appendChild(piece);
    line.appendChild(column);
    /*--------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 150px");
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 12px; font-weight: normal; color: #777");
    piece.innerHTML = "Total View";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #383; margin-top: 8px;");
    piece.innerHTML = "Positive: " + reactionmet.positive + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #09f; margin-top: 5px;");
    piece.innerHTML = "Neutral: " + reactionmet.neutral + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #d33; margin-top: 5px;");
    piece.innerHTML = "Negative: " + reactionmet.negative + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #666; margin-top: 5px;");
    piece.innerHTML = "Ignorance: " + reactionmet.ignorance + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    line.appendChild(column);
    /*--------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 170px");
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 12px; font-weight: normal; color: #777");
    piece.innerHTML = "On Knowledge View";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #383; margin-top: 8px;");
    piece.innerHTML = "Positive: " + reactionmet.onknownpositive + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #09f; margin-top: 5px;");
    piece.innerHTML = "Neutral: " + reactionmet.onknownneutral + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 600; color: #d33; margin-top: 5px;");
    piece.innerHTML = "Negative: " + reactionmet.onknownnegative + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    line.appendChild(column);
    /*--------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 2");
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 500; color: #666");
    piece.innerHTML = "Total valoration";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "margin-top: 5px");
    fullbar = document.createElement("div");
    fullbar.setAttribute("style", "display: flex; flex-direction: row; width: 100%; height: 7px; background-color: #ddd; border-radius: 4px; overflow: hidden");
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.positive + "; height: 20px; background-color: #3c3");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.neutral + "; height: 20px; background-color: #0af");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.negative + "; height: 20px; background-color: #f33");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.ignorance + "; height: 20px; background-color: #eee");
    fullbar.appendChild(partbar);
    piece.appendChild(fullbar);
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 500; color: #666; margin-top: 12px");
    piece.innerHTML = "Knowledge " + reactionmet.knowledge + "%";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "margin-top: 5px");
    fullbar = document.createElement("div");
    fullbar.setAttribute("style", "display: flex; flex-direction: row; width: 100%; height: 7px; background-color: #ddd; border-radius: 4px; overflow: hidden");
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.knowledge + "; height: 20px; background-color: #777");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.ignorance + "; height: 20px; background-color: #eee");
    fullbar.appendChild(partbar);
    piece.appendChild(fullbar);
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "font-size: 14px; font-weight: 500; color: #666; margin-top: 12px");
    piece.innerHTML = "On Knowledge";
    column.appendChild(piece);
    /*-----------------------------------------*/
    piece = document.createElement("div");
    piece.setAttribute("style", "margin-top: 5px");
    fullbar = document.createElement("div");
    fullbar.setAttribute("style", "display: flex; flex-direction: row; width: 100%; height: 7px; background-color: #ddd; border-radius: 4px; overflow: hidden");
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.onknownpositive + "; height: 20px; background-color: #3c3");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.onknownneutral + "; height: 20px; background-color: #0af");
    fullbar.appendChild(partbar);
    partbar = document.createElement("div");
    partbar.setAttribute("style", "flex: " + reactionmet.onknownnegative + "; height: 20px; background-color: #f33");
    fullbar.appendChild(partbar);
    piece.appendChild(fullbar);
    column.appendChild(piece);
    /*--------------------------------------------------*/
    line.appendChild(column);
    /*--------------------------------------------------*/
    return line;
}
/*------------------------------------------------------------------------
 Form Access */
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
 Render Access */
let initAccessList = () => {
    var div = document.getElementById('accesslist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < accesslist.count; n++) 
        addAccess(accesslist.items[n], false);
}
function addAccess (record, isnew) {
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.setAttribute('id', 'accessrec' + record.recordid);
    top.setAttribute('style', 'padding: 15px 0px; border-bottom: solid 1px #dddddd');
    if (isnew) top.style.opacity = 0;
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 13px; color: #666");
    column.innerHTML = record.name;
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    if (record.public) {
        column.setAttribute("style", "flex: 1; font-size: 13px; font-weight: 700; color: #8a8");
        column.innerHTML = "[Public]";
    }
    else {
        column.setAttribute("style", "flex: 1; font-size: 13px; color: #666");
        column.innerHTML = record.username;
    } 
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 1; font-size: 13px; color: #666; text-align: right");
    link = document.createElement("a");
    link.setAttribute("href", "#");
    link.setAttribute("style", "color: #0055ff; text-decoration: none");
    link.setAttribute("onclick", "destroyAccess(" + record.recordid + "); return false;");
    link.innerHTML = "Revoke";
    column.appendChild(link);
    /*---------------------------------------------------*/
    line.appendChild(column);
    /*---------------------------------------------------*/
    top.appendChild(line);
    if (isnew)
        document.getElementById('accesslist').insertBefore(top, document.getElementById('accesslist').childNodes[0]);
    else document.getElementById('accesslist').appendChild(top);
    /*---------------------------------------------------*/
}        
/*------------------------------------------------------------------------*/
