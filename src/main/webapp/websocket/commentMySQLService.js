const MySQL = require('mysql');
const Config = require("./config");
const con = new MySQL.createConnection(Config.MySQL);
con.connect();

function insertComment(data){
    console.log("insert start====================>");
    const INSERT_COMMENT = `INSERT INTO comment(post_id,member_id,content,status,target_id)  VALUES(${data.postId},${data.memberId},'${data.content}',0,0)`;
    let commentId;
    return new Promise((resolve, reject) => {
        con.query(INSERT_COMMENT, function (err, result, fields) {
            console.log(result);
            if (err) {
                reject(err);
            }
            else {
                resolve(result)
                commentId = result.insertId;
                console.log(result);
            }
        });
    }).then(() =>  getCommentResultByPK(commentId));
}

function getCommentResultByPK(commentId){
    console.log("get one====================>");
    const GET_ONE_COMMENT = ` SELECT c.*,m.member_id,m.name,pic.picture_id,pic.preview_url,pic.url
                 FROM comment c JOIN members m ON(m.member_id = c.member_id) 
                 JOIN pet p ON(p.member_id = m.member_id)
                 JOIN picture pic ON(p.picture_id = pic.picture_id)
                 WHERE c.comment_id = ${commentId} `;
    return new Promise((resolve, reject) => {
        con.query(GET_ONE_COMMENT, function (err, result, fields) {
            if (err) reject(err);
            else resolve(result);
        });
    })
}

function insertReply(data){
    console.log("insert reply start====================>");
    const INSERT_REPLY = `INSERT INTO comment(post_id,member_id,content,status,target_id)  VALUES(${data.postId},${data.memberId},'${data.content}',0,${data.targetId})`;
    let commentId;
    return new Promise((resolve, reject) => {
        con.query(INSERT_REPLY, function (err, result, fields) {
            console.log(result);
            if (err) {
                reject(err);
            }
            else {
                resolve(result)
                commentId = result.insertId;
                console.log(result);
            }
        });
    }).then(() =>  getCommentResultByPK(commentId));
}
function updateComment(data){
    console.log("update start====================>");
    const UPDATE_COMMENT = `UPDATE comment cm SET cm.content='${data.content}' WHERE cm.comment_id = ${data.commentId}`
    let commentId;
    return new Promise((resolve, reject) => {
        con.query(UPDATE_COMMENT, function (err, result, fields) {
            console.log(result);
            if (err) {
                reject(err);
            }
            else {
                resolve(result)
                console.log(result);
            }
        });
    });
}
function deleteComment(data){
    console.log("delete start====================>");
    const DELETE_COMMENT = `DELETE FROM comment cm WHERE cm.comment_id = ${data.commentId}`
    return new Promise((resolve, reject) => {
        con.query(DELETE_COMMENT, function (err, result, fields) {
            console.log(result);
            if (err) {
                reject(err);
            }
            else {
                resolve(result)
                console.log(result);
            }
        });
    }).then(()=> deleteReply(data.commentId));
}
function deleteReply(commentId){
    const DELETE_COMMENT = `DELETE FROM comment cm WHERE cm.target_id = ${commentId}`
    return new Promise((resolve, reject) => {
        con.query(DELETE_COMMENT, function (err, result, fields) {
            console.log(result);
            if (err) {
                reject(err);
            }
            else {
                resolve(result)
                console.log(result);
            }
        });
    })
}
module.exports = { deleteComment,updateComment,insertReply,insertComment ,getCommentResultByPK}