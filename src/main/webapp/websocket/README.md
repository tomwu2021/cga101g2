# 使用NodeJs,Express+Socket.IO

## 專案套件
- express
	- Node.js Web 應用程式架構
- nodemon
	- 自動重啟應用程式(一旦程式發生改變)
	- 持續偵測你的預設程式
	- 可在 node 中被存取使用
- aws-sdk
	- AWS SDK
## 專案結構
1. node_module： NodeJs 安裝後套件檔案

## 本地安裝及啟動相關指令
- 安裝 package
	- npm install -f
- 啟動 websocket-server (Prod)
	- 指令: node server.js
	- url: localhost:3000
- 使用 nodemon啟動 websocket-server (Dev)
    - 當檔案發生改變時不需手動重啟server
	- 指令: nodemon server.js
