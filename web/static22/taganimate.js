class FleetingMsgFade {
    element = null;
    curropacity = 0;
    setElement(document, elementid) { this.element = document.getElementById(elementid); }
    setColor(value) { this.element.style.color = value; }
    setText(value) { this.element.innerHTML = value; }
    start () { this.fadeIn(); }
    fadeIn () {
        this.curropacity += 0.2;
        if (this.curropacity > 1) {
            setTimeout(this.fadeOut.bind(this), 1200);
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 50);
    }
    fadeOut () {
        this.curropacity -= 0.2;
        if (this.curropacity < 0) {
            this.element.style.opacity = 0;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeOut.bind(this), 100);
    }
}
class ElementFadeIn {
    element = null;
    curropacity = 0;
    setElement(document, elementid) { this.element = document.getElementById(elementid); }
    start () { this.fadeIn(); }
    fadeIn () {
        this.curropacity += 0.1;
        if (this.curropacity > 1) {
            this.element.style.opacity = 1;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 50);
    }
}
class DivExpandWidth {
    element = null;
    currentwidth = 0;
    endwidth = 0;
    steps = 0;
    setElement (document, elementid) { this.element = document.getElementById(elementid); }
    setDimensions (start, end) {
        this.currentwidth = start;
        this.endwidth = end;
    }
    setSteps (steps) { this.steps = steps; }
    start () { this.expand(); } 
    expand () {
        var width = this.currentwidth + 'px';
        this.element.style.width = width;
        this.currentwidth += this.steps;
        if (this.currentwidth > this.endwidth) {
            width = this.endwidth + 'px';
            this.element.style.width = width;
            return;
        }
        setTimeout(this.expand.bind(this), 20);
    }
}
class DivReduceWidth {
    element = null;
    currentwidth = 0;
    endwidth = 0;
    steps = 0;
    setElement (document, elementid) { this.element = document.getElementById(elementid); }
    setDimensions (start, end) {
        this.currentwidth = start;
        this.endwidth = end;
    }
    setSteps (steps) { this.steps = steps; }
    start () { 
        this.element.style.overflow = 'hidden';
        this.reduce(); 
    } 
    reduce () {
        var width = this.currentwidth + 'px';
        this.element.style.width = width;
        this.currentwidth -= this.steps;
        if (this.currentwidth < this.endwidth) {
            width = this.endwidth + 'px';
            this.element.style.width = width;
            return;
        }
        setTimeout(this.reduce.bind(this), 20);
    }
}
class DivExpandHeight {
    element = null;
    currentheight = 0;
    endheight = 0;
    steps = 0;
    setElement (document, elementid) { this.element = document.getElementById(elementid); }
    setDimensions (start, end) {
        this.currentheight = start;
        this.endheight = end;
    }
    setSteps (steps) { this.steps = steps; }
    start () { this.expand(); } 
    expand () {
        var height = this.currentheight + 'px';
        this.element.style.height = height;
        this.currentheight += this.steps;
        if (this.currentheight > this.endheight) {
            height = this.endheight + 'px';
            this.element.style.height = height;
            return;
        }
        setTimeout(this.expand.bind(this), 20);
    }
}
class DivReduceHeight {
    element = null;
    currentheight = 0;
    endheight = 0;
    steps = 0;
    setElement (document, elementid) { this.element = document.getElementById(elementid); }
    setDimensions (start, end) {
        this.currentheight = start;
        this.endheight = end;
    }
    setSteps (steps) { this.steps = steps; }
    start () { 
        this.element.style.overflow = 'hidden';
        this.reduce(); 
    } 
    reduce () {
        var height = this.currentheight + 'px';
        this.element.style.height = height;
        this.currentheight -= this.steps;
        if (this.currentheight < this.endheight) {
            height = this.endheight + 'px';
            this.element.style.height = height;
            return;
        }
        setTimeout(this.reduce.bind(this), 20);
    }
}
class DivPopUpPromptShow {
    element = null;
    curropacity = 0;
    currmargin = 50;
    setElement(document, elementid) { this.element = document.getElementById(elementid); }
    start () { 
        this.fadeIn();
        this.displaceDown();
    }
    fadeIn () {
        this.curropacity += 0.15;
        if (this.curropacity > 1) {
            this.element.style.opacity = 1;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 30);
    }
    displaceDown () {
        this.currmargin += 10;
        if (this.currmargin > 100) {
            this.element.style.marginTop = "100px";
            return;
        }
        this.element.style.marginTop = this.currmargin + "px";
        setTimeout(this.displaceDown.bind(this), 20);
    }
}
class DivPopUpMessage {
    element = null;
    curropacity = 0;
    currmargin = 50;
    setElement(document, elementid) { this.element = document.getElementById(elementid); }
    start () { 
        this.element.style.height = 'auto';
        this.fadeIn();
        this.displaceDown();
    }
    fadeIn () {
        this.curropacity += 0.15;
        if (this.curropacity > 1) {
            this.element.style.opacity = 1;
            setTimeout(this.fadeOut.bind(this), 2500);
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 30);
    }
    displaceDown () {
        this.currmargin += 10;
        if (this.currmargin > 100) {
            this.element.style.top = "100px";
            return;
        }
        this.element.style.top = this.currmargin + "px";
        setTimeout(this.displaceDown.bind(this), 20);
    }
    fadeOut () {
        this.curropacity -= 0.15;
        if (this.curropacity < 0) {
            this.element.style.opacity = 0;
            this.element.style.height = '0px';
            this.element.style.top = '-100px';
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeOut.bind(this), 50);
    }
}
const reducePanel = (divpanelid, divcmdreduce, divexpandeid) => {
    var reduce = new DivReduceWidth();
    reduce.setElement(document, divpanelid);
    reduce.setDimensions(350, 70);
    reduce.setSteps(50);
    reduce.start();
    document.getElementById(divcmdreduce).style.width = '0px';
    document.getElementById(divcmdreduce).style.height = '0px';
    document.getElementById(divexpandeid).style.width = 'auto';
    document.getElementById(divexpandeid).style.height = 'auto';
}
const expandPanel = (divpanelid, divcmdreduce, divexpandeid) => {
    var expand = new DivExpandWidth();
    expand.setElement(document, divpanelid);
    expand.setDimensions(70, 350);
    expand.setSteps(50);
    expand.start();
    document.getElementById(divexpandeid).style.width = '0px';
    document.getElementById(divexpandeid).style.height = '0px';
    document.getElementById(divcmdreduce).style.width = 'auto';
    document.getElementById(divcmdreduce).style.height = 'auto';
}

