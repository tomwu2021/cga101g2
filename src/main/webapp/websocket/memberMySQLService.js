const MySQL = require('mysql');
const Config = require("./config");
const con = new MySQL.createConnection(Config.MySQL);
con.connect();

getFriendsIdByMemberId = (memberId) => {
    const FIND_FRIENDS_BY_ID = `SELECT * FROM relationship WHERE member_id = ${memberId} AND relation_type=0`;
    return new Promise((resolve, reject) => {
        con.query(FIND_FRIENDS_BY_ID, function (err, result, fields) {
            if (err) reject(err);
            resolve(result);
        });
    })
};

module.exports = {getFriendsIdByMemberId}