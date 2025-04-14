
/* add */
async function addReply(replyObj){
/* async : 비동기 통신(원래위치에서 전송하고 돌아옴 : 페이지는 가만히 있고 원하는 데이터만 가져옴 */
    const response=await axios.post(`/replies/`, replyObj)
    console.log( "response :" + response)
    console.log("response.data : " + response.data)
    return response.data;
}

/* get */
async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

/* modify */
async function modifyReply(replyObj){
    const response =await axios.put(`replies/${replyObj.rno}`, replyObj)
    return response.data
}

/* delete */
async function remove(rno){
    const response = await axios.delete(`replies/${rno}`)
    return response.data
}

/* 댓글 list 가져오기 */
async function getList({bno, page, size, goLast}){
    const response = await axios.get(`/replies/list/${bno}`, {param:{page, size}})
    if(goLast){
        const total=response.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage, size:size})
    }
    return response.data
}