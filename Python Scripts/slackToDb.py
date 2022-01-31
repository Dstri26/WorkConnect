from email import message
import requests
import json
import pandas as pd
import numpy as np
import pandas as pd
from datetime import datetime


bot_token = 'xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q'
user_tokne = "xoxp-2934006031602-2957745528160-3034281573060-351cf2a397d9c66ea2c65d9b08d11fa9"
headers = {'Authorization': 'Bearer ' +bot_token, 'Content-type': 'application/x-www-form-urlencoded'}
channel = 'C0313AG3ZPB'
payload = {'channel':channel}
conv_history_url = "https://slack.com/api/conversations.history"
user_url = "https://slack.com/api/users.info"


r = requests.get(url=conv_history_url,params=payload,headers=headers)
res = r.json()
users = {}
op =[]

for i in res["messages"]:
  if i["type"] == "message":
    if i["user"] not in users.keys():
      if i["user"] == "U030NQSKSCB":
        continue
      user = {'user':i["user"]}
      r = requests.get(user_url,params=user,headers=headers)
      mail_res = r.json()
      users[i["user"]] = mail_res['user']['profile']['email']
    op.append({	'taskName':i["text"].capitalize(),'platform':'slack','email':users[i["user"]],'status':0,'time':datetime.fromtimestamp(float(i["ts"])).strftime('%d-%m-%y')})

final = json.dumps(op, indent=2)
upload_url = 'http://localhost:9191/addTasks'
h = {'Content-type': 'application/json'}
x = requests.post(upload_url, data = final,headers=h)
print(x.text)