import requests

VERSION="1.0.0"
url="https://api.telegram.org/bot651982727:AAHHKgnPSMdOScxWTR5G7nSdsQELduQar1Y/"

def get_updates_json(request):
    response=requests.get(request+'getUpdates')
    return response.json()

def last_update(data):
    results=data['result']
    total_updates=len(results)-1
    return results[total_updates]

def get_chat_id(update):
    chat_id=update['message']['chat']['id']
    return chat_id

def send_mess(chat,text):
    params={'chat_id':chat,'text':text}
    response=requests.post(url+'sendMessage',data=params)
    return response

chat_id=get_chat_id(last_update(get_updates_json(url)))

send_mess(chat_id,'Your message goes here')
