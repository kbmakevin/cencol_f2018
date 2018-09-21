https://djangostars.com/blog/how-to-create-and-deploy-a-telegram-bot/

Use this token to access the HTTP API:
651982727:AAHHKgnPSMdOScxWTR5G7nSdsQELduQar1Y

http://t.me/Virgin1Bot

https://api.telegram.org/bot651982727:AAHHKgnPSMdOScxWTR5G7nSdsQELduQar1Y/getUpdates
When you open this URL in your web browser, you make a request to Telegram server, that responds back with JSON. Response resembles Python dictionary

https://api.telegram.org/bot651982727:AAHHKgnPSMdOScxWTR5G7nSdsQELduQar1Y/sendMessage?chat_id=289934826&text=test

If you open bots documentation and check /sendMessage method section you’ll notice that this method requires 2 additional parameters chat_id and text. In a browser search bar you can chain parameters using ? for the first one and & for all the consequent.
	get chat_id from /getupdates link
	using the /sendmessage, should see response in ur tg chat


