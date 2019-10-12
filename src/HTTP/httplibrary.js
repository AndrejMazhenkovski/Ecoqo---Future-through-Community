class easyHTTP {
    // GET Request
    async get(url) {
    const response = await fetch(url);
    const resData = await response.json();
    return resData;
    }
    
    // POST REQUEST
    async post(url, data) {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-type' : 'application/json'
            },
            body : JSON.stringify(data)
        })
        const resData = await response.json();
        return resData;
    }

    // PUT REQUEST
    async put(url, data) {
            const response = await fetch(url, {
                method: "PUT",
                headers: {
                    'Content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            const resData = await response.json();
            return resData;
    }

    //DELETE Request
    async delete(url) {
            const response = await fetch(url, {
                method: "DELETE",
                headers: {
                    'Content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            const resData = await 'Resource Deleted'
            return resData;

    }
}
  
    /* WHEN USING HHTP REQUEST IN THE MAIN JAVASCRIPT FILE WITH AJAX YOU HAVE TO USE THIS FORMAT
    http.post(url, data)
    .then (data = > do something)
    .catch (error = > do something) */