class ToastMessage {
    element = null;
    curropacity = 0;
    currmargin = 50;
    setElement(elementid) { this.element = document.getElementById(elementid); }
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
class ElementFadeIn {
    element = null;
    curropacity = 0;
    step = 0.15;
    setElement(elementid) { this.element = document.getElementById(elementid); }
    start () { this.fadeIn(); }
    fadeIn () {
        this.curropacity += this.step;
        if (this.curropacity > 1) {
            this.element.style.opacity = 1;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 50);
    }
}
class DivExpandHeight {
    element = null;
    currentheight = 0;
    endheight = 0;
    steps = 100;
    onfinish = null;
    setElement (document, elementid) { this.element = document.getElementById(elementid); }
    setDimensions (start, end) {
        this.currentheight = start;
        this.endheight = end;
    }
    start () { this.expand(); } 
    expand () {
        var height = this.currentheight + 'px';
        this.element.style.height = height;
        this.currentheight += this.steps;
        if (this.currentheight > this.endheight) {
            height = this.endheight + 'px';
            this.element.style.height = height;
            if (this.onfinish !== null) this.onfinish();
            return;
        }
        setTimeout(this.expand.bind(this), 20);
    }
}
class divScrollAhead {
    element = null;
    steps = 50;
    amount = 100;
    setAmount (amount) { this.amount = amount; }
    setElement (elementid) { this.element = document.getElementById(elementid); }
    start () {
        this.advance ();
    }
    advance () {
        var pos = this.element.scrollTop;
        var step = this.steps;
        if (step > this.amount) step = this.amount;
        this.amount -= step;
        pos += step;
        this.element.scrollTop = pos;
        if (this.amount === 0) return;
        setTimeout(this.advance.bind(this), 20);
        
    }
}
