
/* add */
async function addReply(replyObj){
    try {
        const response = await axios.post(`/replies/`, replyObj)
        console.log("댓글 등록 응답:", response.data)
        return response.data
    } catch (error) {
        console.error("댓글 등록 중 오류 발생:", error.response?.data || error.message)
        throw error
    }
}

/* get */
async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

/* modify */
async function modifyReply(replyObj){
    console.log(replyObj)
    const response =await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data
}

/* delete */
async function removeReply(rno){
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}

/* 댓글 list 가져오기 */
async function getList({bno, page, size, goLast}){
    const response = await axios.get(`/replies/list/${bno}`, {params:{page, size}})
    /* goLast : 마지막 댓글이 달린 페이지로 이동시키기 위해 */
    if(goLast){
        const total=response.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage, size:size, goLast:false})
    }
    return response.data
}