class HttpRequest {
    url = null;
    callback = null;
    formdata = null;
    data = '';
    setURL (u) { this.url = u; }
    setCallBack (call) { this.callback = call; } 
    addParam (name, value) {
        if (this.data.length !== 0) this.data += '&';
        this.data += name + '=' + value;
    }
    setFormData (fdata) { this.formdata = fdata; }
    executepost () {
        var request = new XMLHttpRequest();
	request.timeout = 10000;
	request.onreadystatechange = () => {
            if (request.readyState !== 4) return;
            var objresp = {};
            try { objresp = eval ('(' + request.responseText + ')'); } 
            catch (err) {}
            this.callback(request.status, objresp);
	}
	request.open('POST',this.url,true);
        if (this.formdata === null) {
            request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        }
        if (this.formdata !== null) 
            request.send(this.formdata);
        else 
            request.send(this.data);
    }
}

