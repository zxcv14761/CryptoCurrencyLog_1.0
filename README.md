# 簡介 
基於 Spring Boot + MySQL + HTML 建構簡易記帳系統
# 使用技術
 * Spring Boot
 * MySQL
 * JavaScript
 * HTML
 * Redis

# 更新日誌
 * 2023.02.19 更新了多人登陸時使用sse推送不能同時更新錯誤
 * 2023.02.21 更新爬取工具範圍為 top10加密貨幣 且更新為自適應更新不需添加網址到 MAP中
# 功能介紹

## 1.登入 註冊 功能
 * 登入成功跳轉首頁 登入失敗顯示錯誤訊息
 * 註冊成功跳轉登入頁 註冊失敗顯示錯誤訊息
 
![截圖 2023-02-13 下午7 04 43](https://user-images.githubusercontent.com/90376966/218441858-f9483f9b-4f5b-48d2-897b-e132f5e6df93.png)
![截圖 2023-02-13 下午7 06 35](https://user-images.githubusercontent.com/90376966/218442007-b4cc55ff-68b8-43a5-b023-efcb1f400452.png)

## 2.即時報價功能
 * 一組半自動爬蟲工具 主要以 Jsoup 爬取 coinmarketcap 指定貨幣資料後存入Redis 在傳遞資料給前端頁面
 
 ![截圖 2023-02-13 下午7 23 03](https://user-images.githubusercontent.com/90376966/218445741-58206d7b-603f-4112-94bb-d821738f67c7.png)

## 3.查詢資產功能
* 透過帳號查詢資料庫中對應所有資產
	
![截圖 2023-02-13 下午7 34 48](https://user-images.githubusercontent.com/90376966/218447449-f7438215-1b2e-4076-a918-dfefd7dc6c07.png)

## 4.新增資產功能
* 往數據庫填入該用戶資產
	
![截圖 2023-02-13 下午7 17 24](https://user-images.githubusercontent.com/90376966/218447618-42025fd9-b31d-4bcc-97ff-fba9c8c9f0fa.png)

## 5.修改與刪除資產功能
* 透過該行資產 id 修改或刪除數據庫指定數據

![截圖 2023-02-13 下午7 39 59](https://user-images.githubusercontent.com/90376966/218448909-ac116d85-4aba-4cc6-800b-018a8c47f77b.png)
